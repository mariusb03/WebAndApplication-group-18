package backend.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SecurityConfigTest {

  @Autowired
  private SecurityFilterChain securityFilterChain;

  @Autowired
  private UserDetailsService userDetailsService;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Test
  void testSecurityFilterChainNotNull() {
    assertNotNull(securityFilterChain, "SecurityFilterChain should not be null");
  }

  @Test
  void testUserDetailsServiceLoadsUsers() {
    UserDetails user = userDetailsService.loadUserByUsername("user");
    assertNotNull(user, "User 'user' should be loaded");
    assertEquals("user", user.getUsername());
    assertTrue(passwordEncoder.matches("password", user.getPassword()));

    UserDetails admin = userDetailsService.loadUserByUsername("admin");
    assertNotNull(admin, "User 'admin' should be loaded");
    assertEquals("admin", admin.getUsername());
    assertTrue(passwordEncoder.matches("adminpassword", admin.getPassword()));
  }

  @Test
  void testPasswordEncoder() {
    String rawPassword = "testPassword";
    String encodedPassword = passwordEncoder.encode(rawPassword);

    assertNotNull(encodedPassword, "Encoded password should not be null");
    assertTrue(passwordEncoder.matches(rawPassword, encodedPassword), "Password should match after encoding");
  }
}