package webApp.controller.course;

import java.util.Optional;
import webApp.model.coursesdb.Courses;
import webApp.service.courses.CoursesService;
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
 * Controller for managing courses.
 */
@RestController
@RequestMapping("/courses")
public class CoursesController {
  private ControllerCommonResponse response;
  private final Logger logger = LoggerFactory.getLogger("CoursesController");
  @Autowired
  private CoursesService service;

  /**
   * Retrieves all courses from the database.
   *
   * @return An iterable collection of all courses.
   */
  @GetMapping("/getAll")
  public Iterable<Courses> getAll() {
    logger.info("Retrieving all courses");
    return this.service.getAll();
  }

  /**
   * Retrieves course by ID.
   *
   * @param id The ID of the course to retrieve.
   * @return courses with the specified ID.
   */
  @GetMapping("/getById/{id}")
  public ResponseEntity<Optional<Courses>> getById(@PathVariable int id) {
    logger.info("Retrieving course with ID: {}", id);
    ResponseEntity<Optional<Courses>> response;
    Optional<Courses> courses = this.service.getById(id);
    if (courses.isPresent()) {
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
    logger.info("Adding new course: {}", courses);
    ResponseEntity<String> response;
    if (this.service.add(courses)) {
      response = new ResponseEntity<>("Course added", HttpStatus.OK);
    } else if (courses.isValid()) {
      response = new ResponseEntity<>("wrong value in a filed", HttpStatus.BAD_REQUEST);
    } else {
      response = new ResponseEntity<>("Failed to add course", HttpStatus.BAD_REQUEST);
    }
    return response;
  }

  /**
   * Adds a topic to a course.
   *
   * @param courseId of course, that will associate with the topic
   * @param topicId of the topic that will be associated with the course
   * @return ResponseEntity with a message indicating success or failure
   */
  @PostMapping("/{courseId}/topics/{topicId}")
  public ResponseEntity<String> addTopicToCourse(@PathVariable Integer courseId,
                                                  @PathVariable Integer topicId) {
    logger.info("Adding topic with ID: {} to course with ID: {}", topicId, courseId);
    try {
      service.addTopicToCourse(courseId, topicId);
    } catch (IllegalArgumentException e) {
      logger.error("Failed to add topic to course: {}", e.getMessage());
      return ResponseEntity.badRequest().body("Invalid course or topic ID");
    }
    return ResponseEntity.ok("Topic added to course");
  }


  /**
   * Adds a provider to a course.
   *
   * @param courseId of course, that will associate with the provider
   * @param providerId of the provider that will be associated with the course
   * @return ResponseEntity with a message indicating success or failure
   */
  @PostMapping("/{courseId}/provider/{providerId}")
  public ResponseEntity<String> addProviderToCourse(@PathVariable Integer courseId,
                                                    @PathVariable Integer providerId) {
    logger.info("Adding provider with ID: {} to course with ID: {}", providerId, courseId);
    try {
      service.addProviderToCourse(courseId, providerId);
    } catch (IllegalArgumentException e) {
      logger.error("Failed to add provider to course: {}", e.getMessage());
      return ResponseEntity.badRequest().body("Invalid course or provider ID");
    }
    return ResponseEntity.ok("Provider added to course");
  }

  /**
   * Deletes a course from the database.
   */
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> delete(@PathVariable int id) {
    logger.info("Deleting course with ID: {}", id);
    ResponseEntity<String> response;
    if (this.service.delete(id)) {
      response = new ResponseEntity<>("Course deleted", HttpStatus.OK);
    } else {
      response = new ResponseEntity<>("Could not delete course",HttpStatus.NOT_FOUND);
    }
    return response;
  }

  /**
   * Update a course in the database.
   *
   * @param id The ID of the course to update.
   * @param course The course object to update.
   *
   * @return 200 OK if update was successful, 400 BAD REQUEST if not
   */
  @PutMapping("/Update/{id}")
  public ResponseEntity<String> update(@PathVariable Integer id ,@RequestBody Courses course) {
    logger.info("Updating course with ID: {}", course);
    ResponseEntity<String> response;
    if (this.service.update(course, id)) {
      response = new ResponseEntity<>("Course updated", HttpStatus.OK);
    } else {
      response = new ResponseEntity<>("Could not update course", HttpStatus.BAD_REQUEST);
    }
    return response;
  }
}