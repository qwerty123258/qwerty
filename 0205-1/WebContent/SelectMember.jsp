<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!-- JSTL JavaServer Page Standard Tag Library -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>Select.jsp 파일
</h2>
<c:forEach var="result" items="${member}">
${result}<br>
</c:forEach>
</body>
</html>