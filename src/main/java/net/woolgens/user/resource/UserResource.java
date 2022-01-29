package net.woolgens.user.resource;

import io.quarkus.mongodb.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import net.woolgens.user.exception.impl.UserNotFoundException;
import net.woolgens.user.model.User;
import net.woolgens.user.repository.UserRepository;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/users/")
public class UserResource {

    @Inject
    UserRepository repository;

    @GET
    public Response getAll(@QueryParam("sorted") String sorted,
                           @QueryParam("pageindex") String pageIndex,
                           @QueryParam("pagesize") String pageSize,
                           @QueryParam("count") String count) {
        if(count != null) {
            return Response.ok()
                    .entity(new UserCountResponse(repository.findAll().pageCount(),
                            repository.count())).build();
        }
        if(sorted != null) {
            PanacheQuery<User> query = repository.findAll(Sort.descending(sorted));
            if(pageIndex != null && pageSize != null) {
                try {
                    query.page(Integer.valueOf(pageIndex), Integer.valueOf(pageSize));
                }catch (NumberFormatException ex) {}
            }
            List<User> list = query.list();
            return Response.ok().entity(list).build();
        }
        return Response.ok().entity(repository.listAll()).build();
    }


    @GET
    @Path("{uuid}")
    public User get(@PathParam("uuid") String uuid) throws UserNotFoundException {
        Optional<User> optional = repository.findByIdOptional(uuid);
        if(!optional.isPresent()) {
            throw new UserNotFoundException("User with this uuid does not exist");
        }
        return optional.get();
    }

    @POST
    @RolesAllowed("Admin")
    public User post(User user) {
        repository.persistOrUpdate(user);
        return user;
    }

    @DELETE
    @Path("{uuid}")
    @RolesAllowed("Admin")
    public boolean delete(@PathParam("uuid") String uuid) {
        return repository.deleteById(uuid);
    }
}