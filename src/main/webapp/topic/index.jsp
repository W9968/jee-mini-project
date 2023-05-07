<%--
  Created by IntelliJ IDEA.
  User: wales
  Date: 5/4/2023
  Time: 8:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<p>hello topics</p>

<a href="TopicsServlet?action=create">Add New Plate</a>

<form method="get" action="TopicsServlet" class="flex">
    <label>
        <input type="text" name="value" placeholder="Search...">
    </label>
    <button type="submit" name="action" value="search">
        Search
    </button>
</form>

<table>
    <thead>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>description</th>
            <th>image</th>
            <th>published</th>
            <th>action</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${topics}" var="topic">
            <tr>
                <td>${topic.id}</td>
                <td>${topic.topicName}</td>
                <td>${topic.topicDescription}</td>
                <td>
                    <img width="36" height="36" src="${pageContext.request.contextPath}/@config/uploads/${topic.topicImage}" />
                </td>
                <td>${topic.topicIsPublished}</td>
                <td>
                    <a href="TopicsServlet?action=update&uid=${topic.id}">Edit</a>
                    <a href="TopicsServlet?action=delete&uid=${topic.id}">delete</a>
                    <c:choose>
                        <c:when test="${topic.topicIsPublished == false}">
                            <a href="TopicsServlet?action=publish&uid=${topic.id}&isPublish=true">publish</a>
                        </c:when>
                        <c:otherwise>
                            <a href="TopicsServlet?action=publish&uid=${topic.id}&isPublish=false">deactivate</a>
                        </c:otherwise>
                    </c:choose>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
