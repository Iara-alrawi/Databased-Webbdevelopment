<%--
  Created by IntelliJ IDEA.
  User: Iaraa
  Date: 2025-03-27
  Time: 12:09
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../WEB-INF/components/header.jsp" %>

<div class="container mt-4">
  <h1>Login to Library</h1>

  <form method="POST" action="${pageContext.request.contextPath}/login">

    <c:if test="${not empty error}">
      <div class="alert alert-danger">
          ${error}
      </div>
    </c:if>

    <c:if test="${not empty message}">
      <div class="alert alert-success" role="alert">
          ${message}
      </div>
    </c:if>

    <div class="mb-3">
      <label for="username" class="form-label">Username</label>
      <input type="text" id="username" name="username" value="${username}" class="form-control" aria-describedby="usernameHelp">
      <div id="usernameHelp" class="form-text">Enter your username here!</div>
    </div>

    <div class="mb-3">
      <label for="password" class="form-label">Password</label>
      <input type="password" id="password" name="password" class="form-control" aria-describedby="passwordHelp">
      <div id="passwordHelp" class="form-text">Enter your password here!</div>
    </div>

    <button type="submit" class="btn btn-primary">Login</button>
    <a href="${pageContext.request.contextPath}/view/signup.jsp" class="btn btn-secondary">Register</a>

  </form>
</div>

<%@ include file="../WEB-INF/components/footer.jsp" %>
