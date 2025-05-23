package backend.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class UsersTest {
  @Test
  public void testDefaultConstructor() {
    Users user = new Users();
    assertNull(user.getUserId());
    assertNull(user.getName());
    assertNull(user.getEmail());
    assertNull(user.getPassword());
    assertNull(user.getRole());
  }

  @Test
  public void testParameterizedConstructorAndGetters() {
    Users user = new Users("Alice", "alice@example.com", "pass123", "ADMIN");

    assertNull(user.getUserId());
    assertEquals("Alice", user.getName());
    assertEquals("alice@example.com", user.getEmail());
    assertEquals("pass123", user.getPassword());
    assertEquals("ADMIN", user.getRole());
  }

  @Test
  public void testSettersAndGetters() {
    Users user = new Users();

    user.setUserId(10);
    user.setName("Bob");
    user.setEmail("bob@example.com");
    user.setPassword("secret");
    user.setRole("USER");

    assertEquals(10, user.getUserId());
    assertEquals("Bob", user.getName());
    assertEquals("bob@example.com", user.getEmail());
    assertEquals("secret", user.getPassword());
    assertEquals("USER", user.getRole());
  }

  @Test
  public void testIsValidReturnsFalseWhenFieldsAreNull() {
    Users user = new Users();
    assertFalse(user.isValid(user));

    user.setUserId(1);
    assertFalse(user.isValid(user));

    user.setName("Charlie");
    user.setEmail("charlie@example.com");
    user.setPassword("pwd");
    assertFalse(user.isValid(user));
  }

  @Test
  public void testIsValidReturnsTrueWhenAllFieldsSet() {
    Users user = new Users("Dave", "dave@example.com", "pwd", "USER");
    user.setUserId(5);

    assertTrue(user.isValid(user));
  }

  @Test
  public void testIsValidFailsWhenFieldsMissing() {
    Users user = new Users();
    user.setUserId(null);
    user.setName(null);
    user.setEmail("test@example.com");
    user.setPassword("password");
    user.setRole("USER");

    assertFalse(user.isValid(user), "User should be invalid when userId or name is null");
  }
}
