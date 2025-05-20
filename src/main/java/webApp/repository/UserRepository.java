package webApp.repository;

import org.springframework.data.repository.CrudRepository;
import webApp.model.Topics;

public interface UserRepository extends CrudRepository<Topics, Integer> {
}
