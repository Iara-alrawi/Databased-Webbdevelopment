package se.gritacademy.webbutvecklinguppgift.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import se.gritacademy.webbutvecklinguppgift.dao.BorrowDAO;
import se.gritacademy.webbutvecklinguppgift.model.Borrow;

import java.io.IOException;
import java.util.List;

@WebServlet("/userLoans")
public class BorrowServlet extends HttpServlet {

    private final BorrowDAO borrowDAO = new BorrowDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String borrowerName = req.getParameter("search");

        List<Borrow> borrowedBooks = null;
        List<Borrow> returnedBooks = null;

        if(borrowerName != null && !borrowerName.trim().isEmpty()) {
            borrowedBooks = borrowDAO.getBorrowedBooksByUser(borrowerName);
            returnedBooks = borrowDAO.getReturnedBorrowsByUser(borrowerName);
        }
        req.setAttribute("borrowedBooks", borrowedBooks);
        req.setAttribute("returnedBooks", returnedBooks);
        req.setAttribute("searchQuery", borrowerName);
        req.getRequestDispatcher("/view/userLoans.jsp").forward(req, resp);
    }


}