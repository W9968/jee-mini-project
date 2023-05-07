<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: wales
  Date: 5/7/2023
  Time: 10:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<form action="QuestionServlet" method="POST">
    <input value="${question.id}" hidden name="uid" >
    <input value="${question.title}" type="text" name="title" placeholder="name">
    <input value="${question.description}" type="text" name="description" placeholder="description">
    <select name="topic_id">
        <c:forEach items="${topics}" var="topic">
            <option value="${topic.id}" <c:if test="${question.topic.id == topic.id}">selected</c:if> >${topic.topicName}</option>
        </c:forEach>
    </select>

    <% if (request.getParameter("action").equals("update")) { %>
    <button id="formik-button" name="action" value="edit">Update</button>
    <% } else { %>
    <button id="formik-button" name="action" value="store">Create</button>
    <% } %>
</form>

<!--<script>
    document.querySelector("#formik-button").addEventListener("click", function (e) {
        e.preventDefault();
    })
</script> -->
</body>
</html>
