package SivertMarius.MappeAppWeb.DB.server.courses;

import SivertMarius.MappeAppWeb.DB.repository.coursesRepository.CoursesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoursesServer {
  @Autowired
  private CoursesRepository coursesRepository;
}
