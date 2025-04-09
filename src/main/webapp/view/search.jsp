<%--
  Created by IntelliJ IDEA.
  User: Iaraa
  Date: 2025-03-27
  Time: 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../WEB-INF/components/header.jsp" %>

<div class="container mt-4">
    <h1>Search in library</h1>

    <!-- Sökformulär -->
    <form method="GET" action="${pageContext.request.contextPath}/search">
        <c:if test="${not empty error}">
            <div class="alert alert-danger">${error}</div>
        </c:if>
        <c:if test="${not empty message}">
            <div class="alert alert-success" role="alert">${message}</div>
        </c:if>
        <div class="mb-3">
            <label for="query" class="form-label">Search after books</label>
            <input type="text" id="query" name="query" value="${query}" class="form-control" placeholder="Title or Author" required>
            <div id="queryHelp" class="form-text">Type in a title or author and hit search.</div>
        </div>
        <button type="submit" class="btn btn-primary">Search</button>
    </form>

    <!-- Visa sökresultat -->
    <c:if test="${not empty queryList}">
        <h2 class="mt-4">Results for "${query}":</h2>
        <ul class="list-group">
            <c:forEach var="book" items="${queryList}">
                <li class="list-group-item">
                    <strong>${book.title}</strong> av ${book.author}<br>
                    <span>Year: ${book.year}</span> |
                    <span>Published: ${book.publishedYear}</span> |
                    <span>Type: ${book.type}</span> |
                    <span>Media: ${book.mediaType}</span> |
                    <span>Available: ${book.borrower == null ? 'Yes' : 'No'}</span>
                    <a href="${pageContext.request.contextPath}/bookDetails?bookId=${book.id}" class="btn btn-info float-end">More information</a>
                </li>
            </c:forEach>
        </ul>
    </c:if>

    <c:if test="${empty queryList}">
        <p class="mt-3 text-muted">No results found.</p>
    </c:if>
</div>

<%@ include file="../WEB-INF/components/footer.jsp" %>
