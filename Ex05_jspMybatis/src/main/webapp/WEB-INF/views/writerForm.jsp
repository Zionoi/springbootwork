<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
<style>
	body {width:800px; margin:0 auto;}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>글 작 성</h2>
	<form action="write" method="post">
		작성자 : <input type="text" class="form-control" id="writer" name="writer">
		제 목 : <input type="text" class="form-control" id="title" name="title">
		내 용 : <textarea cols="100" rows="5" class="form-control" id="content" name="content"></textarea>
		<button type="submit">글쓰기</button>
		<a href="list"><button type="button" class="btn btn-outline-success">목록</button></a>
	</form>





</body>
</html>