<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../WEB-INF/components/header.jsp"%>

<%@ include file="../WEB-INF/components/navbar.jsp"%>


<form action="${pageContext.request.contextPath}/search" method="GET">
    <input type="text" name="query" placeholder="Search after books...">
    <button type="submit">Search</button>
</form>


<h1><%= "Hello World!" %></h1>
<a href="hello-servlet">Hello Servlet</a>

<b>Användare: </b> ${sessionScope.user.username}


<%@include file="../WEB-INF/components/footer.jsp"%>