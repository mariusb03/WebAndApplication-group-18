package MappeAppWeb.DB.model.CoursesDB;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity class for courses.
 */
@Entity
public class Courses {
  @Id
  @GeneratedValue
  private Integer Id;
  private String title;
  private String difficulty;
  private String session;
  private double size;
  private int hoursPerWeek;
  private String relatedCourses;
  private int price;

  @Column(length = 10000)
  private String description;

  private String category;

  @ManyToMany(mappedBy = "courses")
  private Set<Topics> topics = new HashSet<>();

  @ManyToMany(mappedBy = "courses")
  private Set<Providers> providers = new HashSet<>();

  /**
   * default constructor for courses
   */
  public Courses() {
  }

  /**
   * create a course
   */
  public Courses( String title, String level, String session, double size,
                 int hoursPerWeek, String relatedCourses, int price, String description, String Category) {
    this.title = title;
    this.difficulty = level;
    this.session = session;
    this.size = size;
    this.hoursPerWeek = hoursPerWeek;
    this.relatedCourses = relatedCourses;
    this.price = price;
    this.description = description;
    this.category = Category;
  }

  /**
   * get the id of the course
   * @return id
   */
  public int getId() {
      return Id;
  }

  /**
   * get the title of the course
   * @return title
   */
  public String getTitle() {
    return title;
  }

  /**
   * get the difficulty of the course
   * @return difficulty
   */
  public String getDifficulty() {
    return difficulty;
  }

  /**
   * get the session of the course
   * @return session
   */
  public String getSession() {
    return session;
  }

  /**
   * get the size of the course
   * @return size
   */
  public double getSize() {
      return size;
  }

  /**
   * get the description of the course
   * @return description
   */
  public String getDescription() {
    return description;
  }

  /**
   * get the course providers of the course
   * @return courseProviders
   */
  public Set<Providers> getProviders() {
      return providers;
  }

  /**
   * set the id of the course
   * @param id
   */
  public void setId(int id) {
      this.Id = id;
  }

  /**
   * set the title of the course
   * @param title
   */
  public void setTitle(String title) {
      this.title = title;
  }

  /**
   * set the difficulty of the course
   * @param difficulty
   */
  public void setDifficulty(String difficulty) {
      this.difficulty = difficulty;
  }

  /**
   * set the session of the course
   * @param session
   */
  public void setSession(String session) {
      this.session = session;
  }

  /**
   * set the description of the course
   * @param description
   */
  public void setDescription(String description) {
      this.description = description;
  }

  /**
   * set the size of the course
   * @param size
   */
  public void setSize(double size) {
    this.size = size;
  }

  /**
   * set the hours per week of the course
   * @param topics
   */
  public void setTopics(Set<Topics> topics) {
    this.topics = topics;
  }

  /**
   * set the course providers of the course
   * @param providers
   */
  public void setProviders(Set<Providers> providers) {
    this.providers = providers;
  }

  /**
   * set topics of the course
   * @param topics
   */
  /**
  public void addTopics(Topics topics) {
    this.topics.add(topics);
    topics.getRelatedCourses().add(this);
  }
   */

  /**
   * checks if the course is valid
   * @return true if valid, false if not
   */
  @JsonIgnore
  public boolean isValid() {
    return (Id == 0 || Id > 0) && !"".equals(title) && !"".equals(difficulty)
        && !"".equals(session) && (size == 0 || size > 0) && (hoursPerWeek == 0 || hoursPerWeek > 0)
        && (price == 0 || price > 0) && !"".equals(relatedCourses) && !"".equals(description);
  }
}
