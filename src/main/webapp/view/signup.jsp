<%--
  Created by IntelliJ IDEA.
  User: Iaraa
  Date: 2025-03-27
  Time: 12:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/components/header.jsp" %>

<h1>Registrera dig</h1>
<c:if test="${empty success}">
    <form method="POST" action="${pageContext.request.contextPath}/signup">
        <c:if test="${not empty error}">
            <div class="alert alert-danger" role="alert">
                    ${error}
            </div>
        </c:if>
        <div class="mb-3">
            <label for="username" class="form-label">Username</label>
            <input type="text" class="form-control" id="username" name="username" value="${username}" aria-describedby="usernameHelp">
            <div id="usernameHelp" class="form-text">Enter your username here</div>
        </div>
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email" name="email" value="${email}" aria-describedby="emailHelp">
            <div id="emailHelp" class="form-text">Enter your email here</div>
        </div>
        <div class="mb-3">
            <label for="password1" class="form-label">Password</label>
            <input type="password" class="form-control" id="password1" name="password1" value="${password1}">
        </div>
        <div class="mb-3">
            <label for="password2" class="form-label">Password (again)</label>
            <input type="password" class="form-control" id="password2" name="password2" value="${password2}">
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
    </form>
    <p>You already have an account, you can <a href="${pageContext.request.contextPath}/view/login.jsp">sign in here</a>.</p>
</c:if>
<c:if test="${not empty success}">
    <div class="alert alert-success" role="alert">
            ${success}
    </div>
    <p>
        <a href="${pageContext.request.contextPath}/login">Click here to login!</a>
    </p>
</c:if>

<%@ include file="/WEB-INF/components/footer.jsp" %>
