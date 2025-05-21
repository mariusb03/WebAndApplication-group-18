package webApp.repository;

import org.springframework.data.repository.CrudRepository;
import webApp.model.Users;

/**
 * Repository interface for managing user data in the database.
 * This interface extends CrudRepository to provide CRUD operations.
 */
public interface UserRepository extends CrudRepository<Users, Integer> {
}