<%--
  Created by IntelliJ IDEA.
  User: Iaraa
  Date: 2025-03-27
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
  <title>Borrow History</title>
</head>
<body>

<jsp:include page="/WEB-INF/components/navbar.jsp"/>

<div class="container mt-4">
  <h2>Loan history</h2>

  <!-- Aktiva lån -->
  <h3>Books to return</h3>
  <c:if test="${empty activeBorrows}">
    <p>You have no active loans.</p>
  </c:if>
  <table class="table">
    <thead>
    <tr>
      <th>Titel</th>
      <th>Author</th>
      <th>Loan date</th>
      <th>Due date</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="borrow" items="${activeBorrows}">
      <tr>
        <td>${borrow.book.title}</td>
        <td>${borrow.book.author}</td>
        <td>${borrow.borrowDate}</td>
        <td>${borrow.dueDate}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>

  <!-- Historiska lån -->
  <h3>Books that have been returned</h3>
  <c:if test="${empty returnedBorrows}">
    <p>You have no previous loans.</p>
  </c:if>
  <table class="table">
    <thead>
    <tr>
      <th>Titel</th>
      <th>Author</th>
      <th>Loan date</th>
      <th>Due date</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="borrow" items="${returnedBorrows}">
      <tr>
        <td>${borrow.book.title}</td>
        <td>${borrow.book.author}</td>
        <td>${borrow.borrowDate}</td>
        <td>${borrow.dueDate}</td>
      </tr>
    </c:forEach>
    </tbody>
  </table>

  <a href="${pageContext.request.contextPath}/" class="btn btn-primary">back</a>
</div>

</body>
</html>
