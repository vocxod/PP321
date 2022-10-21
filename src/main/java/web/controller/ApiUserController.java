package web.controller;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import web.model.User;
import web.service.UserService;

/**
 * ApiUserController
 */
@RestController
@RequestMapping("/api/users")
public class ApiUserController {

  private static final Logger logger = LogManager.getLogger(ApiUserController.class);

  @Autowired
  private UserService userService;

  @GetMapping
  public List<User> findAll() {
    logger.info("\u001B[1;32 Start ApiUserController \u001B[0m");
    return userService.listUsers();
  }

}
