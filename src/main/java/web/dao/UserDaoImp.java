package web.dao;

import web.model.User;
import web.model.Car;
// import org.hibernate.SessionFactory;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.NonUniqueResultException;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

  /*
   * @Autowired
   * private SessionFactory sessionFactory;
   */

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public void add(User user) {
    entityManager.getTransaction().begin();
    entityManager.persist(user);
    entityManager.getTransaction().commit();
  }

  @Override
  public void add(User user, Car car) {
    user.setCar(car);
    add(user);
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

}
