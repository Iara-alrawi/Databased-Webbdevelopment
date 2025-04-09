package se.gritacademy.webbutvecklinguppgift.dao;


import se.gritacademy.webbutvecklinguppgift.model.User;
import se.gritacademy.webbutvecklinguppgift.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;


public abstract class BaseDAO<T, ID extends Serializable> {

    private final Class<T> type;

    public BaseDAO(Class<T> type) {
        this.type = type;
    }


    public T findById(ID id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        T entity = session.get(type, id);
        session.close();
        return entity;
    }


    public T findByIdWithOwner(ID id, User owner) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM " + type.getSimpleName() + " e WHERE e.id = :id AND e.user.id = :ownerId";
            Query<T> query = session.createQuery(hql, type);
            query.setParameter("id", id);
            query.setParameter("ownerId", owner.getId());
            List<T> results = query.getResultList();
            return results.isEmpty() ? null : results.get(0);
        }
    }

    public List<T> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM " + type.getSimpleName();
            Query<T> query = session.createQuery(hql, type);
            return query.getResultList();
        }
    }


    public List<T> findAllByOwner(User owner) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM " + type.getSimpleName() + " e WHERE e.user.id = :ownerId";
            Query<T> query = session.createQuery(hql, type);
            query.setParameter("ownerId", owner.getId());
            return query.getResultList();
        }
    }


    public void save(T entity) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(entity);
            tx.commit();
        } catch (Throwable ex) {
            if (tx != null) tx.rollback();
            throw ex;
        }
    }


    public void update(T entity) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(entity);
            tx.commit();
        } catch (Throwable ex) {
            if (tx != null) tx.rollback();
            throw ex;
        }
    }


    public void delete(T entity) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.delete(entity);
            tx.commit();
        } catch (Throwable ex) {
            if (tx != null) tx.rollback();
            throw ex;
        }
    }


    public void deleteById(ID id, User owner) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            T entity = findByIdWithOwner(id, owner);
            if (entity != null) {
                session.delete(entity);
            }
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) tx.rollback();
            throw ex;
        }
    }
}