package webApp.security;

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
 * Security configuration class.
 * This class configures the security settings for the web application.
 * It defines the security filter chain, user details service, and password encoder.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

  /**
   * Configures the security filter chain for the application.
   *
   * @param http the HttpSecurity object
   * @return the configured SecurityFilterChain
   * @throws Exception if an error occurs during configuration
   */
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests((requests) -> requests
            .anyRequest().permitAll()
      )
        .formLogin((form) -> form.disable()
      )
        .logout((logout) -> logout.disable()
      )
        .csrf((csrf) -> csrf.disable()
      )
        .headers((headers) -> headers
        .contentSecurityPolicy((csp) -> csp
            .policyDirectives("default-src 'self'; script-src 'self'")
        )
            .frameOptions((frame) -> frame.deny())
            .httpStrictTransportSecurity((hsts) -> hsts
            .includeSubDomains(true)
            .maxAgeInSeconds(31536000)
        )
        );

    return http.build();
  }

  /**
   * Configures the user details service for the application.
   *
   * @return the configured UserDetailsService
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
   * @return the configured PasswordEncoder
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}