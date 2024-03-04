package petcare.adopt.authentication.services;

import petcare.adopt.authentication.domain.User;

import java.util.Optional;


public interface UserService extends GenericService<User> {

    public Optional<User> authenticate(String username, String password);

}
