package petcare.adopt.authentication.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import petcare.adopt.authentication.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    public Optional<User> findByUsernameAndPassword(String username, String password);

}
