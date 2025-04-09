package se.gritacademy.webbutvecklinguppgift.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import se.gritacademy.webbutvecklinguppgift.model.Book;
import se.gritacademy.webbutvecklinguppgift.service.BookService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    private final BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String query = req.getParameter("query");
        System.out.println("Search after: " + query);

        List<Book> books;

        if (query != null && !query.trim().isEmpty()) {
            books = bookService.searchBooks(query);
        } else {
            books = new ArrayList<>();
        }

        System.out.println("Search term: " + query);
        System.out.println("Number of books found: " + books.size());

        req.setAttribute("query", query);
        req.setAttribute("queryList", books);
        req.getRequestDispatcher("/view/search.jsp").forward(req, res);
    }
}
