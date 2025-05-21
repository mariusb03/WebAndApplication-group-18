package webapp.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import webapp.model.Courses;
import webapp.model.Users;
import webapp.repository.CoursesRepository;
import webapp.repository.UserRepository;

/**
 * Service class for managing users.
 * This class provides methods to perform CRUD operations on users.
 */
@Service
public class UserService {
  private final Logger logger = LoggerFactory.getLogger("UserService");

  @Autowired
  private UserRepository repository;

  /**
   * Get all users.
   *
   * @return All users.
   */
  public Iterable<Users> getAll() {
    logger.info("Fetching all users");

    return repository.findAll();
  }


  public Optional<Users> findByNameAndPassword(String name, String password) {
      return repository.findByNameAndPassword(name, password);
  }


    @Autowired
    private CoursesRepository coursesRepository;

    public boolean addFavourite(Integer userId, Integer courseId) {
        Optional<Users> userOpt = repository.findById(userId);
        Optional<Courses> courseOpt = coursesRepository.findById(courseId);

        if (userOpt.isPresent() && courseOpt.isPresent()) {
            Users user = userOpt.get();
            user.getFavourites().add(courseOpt.get());
            repository.save(user);
            return true;
        }
        return false;
    }

    public boolean removeFavourite(Integer userId, Integer courseId) {
        Optional<Users> userOpt = repository.findById(userId);
        Optional<Courses> courseOpt = coursesRepository.findById(courseId);

        if (userOpt.isPresent() && courseOpt.isPresent()) {
            Users user = userOpt.get();
            user.getFavourites().remove(courseOpt.get());
            repository.save(user);
            return true;
        }
        return false;
    }

    /**
     * Get a user by id.
     *
     * @param id id of the user.
     *
     * @return Users
     */
    public Optional<Users> getById(int id) {
        logger.info("Fetching user by id {}", id);

    return repository.findById(id);
  }

  /**
   * add a user to the database.
   *
   * @param userToAdd the user to add.
   */
  public boolean add(Users userToAdd) {
    logger.info("Adding user {}", userToAdd);

    boolean success = false;
    if (userToAdd.isValid(userToAdd)) {
      try {
        repository.save(userToAdd);
        success = true;
        logger.info("User added successfully");
      } catch (Exception e) {
        logger.error("Error adding user: {}", e.getMessage());
      }
    }
    return success;
  }

  /**
   * Delete a user from the database.
   *
   * @param id the id of the user to delete.
   * @return true if the user was deleted, false if not.
   */
  public boolean delete(int id) {
    logger.info("Deleting user with ID {}", id);

    boolean success = false;
    if (repository.existsById(id)) {
      try {
        repository.deleteById(id);
        logger.info("Deleted user with ID {}", id);
        success = true;
      } catch (Exception e) {
        logger.error("Failed to delete user with ID {}", id);
      }
    } else {
      logger.warn("User with ID {} does not exist", id);
    }
    return success;
  }


  /**
   * Update a user in the database.
   *
   * @param id the id of the user to update.
   * @param user the user to update.
   *
   * @return true if the user was updated, false if not.
   */
  public boolean update(Users user, Integer id) {
    logger.info("Updating user with ID {}", id);

    return repository.findById(id).map(existing -> {
      if (user.isValid(existing)) {
        existing.setName(user.getName());
        existing.setEmail(user.getEmail());
        existing.setPassword(user.getPassword());
        repository.save(existing);
        logger.info("Updated topic with ID {}", id);
        return true;
      } else {
        logger.error("Failed to update topic with ID {}", id);
        return false;
      }
    }).orElse(false);
  }
}