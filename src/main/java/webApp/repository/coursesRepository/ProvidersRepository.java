package webApp.repository.coursesRepository;

import webApp.model.coursesdb.Providers;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for accessing Providers data in the database.
 * Spring will auto-generate the necessary methods.
 */
public interface ProvidersRepository extends CrudRepository<Providers, Integer> {

}
