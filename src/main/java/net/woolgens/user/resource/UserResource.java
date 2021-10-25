package net.woolgens.user.resource;

import net.woolgens.user.exception.UserNotFoundException;
import net.woolgens.user.model.User;
import net.woolgens.user.repository.UserRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.List;
import java.util.Optional;

@Path("/users/")
public class UserResource {

    @Inject
    UserRepository repository;

    @GET
    public List<User> getAll() {
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
    public User post(User user) {
        repository.persistOrUpdate(user);
        return user;
    }

    @DELETE
    @Path("{uuid}")
    public boolean delete(@PathParam("uuid") String uuid) {
        return repository.deleteById(uuid);
    }
}