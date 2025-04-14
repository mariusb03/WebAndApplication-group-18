package MappeAppWeb.DB.repository.coursesRepository;

import MappeAppWeb.DB.model.CoursesDB.CourseProviders;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for accessing CourseProviders data in the database.
 * Spring will auto-generate necessary methods.
 */
public interface CourseProvidersRepository extends CrudRepository<CourseProviders, Integer> {
}
