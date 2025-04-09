package se.gritacademy.webbutvecklinguppgift.dao;


import se.gritacademy.webbutvecklinguppgift.model.Book;
import se.gritacademy.webbutvecklinguppgift.model.Borrow;
import se.gritacademy.webbutvecklinguppgift.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class BorrowDAO {

    public void borrowBook(Borrow borrow) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(borrow);

        transaction.commit();
        session.close();
    }

    public void returnBook(long borrowId) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Borrow borrow = session.get(Borrow.class, borrowId);
            if (borrow != null) {
                borrow.setReturned(true);
                session.update(borrow);


                Book book = borrow.getBook();
                if (book != null) {
                    book.setAvailable(true);
                    session.update(book);
                }
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public List<Borrow> getAllBorrows() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Borrow> borrows = session.createQuery("FROM Borrow", Borrow.class).list();
        session.close();
        return borrows;
    }

    public List<Borrow> getBorrowedBooksByUser(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Borrow> borrowedBooks = session.createQuery("FROM Borrow WHERE borrowerName = :username AND returned = FALSE", Borrow.class)
                .setParameter("username", username)
                .getResultList();
        session.close();
        return borrowedBooks;
    }

    public Borrow getBorrowById(long borrowId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Borrow.class, borrowId);
        }
    }

    public List<Borrow> getActiveBorrowsByUser(String username) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Borrow b WHERE b.borrowerName = :username AND b.returned = false";
            Query<Borrow> query = session.createQuery(hql, Borrow.class);
            query.setParameter("username", username);
            return query.list();
        }
    }

    public List<Borrow> getReturnedBorrowsByUser(String username) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String hql = "FROM Borrow b WHERE b.borrowerName = :username AND b.returned = true";
            Query<Borrow> query = session.createQuery(hql, Borrow.class);
            query.setParameter("username", username);
            return query.list();
        }
    }
}