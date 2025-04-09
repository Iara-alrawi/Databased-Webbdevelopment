package se.gritacademy.webbutvecklinguppgift.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import se.gritacademy.webbutvecklinguppgift.dao.UserDAO;
import se.gritacademy.webbutvecklinguppgift.model.User;

import java.io.IOException;
import java.util.Date;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean logout = Boolean.parseBoolean(req.getParameter("logout"));
        if (logout) {
            HttpSession session = req.getSession(false);
            if (session != null) {
                session.invalidate();
            }
            req.setAttribute("message", "You have been logged out");
        }
        req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username") == null ? "" : req.getParameter("username");
        String password = req.getParameter("password") == null ? "" : req.getParameter("password");

        if (username.isBlank() || password.isBlank()) {
            showError(req, resp, "Username and password cannot be empty", username);
            return;
        }

        UserDAO userDAO = new UserDAO();
        User user = userDAO.getUser(username);

        if (user == null) {
            showError(req, resp, "User not found!", username);
            return;
        }

        if (!password.equals(user.getPassword())) {
            showError(req, resp, "Wrong password!", username);
            return;
        }

        user.setLastLogin(new Date());
        userDAO.updateUser(user, null);

        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        session.setAttribute("role", user.getRole());

        resp.sendRedirect(req.getContextPath() + "/view/search.jsp");
    }

    private void showError(HttpServletRequest req, HttpServletResponse resp, String error, String username) throws ServletException, IOException {
        req.setAttribute("error", error);
        req.setAttribute("username", username);
        req.getRequestDispatcher("/view/login.jsp").forward(req, resp);
    }
}
