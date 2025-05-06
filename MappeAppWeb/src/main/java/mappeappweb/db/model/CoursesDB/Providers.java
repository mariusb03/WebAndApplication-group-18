package mappeappweb.db.model.CoursesDB;

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
  private Integer providers_id;
  private String name;

  @ManyToMany(mappedBy = "providers")
  private Set<Courses> courses = new HashSet<>();

  public Providers() {
  }

  /**
   * Create a course provider.
   *
   * @param providers The id of the course provider
   */
  public Providers(Integer providers, String name) {
    this.providers_id = providers;
    this.name = name;
  }

  /**
   * Get the id of the course provider.
   *
   * @return The id of the course provider
   */
  public Integer getProviders_id() {
      return providers_id;
  }

  /**
   * get the name of the course provider
   *
   * @return name of the course provider
   */
    public String getName() {
      return name;
    }

  /**
   * set the id of the course provider
   *
   * @param id course providers id
   */
  public void setProviders_id(Integer id) {
    this.providers_id = id;
  }

  /**
   * set the name of the course provider
   *
   * @param name course providers name
   */
  public void setName(String name) {
    this.name = name;
  }
}