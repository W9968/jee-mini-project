<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="TopicsServlet">topics</a>
<a href="QuestionServlet" >questions</a>

<p>welcome: ${sessionScope.user.username}</p>

<c:if test="${sessionScope.user}">
    <a href="LogoutServlet">logout</a>
</c:if>

</body>
</html>