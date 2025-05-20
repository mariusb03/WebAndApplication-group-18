package webApp.controller.dbController;

import java.util.Optional;
import webApp.model.Topics;
import webApp.service.TopicService;
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
  @GetMapping("/getById/{id}")
  public ResponseEntity<Optional<Topics>> getById(@PathVariable int id) {
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
  @PostMapping("/add")
  public ResponseEntity<String> add(@RequestBody Topics topic) {
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
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> delete(@PathVariable int id) {
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
  @PutMapping("/update/{id}")
  public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody Topics topic) {
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
