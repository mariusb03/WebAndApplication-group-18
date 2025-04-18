package MappeAppWeb.DB.controller.coursesController;

import MappeAppWeb.DB.model.CoursesDB.CourseProviders;
import MappeAppWeb.DB.service.courses.CourseProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/providers")
public class CourseProviderController {

  @Autowired
  private CourseProviderService courseProviderService;

  /**
   * get all course providers
   *
   * @return all course providers
   */
  @GetMapping
  public Iterable<CourseProviders> getAllCourseProviders() {
    return courseProviderService.getAllCourseProviders();
  }

}
