package webApp.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import webApp.model.Courses;
import webApp.model.Providers;
import org.springframework.data.repository.CrudRepository;
import webApp.model.Topics;
import webApp.model.Users;

/**
 * Repository interface for accessing Course data in the database.
 * Spring will auto-generate the necessary methods.
 */
public interface CoursesRepository extends CrudRepository<Courses, Integer> {

  @Query("SELECT c FROM Courses c JOIN c.providers p WHERE p.providersId = :providerId")
  Iterable<Courses> findCoursesByProvider_ProvidersId(@Param("providerId") int providerId);

  @Query("SELECT c FROM Courses c JOIN c.topics p WHERE p.topicId = :topicId")
  Iterable<Courses> findCourseWhitTopic_TopicId(Integer topicId);

  @Query("SELECT c FROM Courses c JOIN c.users p WHERE p.userId = :userId")
  Iterable<Courses> findCourseWhitUser_UserId(Integer userId);

  @Query("SELECT p FROM Providers p JOIN p.courses c WHERE c.courseId = :courseId")
  Iterable<Providers> findProvidersForACourse(int courseId);

  @Query("SELECT p FROM Topics p JOIN p.courses c WHERE c.courseId = :courseId")
  Iterable<Topics> findTopicForACourse(Integer courseId);

  @Query("SELECT p FROM Topics p JOIN p.courses c WHERE c.courseId = :courseId")
  Iterable<Users> findUsersForACourse(Integer courseId);
}