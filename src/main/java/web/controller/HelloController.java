package web.controller;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;

@Controller
public class HelloController {

  private static final Logger logger = LogManager.getLogger(HelloController.class);

  @GetMapping(value = "/home")
  public String printWelcome(ModelMap model) {
    LocalTime localTime = LocalTime.now();
    List<String> messages = new ArrayList<>();
    model.addAttribute("local_time", localTime);
    model.addAttribute("messages", messages);
    logger.info("\u001B[32m HelloController start \u001B[0m");
    return "home";
  }

}
