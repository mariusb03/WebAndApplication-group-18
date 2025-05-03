package MappeAppWeb.DB.controller.coursesController;

import MappeAppWeb.DB.model.CoursesDB.Providers;
import MappeAppWeb.DB.service.courses.ProviderService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/providers")
public class ProviderController {
  @Autowired
  private ProviderService service;

  /**
   * Retrieves all course providers from the database.
   *
   * @return An iterable collection of all course providers.
   */
  @GetMapping("/getAll")
  public Iterable<Providers> getAll() {
    return this.service.getAll();
  }

  /**
   * Retrieves course providers by their ID.
   *
   * @param id The ID of the course provider to retrieve.
   * @return The course provider with the specified ID.
   */
  @GetMapping("/getById/{id}")
  public ResponseEntity<Optional<Providers>> getById(@PathVariable int id) {
    ResponseEntity<Optional<Providers>> response;
    Optional<Providers> provider = this.service.getByID(id);
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
  @PostMapping("/add")
  public ResponseEntity<String> add(@RequestBody Providers provider) {
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
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<String> delete(@PathVariable int id) {
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
   * @param provider the provider object to update
   *
   * @return 200 OK if update was successful, 400 BAD REQUEST if not
   */
  @PutMapping("/update/{id}")
  public ResponseEntity<String> update(@PathVariable Providers provider) {
    ResponseEntity<String> response;
    if (this.service.update(provider)) {
      response = new ResponseEntity<>(HttpStatus.OK);
    } else {
      response = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    return response;
  }

}