<%--
  Created by IntelliJ IDEA.
  User: wales
  Date: 5/7/2023
  Time: 1:23 PM
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

<p>title => ${question.title}</p>
<p>content => ${question.description}</p>
<p>author => ${question.topic.topicName}</p>

<b>
    <a href="ChoicesServlet?action=create&question=${question.id}" >add choices</a>
</b>

<table>
    <thead>
    <tr>
        <th>id</th>
        <th>content</th>
        <th>is right anwser</th>
        <th>action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${question.choices}" var="choice">
        <tr>
            <td>${choice.id}</td>
            <td>${choice.content}</td>
            <td>${choice.correct}</td>
            <td>
                <a href="ChoicesServlet?action=update&uid=${choice.id}">Edit</a>
                <a href="ChoicesServlet?action=delete&uid=${choice.id}">delete</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</body>
</html>
