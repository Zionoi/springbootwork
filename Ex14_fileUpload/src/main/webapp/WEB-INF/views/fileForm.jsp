<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>File Upload</h1>
					<!--업로드할땐 이 두설정 반드시 해줘야함 method="post" enctype="multipart/form-data" -->
	<form action="fileUpLoad" method="post" enctype="multipart/form-data"> 
		파일 : <br>
		<input type="file" name="files">
		<input type="file" name="files"><br>
		<input type="submit" value="FILE UPLOAD">
	</form>

</body>
</html>