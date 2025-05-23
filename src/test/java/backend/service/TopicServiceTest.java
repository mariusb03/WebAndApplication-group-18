package backend.service;

import backend.model.Topics;
import backend.repository.TopicRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TopicServiceTest {

  @Mock
  private TopicRepository topicRepository;

  @InjectMocks
  private TopicService topicService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testGetAll() {
    topicService.getAll();
    verify(topicRepository, times(1)).findAll();
  }

  @Test
  void testGetById() {
    int topicId = 1;
    Topics topic = new Topics();
    when(topicRepository.findById(topicId)).thenReturn(Optional.of(topic));

    Optional<Topics> result = topicService.getById(topicId);

    assertTrue(result.isPresent());
    assertEquals(topic, result.get());
    verify(topicRepository, times(1)).findById(topicId);
  }

  @Test
  void testAdd() {
    Topics topic = mock(Topics.class);
    when(topic.isValid(topic)).thenReturn(true);

    boolean result = topicService.add(topic);

    assertTrue(result);
    verify(topicRepository, times(1)).save(topic);
  }

  @Test
  void testDelete() {
    int topicId = 1;
    when(topicRepository.existsById(topicId)).thenReturn(true);

    boolean result = topicService.delete(topicId);

    assertTrue(result);
    verify(topicRepository, times(1)).deleteById(topicId);
  }

  @Test
  void testUpdate() {
    int topicId = 1;
    Topics existingTopic = new Topics();
    Topics updatedTopic = mock(Topics.class);
    when(topicRepository.findById(topicId)).thenReturn(Optional.of(existingTopic));
    when(updatedTopic.isValid(existingTopic)).thenReturn(true);

    boolean result = topicService.update(updatedTopic, topicId);

    assertTrue(result);
    verify(topicRepository, times(1)).save(existingTopic);
  }
}