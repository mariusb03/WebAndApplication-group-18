package webapp.service;


import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import webapp.model.Users;
import webapp.repository.UserRepository;

/**
 * Service class to load user details from the database.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepo;

  /**
   * Constructor for CustomUserDetailsService.
   *
   * @param userRepo the user repository
   */
  public CustomUserDetailsService(UserRepository userRepo) {
    this.userRepo = userRepo;
  }

  /**
   * Load user by username.
   *
   * @param username the username
   * @throws UsernameNotFoundException if user not found
   */
  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Users appUser = userRepo.findById(Integer.valueOf(username))
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    return User.builder()
      .username(appUser.getName())
      .password(appUser.getPassword())
      .roles(appUser.getRole())
      .build();
  }
}
