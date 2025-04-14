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
 * Entity class for topics.
 */
@Entity
public class Topics {
  @Id
  @GeneratedValue
  private Integer topic_id;
  private String topic;

  @ManyToMany
  @JoinTable(
      name = "course_topics",
      joinColumns = @JoinColumn(name = "topic_id"),
      inverseJoinColumns = @JoinColumn(name = "courses_id")
  )
  @JsonIgnore
  private Set<Courses> courses = new HashSet<>();

  public Topics() {}

  /**
   * Create a topic.
   *
   * @param topic    The name of the topic
   */
  public Topics( int topicID, String topic) {
    this.topic_id = topic_id;
    this.topic = topic;
  }

  /**
   * Get the id of the topic.
   * @return topic_id
   */
  public Integer getTopic_id() {
    return topic_id;
  }

  /**
   * Set the id of the topic.
   * @param topic_id The id of the topic
   */
  public void setTopic_id(Integer topic_id) {
    this.topic_id = topic_id;
  }

  /**
   * Get the name of the topic.
   * @return topic
   */
  public String getTopic() {
    return topic;
  }

  /**
   * Set the name of the topic.
   * @param topic The name of the topic
   */
  public void setTopic(String topic) {
    this.topic = topic;
  }

  /**
   * Get the courses that are related to this topic.
   * @return courses
   */
  public Set<Courses> getCourses() {
    return courses;
  }

  /**
   * Set the courses that are related to this topic.
   * @param courses The courses that are related to this topic
   */
  public void setCourses(Set<Courses> courses) {
    this.courses = courses;
  }
}
