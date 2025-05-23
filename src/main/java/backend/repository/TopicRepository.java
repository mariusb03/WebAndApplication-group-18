package backend.repository;

import backend.model.Topics;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for accessing Topic data in the database.
 * Spring will auto-generate the necessary methods.
 */
public interface TopicRepository extends CrudRepository<Topics, Integer> {
}