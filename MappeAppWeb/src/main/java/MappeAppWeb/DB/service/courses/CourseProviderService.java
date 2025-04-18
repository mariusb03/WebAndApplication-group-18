package MappeAppWeb.DB.service.courses;

import MappeAppWeb.DB.model.CoursesDB.CourseProviders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class CourseProviderService {
  @Autowired
  private CourseProviderService service;

  /**
   * Retrieves all course providers from the database.
   *
   * @return An iterable collection of all course providers.
   */
  @GetMapping
  public Iterable<CourseProviders> getAllCourseProviders() {
    return this.service.getAll();
  }

  /**
   * Retrieves course providers by their ID.
   *
   * @param id The ID of the course provider to retrieve.
   * @return The course provider with the specified ID.
   */
  @GetMapping("/{id}")
  public ResponseEntity<CourseProviders> getCourseProvider(@PathVariable int id) {
    ResponseEntity<CourseProviders> response;
    CourseProviders provider = this.service.getByID(id);
    if (provider != null) {
      response = ResponseEntity.ok(provider);
    } else {
      response = ResponseEntity.notFound().build();
    }
    return response;
  }

  /**
   * Retrieves course providers by their name.
   *
   * @param name The name of the course provider to retrieve.
   *
   * @return course provider with the specified name.
   */
  @GetMapping("/{name}")
  public ResponseEntity<CourseProviders> getCourseProviderByName(@PathVariable String name) {
    ResponseEntity<CourseProviders> response;
    CourseProviders provider = this.service.getByName(name);
    if (provider != null) {
      response = ResponseEntity.ok(provider);
    } else {
      response = ResponseEntity.notFound().build();
    }
    return response;
  }

  /**
   * Adds a new course provider to the database.
   *
   * @param provider The course provider to add.
   */
  @PostMapping
  public ResponseEntity<String> addProvider(@RequestBody CourseProviders provider) {
    ResponseEntity<String> response;
    if (this.service.add(provider)) {
      response = new ResponseEntity<>(HttpStatus.OK);
    } else {
      response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return response;
  }

  /**
   * Deletes a course provider from the database.
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteProvider(@PathVariable int id) {
    ResponseEntity<String> response;
    if (this.service.delete(id)) {
      response = new ResponseEntity<>(HttpStatus.OK);
    } else {
      response = new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    return response;
  }

  /**
   * Update a course provider in the database.
   *
   * @param id of the course provider to update
   * @param name of the course provider
   *
   * @return 200 OK if update was successful, 400 BAD REQUEST if not
   */
  @PutMapping("/{id}")
  public ResponseEntity<String> updateProvider (@PathVariable int id, @RequestBody CourseProviders name) {
    ResponseEntity<String> response;
    if (this.service.update(id, name)) {
      response = new ResponseEntity<>(HttpStatus.OK);
    } else {
      response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return response;
  }
}
