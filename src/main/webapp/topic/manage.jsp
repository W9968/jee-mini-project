<%--
  Created by IntelliJ IDEA.
  User: wales
  Date: 5/4/2023
  Time: 8:38 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<form action="TopicsServlet" method="POST" >
    <input value="${topic.id}" hidden name="uid" >
    <input value="${topic.topicName}" type="text" name="topicName" placeholder="name">
    <input value="${topic.topicDescription}" type="text" name="topicDescription" placeholder="description">

    <% if (request.getParameter("action").equals("update")) { %>
        <button name="action" value="edit">Update</button>
    <% } else { %>
        <button name="action" value="store">Create</button>
    <% } %>
</form>

</body>
</html>