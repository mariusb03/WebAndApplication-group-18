package backend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ForwardController.class)
class ForwardControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  void testRedirectToIndexHtml() throws Exception {
    mockMvc.perform(get("/somePath"))
        .andExpect(status().isOk())
        .andExpect(forwardedUrl("/index.html"));

    mockMvc.perform(get("/nested/path"))
        .andExpect(status().isOk())
        .andExpect(forwardedUrl("/index.html"));
  }

  @Test
  void testIgnoreFileExtensions() throws Exception {
    mockMvc.perform(get("/file.js"))
        .andExpect(status().isNotFound());

    mockMvc.perform(get("/styles/file.css"))
        .andExpect(status().isNotFound());
  }
}