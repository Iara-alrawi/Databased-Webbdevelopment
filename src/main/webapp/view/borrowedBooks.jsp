<%--
  Created by IntelliJ IDEA.
  User: Iaraa
  Date: 2025-03-27
  Time: 12:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>My borrowed books</title>
</head>
<body>

<jsp:include page="/WEB-INF/components/navbar.jsp"/>

<div class="container mt-4">
  <h2>my borrowed books</h2>

  <c:if test="${not empty sessionScope.returnMessage}">
    <div class="alert alert-success" id="returnMessage">
        ${sessionScope.returnMessage}
    </div>
    <%
      session.removeAttribute("returnMessage");
    %>
  </c:if>

  <c:choose>
    <c:when test="${empty borrowedBooks}">
      <p>You have no loaned boooks.</p>
    </c:when>
    <c:otherwise>
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
        <c:forEach var="borrow" items="${borrowedBooks}">
          <tr>
            <td>${borrow.book.title}</td>
            <td>${borrow.book.author}</td>
            <td>${borrow.borrowDate}</td>
            <td>${borrow.dueDate}</td>
            <td>
              <form action="${pageContext.request.contextPath}/returnBook" method="post">
                <input type="hidden" name="borrowId" value="${borrow.id}" />
                <button type="submit" class="btn btn-danger">Return</button>
              </form>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </c:otherwise>
  </c:choose>

  <a href="${pageContext.request.contextPath}/" class="btn btn-primary">Tillbaka</a>
</div>

</body>
</html>
