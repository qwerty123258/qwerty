<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="write" method="post">
작성자 <input type="text" name="writer"><br>
비밀번호 <input type="password" name="password"> <br><br>
제목
<input type="text" name="title"><br><br><br>
<textarea rows="10" cols="100" name="bcontent"></textarea>
<input type="submit">
</form>
</body>
</html>