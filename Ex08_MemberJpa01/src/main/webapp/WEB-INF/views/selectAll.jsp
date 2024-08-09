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
	<h1>Member JPA #01 - SelectAll</h1>
	<c:forEach var="list"	items="${mList}">
	아이디 : ${list.id }<br>
	이름 : ${list.username }<br>
	날짜 : ${list.createDate }<br>
	<hr>
	</c:forEach>
	

</body>
</html>