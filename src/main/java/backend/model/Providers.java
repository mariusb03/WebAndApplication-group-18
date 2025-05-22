package backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity class for course providers.
 */
@Entity
public class Providers {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer providersId;
  private String name;

  @ManyToMany(mappedBy = "providers")
  private Set<Courses> courses = new HashSet<>();

  /**
   * default constructor for course providers.
   */
  public Providers() {
  }

  /**
   * Create a course provider.
   *
   * @param providers The id of the course provider
   */
  public Providers(Integer providers, String name) {
    this.providersId = providers;
    this.name = name;
  }

  /**
   * Get the id of the course provider.
   *
   * @return The id of the course provider.
   */
  public Integer getProvidersId() {
    return providersId;
  }

  /**
   * get the name of the course provider.
   *
   * @return name of the course provider.
   */
  public String getName() {
    return name;
  }

  /**
   * set the name of the course provider.
   *
   * @param name course providers name.
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * check if is a valid course provider.
   * checks if the name is not null, empty, String and is not blank.
   */
  public boolean isValid(Providers provider) {
    return name != null && !name.isEmpty() && !name.isBlank();
  }
}