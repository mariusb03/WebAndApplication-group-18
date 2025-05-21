package webapp.controller.dbController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
import webapp.model.Providers;
import webapp.service.ProviderService;

/**
 * Controller for managing course providers.
 */
@RestController
@RequestMapping("/providers")
public class ProviderController {
  private final Logger logger = LoggerFactory.getLogger("ProviderController");
  @Autowired
  private ProviderService service;

  /**
   * Retrieves all course providers from the database.
   *
   * @return An iterable collection of all course providers.
   */
  @Operation(
      summary = "Get all course providers",
      description = "Retrieves a list of all course providers in the database."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "Successfully retrieved list of course providers"),
      @ApiResponse(responseCode = "500",
          description = "Internal Server Error - An unexpected error occurred"),
  })
  @GetMapping("/getAll")
  public Iterable<Providers> getAll() {
    logger.info("Retrieving all course providers");
    return this.service.getAll();
  }

  /**
  * Retrieves course providers by their ID.
  *
  * @param id The ID for course provider to retrieve.
  * @return The course provider with the specified ID.
  */
  @Operation(
      summary = "Get course provider by ID",
      description = "Retrieves a course provider by its ID."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "Successfully retrieved course provider by ID"),
      @ApiResponse(responseCode = "404",
          description = "Course provider not found with the specified ID"),
      @ApiResponse(responseCode = "500",
          description = "Internal Server Error - An unexpected error occurred"),
  })
  @GetMapping("/getById/{id}")
  public ResponseEntity<Optional<Providers>> getById(
      @Parameter(description = "Id of the course provider to retrieve", required = true)
      @PathVariable int id) {
    logger.info("Retrieving course provider with ID: {}", id);
    ResponseEntity<Optional<Providers>> response;
    Optional<Providers> provider = this.service.getById(id);
    if (provider.isPresent()) {
      response = ResponseEntity.ok(provider);
    } else {
      response = ResponseEntity.notFound().build();
    }
    return response;
  }

  /**
  * Adds a new course provider to the database.
  *
  * @param provider The course provider to add.
  */
  @Operation(
      summary = "Add a new course provider",
      description = "Adds a new course provider to the database."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "Successfully added new course provider"),
      @ApiResponse(responseCode = "400",
          description = "Failed to add course provider - Bad Request"),
      @ApiResponse(responseCode = "403",
            description = "Forbidden - User lacks ADMIN role"),
      @ApiResponse(responseCode = "500",
          description = "Internal Server Error - An unexpected error occurred")
  })
  @PostMapping("/add")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<String> add(
      @Parameter(description = "Course provider to add", required = true)
      @RequestBody Providers provider) {
    logger.info("Adding new course provider: {}", provider);
    ResponseEntity<String> response;
    if (this.service.add(provider)) {
      response = new ResponseEntity<>(HttpStatus.OK);
    } else {
      response = new ResponseEntity<>("failed to add provider", HttpStatus.BAD_REQUEST);
    }
    return response;
  }

  /**
   * Deletes a course provider from the database.
   */
  @Operation(
      summary = "Delete a course provider",
      description = "Deletes a course provider from the database."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "Successfully deleted course provider"),
      @ApiResponse(responseCode = "404",
          description = "Course provider not found with the specified ID"),
      @ApiResponse(responseCode = "403",
          description = "Forbidden - User lacks ADMIN role"),
      @ApiResponse(responseCode = "500",
          description = "Internal Server Error - An unexpected error occurred")
  })
  @DeleteMapping("/delete/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<String> delete(
      @Parameter(description = "Id of the course provider to delete", required = true)
      @PathVariable int id) {
    logger.info("Deleting course provider with ID: {}", id);
    ResponseEntity<String> response;
    if (this.service.delete(id)) {
      response = new ResponseEntity<>(HttpStatus.OK);
    } else {
      response = new ResponseEntity<>("Failed to delete provider", HttpStatus.NOT_FOUND);
    }
    return response;
  }

  /**
   * Update a course provider in the database.
   *
   * @param id the ID of the course provider to update
   * @param provider the provider object to update
   *
   * @return 200 OK update was successful, 400 BAD REQUEST if not
   */
  @Operation(
        summary = "Update a course provider",
        description = "Updates a course provider in the database."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "Successfully updated course provider"),
      @ApiResponse(responseCode = "400",
          description = "Failed to update course provider - Bad Request"),
      @ApiResponse(responseCode = "403",
          description = "Forbidden - User lacks ADMIN role"),
      @ApiResponse(responseCode = "500",
          description = "Internal Server Error - An unexpected error occurred")
  })
  @PutMapping("/update/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<String> update(
      @Parameter(description = "Id of the course provider to update", required = true)
      @PathVariable Integer id,
      @Parameter(description = "Course provider to update")
      @RequestBody Providers provider) {
    logger.info("Updating course provider: {}", provider);
    ResponseEntity<String> response;
    if (this.service.update(provider, id)) {
      response = new ResponseEntity<>(HttpStatus.OK);
    } else {
      response = new ResponseEntity<>("Failed to update provider", HttpStatus.BAD_REQUEST);
    }
    return response;
  }
}