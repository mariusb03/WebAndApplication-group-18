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
  private Integer topicID;
  private String topicName;

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
    this.topicID = topicID;
    this.topicName = topic;
  }

  /**
   * Get the id of the topic.
   * @return topic_id
   */
  public Integer getID() {
    return topicID;
  }

  /**
   * Set the id of the topic.
   * @param topic_id The id of the topic
   */
  public void setID(Integer topic_id) {
    this.topicID = topic_id;
  }

  /**
   * Get the name of the topic.
   * @return topic
   */
  public String getTopicName() {
    return topicName;
  }

  /**
   * Set the name of the topic.
   * @param topic The name of the topic
   */
  public void setTopicName(String topic) {
    this.topicName = topic;
  }

  /**
   * Set the courses that are related to this topic.
   * @param courses The courses that are related to this topic
   */
  public void setRelatedCourses(Set<Courses> courses) {
    this.courses = courses;
  }
}
