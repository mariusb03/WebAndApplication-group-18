package SivertMarius.MappeAppWeb.DB.model.CoursesDB;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Courses {
  @Id
  @GeneratedValue
  private int id;

  @ManyToOne
  @JoinColumn(name = "topic_id", referencedColumnName = "topic_id")
  private Topics topic;

  @OneToOne
  @JoinColumn(name = "title_id", referencedColumnName = "title_id")
  private Titles title;

  private int difficulty;

  @OneToOne
  @JoinColumn(name = "sessions_id", referencedColumnName = "sessions_id")
  private CourseSessions session;

  @OneToOne
  @JoinColumn(name = "desc_id", referencedColumnName = "desc_id")
  private Description description;

  //TODO: add relationships to course provider

  /**
   * default constructor for courses
   */
  public Courses() {
  }

  /**
   * create a course
   */
  public Courses(Topics topic, Titles title, int difficulty, CourseSessions session, Description description) {
    this.topic = topic;
    this.title = title;
    this.difficulty = difficulty;
    this.session = session;
    this.description = description;
  }

  /**
   * get the id of the course
   * @return id
   */
  public int getId() {
      return id;
  }

  /**
   * set the id of the course
   * @param id
   */
  public void setId(int id) {
      this.id = id;
  }

  /**
   * get the topic of the course
   *
   * @return topic
   */
  public Topics getTopic() {
      return topic;
  }

  /**
   * set the topic of the course
   * @param topic
   */
  public void setTopic(Topics topic) {
      this.topic = topic;
  }

  /**
   * get the title of the course
   * @return title
   */
  public Titles getTitle() {
      return title;
  }

  /**
   * set the title of the course
   * @param title
   */
  public void setTitle(Titles title) {
      this.title = title;
  }

  /**
   * get the difficulty of the course
   * @return difficulty
   */
  public int getDifficulty() {
      return difficulty;
  }

  /**
   * set the difficulty of the course
   * @param difficulty
   */
  public void setDifficulty(int difficulty) {
      this.difficulty = difficulty;
  }

  /**
   * get the session of the course
   * @return session
   */
  public CourseSessions getSession() {
      return session;
  }

  /**
   * set the session of the course
   * @param session
   */
  public void setSession(CourseSessions session) {
      this.session = session;
  }

  /**
   * get the description of the course
   * @return description
   */
  public Description getDescription() {
      return description;
  }

  /**
   * set the description of the course
   * @param description
   */
  public void setDescription(Description description) {
      this.description = description;
  }
}
