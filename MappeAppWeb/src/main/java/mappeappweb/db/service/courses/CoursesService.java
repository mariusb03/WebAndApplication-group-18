package mappeappweb.db.service.courses;

import java.util.Optional;
import mappeappweb.db.model.coursesdb.Courses;
import mappeappweb.db.model.coursesdb.Providers;
import mappeappweb.db.model.coursesdb.Topics;
import mappeappweb.db.repository.coursesRepository.CoursesRepository;
import mappeappweb.db.repository.coursesRepository.ProvidersRepository;
import mappeappweb.db.repository.coursesRepository.TopicRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    logger.info("Topic added to course: topicId={}, courseId={}", topicId, courseId);
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

    logger.info("Provider added to course: providerId={}, courseId={}", providerId, courseId);
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
  public Optional<Courses> getbyid(int id) {
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
    logger.info("Adding course{}", course);

    boolean success = false;
    try {
      courseRepo.save(course);
      success = true;
      logger.info("Course added successfully: {}", course);
    } catch (Exception e) {
      logger.error("Error adding course: {}", course);
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
    try {
      courseRepo.deleteById(id);
      success = true;
      logger.info("Course deleted successfully: {}", id);
    } catch (Exception e) {
      logger.error("Error deleting course: {}", id);
    }
    return success;
  }

  /**
   * Update a course.
   *
   * @param course The course to update.
   * @return true if the course was updated successfully, false otherwise.
   */
  public boolean update(Courses course) {
    int id = course.getCourseId();

    boolean success = false;
    try {
      if (courseRepo.existsById(id)) {
        courseRepo.deleteById(id);
        courseRepo.save(course);
        success = true;

        logger.info("Updating topic with ID {}", id);
      } else {
        logger.warn("Topic with ID {} not found for update", id);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return success;
  }
}
