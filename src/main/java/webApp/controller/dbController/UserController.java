package webApp.controller.dbController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
  @Operation(
      summary = "Get all users",
      description = "Retrieves a list of all users in the database."
  )
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of users",
      content = @Content(mediaType = "application/json",
        schema = @Schema(implementation = Users.class))),
    @ApiResponse(responseCode = "403", description = "Forbidden - User lacks ADMIN role"),
    @ApiResponse(responseCode = "500",
      description = "Internal Server Error - An unexpected error occurred"),
    @ApiResponse(responseCode = "404", description = "Not Found - No users found"),
    @ApiResponse(responseCode = "400",
      description = "Bad Request - Invalid request parameters")
  })
  @GetMapping("/getAll")
  @PreAuthorize("hasRole('ADMIN')")
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
  @Operation(
      summary = "Get user by ID",
      description = "Retrieves a user by their unique ID."
  )
  @ApiResponses(value = {
    @ApiResponse(responseCode = "200", description = "Successfully retrieved user",
      content = @Content(mediaType = "application/json",
        schema = @Schema(implementation = Users.class))),
    @ApiResponse(responseCode = "403", description = "Forbidden - User lacks ADMIN role"),
    @ApiResponse(responseCode = "500",
      description = "Internal Server Error - An unexpected error occurred"),
    @ApiResponse(responseCode = "404", description = "Not Found - User not found"),
    @ApiResponse(responseCode = "400",
      description = "Bad Request - Invalid request parameters")
  })
  @GetMapping("/getById/{id}")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<Optional<Users>> getById(
      @Parameter(description = "ID for the User to retrieve")
      @PathVariable int id) {
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
  @Operation(
      summary = "Get user by username",
      description = "Retrieves a user by their unique username."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved user",
          content = @Content(mediaType = "application/json",
              schema = @Schema(implementation = Users.class))),
      @ApiResponse(responseCode = "403", description = "Forbidden - User lacks ADMIN role"),
      @ApiResponse(responseCode = "500",
          description = "Internal Server Error - An unexpected error occurred"),
      @ApiResponse(responseCode = "404", description = "Not Found - User not found"),
      @ApiResponse(responseCode = "400",
          description = "Bad Request - Invalid request parameters")
  })
  @PostMapping("/add")
  public ResponseEntity<String> add(
      @Parameter(description = "User object to add")
      @RequestBody Users user) {
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
  @Operation(
      summary = "Delete a user",
      description = "Deletes a user by their ID."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User deleted successfully"),
      @ApiResponse(responseCode = "404", description = "User not found"),
      @ApiResponse(responseCode = "403", description = "Forbidden - User lacks USER role"),
      @ApiResponse(responseCode = "500",
          description = "Internal Server Error - An unexpected error occurred")
  })
  @DeleteMapping("/delete/{id}")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<String> delete(
      @Parameter(description = "ID of the user to delete")
      @PathVariable int id) {
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
  @Operation(
      summary = "Update a user",
      description = "Updates an existing user by their ID."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "User updated successfully"),
      @ApiResponse(responseCode = "400", description = "Invalid user data or user not found"),
      @ApiResponse(responseCode = "403", description = "Forbidden - User lacks USER role"),
      @ApiResponse(responseCode = "404", description = "User not found"),
      @ApiResponse(responseCode = "500",
        description = "Internal Server Error - An unexpected error occurred")
  })
  @PutMapping("/update/{id}")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<String> update(
      @Parameter(description = "ID of the user to be updated")
      @PathVariable Integer id,
      @Parameter(description = "Update user object")
      @RequestBody Users user) {
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
