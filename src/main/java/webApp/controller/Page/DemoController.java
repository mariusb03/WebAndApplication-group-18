package webApp.controller.Page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {

  @RequestMapping("/")
  public String home(Model model) {
    model.addAttribute("title", "Home");
    return "pages/home";
  }

  @RequestMapping("/aboutUs")
  public String aboutUs(Model model) {
    model.addAttribute("title", "About Us");
    return "pages/AboutUs";
  }
}
