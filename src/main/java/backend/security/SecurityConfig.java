package backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration class for the Spring Boot application.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

  /**
   * Configures the security filter chain for the application.
   *
   * @param http The HttpSecurity object to configure.
   * @return The configured SecurityFilterChain.
   * @throws Exception If an error occurs during configuration.
   */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(requests -> requests.anyRequest().permitAll())
        .securityContext(securityContext -> securityContext.requireExplicitSave(false))
        .csrf(csrf -> csrf.disable())
        .headers(headers -> headers
            .contentSecurityPolicy(csp -> csp.policyDirectives(
                "default-src 'self'; "
                    + "script-src 'self' https://maps.googleapis.com https://maps.gstatic.com; "
                    + "style-src 'self' 'unsafe-inline' https://fonts.googleapis.com; "
                    + "img-src 'self' data: https://maps.gstatic.com https://maps.googleapis.com; "
                    + "font-src 'self' https://fonts.gstatic.com; "
                    + "frame-src https://www.google.com https://maps.googleapis.com; "
                    + "connect-src 'self' http://129.241.236.99:8082;"
            ))
            .frameOptions(frameOptions -> frameOptions.deny())
            .httpStrictTransportSecurity(hsts -> hsts
                .includeSubDomains(true)
                .maxAgeInSeconds(31536000)
            )
        );

    return http.build();
  }

  /**
   * Configures the in-memory user details service with two users: "user" and "admin".
   *
   * @return The configured UserDetailsService.
   */
  @Bean
  public UserDetailsService userDetailsService() {
    InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    manager.createUser(User.withUsername("user")
        .password(passwordEncoder().encode("password"))
        .roles("USER")
        .build());
    manager.createUser(User.withUsername("admin")
        .password(passwordEncoder().encode("adminpassword"))
        .roles("ADMIN")
        .build());
    return manager;
  }

  /**
   * Configures the password encoder for the application.
   *
   * @return The configured PasswordEncoder.
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}