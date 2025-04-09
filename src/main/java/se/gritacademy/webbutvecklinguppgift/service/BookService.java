package se.gritacademy.webbutvecklinguppgift.service;


import se.gritacademy.webbutvecklinguppgift.dao.BookDAO;
import se.gritacademy.webbutvecklinguppgift.model.Book;
import se.gritacademy.webbutvecklinguppgift.model.BookType;

import java.util.List;

public class BookService {

    private final BookDAO bookDAO;

    public BookService() {
        this.bookDAO = new BookDAO();
    }

    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    public List<Book> searchBooks(String search) {
        return bookDAO.searchBooks(search);
    }

    public Book getBookById(int id) {
        return bookDAO.getBookById(id);
    }

    public Book getBookById(long id) {
        return bookDAO.getBookById(id);
    }

    public void reserveBook(long id) {
        Book book = bookDAO.getBookById(id);
        book.setReserved(true);
        bookDAO.update(book);
    }

    public void borrowBook(long id) {
        Book book = bookDAO.getBookById(id);
        book.setAvailable(false);
        bookDAO.update(book);
    }

    public boolean addBook(String title, String author, String type, int year) {
        if (title.isEmpty() || year < 0) {
            return false;
        }

        BookType bookType;
        try {
            bookType = BookType.valueOf(type);
        } catch (IllegalArgumentException e) {
            return false; // Om någon skriver fel typ
        }

        Book book = new Book(title, author, BookType.Bok, year, true, false);
        return bookDAO.insertBook(book);
    }

}