<%--
  Created by IntelliJ IDEA.
  User: Iaraa
  Date: 2025-03-27
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="../WEB-INF/components/header.jsp" %>

<div class="container">
    <h1>${book.title}</h1>
    <p><strong>Author:</strong> ${book.author}</p>
    <p><strong>Book year:</strong> ${book.year}</p>
    <p><strong>Type:</strong> ${book.type}</p>


    <div class="mb-3">
        <strong>Availability:</strong>
        <c:if test="${book.available}">
            Available
        </c:if>
        <c:if test="${!book.available}">
            Not available
        </c:if>
    </div>

    <div class="mb-3">
        <c:if test="${not empty sessionScope.user}">
            <c:if test="${!book.reserved}">
                <form action="${pageContext.request.contextPath}/borrowBook" method="post">
                    <input type="hidden" name="bookId" value="${book.id}" />
                    <button type="submit" class="btn btn-success">Loan</button>
                </form>
            </c:if>
        </c:if>
    </div>
</div>

<%@ include file="../WEB-INF/components/footer.jsp" %>
