package mappeappweb.db.service.courses;

import java.util.Optional;
import mappeappweb.db.model.coursesdb.Topics;
import mappeappweb.db.repository.coursesRepository.TopicRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing topics in the database.
 * This class contains business logic related to topics.
 */
@Service
public class TopicService {
  private final Logger logger = LoggerFactory.getLogger("TopicService");

  @Autowired
  private TopicRepository repository;

  /**
   * Get all topics.
   *
   * @return All Topics.
   */
  public Iterable<Topics> getAll() {
    logger.info("Fetching all topics");

    return repository.findAll();
  }

  /**
   * Get a topic by id.
   *
   * @param id id of the topic.
   *
   * @return Topics
   */
  public Optional<Topics> getById(int id) {
    logger.info("Fetching topic by id {}", id);

    return repository.findById(id);
  }

  /**
   * add a topic to the database.
   *
   * @param topic the topic to add.
   */
  public boolean add(Topics topic) {
    logger.info("Adding topic {}", topic);

    boolean success = false;
    if (topic.isValid(topic)) {
      try {
        repository.save(topic);
        success = true;

        logger.info("Added topic {}", topic);
      } catch (Exception ignored) {
        logger.error("Failed to add topic {}", topic);
      }
    } else {
      logger.error("Invalid topic {}", topic);
    }

    return success;
  }

  /**
   * Delete a topic from the database.
   *
   * @param id the id of the topic to delete.
   * @return true if the topic was deleted, false if not.
   */
  public boolean delete(int id) {
    logger.info("Deleting topic with ID {}", id);

    boolean deleted = false;
    try {
      repository.deleteById(id);
      deleted = true;

      logger.info("Deleted topic with ID {}", id);
    } catch (Exception ignored) {
      logger.error("Failed to delete topic with ID {}", id);
    }
    return deleted;
  }


  /**
   * Update a topic in the database.
   *
   * @param id the id of the topic to update.
   * @param topics the topic to update.
   *
   * @return true if the topic was updated, false if not.
   */
  public boolean update(Topics topics, Integer id) {
    logger.info("Updating topic with ID {}", id);

    return repository.findById(id).map(existing -> {
      if (topics.isValid(existing)) {
        existing.setTopicName(topics.getTopicName());
        repository.save(existing);
        logger.info("Updated course with ID {}", id);
        return true;
      } else {
        logger.error("Failed to update course with ID {}", id);
        return false;
      }
    }).orElse(false);
  }
}
