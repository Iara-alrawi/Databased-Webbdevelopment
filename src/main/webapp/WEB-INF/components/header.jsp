<%--
  Created by IntelliJ IDEA.
  User: Iaraa
  Date: 2025-03-27
  Time: 12:21
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>

    <title>Library</title>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <div class="container-fluid">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/">Bibliotek</a>

        <div class="d-flex">
            <c:if test="${empty sessionScope.user}">
                <!-- Om användaren INTE är inloggad: Visa "Logga in" -->
                <a href="${pageContext.request.contextPath}/login" class="btn btn-outline-primary">Log in</a>

                <!-- Sök-knapp som leder till search.jsp -->
                <a href="${pageContext.request.contextPath}/view/search.jsp" class="btn btn-primary ms-2">Search</a>

            </c:if>

            <c:if test="${not empty sessionScope.user}">
                <!-- Om användaren är inloggad: Visa "Logga ut" -->
                <a href="${pageContext.request.contextPath}/login?logout=true" class="btn btn-danger">Log out</a>

                <!-- Sök-knapp som leder till search.jsp -->
                <a href="${pageContext.request.contextPath}/view/search.jsp" class="btn btn-primary ms-2">Search</a>

                <!-- Sök-knapp som leder till borrowedBooks -->
                <a href="${pageContext.request.contextPath}/borrowedBooks" class="btn btn-outline-secondary">My loans</a>


                <a href="${pageContext.request.contextPath}/borrowHistory" class="btn btn-outline-info ms-2">History</a>
            </c:if>


        </div>
    </div>
</nav>

<main class="container">


