<%--
  Created by IntelliJ IDEA.
  User: Iaraa
  Date: 2025-03-27
  Time: 12:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List, se.gritacademy.webbutvecklinguppgift.model.Borrow" %>
<!DOCTYPE html>
<html>
<head>
    <title>userLoans</title>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/">Library</a>
        <a href="${pageContext.request.contextPath}/view/admin.jsp" class="btn btn-secondary">⬅ Back</a>
    </div>
</nav>

<main class="container mt-5">
    <h1 class="text-center mb-4">Search loaned books</h1>


    <form action="${pageContext.request.contextPath}/userLoans" method="get" class="mb-4">
        <div class="input-group">
            <input type="text" name="search" class="form-control" placeholder="add username..." value="${searchQuery}">
            <button type="submit" class="btn btn-primary">Search</button>
        </div>
    </form>

    <%
        List<Borrow> borrowedBooks = (List<Borrow>) request.getAttribute("borrowedBooks");
        List<Borrow> returnedBooks = (List<Borrow>) request.getAttribute("returnedBooks");
    %>

    <% if (borrowedBooks != null && !borrowedBooks.isEmpty()) { %>
    <h2 class="text-center text-primary">📌 Unreturned books for <%= request.getAttribute("searchQuery") %></h2>
    <table class="table table-striped">
        <thead class="table-warning">
        <tr>
            <th>#</th>
            <th>Booktitle</th>
            <th>Loaned by</th>
            <th>Loan date</th>
            <th>due date</th>
        </tr>
        </thead>
        <tbody>
        <% for (Borrow borrow : borrowedBooks) { %>
        <tr>
            <td><%= borrow.getId() %></td>
            <td><%= borrow.getBook().getTitle() %></td>
            <td><%= borrow.getBorrowerName() %></td>
            <td><%= borrow.getBorrowDate() %></td>
            <td><%= borrow.getDueDate() %></td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <% } %>

    <% if (returnedBooks != null && !returnedBooks.isEmpty()) { %>
    <h2 class="text-center text-success"> Returned books for <%= request.getAttribute("searchQuery") %></h2>
    <table class="table table-striped">
        <thead class="table-success">
        <tr>
            <th>#</th>
            <th>Book title</th>
            <th>Loaned by</th>
            <th>Loan date</th>
            <th>Due date</th>
        </tr>
        </thead>
        <tbody>
        <% for (Borrow borrow : returnedBooks) { %>
        <tr>
            <td><%= borrow.getId() %></td>
            <td><%= borrow.getBook().getTitle() %></td>
            <td><%= borrow.getBorrowerName() %></td>
            <td><%= borrow.getBorrowDate() %></td>
            <td><%= borrow.getDueDate() %></td>
        </tr>
        <% } %>
        </tbody>
    </table>
    <% } %>

    <% if ((borrowedBooks == null || borrowedBooks.isEmpty()) && (returnedBooks == null || returnedBooks.isEmpty()) && request.getParameter("search") != null) { %>
    <p class="text-center text-danger">No loans found for <%= request.getParameter("search") %>.</p>
    <% } %>

</main>

</body>
</html>
