package web.service;

import web.model.User;
import java.util.List;

public interface UserService {

  void add(User user);

  List<User> listUsers();

  User findById(Long id);

  User delete(Long id);

  User update(User user);

}
