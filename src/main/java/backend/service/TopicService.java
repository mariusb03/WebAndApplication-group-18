package backend.service;

import backend.model.Topics;
import backend.repository.TopicRepository;
import java.util.Optional;
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
   * @param topicToAdd the topic to add.
   */
  public boolean add(Topics topicToAdd) {
    logger.info("Adding topic {}", topicToAdd);

    boolean success = false;
    if (topicToAdd.isValid(topicToAdd)) {
      try {
        repository.save(topicToAdd);
        success = true;
        logger.info("Topic added successfully");
      } catch (Exception e) {
        logger.error("Error adding topic: {}", e.getMessage());
      }
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

    boolean success = false;
    if (repository.existsById(id)) {
      try {
        repository.deleteById(id);
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
        logger.info("Updated topic with ID {}", id);
        return true;
      } else {
        logger.error("Failed to update topic with ID {}", id);
        return false;
      }
    }).orElse(false);
  }
}