package webApp.controller.dbController;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webApp.model.Users;
import webApp.service.UserService;

/**
 * Controller for managing users.
 * This class handles HTTP requests related to user operations.
 */
@RestController
@RequestMapping("/user")
public class UserController {
  private final Logger logger = LoggerFactory.getLogger("userController");
  @Autowired
  private UserService service;

  /**
   * Retrieves all users from the database.
   *
   * @return An iterable collection of all users.
   */
  @GetMapping("/getAll")
  public Iterable<Users> getAll() {
    logger.info("Retrieving all users");
    return this.service.getAll();
  }

  /**
   * Retrieves user by ID.
   *
   * @param id The ID of the user to retrieve.
   * @return user with the specified ID.
   */
  @GetMapping("/getById/{id}")
  public ResponseEntity<Optional<Users>> getById(@PathVariable int id) {
    logger.info("Retrieving user with ID: {}", id);
    ResponseEntity<Optional<Users>> response;
    Optional<Users> user = this.service.getById(id);
    if (user.isPresent()) {
      response = ResponseEntity.ok(user);
    } else {
      response = ResponseEntity.notFound().build();
    }
    return response;
  }

  /**
   * Adds a new user to the database.
   *
   * @param user The user to add.
   */
  @PostMapping("/add")
  public ResponseEntity<String> add(@RequestBody Users user) {
    logger.info("Adding new user: {}", user);
    ResponseEntity<String> response;
    if (this.service.add(user)) {
      response = new ResponseEntity<>(HttpStatus.OK);
    } else {
      response = new ResponseEntity<>("Failed to add user.", HttpStatus.BAD_REQUEST);
    }
    return response;
  }

  /**
   * Deletes a user from the database.
   */
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> delete(@PathVariable int id) {
    logger.info("Deleting user with ID: {}", id);
    ResponseEntity<String> response;
    if (this.service.delete(id)) {
      response = new ResponseEntity<>(HttpStatus.OK);
    } else {
      response = new ResponseEntity<>("Failed to delete user.", HttpStatus.NOT_FOUND);
    }
    return response;
  }

  /**
   * Update a user in the database.
   *
   * @param id The ID of the user to update.
   * @param user The user object to update.
   *
   * @return 200 OK if the update was successful, 400 BAD REQUEST if not
   */
  @PutMapping("/update/{id}")
  public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody Users user) {
    logger.info("Updating user: {}", user);
    ResponseEntity<String> response;
    if (this.service.update(user, id) && user.isValid(user)) {
      response = new ResponseEntity<>(HttpStatus.OK);
    } else {
      response = new ResponseEntity<>("Failed to update user.", HttpStatus.BAD_REQUEST);
    }
    return response;
  }
}
