<%--
  Created by IntelliJ IDEA.
  User: Iaraa
  Date: 2025-03-27
  Time: 12:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="sv">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Search Results</title>
</head>
<body>

<h1>Search</h1>

<!-- Sökruta -->
<form action="searchResults.jsp" method="get">
    <input type="text" name="query" placeholder="Enter title or author" required>
    <button type="submit">Search</button>
</form>


<c:if test="${not empty mediaList}">
    <h2>Search Results:</h2>
    <ul>
        <c:forEach var="media" items="${mediaList}">
            <li><strong>${media.title}</strong> by ${media.author}</li>
        </c:forEach>
    </ul>
</c:if>


</body>
</html>
