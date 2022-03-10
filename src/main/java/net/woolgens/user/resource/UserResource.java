package net.woolgens.user.resource;

import net.woolgens.library.microservice.exception.ServiceException;
import net.woolgens.user.model.User;
import net.woolgens.user.repository.UserRepository;
import net.woolgens.user.resource.response.UserCountResponse;
import net.woolgens.user.service.UserService;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/users/")
public class UserResource {

    @Inject
    UserRepository repository;

    @Inject
    UserService service;

    @GET
    public Response getAll(@QueryParam("sorted") String sorted,
                           @QueryParam("pageindex") String pageIndex,
                           @QueryParam("pagesize") String pageSize,
                           @QueryParam("count") String count,
                           @QueryParam("small") String small) {
        if(count != null) {
            return Response.ok()
                    .entity(new UserCountResponse(repository.count())).build();
        }
        if(small != null) {
            return Response.ok().entity(service.getAllSmallProjectedUsers()).build();
        }
        if(sorted != null) {
            return Response.ok().entity(service.getAllSortedAndPaged(sorted, pageIndex, pageSize)).build();
        }
        return Response.ok().entity(repository.listAll()).build();
    }


    @GET
    @Path("{uuid}")
    public User get(@PathParam("uuid") String uuid) throws ServiceException {
        Optional<User> optional = repository.findByIdOptional(uuid);
        if(!optional.isPresent()) {
            throw new ServiceException(404, "User not found");
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