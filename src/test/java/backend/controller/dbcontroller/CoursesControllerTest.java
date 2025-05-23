package backend.controller.dbcontroller;

import backend.model.Courses;
import backend.service.CoursesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CoursesController.class)
class CoursesControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CoursesService coursesService;

  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    objectMapper = new ObjectMapper();
  }

  @Test
  void testGetAllCourses() throws Exception {
    Courses course = new Courses();
    course.setTitle("Test Course");

    Mockito.when(coursesService.getAll()).thenReturn(Collections.singletonList(course));

    mockMvc.perform(get("/api/courses/getAll")
            .header("userRole", "admin"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(1))
        .andExpect(jsonPath("$[0].name").value("Test Course"));
  }

  @Test
  void testGetCourseById() throws Exception {
    Courses course = new Courses();
    course.setTitle("Test Course");

    Mockito.when(coursesService.getById(1)).thenReturn(Optional.of(course));

    mockMvc.perform(get("/api/courses/getById/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1))
        .andExpect(jsonPath("$.name").value("Test Course"));
  }

  @Test
  void testAddCourse() throws Exception {
    Courses course = new Courses();
    course.setTitle("New Course");

    Mockito.when(coursesService.add(any(Courses.class))).thenReturn(true);

    mockMvc.perform(post("/api/courses/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(course)))
        .andExpect(status().isOk())
        .andExpect(content().string("Course added"));
  }

  @Test
  void testDeleteCourse() throws Exception {
    Mockito.when(coursesService.delete(1)).thenReturn(true);

    mockMvc.perform(delete("/api/courses/delete/1"))
        .andExpect(status().isOk())
        .andExpect(content().string("Course deleted"));
  }

  @Test
  void testToggleCourseVisibility() throws Exception {
    Mockito.when(coursesService.toggleCourseVisibility(1)).thenReturn(true);

    mockMvc.perform(put("/api/courses/1/toggleVisibility"))
        .andExpect(status().isOk())
        .andExpect(content().string("Visibility toggled."));
  }
}