package MappeAppWeb.DB.controller.coursesController;

import MappeAppWeb.DB.model.CoursesDB.Topics;
import MappeAppWeb.DB.service.courses.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class TopicsController {
  @Autowired
  private TopicService service;

  /**
   * Retrieves all topics from the database.
   *
   * @return An iterable collection of all topics.
   */
  @GetMapping
  public Iterable<Topics> getAllTopics() {
    return this.service.getAll();
  }

  /**
   * Retrieves topic by ID.
   *
   * @param id The ID of the topic to retrieve.
   * @return topic with the specified ID.
   */
  @GetMapping("/{id}")
  public ResponseEntity<Topics> getCourseProvider(@PathVariable int id) {
    ResponseEntity<Topics> response;
    Topics topic = this.service.getByID(id);
    if (topic != null) {
      response = ResponseEntity.ok(topic);
    } else {
      response = ResponseEntity.notFound().build();
    }
    return response;
  }

  /**
   * Retrieves topic by their name.
   *
   * @param name The name of the topic to retrieve.
   *
   * @return topic with the specified name.
   */
  @GetMapping("/{name}")
  public ResponseEntity<Topics> getCourseProviderByName(@PathVariable String name) {
    ResponseEntity<Topics> response;
    Topics topic = this.service.getByName(name);
    if (topic != null) {
      response = ResponseEntity.ok(topic);
    } else {
      response = ResponseEntity.notFound().build();
    }
    return response;
  }

  /**
   * Adds a new topic to the database.
   *
   * @param topic The course provider to add.
   */
  @PostMapping
  public ResponseEntity<String> addProvider(@RequestBody Topics topic) {
    ResponseEntity<String> response;
    if (this.service.add(topic)) {
      response = new ResponseEntity<>(HttpStatus.OK);
    } else {
      response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return response;
  }

  /**
   * Deletes a topic from the database.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteTopic(@PathVariable int id) {
    ResponseEntity<String> response;
    if (this.service.delete(id)) {
      response = new ResponseEntity<>(HttpStatus.OK);
    } else {
      response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return response;
  }

  /**
   * Update a topic in the database.
   *
   * @param id of the topic to update
   * @param name of the topic
   *
   * @return 200 OK if update was successful, 400 BAD REQUEST if not
   */
  @PutMapping("/{id}")
  public ResponseEntity<String> updateTopic (@PathVariable int id, @RequestBody Topics name) {
    ResponseEntity<String> response;
    if (this.service.update(id, name)) {
      response = new ResponseEntity<>(HttpStatus.OK);
    } else {
      response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return response;
  }
}
