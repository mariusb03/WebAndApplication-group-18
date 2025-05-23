package backend.service;

import backend.model.Courses;
import backend.model.Providers;
import backend.model.Topics;
import backend.repository.CoursesRepository;
import backend.repository.ProvidersRepository;
import backend.repository.TopicRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CoursesServiceTest {

  @Mock
  private CoursesRepository courseRepo;

  @Mock
  private TopicRepository topicRepo;

  @Mock
  private ProvidersRepository providerRepo;

  @InjectMocks
  private CoursesService coursesService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void testAddTopicToCourse() {
    int courseId = 1;
    int topicId = 2;
    Courses course = new Courses();
    Topics topic = new Topics();

    when(courseRepo.findById(courseId)).thenReturn(Optional.of(course));
    when(topicRepo.findById(topicId)).thenReturn(Optional.of(topic));

    coursesService.addTopicToCourse(courseId, topicId);

    assertTrue(course.getTopics().contains(topic));
    verify(courseRepo, times(1)).save(course);
  }

  @Test
  void testAddProviderToCourse() {
    int courseId = 1;
    int providerId = 2;
    Courses course = new Courses();
    Providers provider = new Providers();

    when(courseRepo.findById(courseId)).thenReturn(Optional.of(course));
    when(providerRepo.findById(providerId)).thenReturn(Optional.of(provider));

    coursesService.addProviderToCourse(courseId, providerId);

    assertTrue(course.getProviders().contains(provider));
    verify(courseRepo, times(1)).save(course);
  }

  @Test
  void testToggleCourseVisibility() {
    int courseId = 1;
    Courses course = new Courses();
    course.setHidden(false);

    when(courseRepo.findById(courseId)).thenReturn(Optional.of(course));

    boolean result = coursesService.toggleCourseVisibility(courseId);

    assertTrue(result);
    assertTrue(course.isHidden());
    verify(courseRepo, times(1)).save(course);
  }

  @Test
  void testGetAllCoursesOfProvider() {
    int providerId = 1;
    Providers provider = new Providers();
    List<Courses> courses = List.of(new Courses());

    when(providerRepo.findById(providerId)).thenReturn(Optional.of(provider));
    when(courseRepo.findCoursesByProviderProvidersId(providerId)).thenReturn(courses);

    Iterable<Courses> result = coursesService.getAllCoursesOfProvider(providerId);

    assertEquals(courses, result);
  }

  @Test
  void testGetCoursePrice() {
    int courseId = 1;
    List<Object[]> rows = (List<Object[]>) List.of(new Object[]{1, 100.0});
    Providers provider = new Providers();
    provider.setName("ProviderName");

    when(courseRepo.findPricesByCourseId(courseId)).thenReturn(rows);
    when(providerRepo.findById(1)).thenReturn(Optional.of(provider));

    List<Map<String, Object>> result = coursesService.getCoursePrice(courseId);

    assertEquals(1, result.size());
    assertEquals("ProviderName", result.get(0).get("providerName"));
    assertEquals(100.0, result.get(0).get("price"));
  }

  @Test
  void testAddCourse() {
    Courses course = mock(Courses.class);
    when(course.isValid()).thenReturn(true);

    boolean result = coursesService.add(course);

    assertTrue(result);
    verify(courseRepo, times(1)).save(course);
  }

  @Test
  void testDeleteCourse() {
    int courseId = 1;
    when(courseRepo.existsById(courseId)).thenReturn(true);

    boolean result = coursesService.delete(courseId);

    assertTrue(result);
    verify(courseRepo, times(1)).deleteById(courseId);
  }

  @Test
  void testUpdateCourse() {
    int courseId = 1;
    Courses existingCourse = new Courses();
    Courses updatedCourse = mock(Courses.class);
    when(courseRepo.findById(courseId)).thenReturn(Optional.of(existingCourse));
    when(updatedCourse.isValid()).thenReturn(true);

    boolean result = coursesService.update(updatedCourse, courseId);

    assertTrue(result);
    verify(courseRepo, times(1)).save(existingCourse);
  }
}