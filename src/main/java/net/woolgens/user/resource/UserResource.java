package net.woolgens.user.resource;

import io.quarkus.panache.common.Sort;
import net.woolgens.user.exception.impl.UserNotFoundException;
import net.woolgens.user.model.User;
import net.woolgens.user.repository.UserRepository;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;
import java.util.Optional;

@Path("/users/")
public class UserResource {

    @Inject
    UserRepository repository;

    @GET
    public List<User> getAll(@QueryParam("sorted") String sorted) {
        if(sorted != null) {
            List<User> list = repository.listAll(Sort.descending(sorted));
            System.out.println(list);
            return list;
        }
        return repository.listAll();
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