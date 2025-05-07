package mappeappweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entrypoint for the application.
 */
@SpringBootApplication
public class ApplicationStarter {

  /**
   * Main method to run the application.
   *
   * @param args command line arguments
   */
  public static void main(String[] args) {
    SpringApplication.run(ApplicationStarter.class, args);
  }
}
