package webapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity class for topics.
 */
@Entity
public class Topics {
  @Id
  @GeneratedValue (strategy = GenerationType.IDENTITY)
  private Integer topicId;
  private String topicName;

  @ManyToMany(mappedBy = "topics")
  private Set<Courses> courses = new HashSet<>();

  /**
   * default constructor for topics.
   */
  public Topics() {}

  /**
   * Create a topic.
   *
   * @param topic The name of the topic
   */
  public Topics(int topicId, String topic) {
    this.topicId = topicId;
    this.topicName = topic;
  }

  /**
   * Get the id of the topic.
   *
   * @return topic_id.
   */
  public Integer getId() {
    return topicId;
  }

  /**
   * Set the id of the topic.
   *
   * @param topicId The id of the topic
   */
  public void setId(Integer topicId) {
    this.topicId = topicId;
  }

  /**
   * Get the name of the topic.
   *
   * @return topic.
   */
  public String getTopicName() {
    return topicName;
  }

  /**
   * Set the name of the topic.
   *
   * @param topic The name of the topic
   */
  public void setTopicName(String topic) {
    this.topicName = topic;
  }

  /**
   * method for checking if the topic is valid.
   */
  public boolean isValid(Topics topic) {
    return topicName != null && !topicName.isEmpty() && !topicName.isBlank();
  }
}