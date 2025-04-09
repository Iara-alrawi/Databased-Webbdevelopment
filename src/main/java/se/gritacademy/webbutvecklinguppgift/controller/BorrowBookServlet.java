package se.gritacademy.webbutvecklinguppgift.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import se.gritacademy.webbutvecklinguppgift.dao.BookDAO;
import se.gritacademy.webbutvecklinguppgift.dao.BorrowDAO;
import se.gritacademy.webbutvecklinguppgift.model.Book;
import se.gritacademy.webbutvecklinguppgift.model.Borrow;
import se.gritacademy.webbutvecklinguppgift.model.User;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

@WebServlet("/borrowBook")
public class BorrowBookServlet extends HttpServlet {

    private final BookDAO bookDAO = new BookDAO();
    private final BorrowDAO borrowDAO = new BorrowDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        String bookIdStr = req.getParameter("bookId");
        if (bookIdStr == null || bookIdStr.isEmpty()){
            resp.sendRedirect(req.getContextPath() + "/view/search.jsp?error=missingBookId");
            return;
        }
        long bookId = Long.parseLong(bookIdStr);
        Book book = bookDAO.getBookById(bookId);

        if (book == null || !book.isAvailable()){
            resp.sendRedirect(req.getContextPath() + "/view/search.jsp?error=bookNotFound");
            return;
        }
        Date borrowDate = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(borrowDate);
        cal.add(Calendar.DAY_OF_MONTH, 30);
        Date dueDate = cal.getTime();

        Borrow borrow = new Borrow(0, book, user.getUsername(), borrowDate, dueDate, false, false);
        borrowDAO.borrowBook(borrow);

        book.setAvailable(false);
        bookDAO.update(book);

        resp.sendRedirect(req.getContextPath() + "/borrowedBooks");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
