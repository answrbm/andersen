package lesson10.dao;

import jakarta.persistence.Query;
import lesson10.model.User;
import lesson10.util.SessionFactoryManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserDAOImpl implements UserDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public UserDAOImpl(SessionFactoryManager sessionFactoryManager) {
        this.sessionFactory = sessionFactoryManager.getSessionFactory();
    }

    @Override
    public User save(User user) {
        try(Session s = sessionFactory.openSession()) {
            Transaction t = s.beginTransaction();
            try {
                s.persist(user);
                t.commit();
            } catch (Exception e) {
                t.rollback();
                e.printStackTrace();
            }
        }
        return user;
    }

    @Override
    public Optional<User> findById(Long userId) {
        Optional<User> user = Optional.empty();
        try(Session s = sessionFactory.openSession()) {
            Transaction t = s.beginTransaction();
            try {
                user = Optional.of(s.get(User.class, userId));
                t.commit();
            } catch (Exception e) {
                t.rollback();
                e.printStackTrace();
            }
            return user;
        }
    }

    @Override
    public Optional<User> findByName(String name) {
        Optional<User> user = Optional.empty();
        try(Session s = sessionFactory.openSession()) {
            Transaction t = s.beginTransaction();
            try {
                Query q = s.createQuery("FROM User WHERE name=:n", User.class);
                q.setParameter("n",name);
                user = Optional.of((User) q.getResultList().get(0));
                t.commit();
            } catch (Exception e) {
                t.rollback();
                e.printStackTrace();
            }
            return user;
        }
    }

    @Override
    public Long deleteById(Long userId) {
        try(Session s = sessionFactory.openSession()) {
            Transaction t = s.beginTransaction();
            try {
                User user = s.get(User.class, userId);
                s.remove(user);
                t.commit();
            } catch (Exception e) {
                t.rollback();
                e.printStackTrace();
            }
        }
        return userId;
    }
}
