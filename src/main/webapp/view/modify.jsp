<%--
  Created by IntelliJ IDEA.
  User: Iaraa
  Date: 2025-03-27
  Time: 11:50
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/components/header.jsp"%>
<%@ include file="/WEB-INF/components/navbar.jsp"%>


<h2>Edit Account</h2>

<form method="POST" action="/accounts">

    <input type="hidden" name="id" value="${account.id}">

    <div class="mb-3">
        <label for="name" class="form-label">Name</label>
        <input type="text" name="name" id="name" placeholder="Acme Inc" value="${account.name}">
    </div>



    <input type="submit" value="Save" class="btn btn-primary">
    <a href="/accounts" class="btn btn-secondary ms-2"></a>


</form>










<%@include file="/WEB-INF/components/footer.jsp"%>
