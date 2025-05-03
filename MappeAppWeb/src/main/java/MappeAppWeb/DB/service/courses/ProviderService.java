package MappeAppWeb.DB.service.courses;

import MappeAppWeb.DB.model.CoursesDB.Providers;
import MappeAppWeb.DB.repository.coursesRepository.ProvidersRepository;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderService {
  private final Logger logger = LoggerFactory.getLogger("ProviderService");

  @Autowired
  private ProvidersRepository repository;

  /**
   * Get all providers
   *
   * @return All Topics
   */
  public Iterable<Providers> getAll() {
    logger.info("Fetching all providers");

    return repository.findAll();
  }

  /**
   * Get a provider by ID
   *
   * @param Id
   * @return providers
   */
  public Optional<Providers> getByID(int Id) {
    logger.info("Fetching topic by ID {}", Id);

    return repository.findById(Id);
  }

  /**
   * add a providers to the database
   * @param providers
   */
  public boolean add(Providers providers) {
    logger.info("Adding topic {}", providers);

    try {
      repository.save(providers);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Delete a providers by ID
   * @param id
   */
  public boolean delete(int id) {
    logger.info("Deleting providers with ID {}", id);

    try {
      repository.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Update a providers by ID
   * @param providers
   */
  public boolean update(Providers providers) {
    int id = providers.getId();

    boolean success = false;

    try {
      if (repository.existsById(id)) {
        repository.deleteById(id);
        repository.save(providers);
        success = true;

        logger.info("Updating providers with ID {}", id);
      } else {
        logger.warn("providers with ID {} not found for update", id);

        success = false;
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
    return success;
  }
}
