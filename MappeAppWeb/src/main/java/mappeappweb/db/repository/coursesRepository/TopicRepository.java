package mappeappweb.db.repository.coursesRepository;

import mappeappweb.db.model.coursesdb.Topics;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for accessing Topic data in the database.
 * Spring will auto-generate necessary methods.
 */
public interface TopicRepository extends CrudRepository<Topics, Integer> {
}

