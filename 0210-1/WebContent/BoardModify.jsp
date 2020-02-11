<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>
글 수정하기
</h2>
<form action="BoardUpdate?bnum=${requestScope.bnum}" method="post">
제목 <input type="text" name="title" value="${requestScope.title}"><br>
<br>
<textarea rows="10" cols="100" name="bcontent">${requestScope.bcontent}</textarea>
<input type="submit" value="수정하기" >
</form>
<a href="BoardDetail?board=${requestScope.bnum}">뒤로가기</a>
</body>
</html>