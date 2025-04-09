package se.gritacademy.webbutvecklinguppgift.controller;

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

@WebServlet("/borrowHistory")
public class BorrowHistoryServlet extends HttpServlet {

    private final BorrowDAO borrowDAO = new BorrowDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }


        List<Borrow> activeBorrows = borrowDAO.getActiveBorrowsByUser(user.getUsername());
        List<Borrow> returnedBorrows = borrowDAO.getReturnedBorrowsByUser(user.getUsername());

        req.setAttribute("activeBorrows", activeBorrows);
        req.setAttribute("returnedBorrows", returnedBorrows);
        req.getRequestDispatcher("/view/borrowHistory.jsp").forward(req, resp);
    }
}
