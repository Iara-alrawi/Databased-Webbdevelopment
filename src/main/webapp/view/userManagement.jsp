<%--
  Created by IntelliJ IDEA.
  User: Iaraa
  Date: 2025-03-27
  Time: 12:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List,se.gritacademy.webbutvecklinguppgift.model.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Manage users</title>
</head>
<body>

<jsp:include page="/WEB-INF/components/navbar.jsp"/>

<div class="container mt-4">
    <h2>Manage users</h2>

    <!-- Sökformulär -->
    <form action="${pageContext.request.contextPath}/users/" method="GET" class="mb-3">
        <input type="text" name="search" placeholder="Search users..." class="form-control w-50 d-inline">
        <input type="hidden" name="action" value="search">
        <button type="submit" class="btn btn-primary">Search</button>
    </form>

    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger">${errorMessage}</div>
    </c:if>

    <table class="table table-striped">
        <thead>
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Password</th>
            <th>Update</th>

        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/view/modifyUser.jsp?id=${user.id}" class="btn btn-warning"></a>
                </td>
                    <%--
                    <td>
                      <a href="${pageContext.request.contextPath}/users/delete" class="btn btn-danger"></a>
                    </td>
                    --%>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>
