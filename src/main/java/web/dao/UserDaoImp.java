package web.dao;

import web.model.User;
// import org.hibernate.SessionFactory;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.NonUniqueResultException;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

  private static final Logger logger = LogManager.getLogger(UserDaoImp.class);

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public void add(User user) {
    entityManager.persist(user);
  }

  @Override
  public User update(User user) {
    User userResult = entityManager.merge(user);
    // return updated user
    return userResult;
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<User> listUsers() {
    List<User> users = entityManager.createQuery("SELECT user FROM User user").getResultList();
    return users;
  }

  @Override
  public User findById(Long id) {
    User user = entityManager.find(User.class, id);
    entityManager.detach(user);
    return user;
  }

  @Override
  public void delete(Long id) {
    logger.info("\u001B[1;32m Remove " + id + " user. \u001B[0m");
    User user = entityManager.find(User.class, id);
    if (user != null) {
      entityManager.remove(user);
    }
  }

}
