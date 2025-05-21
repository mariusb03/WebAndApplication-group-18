package webApp.service;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import webApp.model.Courses;
import webApp.model.Providers;
import webApp.model.Topics;
import webApp.model.Users;
import webApp.repository.CoursesRepository;
import webApp.repository.ProvidersRepository;
import webApp.repository.TopicRepository;
import webApp.repository.UserRepository;

/**
 * Service class for managing courses.
 * This class contains business logic related to courses and interacts with the repository layer.
 */
@Service
public class CoursesService {
  private final Logger logger = LoggerFactory.getLogger("CoursesService");
  @Autowired
  private CoursesRepository courseRepo;

  @Autowired
  private TopicRepository topicRepo;

  @Autowired
  private ProvidersRepository providerRepo;

  @Autowired
  private UserRepository userRepo;

  /**
   * Add a topic to a course.
   *
   * @param courseId The ID of the course.
   * @param topicId  The ID of the topic.
   */
  @Transactional
  public void addTopicToCourse(Integer courseId, Integer topicId) {
    logger.info("Adding topic to course: topicId={}, courseId={}", topicId, courseId);
    Courses course = courseRepo.findById(courseId)
        .orElseThrow(() -> new RuntimeException("Course not found"));
    Topics topic = topicRepo.findById(topicId)
        .orElseThrow(() -> new RuntimeException("Topic not found"));

    course.getTopics().add(topic);
    courseRepo.save(course);
  }

  /**
   * add a provider to a course.
   *
   * @param courseId The ID of the course.
   * @param providerId The ID of the provider.
   */
  @Transactional
  public void addProviderToCourse(Integer courseId, Integer providerId) {
    logger.info("Adding provider to course: providerId={}, courseId={}", providerId, courseId);
    Courses course = courseRepo.findById(courseId)
        .orElseThrow(() -> new RuntimeException("Course not found"));
    Providers provider = providerRepo.findById(providerId)
        .orElseThrow(() -> new RuntimeException("provider not found"));

    course.getProviders().add(provider);
    courseRepo.save(course);
  }

  /**
   * add a user to a course.
   *
   * @param courseId The ID of the course.
   * @param userId The ID of the user.
   */
  public void addUserToCourse(Integer courseId, Integer userId) {
    logger.info("Adding user to course: userId={}, courseId={}", userId, courseId);

    Courses course = courseRepo.findById(courseId)
        .orElseThrow(() -> new RuntimeException("Course not found"));
    Users user = userRepo.findById(userId)
        .orElseThrow(() -> new RuntimeException("user not found"));

    course.getUsers().add(user);
    courseRepo.save(course);
  }

  /**
   * get all courses of a provider.
   *
   * @param providerId The ID of the provider.
   * @return All courses of the provider.
   */
  public Iterable<Courses> getAllCoursesOfProvider(int providerId) {
    logger.info("Fetching all courses of provider with ID {}", providerId);

    Providers provider = providerRepo.findById(providerId)
        .orElseThrow(() -> new RuntimeException("provider not found"));

    return courseRepo.findCoursesByProviderProvidersId(providerId);
  }

  /**
   * Get all courses of a topic.
   *
   * @param topicId The ID of the topic.
   * @return All courses of the topic.
   */
  public Iterable<Courses> getAllCoursesOfTopic(Integer topicId) {
    logger.info("Fetching all courses with topic with ID {}", topicId);

    Topics topic = topicRepo.findById(topicId)
        .orElseThrow(() -> new RuntimeException("topic not found"));

    return courseRepo.findCourseWhitTopicTopicId(topicId);
  }

  /**
   * get all courses of a user.
   *
   * @param userId The ID of the user.
   * @return All courses of the user.
   */
  public Iterable<Courses> getAllCoursesOfUser(Integer userId) {
    logger.info("Fetching all courses with user with ID {}", userId);

    return courseRepo.findCourseWithUserUserId(userId);
  }

  /**
   * get all providers of a course.
   *
   * @param courseId The ID of the course.
   * @return All providers of the course.
   */
  public Iterable<Providers> getAllProvidersOfCourse(int courseId) {
    logger.info("Fetching all providers of course with ID {}", courseId);

    Courses course = courseRepo.findById(courseId)
        .orElseThrow(() -> new RuntimeException("course not found"));

    return courseRepo.findProvidersForCourse(courseId);
  }

  /**
   * get all topics of a course.
   *
   * @param courseId The ID of the course.
   * @return All topics of the course.
   */
  public Iterable<Topics> getAllTopicsOfCourse(Integer courseId) {
    logger.info("Fetching all topics of course with ID {}", courseId);

    Courses course = courseRepo.findById(courseId)
        .orElseThrow(() -> new RuntimeException("course not found"));

    return courseRepo.findTopicForCourse(courseId);
  }

  /**
   * Get all courses of a user.
   *
   * @param courseId The ID of the course.
   * @return All courses of the user.
   */
  public Iterable<Users> getAllUsersOfCourse(Integer courseId) {
    logger.info("Fetching all users of course with ID {}", courseId);

    Courses course = courseRepo.findById(courseId)
        .orElseThrow(() -> new RuntimeException("course not found"));

    return courseRepo.findUsersForCourse(courseId);
  }

  /**
   * Get all Courses.
   *
   * @return All Courses.
   */
  public Iterable<Courses> getAll() {
    logger.info("Fetching all topics");

    return courseRepo.findAll();
  }

  /**
   * Get all topics.
   *
   * @param id The ID of the course.
   * @return All topics.
   */
  public Optional<Courses> getById(int id) {
    logger.info("Fetching topic by ID {}", id);

    return courseRepo.findById(id);
  }


  /**
   * Add a new course.
   *
   * @param course The course to add.
   * @return true if the course was added successfully, false otherwise.
   */
  public boolean add(Courses course) {
    logger.info("Adding course {}", course);

    boolean success = false;
    if (course.isValid()) {
      try {
        courseRepo.save(course);
        success = true;
        logger.info("Provider added successfully");
      } catch (Exception e) {
        logger.error("Error adding provider: {}", e.getMessage());
      }
    }
    return success;
  }

  /**
   * Delete a course by ID.
   *
   * @param id The ID of the course to delete.
   * @return true if the course was deleted successfully, false otherwise.
   */
  public boolean delete(int id) {
    logger.info("Deleting topic with ID {}", id);

    boolean success = false;
    if (courseRepo.existsById(id)) {
      try {
        courseRepo.deleteById(id);
        logger.info("Deleted topic with ID {}", id);
        success = true;
      } catch (Exception e) {
        logger.error("Failed to delete topic with ID {}", id);
      }
    } else {
      logger.warn("Topic with ID {} does not exist", id);
    }
    return success;
  }

  /**
   * Update a course.
   *
   * @param course The course to update.
   * @return true if the course was updated successfully, false otherwise.
   */
  public boolean update(Courses course, Integer id) {
    return courseRepo.findById(id).map(existing -> {
      if (course.isValid()) {
        existing.setTitle(course.getTitle());
        existing.setDifficulty(course.getDifficulty());
        existing.setSession(course.getSession());
        existing.setSize(course.getSize());
        existing.setHoursPerWeek(course.getHoursPerWeek());
        existing.setRelatedCourses(course.getRelatedCourses());
        existing.setPrice(course.getPrice());
        existing.setDescription(course.getDescription());
        existing.setCategory(course.getCategory());
        courseRepo.save(existing);
        logger.info("Updated course with ID {}", id);
        return true;
      } else {
        logger.error("Course with ID {} is not valid", id);
        return false;
      }
    }).orElse(false);
  }
}
