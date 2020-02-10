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
<c:forEach var="result" items="${board}">
<form action="Update" method="post">
글 번호 : ${result.bnum}
제목
<input type="text" name="title" value="${result.title}">
내용
<textarea rows="10" cols="100" name="bcontent">${result.bcontent}</textarea>
<input type="submit">
</form>
</c:forEach>
</body>
</html>