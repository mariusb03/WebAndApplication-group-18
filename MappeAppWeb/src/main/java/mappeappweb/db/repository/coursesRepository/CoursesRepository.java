package mappeappweb.db.repository.coursesRepository;

import mappeappweb.db.model.coursesdb.Courses;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for accessing Course data in the database.
 * Spring will auto-generate necessary methods.
 */
public interface CoursesRepository extends CrudRepository<Courses, Integer> {
}