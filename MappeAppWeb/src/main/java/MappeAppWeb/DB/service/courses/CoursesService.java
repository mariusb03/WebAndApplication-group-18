package MappeAppWeb.DB.service.courses;

import MappeAppWeb.DB.model.CoursesDB.Courses;
import MappeAppWeb.DB.model.CoursesDB.Topics;
import MappeAppWeb.DB.repository.coursesRepository.CoursesRepository;
import MappeAppWeb.DB.repository.coursesRepository.TopicRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CoursesService {
  @Autowired
  private CoursesRepository coursesRepository;

  @Autowired
  private TopicRepository topicRepository;

  @Transactional
  public void linkCourseToTopic(int courseId, int topicId) {
    Courses course = coursesRepository.findById(courseId)
        .orElseThrow(() -> new IllegalArgumentException("Course not found"));
    Topics topic = topicRepository.findById(topicId)
        .orElseThrow(() -> new IllegalArgumentException("Topic not found"));

    course.getTopics().add(topic);
    topic.getCourses().add(course);

    coursesRepository.save(course);
    // topicRepository.save(topic); // Not needed due to cascading
  }

  public List<Courses> getAllCourses() {
    return (List<Courses>) coursesRepository.findAll();
  }

  public List<Topics> getAllTopics() {
    return (List<Topics>) topicRepository.findAll();
  }
}
