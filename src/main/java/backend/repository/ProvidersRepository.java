package backend.repository;

import org.springframework.data.repository.CrudRepository;
import backend.model.Providers;

/**
 * Repository interface for accessing Providers data in the database.
 * Spring will auto-generate the necessary methods.
 */
public interface ProvidersRepository extends CrudRepository<Providers, Integer> {
}