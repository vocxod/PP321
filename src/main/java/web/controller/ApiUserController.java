package web.controller;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import web.model.User;
import web.service.UserService;
import web.exception.MyResourceNotFoundException;

/**
 * ApiUserController
 */
@RestController
@RequestMapping("/api/users")
public class ApiUserController {

  private static final Logger logger = LogManager.getLogger(ApiUserController.class);

  // custom exception for better debug inspired idea baeldung.com
  public static <T> T checkFound(final T resource) {
    if (resource == null) {
      throw new MyResourceNotFoundException();
    }
    return resource;
  }

  @Autowired
  private UserService userService;

  @GetMapping
  public List<User> listUsers() {
    logger.info("\u001B[1;32 get All users \u001B[0m");
    return userService.listUsers();
  }

  @GetMapping(value = "/{id}")
  public User findById(Long id) {
    return checkFound(userService.findById(id));
  }

  @PostMapping(value = "/create", consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public void createUser(@RequestBody User user) {
    logger.info("POST data: {}", user);
    userService.add(user);
  }

}
