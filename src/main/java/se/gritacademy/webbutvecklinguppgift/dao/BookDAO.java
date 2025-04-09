package se.gritacademy.webbutvecklinguppgift.dao;


import se.gritacademy.webbutvecklinguppgift.model.Book;
import se.gritacademy.webbutvecklinguppgift.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;


public class BookDAO {

    public List<Book> getAllBooks() {
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Book";  // Hämta alla böcker
            Query<Book> query = session.createQuery(hql, Book.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveBook(Book book) {
        Transaction transaction = null;
        try(Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    public List<Book> searchBooks(String query) {

        List<Book> books = new ArrayList<>();

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String sql = "FROM Book b WHERE b.title LIKE :query OR b.author LIKE :query";
            Query<Book> queryObj = session.createQuery(sql, Book.class);
            queryObj.setParameter("query", "%" + query + "%");

            books = queryObj.list();
            System.out.println("Number of hits: " + books.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return books;
    }


    public Book getBookById(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Book book = session.get(Book.class, id);
            session.close();
            return book;

        }
    }


    public void update(Book book) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();


        session.update(book);

        transaction.commit();
        session.close();
    }


    public boolean insertBook(Book book) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(book);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

}

