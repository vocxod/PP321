package web.dao;

import web.model.User;
import web.model.Car;

import java.util.List;

public interface UserDao {

  void add(User user);

  List<User> listUsers();

  User findById(Long id);

  void add(User user, Car car);

}
