package MappeAppWeb.DB.server.courses;

import MappeAppWeb.DB.repository.coursesRepository.CoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseProviderServer {
  @Autowired
  private CoursesRepository coursesRepository;
}
