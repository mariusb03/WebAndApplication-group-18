package backend.service;

import backend.model.Courses;
import backend.model.Users;
import backend.repository.CoursesRepository;
import backend.repository.UserRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing users.
 * This class provides methods to perform CRUD operations on users.
 */
@Service
public class UserService {
  private final Logger logger = LoggerFactory.getLogger("UserService");
  @Autowired
  private UserRepository repository;
  @Autowired
  private CoursesRepository coursesRepository;

  /**
   * Get all users.
   *
   * @return All users.
   */
  public Iterable<Users> getAll() {
    logger.info("Fetching all users");

    return repository.findAll();
  }

  /**
   * Get a user by name and password.
   *
   * @param name of the user to find.
   * @param password of the user to find.
   * @return Optional of the user.
   */
  public Optional<Users> findByNameAndPassword(String name, String password) {
    return repository.findByNameAndPassword(name, password);
  }

  /**
   * Get a user by name.
   *
   * @param userId for the user adding the course to favourites.
   * @param courseId for the course to be added to favourites.
   * @return Optional of the user.
   */
  public boolean addFavourite(Integer userId, Integer courseId) {
    Optional<Users> userOpt = repository.findById(userId);
    Optional<Courses> courseOpt = coursesRepository.findById(courseId);

    boolean favourite = false;
    if (userOpt.isPresent() && courseOpt.isPresent()) {
      Users user = userOpt.get();
      user.getFavourites().add(courseOpt.get());
      repository.save(user);
      favourite = true;
    }
    return favourite;
  }

  /**
   * Remove a course from the user's favourites.
   *
   * @param userId for the user removing the course from favourites.
   * @param courseId for the course to be removed from favourites.
   * @return true if the course was removed, false if not.
   */
  public boolean removeFavourite(Integer userId, Integer courseId) {
    Optional<Users> userOpt = repository.findById(userId);
    Optional<Courses> courseOpt = coursesRepository.findById(courseId);

    boolean removed = false;
    if (userOpt.isPresent() && courseOpt.isPresent()) {
      Users user = userOpt.get();
      user.getFavourites().remove(courseOpt.get());
      repository.save(user);
      removed = true;
    }
    return removed;
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