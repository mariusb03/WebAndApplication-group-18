package mappeappweb.db.service.courses;

import mappeappweb.db.model.CoursesDB.Courses;
import mappeappweb.db.model.CoursesDB.Providers;
import mappeappweb.db.model.CoursesDB.Topics;
import mappeappweb.db.repository.coursesRepository.CoursesRepository;
import mappeappweb.db.repository.coursesRepository.ProvidersRepository;
import mappeappweb.db.repository.coursesRepository.TopicRepository;
import java.util.Optional;
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
    Courses course = courseRepo.findById(courseId)
        .orElseThrow(() -> new RuntimeException("Course not found"));
    Topics topic = topicRepo.findById(topicId)
        .orElseThrow(() -> new RuntimeException("Topic not found"));

    course.getTopics().add(topic);
    courseRepo.save(course);  // Persist the relationship
  }

  /**
   * add a provider to a course.
   *
   * @param courseId The ID of the course.
   * @param providerId The ID of the provider.
   */
    @Transactional
    public void addProviderToCourse(Integer courseId, Integer providerId) {
      Courses course = courseRepo.findById(courseId)
          .orElseThrow(() -> new RuntimeException("Course not found"));
      Providers provider = providerRepo.findById(providerId)
          .orElseThrow(() -> new RuntimeException("provider not found"));

      course.getProviders().add(provider);
      courseRepo.save(course);  // Persist the relationship
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
   * Get a course by ID.
   * @param Id The ID of the course to retrieve.
   * @return Topics.
   */
  public Optional<Courses> getByID(int Id) {
    logger.info("Fetching topic by ID {}", Id);

    return courseRepo.findById(Id);
  }

  /**
   * add a course to the database.
   * @param course The course to add.
   */
  public boolean add(Courses course) {
    logger.info("Adding topic {}", course);

    try {
      courseRepo.save(course);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Delete a course by ID.
   * @param id The ID of the course to delete.
   */
  public boolean delete(int id) {
    logger.info("Deleting topic with ID {}", id);

    try {
      courseRepo.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Update a course by ID.
   * @param course The course to update.
   */
  public boolean update(Courses course) {
    int id = course.getCourse_Id();

    boolean success = false;

    try {
      if (courseRepo.existsById(id)) {
        courseRepo.deleteById(id);
        courseRepo.save(course);
        success = true;

        logger.info("Updating topic with ID {}", id);
      } else {
        logger.warn("Topic with ID {} not found for update", id);

        success = false;
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return success;
  }
}
