<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
title{
width:100%;
}
td,th{
border:black solid 1px;
text-align:center;
padding:3px;
}
#title{
	width:60%;
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
작성시간
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
<a href="BoardDetailOrder?board=${result.bnum}&page=${paging.page}">
${result.title}
</a>
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
</tr>
</c:forEach>
</table>
<div id="paging">
<c:url var="action" value="/PageListOrder"/>
 <a href="${action}?page=1">처음으로</a>
     <c:choose>
      <c:when test="${paging.beginPage==1}">
            <a>이전</a>
        </c:when>
        <c:otherwise>
   			 <a href="${action}?page=${paging.beginPage-1}">이전</a>
        </c:otherwise>
    </c:choose>
<c:forEach begin="${paging.beginPage}" end="${paging.endPage}" step="1" var="index">
    <c:choose>
        <c:when test="${paging.page==index}">
            ${index}
        </c:when>
        <c:otherwise>
            <a href="${action}?page=${index}">${index}</a>
        </c:otherwise>
    </c:choose>
    </c:forEach>
        <c:choose>
        <c:when test="${paging.endPage==paging.totalPage}">
            <a>다음</a>
        </c:when>
        <c:otherwise>
   			 <a href="${action}?page=${paging.endPage+1}">다음</a>
        </c:otherwise>
    </c:choose>
 <a href="${action}?page=${paging.totalPage}">끝으로</a>
</div>
<a href="BoardWrite.jsp">글쓰기</a>
<a href="LoginMain.jsp">홈</a>
</body>
</html>