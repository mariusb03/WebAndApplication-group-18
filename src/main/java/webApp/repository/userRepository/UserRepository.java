package webApp.repository.userRepository;

import org.springframework.data.repository.CrudRepository;
import webApp.model.userdb.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}