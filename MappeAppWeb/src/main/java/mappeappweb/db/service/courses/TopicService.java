package mappeappweb.db.service.courses;

import mappeappweb.db.model.CoursesDB.Topics;
import mappeappweb.db.repository.coursesRepository.TopicRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicService {
  private final Logger logger = LoggerFactory.getLogger("TopicService");

  @Autowired
  private TopicRepository repository;

  /**
   * Get all topics
   *
   * @return All Topics
   */
  public Iterable<Topics> getAll() {
    logger.info("Fetching all topics");

    return repository.findAll();
  }

  /**
   * Get a topic by ID
   * @param ID
   * @return Topics
   */
  public Optional<Topics> getByID(int ID) {
    logger.info("Fetching topic by ID {}", ID);

    return repository.findById(ID);
  }

  /**
   * add a topic to the database
   * @param topic
   */
  public boolean add(Topics topic) {
    logger.info("Adding topic {}", topic);

    try {
      repository.save(topic);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Delete a topic by ID
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
   * Update a topic by ID
   * @param topics
   */
  public boolean update(Topics topics) {
    int id = topics.getID();

    boolean success = false;

    try {
      if (repository.existsById(id)) {
        repository.deleteById(id);
        repository.save(topics);
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
