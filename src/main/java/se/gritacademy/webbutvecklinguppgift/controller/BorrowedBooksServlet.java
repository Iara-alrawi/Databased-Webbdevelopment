package se.gritacademy.webbutvecklinguppgift.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import se.gritacademy.webbutvecklinguppgift.dao.BorrowDAO;
import se.gritacademy.webbutvecklinguppgift.model.Borrow;
import se.gritacademy.webbutvecklinguppgift.model.User;

import java.io.IOException;
import java.util.List;

@WebServlet("/borrowedBooks")
public class BorrowedBooksServlet extends HttpServlet {

    private final BorrowDAO borrowDAO = new BorrowDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        List<Borrow> borrowedBooks =
                borrowDAO.getBorrowedBooksByUser(user.getUsername());
        req.setAttribute("borrowedBooks", borrowedBooks);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/view/borrowedBooks.jsp");
        dispatcher.forward(req, resp);


    }
}