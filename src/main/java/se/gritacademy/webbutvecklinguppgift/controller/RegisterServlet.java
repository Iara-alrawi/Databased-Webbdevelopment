package se.gritacademy.webbutvecklinguppgift.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import se.gritacademy.webbutvecklinguppgift.dao.UserDAO;
import se.gritacademy.webbutvecklinguppgift.model.User;

import java.io.IOException;

@WebServlet("/signup")
public class RegisterServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/view/signup.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username") != null ? req.getParameter("username") : "";
        String password1 = req.getParameter("password1") != null ? req.getParameter("password1") : "";
        String password2 = req.getParameter("password2") != null ? req.getParameter("password2") : "";
        String email = req.getParameter("email") != null ? req.getParameter("email") : "";
        String error = null;

        if (username.isEmpty() || password1.isEmpty() || password2.isEmpty() || email.isEmpty()) {
            error = "Username, email and password cannot be empty";
        } else if (!password1.equals(password2)) {
            error = "Passwords do not match";
        }

        if (error != null) {
            req.setAttribute("error", error);
            req.setAttribute("username", username);
            req.setAttribute("password1", password1);
            req.setAttribute("password2", password2);
            req.setAttribute("email", email);
            System.out.println("Returning with error: " + error);
            req.getRequestDispatcher("/view/signup.jsp").forward(req, resp);
            return;
        }


        User user = new User();
        user.setUsername(username);
        user.setPassword(password1);
        user.setEmail(email);
        UserDAO userDAO = new UserDAO();
        try {
            userDAO.save(user);
            req.setAttribute("success", "User registered successfully. Please log in.");
            req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
        } catch (Throwable e) {
            req.setAttribute("error", "Cannot register user: " + e.getMessage());
            System.out.println("Registration error: " + e.getMessage());
            req.getRequestDispatcher("/view/signup.jsp").forward(req, resp);
        }
    }
}
