<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보수정</title>
</head>
<body>
	                    <c:forEach var="member" items="${memberList}">
	                    <form action="Update?id=${sessionScope.id}" method="post">
	                   		<input type="text" name="name" value="${member.name}">
	                   		<input type="submit" value="수정">
	                    </form>     
	                    </c:forEach>
</body>
</html>