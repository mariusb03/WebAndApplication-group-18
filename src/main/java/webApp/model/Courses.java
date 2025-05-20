package webApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;
import webApp.service.UserService;

/**
 * Entity class for courses.
 */
@Entity
public class Courses {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer courseId;
  private String title;
  private String difficulty;
  private String session;
  private Double size;
  private int hoursPerWeek;
  private String relatedCourses;
  private int price;

  @Column(length = 10000)
  private String description;

  private String category;

  @ManyToMany
  @JoinTable(
          name = "course_topics",
          joinColumns = @JoinColumn(name = "course_id"),
          inverseJoinColumns = @JoinColumn(name = "topic_id")
  )
  @JsonIgnore
  private Set<Topics> topics = new HashSet<>();

  @ManyToMany
  @JoinTable(
          name = "course_providers",
          joinColumns = @JoinColumn(name = "courses_id"),
          inverseJoinColumns = @JoinColumn(name = "providers_id")
  )
  @JsonIgnore
  private Set<Providers> providers = new HashSet<>();

  @ManyToMany
  @JoinTable(
          name = "course_user",
          joinColumns = @JoinColumn(name = "course_id"),
          inverseJoinColumns = @JoinColumn(name = "user_id")
  )
  @JsonIgnore
  private Set<Users> users = new HashSet<>();

  /**
   * default constructor for courses.
   */
  public Courses() {
  }

  /**
   * create a course.
   */
  public Courses(String title, String level, String session, double size,
                 int hoursPerWeek, String relatedCourses, int price,
                 String description, String category) {
    this.title = title;
    this.difficulty = level;
    this.session = session;
    this.size = size;
    this.hoursPerWeek = hoursPerWeek;
    this.relatedCourses = relatedCourses;
    this.price = price;
    this.description = description;
    this.category = category;
  }

  /**
   * get the id of the course.
   *
   * @return id of course.
   */
  public Integer getCourseId() {
    return this.courseId;
  }

  /**
   * get the title of the course.
   *
   * @return title of the course.
   */
  public String getTitle() {
    return this.title;
  }

  /**
   * get the difficulty of the course.
   *
   * @return difficulty
   */
  public String getDifficulty() {
    return this.difficulty;
  }

  /**
   * get the session of the course.
   *
   * @return session
   */
  public String getSession() {
    return this.session;
  }

  /**
   * get the size of the course.
   *
   * @return size.
   */
  public double getSize() {
    return this.size;
  }

  /**
   * get the hours per week of the course.
   *
   * @return hoursPerWeek.
   */
  public int getHoursPerWeek() {
    return this.hoursPerWeek;
  }

  /**
   * get the related courses of the course.
   *
   * @return relatedCourses.
   */
  public String getRelatedCourses() {
    return this.relatedCourses;
  }

  /**
   * get the price of the course.
   *
   * @return price.
   */
  public int getPrice() {
    return this.price;
  }

  /**
   * get the description of the course.
   *
   * @return description.
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * get the category of the course.
   */
  public String getCategory() {
    return this.category;
  }

  /**
   * get the course providers of the course.
   *
   * @return courseProviders.
   */
  public Set<Providers> getProviders() {
    return this.providers;
  }

  /**
   * get the topics of the course.
   *
   * @return topics.
   */
  public Set<Topics> getTopics() {
    return this.topics;
  }

  /**
   * get the users of the course.
   *
   * @return users.
   */
  public Set<Users> getUsers() {
    return this.users;
  }

  /**
   * set the id of the course.
   *
   * @param courseId of the course.
   */
  public void setCourseId(Integer courseId) {
    this.courseId = courseId;
  }

  /**
   * set the title of the course.
   *
   * @param title of the course.
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * set the difficulty of the course.
   *
   * @param difficulty of the course.
   */
  public void setDifficulty(String difficulty) {
    this.difficulty = difficulty;
  }

  /**
   * set the session of the course.
   *
   * @param session of the course.
   */
  public void setSession(String session) {
    this.session = session;
  }

  /**
   * set size of the course.
   *
   * @param size of the course.
   */
  public void setSize(Double size) {
    this.size = size;
  }

  /**
   * set the hours per week of the course.
   *
   * @param hoursPerWeek of the course.
   */
  public void setHoursPerWeek(int hoursPerWeek) {
    this.hoursPerWeek = hoursPerWeek;
  }

  /**
   * set the related courses of the course.
   *
   * @param relatedCourses of the course.
   */
  public void setRelatedCourses(String relatedCourses) {
    this.relatedCourses = relatedCourses;
  }

  /**
   * set the price of the course.
   *
   * @param price of the course.
   */
  public void setPrice(int price) {
    this.price = price;
  }

  /**
   * set the description of the course.
   *
   * @param description of the course.
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * set the category of the course.
   */
  public void setCategory(String category) {
    this.category = category;
  }

  /**
   * set the hours per week of the course.
   *
   * @param topics of the course.
   */
  public void setTopics(Set<Topics> topics) {
    this.topics = topics;
  }

  /**
   * set the course providers of the course.
   *
   * @param providers of the course.
   */
  public void setProviders(Set<Providers> providers) {
    this.providers = providers;
  }

  /**
   * set the users of the course.
   *
   * @param user of the course.
   */
  public void setUsers(Set<Users> user) {
    this.users = user;
  }

  /**
   * checks if the course is valid.
   *
   * @return true if valid, false if not.
   */
  @JsonIgnore
  public boolean isValid() {
    return  !"".equals(title) && !"".equals(difficulty)
            && !"".equals(session) && (size == 0 || size > 0) && (hoursPerWeek == 0 || hoursPerWeek > 0)
            && (price == 0 || price > 0) && !"".equals(relatedCourses) && !"".equals(description)
            && (category != null || !"".equals(category));
  }
}