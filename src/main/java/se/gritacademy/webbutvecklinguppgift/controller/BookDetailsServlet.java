package se.gritacademy.webbutvecklinguppgift.controller;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import se.gritacademy.webbutvecklinguppgift.model.Book;
import se.gritacademy.webbutvecklinguppgift.service.BookService;

import javax.sql.rowset.serial.SerialException;
import java.io.IOException;

@WebServlet("/bookDetails")
public class BookDetailsServlet extends HttpServlet {
    private BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookId = req.getParameter("bookId");

        int id = (int) Long.parseLong(bookId);

        Book book = bookService.getBookById(id);
        req.setAttribute("book",book);
        req.getRequestDispatcher("/view/bookDetails.jsp").forward(req, resp);

    }

}