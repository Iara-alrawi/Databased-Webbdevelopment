package se.gritacademy.webbutvecklinguppgift.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import se.gritacademy.webbutvecklinguppgift.dao.BookDAO;
import se.gritacademy.webbutvecklinguppgift.model.Book;
import se.gritacademy.webbutvecklinguppgift.model.BookType;
import se.gritacademy.webbutvecklinguppgift.service.BookService;

import java.io.IOException;
import java.util.List;

@WebServlet("/books")
public class BookServlet extends HttpServlet {

    private BookDAO bookDAO = new BookDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookDAO.getAllBooks();
        req.setAttribute("books", books);
        req.getRequestDispatcher("/view/books.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("doPost runs!");

        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String typeString = req.getParameter("type");
        int year = Integer.parseInt(req.getParameter("year"));

        System.out.println("Received data: " + title + ", " + author + ", " + typeString + ", " + year);
        try {
            BookType type = BookType.valueOf(typeString);

            Book newBook = new Book (title, author, type, year, true, false);
            bookDAO.saveBook(newBook);

            System.out.println("Book saved: " + newBook);
            resp.sendRedirect(req.getContextPath() + "/books?success=true");
        } catch (IllegalArgumentException e) {
            req.setAttribute("error", "Invalid BookType!");
            req.getRequestDispatcher("/view/addBook.jsp?error=InvalidData").forward(req, resp);
        }
    }

}

