package web.dao;

import web.model.User;
import web.model.Car;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.NonUniqueResultException;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

  @Autowired
  private SessionFactory sessionFactory;

  @Override
  public void add(User user) {
    sessionFactory.getCurrentSession().save(user);
  }

  @Override
  public void add(User user, Car car) {
    user.setCar(car);
    sessionFactory.getCurrentSession().save(user);
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<User> listUsers() {
    TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
    return query.getResultList();
  }

  @Override
  public User getUserByCar(Car car) {
    User user = null;
    try {
      TypedQuery<User> query = sessionFactory.getCurrentSession()
          .createQuery(
              "SELECT u FROM User u WHERE u.car.model = :model AND u.car.series = :series",
              User.class);
      query.setParameter("model", car.getModel());
      query.setParameter("series", car.getSeries());
      // user = query.getSingleResult();
      List<User> users = query.getResultList();
      if (users.size() > 0 ) {
        user = users.get(0);
      } else {
        user = null;
      }
    } catch (NoResultException e) {
      user = null;
    } catch (NonUniqueResultException ee) {
      // get only first user
    }
    return user;
  }

}
