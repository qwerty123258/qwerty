<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
td,th{
border:black solid 1px;
width:150px;
text-align:center;
padding:3px;
}
</style>
</head>
<body>
<table>
<tr>
<th>
글 번호
</th>
<th>
글 제목
</th>
<th>
작성자
</th>
<th>
작성일자
</th>
<th>
조회수
</th>
<th>
글 내용 보기
</th>
</tr>
<c:forEach var="result" items="${board}">
<tr>
<td>
${result.bnum}
</td>
<td>
${result.title}
</td>
<td>
${result.writer}
</td>
<td>
${result.writedate}
</td>
<td>
${result.bview}
</td>
<td>
<a href="Detail?board=${result.bnum}">
보기
</a>
</td>
</tr>
</c:forEach>
</table>
</body>
</html>