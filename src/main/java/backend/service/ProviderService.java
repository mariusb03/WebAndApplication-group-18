package backend.service;

import backend.model.Providers;
import backend.repository.ProvidersRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing providers.
 * This class contains methods to perform CRUD operations on providers.
 */
@Service
public class ProviderService {
  private final Logger logger = LoggerFactory.getLogger("ProviderService");

  @Autowired
  private ProvidersRepository repository;

  /**
   * Get all providers.
   *
   * @return All Topics.
   */
  public Iterable<Providers> getAll() {
    logger.info("Fetching all providers");

    return repository.findAll();
  }

  /**
   * Get a provider by ID.
   *
   * @param id the ID of the provider to fetch.
   * @return providers.
   */
  public Optional<Providers> getById(int id) {
    logger.info("Fetching topic by ID {}", id);

    return repository.findById(id);
  }

  /**
   * Add a new provider.
   *
   * @param providers the provider to add.
   * @return true if the addition was successful, false otherwise.
   */
  public boolean add(Providers providers) {
    logger.info("Adding provider {}", providers);

    boolean success = false;
    if (providers.isValid(providers)) {
      try {
        repository.save(providers);
        success = true;
        logger.info("Provider added successfully");
      } catch (Exception e) {
        logger.error("Error adding provider: {}", e.getMessage());
      }
    }
    return success;
  }

  /**
   * Delete a provider by ID.
   *
   * @param id the ID of the provider to delete.
   * @return true if the deletion was successful, false otherwise.
   */
  public boolean delete(int id) {
    logger.info("Deleting providers with ID {}", id);

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
   * Update a provider.
   *
   * @param providers the provider to update.
   * @return true if the update was successful, false otherwise.
   */
  public boolean update(Providers providers, Integer id) {
    return repository.findById(id).map(existing -> {
      if (providers.isValid(existing)) {
        existing.setName(providers.getName());
        repository.save(existing);
        logger.info("Updated provider with ID {}", id);
        return true;
      } else {
        logger.error("Invalid provider data");
        return false;
      }
    }).orElse(false);
  }
}