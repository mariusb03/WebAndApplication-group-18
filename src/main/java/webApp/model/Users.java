package webApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity class for users.
 */
@Entity
public class Users {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer userId;
  private String name;
  private String email;
  private String password;
  private String role;

  @ManyToMany(mappedBy = "Users")
  private Set<Courses> courses = new HashSet<>();

  /**
   * default constructor for users.
   */
  public Users() {
  }

  /**
   * Create a user.
   *
   * @param userId The id of the user
   */
  public Users(Integer userId, String firstName, String email,
               String password, String role) {
    this.userId = userId;
    this.name = firstName;
    this.email = email;
    this.password = password;
    this.role = role;
  }

  /**
   * Get the id of the user.
   *
   * @return userId.
   */
  public Integer getUserId() {
    return userId;
  }

  /**
   * get the first name of the user.
   *
   * @return first name of the user.
   */
  public String getName() {
    return name;
  }

  /**
   * get the email of the user.
   *
   * @return email of the user.
   */
  public String getEmail() {
    return email;
  }

  /**
   * get the password of the user.
   *
   * @return password of the user.
   */
  public String getPassword() {
    return password;
  }

  /**
   * get the role of the user.
   *
   * @return role of the user.
   */
  public String getRole() {
    return role;
  }

  /**
   * set the id of the user.
   *
   * @param userId The id of the user
   */
  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  /**
   * ser name of the user.
   *
   * @param name The name of the user
   */
  public void setName(String name) {
    this.name = name;
  }


  /**
   * set the email of the user.
   *
   * @param email The email of the user
   */
  public void setEmail(String email) {
    this.email = email;
  }

  /**
   * set the password of the user.
   *
   * @param password The password of the user
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * set the role of the user.
   *
   * @param role The role of the user
   */
  public void setRole(String role) {
    this.role = role;
  }

  /**
   * check if is a valid user.
   *
   * @return true if the user is valid.
   */
  public boolean isValid(Users userToAdd) {
    return this.userId != null && this.name != null && this.email != null
        && this.password != null && this.role != null;
  }
}
