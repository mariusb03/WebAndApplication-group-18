package MappeAppWeb.DB.controller.coursesController;

import MappeAppWeb.DB.model.CoursesDB.Topics;
import MappeAppWeb.DB.service.courses.TopicService;
import java.util.Optional;
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

@RestController
@RequestMapping("/topics")
public class TopicsController {
  @Autowired
  private TopicService service;

  /**
   * Retrieves all topics from the database.
   *
   * @return An iterable collection of all topics.
   */
  @GetMapping("/getAll")
  public Iterable<Topics> getAll() {
    return this.service.getAll();
  }

  /**
   * Retrieves topic by ID.
   *
   * @param id The ID of the topic to retrieve.
   * @return topic with the specified ID.
   */
  @GetMapping("/getById/{id}")
  public ResponseEntity<Optional<Topics>> getByID(@PathVariable int id) {
    ResponseEntity<Optional<Topics>> response;
    Optional<Topics> topic = this.service.getByID(id);
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
  @PostMapping("/add}")
  public ResponseEntity<String> add(@RequestBody Topics topic) {
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
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> delete(@PathVariable int id) {
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
   * @param topics The topic object to update.
   *
   * @return 200 OK if update was successful, 400 BAD REQUEST if not
   */
  @PutMapping("/Update/{id}")
  public ResponseEntity<String> update(@PathVariable Topics topics) {
    ResponseEntity<String> response;
    if (this.service.update(topics)) {
      response = new ResponseEntity<>(HttpStatus.OK);
    } else {
      response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return response;
  }
}
