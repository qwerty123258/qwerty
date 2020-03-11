<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인 페이지</title>
</head>
<body>
<c:if test="${sessionScope.id eq null}">
<form action="Login" method="post">
아이디<br>
<input type="text" name="id"><br>
비밀번호<br>
<input type="password" name="pw">
<input type="submit" value="로그인">
</form>
<a href="CreateMembers">회원가입</a>
</c:if>
<c:if test="${sessionScope.id ne null}">
${sessionScope.id}
<c:if test="${sessionScope.id eq 'qwerty123258'}">
<a href="goSelect">전체 조회</a>
</c:if>
<a href="goModify?id=${sessionScope.id}">정보수정</a>
<a href="goDelete?id=${sessionScope.id}">탈퇴</a>
<a href="goLogout">로그아웃</a>
</c:if>
</body>
</html>