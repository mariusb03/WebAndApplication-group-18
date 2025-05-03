package MappeAppWeb.DB.repository.coursesRepository;

import MappeAppWeb.DB.model.CoursesDB.Topics;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for accessing Topic data in the database.
 * Spring will auto-generate necessary methods.
 */
public interface TopicRepository extends CrudRepository<Topics, Integer> {
}

