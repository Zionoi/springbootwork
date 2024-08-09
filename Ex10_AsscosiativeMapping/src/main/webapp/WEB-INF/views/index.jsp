<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="minsert" method="post">
		ID : <input name="id"><br>
		NAME :	<input name="name"><br>
		PASSWORD : <input type="password" name="password"><br>
		<button>회원가입</button>
	</form>
	<hr>
	<form action="binsert" method="post">
		제목 : <input name="title"><br>
		내용 : <input name="content">
		<button>등록</button>
	</form>
	
	<hr>
	<form action="mupdate" method="post">
	계정 이름 바꾸기
	아이디 : <input name="id"><br>
	
	변경할 이름 : <input name="name"><br>
	<button>변경</button>
	</form>
	
</body>
</html>