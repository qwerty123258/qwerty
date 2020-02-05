<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이름 입력창</title>
</head>
<body>
	<h2>InputDB.jsp 파일
	</h2>
	<form action="inputServer" method="post">
         <label for="id_input">이름</label> 
         <input type="text" id="id_input" name="data1" autocomplete="off"> 
	<input type="submit">
	</form>
</body>
</html>