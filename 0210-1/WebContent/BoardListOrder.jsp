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
width:60px;
text-align:center;
padding:3px;
}
#title{
	width:800px;
}
</style>
</head>
<body>
<h2>
글 목록(조회수순)
</h2>
<table>
<tr>
<th>
글 번호
</th>
<th id="title">
글 제목
</th>
<th>
작성자
</th>
<th>
<a href="PageList">
조회수
</a>
</th>
</tr>
<c:forEach var="result" items="${board}">
<tr>
<td>
${result.bnum}
</td>
<td id="title">
<a href="BoardDetail?board=${result.bnum}">
${result.title}
</a>
</td>
<td>
${result.writer}
</td>
<td>
${result.bview}
</td>
</tr>
</c:forEach>
</table>
<jsp:include page="paging.jsp">
    <jsp:param value="${paging.page}" name="page"/>
    <jsp:param value="${paging.beginPage}" name="beginPage"/>
    <jsp:param value="${paging.endPage}" name="endPage"/>
    <jsp:param value="${paging.prev}" name="prev"/>
    <jsp:param value="${paging.next}" name="next"/>
</jsp:include>
<a href="BoardWrite.jsp">글쓰기</a>
<a href="BoardMain.jsp">홈</a>
</body>
</html>