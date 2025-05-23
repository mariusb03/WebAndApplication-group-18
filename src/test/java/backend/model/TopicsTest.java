package backend.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TopicsTest {

  @Test
  void testDefaultConstructor() {
    Topics topic = new Topics();
    assertNull(topic.getId());
    assertNull(topic.getTopicName());
  }

  @Test
  void testParameterizedConstructor() {
    Topics topic = new Topics(1, "Java");
    assertEquals(1, topic.getId());
    assertEquals("Java", topic.getTopicName());
  }

  @Test
  void testSetAndGetId() {
    Topics topic = new Topics();
    topic.setId(10);
    assertEquals(10, topic.getId());
  }

  @Test
  void testSetAndGetTopicName() {
    Topics topic = new Topics();
    topic.setTopicName("Spring Boot");
    assertEquals("Spring Boot", topic.getTopicName());
  }

  @Test
  void testIsValid_withValidName() {
    Topics topic = new Topics();
    topic.setTopicName("Security");
    assertTrue(topic.isValid(topic));
  }

  @Test
  void testIsValid_withNullName() {
    Topics topic = new Topics();
    topic.setTopicName(null);
    assertFalse(topic.isValid(topic));
  }

  @Test
  void testIsValid_withEmptyName() {
    Topics topic = new Topics();
    topic.setTopicName("");
    assertFalse(topic.isValid(topic));
  }

  @Test
  void testIsValid_withBlankName() {
    Topics topic = new Topics();
    topic.setTopicName("   ");
    assertFalse(topic.isValid(topic));
  }
}
