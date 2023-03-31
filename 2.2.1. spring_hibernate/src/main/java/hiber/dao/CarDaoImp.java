package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CarDaoImp implements CarDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    public List<Car> listCars() {
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery("from Car", Car.class);
        return query.getResultList();
    }

    @Override
    public User get(String model, Integer series) {
        String carHql = "from Car where model=:m and series=:s";
        String userHql = "from User where id=:id";
        TypedQuery<Car> carQuery = sessionFactory.getCurrentSession().createQuery(carHql ,Car.class);
        carQuery.setParameter("m", model).setParameter("s", series);
        TypedQuery<User> userQuery = sessionFactory.getCurrentSession().createQuery(userHql ,User.class);
        userQuery.setParameter("id", carQuery.getResultList().get(0).getId());
        return userQuery.getSingleResult();
    }

}
