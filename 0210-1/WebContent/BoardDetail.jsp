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
작성자 : ${result.writer}<br>
글 번호 : ${result.bnum} 제목 : ${result.title} 조회수 : ${result.bview}<br>
본문 : ${result.bcontent}<br>
<a href="Modify?bnum=${result.bnum}">수정하기</a>
</c:forEach>
<a href="Main.jsp">홈으로</a>
</body>
</html>