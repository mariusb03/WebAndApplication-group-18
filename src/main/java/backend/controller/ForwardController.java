package backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * This controller is used to forward all requests to the index.html file.
 * It is used to serve the single-page application (SPA) in a Spring Boot application.
 * The index.html file is typically the entry point for the frontend application.
 */
@Controller
public class ForwardController {

  /**
   * This method handles all requests that do not contain a file extension (e.g., .html, .js, .css).
   * It forwards the request to the index.html file.
   *
   * @return The path to the index.html file.
   */
  @RequestMapping(value = {"/{path:[^\\.]*}", "/**/{path:[^\\.]*}"})
  public String redirect() {
    return "forward:/index.html";
  }
}