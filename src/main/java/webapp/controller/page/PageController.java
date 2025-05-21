package webapp.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

  @RequestMapping("/aboutUs")
  public String aboutUs(Model model) {
    model.addAttribute("title", "About Us");
    return "pages/aboutUs";
  }

  @RequestMapping("/allCourses")
  public String allCourses(Model model) {
    model.addAttribute("title", "All Courses");
    return "pages/allCourses";
  }

  @RequestMapping("/cart")
  public String cart(Model model) {
      model.addAttribute("title", "Cart");
      return "pages/cart";
  }

  @RequestMapping("/landing")
  public String landingPage(Model model) {
      model.addAttribute("title", "Landing Page");
      return "pages/landing";
  }

  @RequestMapping("/login")
  public String login(Model model) {
      model.addAttribute("title", "Login");
      return "pages/login";
  }

  @RequestMapping("/profile")
  public String profile(Model model) {
      model.addAttribute("title", "Profile");
      return "pages/profile";
  }

  @RequestMapping("/register")
  public String register(Model model) {
      model.addAttribute("title", "Register");
      return "pages/register";
  }
}
