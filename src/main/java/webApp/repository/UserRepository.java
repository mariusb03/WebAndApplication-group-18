package webApp.repository;

import org.springframework.data.repository.CrudRepository;
import webApp.model.Users;

public interface UserRepository extends CrudRepository<Users, Integer> {
}
