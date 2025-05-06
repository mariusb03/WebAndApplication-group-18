package mappeappweb.db.repository.coursesRepository;

import mappeappweb.db.model.CoursesDB.Providers;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for accessing Providers data in the database.
 * Spring will auto-generate necessary methods.
 */
public interface ProvidersRepository extends CrudRepository<Providers, Integer> {

}
