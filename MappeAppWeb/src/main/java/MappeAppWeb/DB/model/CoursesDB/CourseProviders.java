package MappeAppWeb.DB.model.CoursesDB;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity class for course providers.
 */
@Entity
public class CourseProviders {
  @Id
  @GeneratedValue
  private int providers_id;
  private String name;

  @ManyToMany
  @JoinTable(
      name = "course_providers_courses",
      joinColumns = @JoinColumn(name = "course_providers_id"),
      inverseJoinColumns = @JoinColumn(name = "courses_id")
  )
  @JsonIgnore
  private Set<Courses> courses = new HashSet<>();

  public CourseProviders() {
  }

  /**
   * Create a course provider.
   *
   * @param course_providers The id of the course provider
   */
  public CourseProviders(int course_providers, String name) {
    this.providers_id = course_providers;
    this.name = name;
  }

  /**
   * Get the id of the course provider.
   *
   * @return The id of the course provider
   */
  public int getProviders_id() {
      return providers_id;
  }

  /**
   * get the name of the course provider
   *
   * @return name
   */
    public String getName() {
      return name;
    }

  /**
   * set the id of the course provider
   *
   * @param id
   */
  public void setProviders_id(int id) {
    this.providers_id = id;
  }

  /**
   * set the name of the course provider
   *
   * @param name
   */
  public void setName(String name) {
    this.name = name;
  }
}