package web.controller;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    logger.info("\u001B[1;32m get All users \u001B[0m");
    return userService.listUsers();
  }

  @GetMapping(value = "/{id}")
  public User findById(@PathVariable Long id) {
    logger.info("\u001B[1;33m Get userId " + id + " data. \u001B[0m");
    // return checkFound(userService.findById(id));
    User user;
    try {
      user = userService.findById(id);
    } catch (Exception e) {
      user = null;
    }
    return user;
  }

  @PostMapping(value = "/create", consumes = "application/json")
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<User> createUser(@RequestBody User user) {
    logger.info("POST data: {}", user);
    userService.add(user);
    ResponseEntity<User> userResponse = new ResponseEntity<User>(user, HttpStatus.CREATED);
    return userResponse;
  }

  @PutMapping(value = "/update/{id}", consumes = "application/json")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
    logger.info("UPDATE data: {}", user);
    User updatedUser = userService.update(user);
    ResponseEntity<User> userResponse = new ResponseEntity<User>(updatedUser, HttpStatus.OK);
    return userResponse;
  }

  @DeleteMapping(value = "/delete/{id}", consumes = "application/json")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<User> deleteUser(@PathVariable Long id) {
    userService.delete(id);
    return ResponseEntity.ok().build();
  }

}
