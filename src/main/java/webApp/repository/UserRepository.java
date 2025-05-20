package webApp.repository;

import org.springframework.data.repository.CrudRepository;
import webApp.model.Users;

import java.util.Optional;

public interface UserRepository extends CrudRepository<Users, Integer> {

    Optional<Users> findByNameAndPassword(String name, String password);
}