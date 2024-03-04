package petcare.adopt.authentication.services.impl;

import org.springframework.stereotype.Service;
import petcare.adopt.authentication.domain.User;
import petcare.adopt.authentication.repositories.UserRepository;
import petcare.adopt.authentication.services.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl extends GenericServiceImpl<User, Long, UserRepository> implements UserService {

    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

    @Override
    public Optional<User> authenticate(String username, String password) {
        Optional<User> optionalUser = repository.findByUsernameAndPassword(username, password);

        return optionalUser;
    }

}
