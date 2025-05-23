package backend.repository;

import backend.model.Topics;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class TopicRepositoryTest {

  @Autowired
  private TopicRepository topicRepository;

  @Test
  void testSaveAndFindById() {
    Topics topic = new Topics();
    topic.setTopicName("Artificial Intelligence");

    Topics savedTopic = topicRepository.save(topic);

    Optional<Topics> foundTopic = topicRepository.findById(savedTopic.getId());

    assertTrue(foundTopic.isPresent());
    assertEquals("Artificial Intelligence", foundTopic.get().getTopicName());
  }

  @Test
  void testDeleteById() {
    Topics topic = new Topics();
    topic.setTopicName("Machine Learning");

    Topics savedTopic = topicRepository.save(topic);

    topicRepository.deleteById(savedTopic.getId());

    Optional<Topics> foundTopic = topicRepository.findById(savedTopic.getId());
    assertFalse(foundTopic.isPresent());
  }

  @Test
  void testFindAll() {
    Topics topic1 = new Topics();
    topic1.setTopicName("Data Science");

    Topics topic2 = new Topics();
    topic2.setTopicName("Cybersecurity");

    topicRepository.save(topic1);
    topicRepository.save(topic2);

    Iterable<Topics> topics = topicRepository.findAll();

    assertNotNull(topics);
    assertTrue(topics.iterator().hasNext());
  }
}