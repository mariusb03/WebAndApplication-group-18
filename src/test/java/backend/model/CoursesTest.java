package backend.model;

import org.junit.jupiter.api.Test;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CoursesTest {

  @Test
  void testDefaultConstructor() {
    Courses course = new Courses();
    assertNull(course.getCourseId());
    assertFalse(course.isHidden());
  }

  @Test
  void testParameterizedConstructor() {
    Courses course = new Courses("Java", "Intermediate", "Fall", 5.0, 10, "None", 100, "Learn Java", "Programming");

    assertEquals("Java", course.getTitle());
    assertEquals("Intermediate", course.getDifficulty());
    assertEquals("Fall", course.getSession());
    assertEquals(5.0, course.getSize());
    assertEquals(10, course.getHoursPerWeek());
    assertEquals("None", course.getRelatedCourses());
    assertEquals(100, course.getPrice());
    assertEquals("Learn Java", course.getDescription());
    assertEquals("Programming", course.getCategory());
  }

  @Test
  void testGettersAndSetters() {
    Courses course = new Courses();

    course.setCourseId(1);
    course.setTitle("Python");
    course.setDifficulty("Beginner");
    course.setSession("Spring");
    course.setSize(3.5);
    course.setHoursPerWeek(5);
    course.setRelatedCourses("JavaScript");
    course.setPrice(50);
    course.setDescription("Python course");
    course.setCategory("Programming");
    course.setHidden(true);

    assertEquals(1, course.getCourseId());
    assertEquals("Python", course.getTitle());
    assertEquals("Beginner", course.getDifficulty());
    assertEquals("Spring", course.getSession());
    assertEquals(3.5, course.getSize());
    assertEquals(5, course.getHoursPerWeek());
    assertEquals("JavaScript", course.getRelatedCourses());
    assertEquals(50, course.getPrice());
    assertEquals("Python course", course.getDescription());
    assertEquals("Programming", course.getCategory());
    assertTrue(course.isHidden());
  }

  @Test
  void testCollectionsSettersAndGetters() {
    Courses course = new Courses();

    Topics topic = new Topics(1, "AI");
    Providers provider = new Providers(1, "Coursera");
    Users user = new Users(); // You’ll need a basic Users class to compile

    Set<Topics> topics = new HashSet<>();
    topics.add(topic);
    course.setTopics(topics);
    assertTrue(course.getTopics().contains(topic));

    Set<Providers> providers = new HashSet<>();
    providers.add(provider);
    course.setProviders(providers);
    assertTrue(course.getProviders().contains(provider));

    Set<Users> users = new HashSet<>();
    users.add(user);
    course.setUsers(users);
    assertTrue(course.getUsers().contains(user));
  }

  @Test
  void testIsValid_whenAllFieldsAreValid() {
    Courses course = new Courses("JS", "Easy", "Summer", 4.0, 6, "HTML", 30, "JavaScript basics", "Web");

    assertTrue(course.isValid());
  }

  @Test
  void testIsValid_whenTitleIsEmpty() {
    Courses course = new Courses("", "Easy", "Summer", 4.0, 6, "HTML", 30, "JavaScript basics", "Web");

    assertFalse(course.isValid());
  }

  @Test
  void testIsValid_whenSizeIsNegative() {
    Courses course = new Courses("JS", "Easy", "Summer", -1.0, 6, "HTML", 30, "JavaScript basics", "Web");

    // The current isValid() allows size == 0 or > 0. Negative is invalid.
    assertFalse(course.isValid());
  }

  @Test
  void testIsValid_whenCategoryIsNull() {
    Courses course = new Courses("JS", "Easy", "Summer", 4.0, 6, "HTML", 30, "JavaScript basics", null);

    assertFalse(course.isValid());
  }

  @Test
  void testIsValid_whenDescriptionIsEmpty() {
    Courses course = new Courses("JS", "Easy", "Summer", 4.0, 6, "HTML", 30, "", "Web");

    assertFalse(course.isValid());
  }
}