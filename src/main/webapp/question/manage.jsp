<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wales
  Date: 5/7/2023
  Time: 10:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="QuestionServlet" method="POST"  enctype="multipart/form-data" >
    <input value="${question.id}" hidden name="uid" >
    <input value="${topic}" hidden name="topic_id" >
    <input value="${question.title}" type="text" name="title" placeholder="name">
    <input value="${question.description}" type="text" name="description" placeholder="description">

    <% if (request.getParameter("action").equals("update")) { %>
    <button id="formik-button" name="action" value="edit">Update</button>
    <% } else { %>
    <button id="formik-button" name="action" value="store">Create</button>
    <% } %>
</form>

</body>
</html>
