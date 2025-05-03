package MappeAppWeb.DB.repository.coursesRepository;

import MappeAppWeb.DB.model.CoursesDB.Courses;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Repository interface for accessing Course data in the database.
 * Spring will auto-generate necessary methods.
 */
public interface CoursesRepository extends CrudRepository<Courses, Integer> {

  //List<Courses> findByTopicNameContainingIgnoreCase(String topic);

  //List<Courses> findByProviderNameContainingIgnoreCase(String provider);
}