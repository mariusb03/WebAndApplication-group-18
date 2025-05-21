package webapp.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Controller for handling page navigation.
 * This class is responsible for serving the HTML pages of the web application.
 */
@Controller
public class PageController {

  /**
   * Serves the home page.
   *
   * @param model The model to be used in the view.
   * @return The name of the home page view.
   */
  @RequestMapping("/aboutUs")
  public String aboutUs(Model model) {
    model.addAttribute("title", "About Us");
    return "pages/aboutUs.html";
  }

  /**
   * Serves the home page.
   *
   * @param model The model to be used in the view.
   * @return The name of the home page view.
   */
  @RequestMapping("/allCourses")
  public String allCourses(Model model) {
    model.addAttribute("title", "All Courses");
    return "pages/allCourses.html";
  }

  /**
   * Serves the home page.
   *
   * @param model The model to be used in the view.
   * @return The name of the home page view.
   */
  @RequestMapping("/cart")
  public String cart(Model model) {
    model.addAttribute("title", "Cart");
    return "pages/cart";
  }

  /**
   * Serves the home page.
   *
   * @param model The model to be used in the view.
   * @return The name of the home page view.
   */
  @RequestMapping("/landing")
  public String landingPage(Model model) {
    model.addAttribute("title", "Landing Page");
    return "pages/landing";
  }

  /**
   * Serves the home page.
   *
   * @param model The model to be used in the view.
   * @return The name of the home page view.
   */
  @RequestMapping("/login")
  public String login(Model model) {
    model.addAttribute("title", "Login");
    return "pages/login";
  }

  /**
   * Serves the home page.
   *
   * @param model The model to be used in the view.
   * @return The name of the home page view.
   */
  @RequestMapping("/profile")
  public String profile(Model model) {
    model.addAttribute("title", "Profile");
    return "pages/profile";
  }

  /**
   * Serves the home page.
   *
   * @param model The model to be used in the view.
   * @return The name of the home page view.
   */
  @RequestMapping("/register")
  public String register(Model model) {
    model.addAttribute("title", "Register");
    return "pages/register";
  }
}
