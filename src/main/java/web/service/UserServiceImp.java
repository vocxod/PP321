package web.service;

import web.dao.UserDao;
import web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

  @Autowired
  private UserDao userDao;

  @Transactional
  @Override
  public void add(User user) {
    userDao.add(user);
  }

  @Transactional(readOnly = true)
  @Override
  public List<User> listUsers() {
    return userDao.listUsers();
  }

  @Transactional(readOnly = true)
  @Override
  public User findById(Long id) {
    User user = userDao.findById(id);
    return user;
  }

  @Transactional
  public User delete(Long id) {
    User user = userDao.findById(id);
    userDao.delete(id);
    return user;
  }

  @Transactional
  @Override
  public User update(User user) {
    userDao.update(user);
    return user;
  }

}
