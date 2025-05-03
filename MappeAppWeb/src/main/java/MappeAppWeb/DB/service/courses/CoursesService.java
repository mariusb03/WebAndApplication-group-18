package MappeAppWeb.DB.service.courses;

import MappeAppWeb.DB.model.CoursesDB.Courses;
import MappeAppWeb.DB.model.CoursesDB.Topics;
import MappeAppWeb.DB.repository.coursesRepository.CoursesRepository;
import MappeAppWeb.DB.repository.coursesRepository.TopicRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CoursesService {
  private final Logger logger = LoggerFactory.getLogger("CoursesService");
  @Autowired
  private CoursesRepository repository;

  @Autowired
  private TopicRepository topicRepository;

  @Transactional
  public void linkCourseToTopic(int courseId, int topicId) {
    Courses course = coursesRepository.findById(courseId)
        .orElseThrow(() -> new IllegalArgumentException("Course not found"));
    Topics topic = topicRepository.findById(topicId)
        .orElseThrow(() -> new IllegalArgumentException("Topic not found"));

    course.getTopics().add(topic);
    //topic.getRelatedCourses().add(course);

    coursesRepository.save(course);
  }

  /**
   * Get all Courses
   *
   * @return All Courses
   */
  public Iterable<Courses> getAll() {
    logger.info("Fetching all topics");

    return repository.findAll();
  }

  /**
   * Get a course by ID
   * @param Id
   * @return Topics
   */
  public Optional<Courses> getByID(int Id) {
    logger.info("Fetching topic by ID {}", Id);

    return repository.findById(Id);
  }

  /**
   * add a course to the database
   * @param course
   */
  public boolean add(Courses course) {
    logger.info("Adding topic {}", course);

    try {
      repository.save(course);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Delete a course by ID
   * @param id
   */
  public boolean delete(int id) {
    logger.info("Deleting topic with ID {}", id);

    try {
      repository.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Update a course by ID
   * @param course
   */
  public boolean update(Courses course) {
    int id = course.getId();

    boolean success = false;

    try {
      if (repository.existsById(id)) {
        repository.deleteById(id);
        repository.save(course);
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
