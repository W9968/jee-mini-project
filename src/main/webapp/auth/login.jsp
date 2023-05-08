<%--
  Created by IntelliJ IDEA.
  User: wales
  Date: 5/8/2023
  Time: 9:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Title</title>
</head>
<body>

  <form method="POST" action="AuthServlet" >

    <input name="username" type="text" placeholder="username" >
    <input name="password" type="password" placeholder="password" >

    <button type="submit">Login</button>

  </form>

  <c:if test="${error != null}">
    <span>${error}</span>
  </c:if>

</body>
</html>
