package backend.controller.dbcontroller;

import backend.model.Providers;
import backend.service.ProviderService;
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

@WebMvcTest(ProviderController.class)
class ProviderControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private ProviderService providerService;

  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    objectMapper = new ObjectMapper();
  }

  @Test
  void testGetAllProviders() throws Exception {
    Providers provider = new Providers();;
    provider.setName("Test Provider");

    Mockito.when(providerService.getAll()).thenReturn(Collections.singletonList(provider));

    mockMvc.perform(get("/api/providers/getAll"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(1))
        .andExpect(jsonPath("$[0].name").value("Test Provider"));
  }

  @Test
  void testGetProviderById() throws Exception {
    Providers provider = new Providers();
    provider.setName("Test Provider");

    Mockito.when(providerService.getById(1)).thenReturn(Optional.of(provider));

    mockMvc.perform(get("/api/providers/getById/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.name").value("Test Provider"));
  }

  @Test
  void testAddProvider() throws Exception {
    Providers provider = new Providers();
    provider.setName("New Provider");

    Mockito.when(providerService.add(any(Providers.class))).thenReturn(true);

    mockMvc.perform(post("/api/providers/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(provider)))
        .andExpect(status().isOk());
  }

  @Test
  void testDeleteProvider() throws Exception {
    Mockito.when(providerService.delete(1)).thenReturn(true);

    mockMvc.perform(delete("/api/providers/delete/1"))
        .andExpect(status().isOk());
  }

  @Test
  void testUpdateProvider() throws Exception {
    Providers provider = new Providers();
    provider.setName("Updated Provider");

    Mockito.when(providerService.update(any(Providers.class), eq(1))).thenReturn(true);

    mockMvc.perform(put("/api/providers/update/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(provider)))
        .andExpect(status().isOk());
  }
}