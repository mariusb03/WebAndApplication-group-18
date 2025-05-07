package mappeappweb.db.controller.coursesController;

import mappeappweb.db.model.CoursesDB.Courses;
import mappeappweb.db.service.courses.CoursesService;
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
@RequestMapping("/courses")
public class CoursesController {
  @Autowired
  private CoursesService service;

  /**
   * Retrieves all courses from the database.
   *
   * @return An iterable collection of all courses.
   */
  @GetMapping("/getAll")
  public Iterable<Courses> getAll() {
    return this.service.getAll();
  }

  /**
   * Retrieves course by ID.
   *
   * @param id The ID of the course to retrieve.
   * @return courses with the specified ID.
   */
  @GetMapping("/getById/{id}")
  public ResponseEntity<Optional<Courses>> getByID(@PathVariable int id) {
    ResponseEntity<Optional<Courses>> response;
    Optional<Courses> courses = this.service.getByID(id);
    if (courses != null) {
      response = ResponseEntity.ok(courses);
    } else {
      response = ResponseEntity.notFound().build();
    }
    return response;
  }

  /**
   * Adds a new courses to the database.
   *
   * @param courses The courses to add.
   */
  @PostMapping("/add")
  public ResponseEntity<String> addCourse(@RequestBody Courses courses)  {
    ResponseEntity<String> response;
    if (this.service.add(courses)) {
      response = new ResponseEntity<>(HttpStatus.OK);
    } else {
      response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return response;
  }

  /**
   * Adds a topic to a course.
   * @param courseId of the course that will associate with the topic
   * @param topicId of the topic that will be associated with the course
   * @return ResponseEntity with a message indicating success or failure
   */
  @PostMapping("/{courseId}/topics/{topicId}")
  public ResponseEntity<String> addTopicToCourse
      (@PathVariable Integer courseId, @PathVariable Integer topicId) {
    service.addTopicToCourse(courseId, topicId);
    return ResponseEntity.ok("Topic added to course");
  }


  /**
   * Adds a provider to a course.
   * @param courseId of the course that will associate with the topic
   * @param providerId of the provider that will be associated with the course
   * @return ResponseEntity with a message indicating success or failure
   */
  @PostMapping("/{courseId}/provider/{providerId}")
  public ResponseEntity<String> addProviderToCourse
  (@PathVariable Integer courseId, @PathVariable Integer providerId) {
    service.addProviderToCourse(courseId, providerId);
    return ResponseEntity.ok("Provider added to course");
  }

  /**
   * Deletes a course from the database.
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
   * Update a course in the database.
   *
   * @param course The course object to update.
   *
   * @return 200 OK if update was successful, 400 BAD REQUEST if not
   */
  @PutMapping("/Update/{id}")
  public ResponseEntity<String> update(@PathVariable Courses course) {
    ResponseEntity<String> response;
    if (this.service.update(course)) {
      response = new ResponseEntity<>(HttpStatus.OK);
    } else {
      response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return response;
  }
}
