<%--
  Created by IntelliJ IDEA.
  User: wales
  Date: 5/7/2023
  Time: 1:16 PM
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

<img width="36" height="36" src="${pageContext.request.contextPath}/@config/uploads/${topic.topicImage}" />
<p>name => ${topic.topicName}</p>
<p>published => ${topic.topicIsPublished}</p>
<p>description =>  ${topic.topicDescription}</p>

<a href="QuestionServlet?action=create&topic=${topic.id}" >add new question</a>

<table>
  <thead>
  <tr>
    <th>id</th>
    <th>name</th>
    <th>description</th>
    <th>action</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach items="${topic.questions}" var="question">
    <tr>
      <td>${question.id}</td>
      <td>${question.title}</td>
      <td>${question.description}</td>
      <td>
        <a href="QuestionServlet?action=update&uid=${question.id}&topic=${topic.id}">Edit</a>
        <a href="QuestionServlet?action=delete&uid=${question.id}&topic=${topic.id}">delete</a>
      </td>
    </tr>
  </c:forEach>
  </tbody>
</table>

</body>
</html>
