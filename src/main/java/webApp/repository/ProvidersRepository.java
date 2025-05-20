package webApp.repository;

import webApp.model.Providers;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository interface for accessing Providers data in the database.
 * Spring will auto-generate the necessary methods.
 */
public interface ProvidersRepository extends CrudRepository<Providers, Integer> {
}
