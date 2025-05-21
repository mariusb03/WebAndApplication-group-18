package webapp.controller.dbcontroller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import webapp.controller.ControllerCommonResponse;
import webapp.model.Courses;
import webapp.model.Providers;
import webapp.model.Topics;
import webapp.model.Users;
import webapp.service.CoursesService;

/**
 * Controller for managing courses.
 */
@RestController
@RequestMapping("/api/courses")
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
  @Operation(
      summary = "Get all courses",
      description = "Retrieves a list of all courses in the database."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved list of courses"),
      @ApiResponse(responseCode = "500",
          description = "Internal Server Error - An unexpected error occurred")
  })
  @GetMapping("/getAll")
  public ResponseEntity<Iterable<Courses>> getAllCourses(
      @Parameter(description = "User role for filtering courses")
      @RequestHeader(value = "userRole", required = false) String userRole) {

    logger.info("Retrieving all courses, user role: {}", userRole);

    Iterable<Courses> allCourses = service.getAll();
    List<Courses> visibleCourses;

    if ("admin".equalsIgnoreCase(userRole)) {
      visibleCourses = StreamSupport.stream(allCourses.spliterator(), false)
              .collect(Collectors.toList());
    } else {
      visibleCourses = StreamSupport.stream(allCourses.spliterator(), false)
              .filter(course -> !course.isHidden())
              .collect(Collectors.toList());
    }

    return ResponseEntity.ok(visibleCourses);
  }

  /**
   * Retrieves course by ID.
   *
   * @param id The ID the course to retrieve.
   * @return courses with the specified ID.
   */
  @Operation(
      summary = "Get course by ID",
      description = "Retrieves a course by its ID."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved the course"),
      @ApiResponse(responseCode = "404", description = "Course not found"),
      @ApiResponse(responseCode = "500",
          description = "Internal Server Error - An unexpected error occurred")
  })
  @GetMapping("/getById/{id}")
  public ResponseEntity<Optional<Courses>> getById(
      @Parameter(description = "Id of the course to retrieve", required = true)
      @PathVariable int id) {
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
   * Adds new courses to the database.
   *
   * @param courses The courses to add.
   */
  @Operation(
        summary = "Add a new course",
        description = "Adds a new course to the database."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully added the course"),
      @ApiResponse(responseCode = "400", description = "Bad Request - Invalid request parameters"),
      @ApiResponse(responseCode = "403", description = "Forbidden - User lacks ADMIN role"),
      @ApiResponse(responseCode = "500",
          description = "Internal Server Error - An unexpected error occurred")
  })
  @PostMapping("/add")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<String> addCourse(
      @Parameter(description = "Course object to be added", required = true)
      @RequestBody Courses courses)  {
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
  @Operation(
        summary = "Add a topic to a course",
        description = "associates a topic to a course, that make it possible to get all courses "
            + "of a topic, and all topics of a course"
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "Successfully added the topic to the course"),
      @ApiResponse(responseCode = "400", description = "Bad Request - Invalid request parameters"),
      @ApiResponse(responseCode = "403", description = "Forbidden - User lacks ADMIN role"),
      @ApiResponse(responseCode = "500",
          description = "Internal Server Error - An unexpected error occurred")
  })
  @PostMapping("/{courseId}/topics/{topicId}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<String> addTopicToCourse(
      @Parameter(description = "Id of the course to associate with the topic", required = true)
      @PathVariable Integer courseId,
      @Parameter(description = "Id of the topic to associate with the course", required = true)
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
  @Operation(
        summary = "Add a provider to a course",
        description = "associates a provider to a course, that make it possible to get all "
            + "courses of a provider, and all providers of a course"
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "Successfully added the provider to the course"),
      @ApiResponse(responseCode = "400", description = "Bad Request - Invalid request parameters"),
      @ApiResponse(responseCode = "403", description = "Forbidden - User lacks ADMIN role"),
      @ApiResponse(responseCode = "500",
          description = "Internal Server Error - An unexpected error occurred")
  })
  @PostMapping("/{courseId}/provider/{providerId}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<String> addProviderToCourse(
      @Parameter(description = "Id of the course to associate with the provider", required = true)
      @PathVariable Integer courseId,
      @Parameter(description = "Id of the provider to associate with the course", required = true)
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
   * Adds a user to a course.
   *
   * @param courseId of course, that will associate with the topic
   * @param userId of the user that will be associated with the course
   * @return ResponseEntity with a message indicating success or failure
   */
  @Operation(
          summary = "Add a user to a course",
          description = "associates a user to a course, that make it possible to get all courses "
              + "of a user, and all users of a course"
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200",
          description = "Successfully added the user to the course"),
      @ApiResponse(responseCode = "400", description = "Bad Request - Invalid request parameters"),
      @ApiResponse(responseCode = "403", description = "Forbidden - User lacks USER role"),
      @ApiResponse(responseCode = "500",
          description = "Internal Server Error - An unexpected error occurred")
  })
  @PostMapping("/{courseId}/user/{userId}")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<String> addUserToCourse(
      @Parameter(description = "Id of the course to associate with the user", required = true)
      @PathVariable Integer courseId,
      @Parameter(description = "Id of the user to associate with the course", required = true)
      @PathVariable Integer userId) {
    logger.info("Adding user with ID: {} to course with ID: {}", userId, courseId);
    try {
      service.addUserToCourse(courseId, userId);
    } catch (IllegalArgumentException e) {
      logger.error("Failed to add user to course: {}", e.getMessage());
      return ResponseEntity.badRequest().body("Invalid course or user ID");
    }
    return ResponseEntity.ok("user added to course");
  }

  /**
   * get all courses of a topic.
   *
   * @param topicId of the provider
   * @return ResponseEntity with a message indicating success or failure
   */
  @Operation(
      summary = "Get all courses of a topic",
      description = "Retrieves all courses associated with a specific topic."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved courses"),
      @ApiResponse(responseCode = "404", description = "Topic not found"),
      @ApiResponse(responseCode = "500",
          description = "Internal Server Error - An unexpected error occurred")
  })
  @GetMapping("/topic/{topicId}")
  public ResponseEntity<Iterable<Courses>> getAllCoursesOfTopic(
      @Parameter(description = "Id of the topic to retrieve courses for", required = true)
      @PathVariable Integer topicId) {
    logger.info("Retrieving all courses with topic ID: {}", topicId);

    ResponseEntity<Iterable<Courses>> response;
    Iterable<Courses> courses = this.service.getAllCoursesOfTopic(topicId);
    if (courses != null) {
      response = ResponseEntity.ok(courses);
    } else {
      response = ResponseEntity.notFound().build();
    }
    return response;
  }

  /**
   * get all courses of a provider.
   *
   * @param providerId of the provider
   * @return ResponseEntity with a message indicating success or failure
   */
  @Operation(
      summary = "Get all courses of a provider",
      description = "Retrieves all courses associated with a specific provider."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved courses"),
      @ApiResponse(responseCode = "404", description = "Provider not found"),
      @ApiResponse(responseCode = "500",
          description = "Internal Server Error - An unexpected error occurred")
  })
  @GetMapping("/provider/{providerId}")
  public ResponseEntity<Iterable<Courses>> getAllCoursesOfProvider(
      @Parameter(description = "Id of the provider to retrieve courses for", required = true)
      @PathVariable Integer providerId) {
    logger.info("Retrieving all courses of provider with ID: {}", providerId);

    ResponseEntity<Iterable<Courses>> response;
    Iterable<Courses> courses = this.service.getAllCoursesOfProvider(providerId);
    if (courses != null) {
      response = ResponseEntity.ok(courses);
    } else {
      response = ResponseEntity.notFound().build();
    }
    return response;
  }

  /**
   * get all courses of a User.
   *
   * @param userId of the provider
   * @return ResponseEntity with a message indicating success or failure
   */
  @Operation(
        summary = "Get all courses of a user",
        description = "Retrieves all courses associated with a specific user."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved courses"),
      @ApiResponse(responseCode = "404", description = "User not found"),
      @ApiResponse(responseCode = "500",
          description = "Internal Server Error - An unexpected error occurred")
  })
  @GetMapping("/user/{userId}")
  @PreAuthorize("hasRole('USER')")
  public ResponseEntity<Iterable<Courses>> getAllCoursesOfUser(
      @Parameter(description = "Id of the user to retrieve courses for", required = true)
      @PathVariable Integer userId) {
    logger.info("Retrieving all courses with user ID: {}", userId);

    ResponseEntity<Iterable<Courses>> response;
    Iterable<Courses> courses = this.service.getAllCoursesOfUser(userId);
    if (courses != null) {
      response = ResponseEntity.ok(courses);
    } else {
      response = ResponseEntity.notFound().build();
    }
    return response;
  }

  /**
   * get all providers of a course.
   *
   * @param courseId of the course
   * @return ResponseEntity with a message indicating success or failure
   */
  @Operation(
      summary = "Get all providers of a course",
      description = "Retrieves all providers associated with a specific course."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved providers"),
      @ApiResponse(responseCode = "404", description = "Course not found"),
      @ApiResponse(responseCode = "500",
          description = "Internal Server Error - An unexpected error occurred")
  })
  @GetMapping("/getProviderForCourse/{courseId}")
  public ResponseEntity<Iterable<Providers>> getAllProvidersOfCourse(
      @Parameter(description = "Id of the course to retrieve providers for", required = true)
      @PathVariable Integer courseId) {
    logger.info("Retrieving all providers related to a course with ID: {}", courseId);

    ResponseEntity<Iterable<Providers>> response;
    Iterable<Providers> providers = this.service.getAllProvidersOfCourse(courseId);
    if (providers != null) {
      response = ResponseEntity.ok(providers);
    } else {
      response = ResponseEntity.notFound().build();
    }
    return response;
  }

  /**
   * get all topics of a course.
   *
   * @param courseId of the course
   * @return ResponseEntity with a message indicating success or failure
   */
  @Operation(
      summary = "Get all topics of a course",
      description = "Retrieves all topics associated with a specific course."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved topics"),
      @ApiResponse(responseCode = "404", description = "Course not found"),
      @ApiResponse(responseCode = "500",
          description = "Internal Server Error - An unexpected error occurred")
  })
  @GetMapping("/getTopicsForCourse/{courseId}")
  public ResponseEntity<Iterable<Topics>> getAllTopicsOfCourse(
      @Parameter(description = "Id of the course to retrieve topics for", required = true)
      @PathVariable Integer courseId) {
    logger.info("Retrieving all topics related to a course with ID: {}", courseId);

    ResponseEntity<Iterable<Topics>> response;
    Iterable<Topics> topics = this.service.getAllTopicsOfCourse(courseId);
    if (topics != null) {
      response = ResponseEntity.ok(topics);
    } else {
      response = ResponseEntity.notFound().build();
    }
    return response;
  }

  /**
   * get all users that are registered to a course.
   *
   * @param courseId of the course
   * @return ResponseEntity with a message indicating success or failure
   */
  @Operation(
      summary = "Get all users of a course",
      description = "Retrieves all users associated with a specific course."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully retrieved users"),
      @ApiResponse(responseCode = "404", description = "Course not found"),
      @ApiResponse(responseCode = "500",
          description = "Internal Server Error - An unexpected error occurred")
  })
  @GetMapping("/getUsersForCourse/{courseId}")
  public ResponseEntity<Iterable<Users>> getAllUsersOfCourse(
      @Parameter(description = "Id of the course to retrieve users for", required = true)
      @PathVariable Integer courseId) {
    logger.info("Retrieving all users that is registered to a course with ID: {}", courseId);

    ResponseEntity<Iterable<Users>> response;
    Iterable<Users> users = this.service.getAllUsersOfCourse(courseId);
    if (users != null) {
      response = ResponseEntity.ok(users);
    } else {
      response = ResponseEntity.notFound().build();
    }
    return response;
  }

  /**
   * Deletes a course from the database.
   */
  @Operation(
      summary = "Delete a course",
      description = "Deletes a course from the database."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully deleted the course"),
      @ApiResponse(responseCode = "404", description = "Course not found"),
      @ApiResponse(responseCode = "500",
          description = "Internal Server Error - An unexpected error occurred")
  })
  @DeleteMapping("/delete/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<String> delete(
      @Parameter(description = "Id of the course to delete", required = true)
      @PathVariable int id) {
    logger.info("Deleting course with ID: {}", id);
    ResponseEntity<String> response;
    if (this.service.delete(id)) {
      response = new ResponseEntity<>("Course deleted", HttpStatus.OK);
    } else {
      response = new ResponseEntity<>("Could not delete course", HttpStatus.NOT_FOUND);
    }
    return response;
  }

  /**
   * Update a course in the database.
   *
   * @param id ID of course to update.
   * @param course The course objects to update.
   *
   * @return 200 OK if the update was successful, 400 BAD REQUEST if not
   */
  @Operation(
        summary = "Update a course",
        description = "Updates an existing course in the database."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully updated the course"),
      @ApiResponse(responseCode = "400", description = "Invalid course data or course not found"),
      @ApiResponse(responseCode = "403", description = "Forbidden - User lacks ADMIN role"),
      @ApiResponse(responseCode = "404", description = "Course not found"),
      @ApiResponse(responseCode = "500",
          description = "Internal Server Error - An unexpected error occurred")
  })
  @PutMapping("/Update/{id}")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<String> update(
      @Parameter(description = "Id of the course to update", required = true)
      @PathVariable Integer id,
      @Parameter(description = "Course object to be updated", required = true)
      @RequestBody Courses course) {
    logger.info("Updating course with ID: {}", course);
    ResponseEntity<String> response;
    if (this.service.update(course, id)) {
      response = new ResponseEntity<>("Course updated", HttpStatus.OK);
    } else {
      response = new ResponseEntity<>("Could not update course", HttpStatus.BAD_REQUEST);
    }
    return response;
  }

  /**
   * Toggles the visibility of a course.
   *
   * @param courseId ID of the course to toggle visibility for.
   * @return ResponseEntity with a message indicating success or failure
   */
  @Operation(
      summary = "Toggle course visibility",
      description = "Toggles the visibility of a course."
  )
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "Successfully toggled course visibility"),
      @ApiResponse(responseCode = "404", description = "Course not found"),
      @ApiResponse(responseCode = "500",
          description = "Internal Server Error - An unexpected error occurred")
  })
  @PutMapping("{courseId}/toggleVisibility")
  public ResponseEntity<String> toggleCourseVisibility(
      @Parameter(description = "Id of the course to toggle visibility for")
      @PathVariable Integer courseId) {
    logger.info("Toggling visibility for course with ID: {}", courseId);
    if (service.toggleCourseVisibility(courseId)) {
      return ResponseEntity.ok("Visibility toggled.");
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found.");
    }
  }
}
