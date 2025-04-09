package se.gritacademy.webbutvecklinguppgift.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import se.gritacademy.webbutvecklinguppgift.dao.BorrowDAO;

import java.io.IOException;

@WebServlet("/returnBook")
public class ReturnBookServlet extends HttpServlet {

    private final BorrowDAO borrowDAO = new BorrowDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String borrowId = req.getParameter("borrowId");


        if (borrowId != null) {
            borrowDAO.returnBook(Long.parseLong(borrowId));



            session.setAttribute("returnMessage", "Book has been returned!");
        }


        res.sendRedirect(req.getContextPath() + "/borrowedBooks");
    }
}