package webApp.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import webApp.model.Users;

/**
 * Repository interface for managing user data in the database.
 * This interface extends CrudRepository to provide CRUD operations.
 */
public interface UserRepository extends CrudRepository<Users, Integer> {

  /**
   * Find a user by their name and password.
   *
   * @param name     The name of the user.
   * @param password The password of the user.
   * @return An Optional containing the user if found, or empty if not found.
   */
  Optional<Users> findByNameAndPassword(String name, String password);
}