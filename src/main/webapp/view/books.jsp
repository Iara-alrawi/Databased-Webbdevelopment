<%--
  Created by IntelliJ IDEA.
  User: Iaraa
  Date: 2025-03-27
  Time: 12:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Booklist</title>
</head>
<body>
<h2>Booklist</h2>
<table>
    <tr>
        <th>Titel</th>
        <th>Author</th>
        <th>Type</th>
        <th>Year</th>
    </tr>
    <c:forEach var="book" items="${books}">
        <tr>
            <td>${book.title}</td>
            <td>${book.author}</td>
            <td>${book.type}</td>
            <td>${book.year}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
