<%--
  Created by IntelliJ IDEA.
  User: Iaraa
  Date: 2025-03-27
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <title>add new book</title>
</head>
<body>
<div class="container">
  <h2 class="mt-4">add new book</h2>

  <c:if test="${not empty error}">
    <div class="alert alert-danger">${error}</div>
  </c:if>

  <form action="${pageContext.request.contextPath}/books" method="post">
    <div class="mb-3">
      <label for="title" class="form-label">Titel</label>
      <input type="text" class="form-control" id="title" name="title" required>
    </div>

    <div class="mb-3">
      <label for="author" class="form-label">Author</label>
      <input type="text" class="form-control" id="author" name="author">
    </div>

    <div class="mb-3">
      <label for="type" class="form-label">Typ</label>
      <select class="form-select" id="type" name="type" required>
        <option value="Book">Book</option>
        <option value="Tidskrifter">Tidskrifter</option>
        <option value="Media">Media</option>
      </select>
    </div>

    <div class="mb-3">
      <label for="year" class="form-label">year</label>
      <input type="number" class="form-control" id="year" name="year" required>
    </div>

    <button type="submit" class="btn btn-primary">add book</button>
  </form>

</div>
</body>
</html>
