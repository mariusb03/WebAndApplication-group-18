package backend.controller.dbcontroller;

import backend.model.Topics;
import backend.service.TopicService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TopicsController.class)
class TopicsControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TopicService topicService;

  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    objectMapper = new ObjectMapper();
  }

  @Test
  void testGetAllTopics() throws Exception {
    Mockito.when(topicService.getAll()).thenReturn(Collections.emptyList());

    mockMvc.perform(get("/api/topics/getAll"))
        .andExpect(status().isOk());
  }

  @Test
  void testGetTopicById() throws Exception {
    Topics topic = new Topics();
    topic.setTopicName("Sample Topic");

    Mockito.when(topicService.getById(1)).thenReturn(Optional.of(topic));

    mockMvc.perform(get("/api/topics/getById/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Sample Topic"));
  }

  @Test
  void testAddTopic() throws Exception {
    Topics topic = new Topics();
    topic.setTopicName("New Topic");

    Mockito.when(topicService.add(any(Topics.class))).thenReturn(true);

    mockMvc.perform(post("/api/topics/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(topic)))
        .andExpect(status().isOk());
  }

  @Test
  void testDeleteTopic() throws Exception {
    Mockito.when(topicService.delete(1)).thenReturn(true);

    mockMvc.perform(delete("/api/topics/delete/1"))
        .andExpect(status().isOk());
  }

  @Test
  void testUpdateTopic() throws Exception {
    Topics topic = new Topics();
    topic.setTopicName("Updated Topic");

    Mockito.when(topicService.update(any(Topics.class), eq(1))).thenReturn(true);

    mockMvc.perform(put("/api/topics/update/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(topic)))
        .andExpect(status().isOk());
  }
}