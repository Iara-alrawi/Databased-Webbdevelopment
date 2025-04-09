<%--
  Created by IntelliJ IDEA.
  User: Iaraa
  Date: 2025-03-27
  Time: 11:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <title>add User</title>
<body>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container-fluid">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/">Library</a>
  </div>
</nav>

<main class="container mt-5">
  <h1 class="text-center mb-4">Create new user</h1>

  <form action="${pageContext.request.contextPath}/users/create" method="post" class="w-50 mx-auto">
    <div class="mb-3">
      <label for="username" class="form-label">Username</label>
      <input type="text" class="form-control" id="username" name="username" required>
    </div>

    <div class="mb-3">
      <label for="password" class="form-label">Password</label>
      <input type="password" class="form-control" id="password" name="password" required>
    </div>

    <div class="mb-3">
      <label for="role" class="form-label">Roll</label>
      <select class="form-select" id="role" name="role">
        <option value="USER">User</option>
      </select>
    </div>

    <button type="submit" class="btn btn-success w-100"> Create User</button>
  </form>
</main>

</body>
</html>
--%>
