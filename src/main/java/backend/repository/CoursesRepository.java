package backend.repository;

import backend.model.Courses;
import backend.model.Providers;
import backend.model.Topics;
import backend.model.Users;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * Repository interface for accessing Course data in the database.
 * Spring will auto-generate the necessary methods.
 */
public interface CoursesRepository extends CrudRepository<Courses, Integer> {

  /**
   * Find all courses by provider ID.
   *
   * @param providerId The ID of the provider.
   * @return An iterable collection of courses associated with the given provider ID.
   */
  @Query("SELECT c FROM Courses c JOIN c.providers p WHERE p.providersId = :providerId")
  Iterable<Courses> findCoursesByProviderProvidersId(@Param("providerId") int providerId);

  /**
   * Find all courses by topic ID.
   *
   * @param topicId The ID of the topic.
   * @return An iterable collection of courses associated with the given topic ID.
   */
  @Query("SELECT c FROM Courses c JOIN c.topics p WHERE p.topicId = :topicId")
  Iterable<Courses> findCourseWhitTopicTopicId(Integer topicId);

  /**
   * Find all courses by user ID.
   *
   * @param userId The ID of the user.
   * @return An iterable collection of courses associated with the given user ID.
   */
  @Query("SELECT c FROM Courses c JOIN c.users p WHERE p.userId = :userId")
  Iterable<Courses> findCourseWithUserUserId(Integer userId);

  /**
   * Find all providers for a specific course.
   *
   * @param courseId The ID of the course.
   * @return An iterable collection of providers associated with the given course ID.
   */
  @Query("SELECT p FROM Providers p JOIN p.courses c WHERE c.courseId = :courseId")
  Iterable<Providers> findProvidersForCourse(int courseId);

  /**
   * Find all topics for a specific course.
   *
   * @param courseId The ID of the course.
   * @return An iterable collection of topics associated with the given course ID.
   */
  @Query("SELECT p FROM Topics p JOIN p.courses c WHERE c.courseId = :courseId")
  Iterable<Topics> findTopicForCourse(Integer courseId);

  /**
   * Find all users for a specific course.
   *
   * @param courseId The ID of the course.
   * @return An iterable collection of users associated with the given course ID.
   */
  @Query("SELECT p FROM Users p JOIN p.courses c WHERE c.courseId = :courseId")
  Iterable<Users> findUsersForCourse(Integer courseId);

  /**
   * Find all prices for a specific course.
   *
   * @param courseId The ID of the course.
   * @return A list of objects containing the provider name and price for the given course ID.
   */
  @Query(value = "SELECT providers_id, price FROM course_providers WHERE courses_id = :courseId",
      nativeQuery = true)
  List<Object[]> findPricesByCourseId(@Param("courseId") Integer courseId);
}