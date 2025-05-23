package backend.repository;

import backend.model.Courses;
import backend.model.Providers;
import backend.model.Topics;
import backend.model.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CoursesRepositoryTest {

  @Autowired
  private CoursesRepository coursesRepository;

  @Autowired
  private ProvidersRepository providersRepository;

  @Autowired
  private TopicRepository topicRepository;

  @BeforeEach
  @Sql("/test-data.sql")
  void setUp() {
  }

  @Test
  void testFindCoursesByProviderProvidersId() {
    int providerId = 1;
    Iterable<Courses> courses = coursesRepository.findCoursesByProviderProvidersId(providerId);
    assertNotNull(courses);
    assertTrue(courses.iterator().hasNext());
  }

  @Test
  void testFindCourseWhitTopicTopicId() {
    int topicId = 1;
    Iterable<Courses> courses = coursesRepository.findCourseWhitTopicTopicId(topicId);
    assertNotNull(courses);
    assertTrue(courses.iterator().hasNext());
  }

  @Test
  void testFindCourseWithUserUserId() {
    int userId = 1;
    Iterable<Courses> courses = coursesRepository.findCourseWithUserUserId(userId);
    assertNotNull(courses);
    assertTrue(courses.iterator().hasNext());
  }

  @Test
  void testFindProvidersForCourse() {
    int courseId = 1;
    Iterable<Providers> providers = coursesRepository.findProvidersForCourse(courseId);
    assertNotNull(providers);
    assertTrue(providers.iterator().hasNext());
  }

  @Test
  void testFindTopicForCourse() {
    int courseId = 1;
    Iterable<Topics> topics = coursesRepository.findTopicForCourse(courseId);
    assertNotNull(topics);
    assertTrue(topics.iterator().hasNext());
  }

  @Test
  void testFindUsersForCourse() {
    int courseId = 1;
    Iterable<Users> users = coursesRepository.findUsersForCourse(courseId);
    assertNotNull(users);
    assertTrue(users.iterator().hasNext());
  }

  @Test
  void testFindPricesByCourseId() {
    int courseId = 1;
    List<Object[]> prices = coursesRepository.findPricesByCourseId(courseId);
    assertNotNull(prices);
    assertFalse(prices.isEmpty());
  }
}