<%--
  Created by IntelliJ IDEA.
  User: wales
  Date: 5/7/2023
  Time: 10:41 AM
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

<p>hello questions</p>

<a href="QuestionServlet?action=create">Add New question</a>

<form method="get" action="QuestionServlet" class="flex">
    <label>
        <input type="text" name="value" placeholder="Search...">
    </label>
    <button type="submit" name="action" value="search">
        Search
    </button>
</form>

<form method="get" action="QuestionServlet" class="flex" >
    <select name="topicId" >
        <option value="0" >select to filter</option>
        <c:forEach items="${topics}" var="topic">
            <option value="${topic.id}" <c:if test="${param['topicId'] == topic.id}">selected</c:if> >${topic.topicName}</option>
        </c:forEach>
    </select>
    <button type="submit" name="action" value="filter">
        Search
    </button>
</form>

<table>
    <thead>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>description</th>
        <th>belongs to</th>
        <th>mcq</th>
        <th>action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${questions}" var="question">
        <tr>
            <td>${question.id}</td>
            <td>${question.title}</td>
            <td>${question.description}</td>
            <td>${question.topic.topicName}</td>
            <td>${fn:length(question.choices)}</td>
            <td>
                <a href="QuestionServlet?action=show&uid=${question.id}">show</a>
                <a href="QuestionServlet?action=update&uid=${question.id}">Edit</a>
                <a href="QuestionServlet?action=delete&uid=${question.id}">delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
