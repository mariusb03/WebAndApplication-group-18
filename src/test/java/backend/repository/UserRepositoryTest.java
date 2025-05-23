package backend.repository;

import backend.model.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  void testSaveAndFindById() {
    Users user = new Users();
    user.setName("JohnDoe");
    user.setPassword("password123");

    Users savedUser = userRepository.save(user);

    Optional<Users> foundUser = userRepository.findById(savedUser.getUserId());

    assertTrue(foundUser.isPresent());
    assertEquals("JohnDoe", foundUser.get().getName());
  }

  @Test
  void testFindByNameAndPassword() {
    Users user = new Users();
    user.setName("JaneDoe");
    user.setPassword("securePass");

    userRepository.save(user);

    Optional<Users> foundUser = userRepository.findByNameAndPassword("JaneDoe", "securePass");

    assertTrue(foundUser.isPresent());
    assertEquals("JaneDoe", foundUser.get().getName());
  }

  @Test
  void testDeleteById() {
    Users user = new Users();
    user.setName("TestUser");
    user.setPassword("testPass");

    Users savedUser = userRepository.save(user);

    userRepository.deleteById(savedUser.getUserId());

    Optional<Users> foundUser = userRepository.findById(savedUser.getUserId());
    assertFalse(foundUser.isPresent());
  }

  @Test
  void testFindAll() {
    Users user1 = new Users();
    user1.setName("User1");
    user1.setPassword("pass1");

    Users user2 = new Users();
    user2.setName("User2");
    user2.setPassword("pass2");

    userRepository.save(user1);
    userRepository.save(user2);

    Iterable<Users> users = userRepository.findAll();

    assertNotNull(users);
    assertTrue(users.iterator().hasNext());
  }
}