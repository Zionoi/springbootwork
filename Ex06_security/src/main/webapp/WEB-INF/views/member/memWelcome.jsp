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
	<c:if test="${!empty pageContext.request.userPrincipal }">
		사용자 정보 : ${pageContext.request.userPrincipal }<br><br>
		<h4>Login된 상태</h4>
		${pageContext.request.userPrincipal.name }님이 로그인하였습니다아아아ㅏ아아아아아아아아ㅏ아<br>
	</c:if>
	welcome member
</body>
</html>