package webApp.repository.coursesRepository;

import webApp.model.coursesdb.Topics;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for accessing Topic data in the database.
 * Spring will auto-generate necessary methods.
 */
public interface TopicRepository extends CrudRepository<Topics, Integer> {
}

