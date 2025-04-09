<%--
  Created by IntelliJ IDEA.
  User: Iaraa
  Date: 2025-03-27
  Time: 12:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="se.gritacademy.webbutvecklinguppgift.model.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>modify User</title>
</head>
<body>

<jsp:include page="/WEB-INF/components/navbar.jsp"/>

<c:if test="${not empty successMessage}">
    <div class="alert alert-success">${successMessage}</div>
</c:if>

<c:if test="${not empty errorMessage}">
    <div class="alert alert-danger">${errorMessage}</div>
</c:if>

<div class="container mt-4">
    <h2>✏ Update users</h2>

    <form action="${pageContext.request.contextPath}/users/" method="POST" class="mb-3">

        <input type="hidden" name="id" value="${param.id}">

        <div class="mb-3">
            <label for="username" class="form-label">Username:</label>
            <input type="text" class="form-control" id="username" name="username">
        </div>

        <div class="mb-3">
            <label for="password" class="form-label">New password (valfritt):</label>
            <input type="password" class="form-control" id="password" name="password">
        </div>

        <div class="mb-3">
            <label for="role" class="form-label">Roll:</label>
            <select class="form-select" id="role" name="role">
                <option value="USER" ${user.role == 'USER' ? 'selected' : ''}>User</option>
            </select>
        </div>

        <button type="submit" class="btn btn-success">Save</button>
        <a href="${pageContext.request.contextPath}/view/modifyUser.jsp" class="btn btn-secondary">🔙 Tillbaka</a>
    </form>
</div>

</body>
</html>

