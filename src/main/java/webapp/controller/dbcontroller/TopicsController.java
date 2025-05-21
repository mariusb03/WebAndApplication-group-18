package webapp.controller.dbcontroller;

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
import webapp.model.Topics;
import webapp.service.TopicService;

/**
 * Controller for managing topics.
 */
@RestController
@RequestMapping("/topics")
public class TopicsController {
  private final Logger logger = LoggerFactory.getLogger("TopicsController");
  @Autowired
  private TopicService service;

  /**
   * Retrieves all topics from the database.
   *
   * @return An iterable collection of all topics.
   */
  @Operation(
      summary = "Get all topics",
      description = "Retrieves a list of all topics in the database."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved list of topics"),
      @ApiResponse(responseCode = "500",
          description = "Internal Server Error - An unexpected error occurred"),
  })
  @GetMapping("/getAll")
  public Iterable<Topics> getAll() {
    logger.info("Retrieving all topics");
    return this.service.getAll();
  }

  /**
   * Retrieves topic by ID.
   *
   * @param id The ID of the topic to retrieve.
   * @return topic with the specified ID.
   */
  @Operation(
      summary = "Get a topic by ID",
      description = "Returns a topic if found, otherwise returns 404 Not Found"
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved the topic"),
      @ApiResponse(responseCode = "404", description = "Topic not found"),
      @ApiResponse(responseCode = "500",
        description = "Internal Server Error - An unexpected error occurred")
  })
  @GetMapping("/getById/{id}")
  public ResponseEntity<Optional<Topics>> getById(
      @Parameter(description = "Id of the Topic to retrieve", required = true)
      @PathVariable int id) {
    logger.info("Retrieving topic with ID: {}", id);
    ResponseEntity<Optional<Topics>> response;
    Optional<Topics> topic = this.service.getById(id);
    if (topic.isPresent()) {
      response = ResponseEntity.ok(topic);
    } else {
      response = ResponseEntity.notFound().build();
    }
    return response;
  }

  /**
   * Adds a new topic to the database.
   *
   * @param topic The topic to add.
   */
  @Operation(
      summary = "Add a new topic",
      description = "Adds a new topic to the database"
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully added the topic"),
      @ApiResponse(responseCode = "400", description = "Failed to add topic"),
      @ApiResponse(responseCode = "403",
          description = "Forbidden - User lacks ADMIN role"),
      @ApiResponse(responseCode = "500",
          description = "Internal Server Error - An unexpected error occurred")
  })
  @PostMapping("/add")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<String> add(
      @Parameter(description = "Topic objet to add")
      @RequestBody Topics topic) {
    logger.info("Adding new topic: {}", topic);
    ResponseEntity<String> response;
    if (this.service.add(topic)) {
      response = new ResponseEntity<>(HttpStatus.OK);
    } else {
      response = new ResponseEntity<>("Failed to add topic.", HttpStatus.BAD_REQUEST);
    }
    return response;
  }

  /**
   * Deletes a topic from the database.
   */
  @Operation(
      summary = "Delete a topic",
      description = "Deletes a topic from the database"
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully deleted the topic"),
      @ApiResponse(responseCode = "404", description = "Topic not found"),
      @ApiResponse(responseCode = "403",
          description = "Forbidden - User lacks ADMIN role"),
      @ApiResponse(responseCode = "500",
          description = "Internal Server Error - An unexpected error occurred")
  })
  @DeleteMapping("/delete/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<String> delete(
      @Parameter(description = "Id of the Topic to delete", required = true)
      @PathVariable int id) {
    logger.info("Deleting topic with ID: {}", id);
    ResponseEntity<String> response;
    if (this.service.delete(id)) {
      response = new ResponseEntity<>(HttpStatus.OK);
    } else {
      response = new ResponseEntity<>("Failed to delete topic.", HttpStatus.NOT_FOUND);
    }
    return response;
  }

  /**
   * Update a topic in the database.
   *
   * @param id The ID of the topic to update.
   * @param topic The topic object to update.
   *
   * @return 200 OK if the update was successful, 400 BAD REQUEST if not
   */
  @Operation(
      summary = "Update a topic",
      description = "Updates an existing topic in the database"
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully updated the topic"),
      @ApiResponse(responseCode = "400", description = "Failed to update topic"),
      @ApiResponse(responseCode = "403",
          description = "Forbidden - User lacks ADMIN role"),
      @ApiResponse(responseCode = "404", description = "Topic not found"),
      @ApiResponse(responseCode = "500",
          description = "Internal Server Error - An unexpected error occurred")
  })
  @PutMapping("/update/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<String> update(
      @Parameter(description = "Id of the Topic to be updated", required = true)
      @PathVariable Integer id,
      @Parameter(description = "Topic object to update")
      @RequestBody Topics topic) {
    logger.info("Updating topic: {}", topic);
    ResponseEntity<String> response;
    if (this.service.update(topic, id) && topic.isValid(topic)) {
      response = new ResponseEntity<>(HttpStatus.OK);
    } else {
      response = new ResponseEntity<>("Failed to update topic.", HttpStatus.BAD_REQUEST);
    }
    return response;
  }
}
