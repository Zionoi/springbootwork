<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>Member JPA #02 - Select${title }</h1>
	
	<c:forEach var="member" items="${ mList }">
	ID : ${member.id } <br>
	EMAIL : ${member.email }<br>
	NAME : ${member.username }<br>
	<hr>
	</c:forEach>

</body>
</html>