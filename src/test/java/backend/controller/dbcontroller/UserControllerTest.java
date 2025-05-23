package backend.controller.dbcontroller;

import backend.model.Courses;
import backend.model.Users;
import backend.service.UserService;
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
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;

  private ObjectMapper objectMapper;

  @BeforeEach
  void setUp() {
    objectMapper = new ObjectMapper();
  }

  @Test
  void testGetAllUsers() throws Exception {
    Mockito.when(userService.getAll()).thenReturn(Collections.emptyList());

    mockMvc.perform(get("/user/getAll"))
        .andExpect(status().isOk());
  }

  @Test
  void testGetUserById() throws Exception {
    Users user = new Users();
    user.setUserId(1);
    user.setName("Test User");

    Mockito.when(userService.getById(1)).thenReturn(Optional.of(user));

    mockMvc.perform(get("/user/getById/1"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Test User"));
  }

  @Test
  void testGetUserFavourites() throws Exception {
    Users user = new Users();
    user.setUserId(1);
    Courses course = new Courses();
    course.setCourseId(1);
    course.setTitle("Test Course");
    user.setFavourites(Set.of(course));

    Mockito.when(userService.getById(1)).thenReturn(Optional.of(user));

    mockMvc.perform(get("/user/1/favourites"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].name").value("Test Course"));
  }

  @Test
  void testAddUser() throws Exception {
    Users user = new Users();
    user.setUserId(1);
    user.setName("New User");

    Mockito.when(userService.add(any(Users.class))).thenReturn(true);

    mockMvc.perform(post("/user/add")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(user)))
        .andExpect(status().isOk());
  }

  @Test
  void testLoginUser() throws Exception {
    Users user = new Users();
    user.setUserId(1);
    user.setName("Test User");
    user.setPassword("password");

    Mockito.when(userService.findByNameAndPassword("Test User", "password"))
        .thenReturn(Optional.of(user));

    mockMvc.perform(post("/user/login")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(user)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.name").value("Test User"));
  }

  @Test
  void testAddFavourite() throws Exception {
    Mockito.when(userService.addFavourite(1, 1)).thenReturn(true);

    mockMvc.perform(post("/user/1/favourite/1"))
        .andExpect(status().isOk())
        .andExpect(content().string("Favourite added"));
  }

  @Test
  void testDeleteUser() throws Exception {
    Mockito.when(userService.delete(1)).thenReturn(true);

    mockMvc.perform(delete("/user/delete/1"))
        .andExpect(status().isOk());
  }

  @Test
  void testRemoveFavourite() throws Exception {
    Mockito.when(userService.removeFavourite(1, 1)).thenReturn(true);

    mockMvc.perform(delete("/user/1/favourite/1"))
        .andExpect(status().isOk())
        .andExpect(content().string("Favourite removed"));
  }

  @Test
  void testUpdateUser() throws Exception {
    Users user = new Users();
    user.setUserId(1);
    user.setName("Updated User");

    Mockito.when(userService.update(any(Users.class), eq(1))).thenReturn(true);

    mockMvc.perform(put("/user/update/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(user)))
        .andExpect(status().isOk());
  }
}