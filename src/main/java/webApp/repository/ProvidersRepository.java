package webApp.repository;

import org.springframework.data.repository.CrudRepository;
import webApp.model.Providers;

/**
 * Repository interface for accessing Providers data in the database.
 * Spring will auto-generate the necessary methods.
 */
public interface ProvidersRepository extends CrudRepository<Providers, Integer> {
}