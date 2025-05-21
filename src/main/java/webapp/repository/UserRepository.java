package webapp.repository;

import org.springframework.data.repository.CrudRepository;
import webapp.model.Users;

/**
 * Repository interface for managing user data in the database.
 * This interface extends CrudRepository to provide CRUD operations.
 */
import java.util.Optional;

public interface UserRepository extends CrudRepository<Users, Integer> {

    Optional<Users> findByNameAndPassword(String name, String password);
}