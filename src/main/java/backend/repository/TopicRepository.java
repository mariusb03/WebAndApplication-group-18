package backend.repository;

import org.springframework.data.repository.CrudRepository;
import backend.model.Topics;

/**
 * Repository interface for accessing Topic data in the database.
 * Spring will auto-generate the necessary methods.
 */
public interface TopicRepository extends CrudRepository<Topics, Integer> {
}