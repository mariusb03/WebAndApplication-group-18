package mappeappweb.db.repository.coursesRepository;

import mappeappweb.db.model.CoursesDB.Courses;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for accessing Course data in the database.
 * Spring will auto-generate necessary methods.
 */
public interface CoursesRepository extends CrudRepository<Courses, Integer> {

  //List<Courses> findByTopicNameContainingIgnoreCase(String topic);

  //List<Courses> findByProviderNameContainingIgnoreCase(String provider);
}