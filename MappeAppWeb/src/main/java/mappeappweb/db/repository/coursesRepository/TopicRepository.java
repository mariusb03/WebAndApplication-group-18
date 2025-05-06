package mappeappweb.db.repository.coursesRepository;

import mappeappweb.db.model.CoursesDB.Topics;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for accessing Topic data in the database.
 * Spring will auto-generate necessary methods.
 */
public interface TopicRepository extends CrudRepository<Topics, Integer> {

  Optional<Topics> findByTopicName(String topicName);

  List<Topics> findByTopicNameIn(List<String> names);

}

