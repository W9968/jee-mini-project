<%--
  Created by IntelliJ IDEA.
  User: wales
  Date: 5/7/2023
  Time: 1:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<form action="ChoicesServlet" method="POST">
    <input value="${choice.id}" hidden name="uid" >
    <input value="${question}" hidden name="question_id" >
    <input value="${choice.content}" type="text" name="content" placeholder="name">

    <select name="correct" >
        <option value="0">this is just a choice</option>
        <option value="1">this is the correct anwser</option>
    </select>

    <% if (request.getParameter("action").equals("update")) { %>
    <button id="formik-button" name="action" value="edit">Update</button>
    <% } else { %>
    <button id="formik-button" name="action" value="store">Create</button>
    <% } %>
</body>
</form>
</html>
