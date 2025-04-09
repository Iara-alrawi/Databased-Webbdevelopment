package se.gritacademy.webbutvecklinguppgift.dao;

import se.gritacademy.webbutvecklinguppgift.model.User;
import se.gritacademy.webbutvecklinguppgift.util.HibernateUtil;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAO extends BaseDAO<User, Integer> {

    public UserDAO(){
        super(User.class);
    }

    public User getUser(String username) throws HibernateException {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("FROM User WHERE lower(username) = :username", User.class);
            query.setParameter("username", username.toLowerCase());
            List<User> users = query.list();
            if (users.isEmpty()) {
                return null;
            }
            return users.get(0);
        }
    }


    public List<User> getAllUsers() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("SELECT u FROM User u LEFT JOIN FETCH u.borrowedBooks", User.class).getResultList();
        }
    }

    public User getUserById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(User.class, id);
        }
    }

    public User searchUserByExactUsername(String username) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            User user = session.createQuery("FROM User WHERE username = :username", User.class)
                    .setParameter("username", username)
                    .uniqueResult();

            if (user != null) {
                Hibernate.initialize(user.getBorrowedBooks()); // Laddar böcker innan sessionen stängs
            }
            return user;
        }
    }

    public void updateUser(User user, String newPassword) {
        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            User existingUser = session.get(User.class, user.getId());
            if (existingUser != null) {
                existingUser.setUsername(user.getUsername());
                existingUser.setRole(user.getRole());

                // Endast uppdatera lösenordet om ett nytt har angivits
                if (newPassword != null && !newPassword.isEmpty()) {
                    existingUser.setPassword(newPassword);
                }

                session.update(existingUser);
                transaction.commit();
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public void createUser(User user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public void deleteUser(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
            }
            transaction.commit();
        }
    }
}

