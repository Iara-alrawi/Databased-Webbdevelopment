package se.gritacademy.webbutvecklinguppgift.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import se.gritacademy.webbutvecklinguppgift.dao.UserDAO;
import se.gritacademy.webbutvecklinguppgift.model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/users/")
public class UserServlet extends HttpServlet {
    private UserDAO userDAO = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) {
        String action = req.getPathInfo();
        System.out.println("POST New");

        int id = Integer.parseInt(req.getParameter("id"));
        String username = req.getParameter("username");
        String role = req.getParameter("role");
        String newPassword = req.getParameter("password");

        System.out.println("🔹 doPost() runs! URL: " + req.getRequestURI());
        System.out.println("ID: " + id);
        System.out.println("Username: " + username);
        System.out.println("Role: " + role);
        System.out.println("Password: " + newPassword);

        try {

            User user = userDAO.getUserById(id);
            if (user == null) {
                req.setAttribute("errorMessage", "User not found.");
                req.getRequestDispatcher("/view/editUser.jsp").forward(req, res);
                return;
            }


            user.setUsername(username);
            user.setRole(role);
            if (newPassword != null && !newPassword.isEmpty()) {
                user.setPassword(newPassword);
            }


            userDAO.updateUser(user, newPassword != null && !newPassword.isEmpty() ? newPassword : null);


            req.setAttribute("user", user);
            req.setAttribute("successMessage", "User has been updated.!");
            req.getRequestDispatcher("/view/editUser.jsp").forward(req, res);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getPathInfo();
        System.out.println("GET");
        if (action == null) action = req.getParameter("action");

        if (action == null || action.equals("/")) {
            req.getRequestDispatcher("/view/manageUsers.jsp").forward(req, res);
        } else if ("search".equals(action)) {
            String searchQuery = req.getParameter("search");
            System.out.println("Search after users: " + searchQuery);
            User user = userDAO.searchUserByExactUsername(searchQuery);

            if (user != null && user.getBorrowedBooks() == null) {
                user.setBorrowedBooks(new ArrayList<>());
            }

            if (user == null) {
                req.setAttribute("errorMessage", "No users found with that name: " + searchQuery);
                req.setAttribute("users", List.of());
            } else {
                req.setAttribute("users", List.of(user));
            }
            req.getRequestDispatcher("/view/manageUsers.jsp").forward(req, res);
        } else {
            res.sendError(HttpServletResponse.SC_NOT_FOUND, "Site could not be found.");
        }
    }
}
