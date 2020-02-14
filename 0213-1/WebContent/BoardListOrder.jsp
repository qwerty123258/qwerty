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
<a href="#" onclick="window.open('DetailPopUp?id=${result.writer}','상세보기','width=430,height=500,location=no,status=no,scrollbars=yes');">
${result.writer}
</td>
</a>
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
            <c:choose>
        <c:when test="${paging.page==paging.beginPage}">
            <a>처음으로</a>
        </c:when>
        <c:otherwise>
 <a href="${action}?page=1&searchOpt=${requestScope.searchOpt}&keyword${requestScope.keyword}">처음으로</a>
        </c:otherwise>
    </c:choose>
     <c:choose>
      <c:when test="${paging.beginPage==1}">
            <a>이전</a>
        </c:when>
        <c:otherwise>
   			 <a href="${action}?page=${paging.beginPage-1}">이전</a>
        </c:otherwise>
    </c:choose>
<c:forEach begin="${paging.beginPage}" end="${paging.endPage}" step="1" var="page">
    <c:choose>
        <c:when test="${paging.page==page}">
            ${page}
        </c:when>
        <c:otherwise>
            <a href="${action}?page=${page}&searchOpt=${requestScope.searchOpt}&keyword${requestScope.keyword}">${page}</a>
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
            <c:choose>
        <c:when test="${paging.page==paging.totalPage}">
            <a>끝으로</a>
        </c:when>
        <c:otherwise>
 <a href="${action}?page=${paging.totalPage}&searchOpt=${requestScope.searchOpt}&keyword${requestScope.keyword}">끝으로</a>
        </c:otherwise>
    </c:choose>
</div>
<form action="BoardSearchOrder">
<select name="searchOpt">
<option value="제목" <c:if test="${requestScope.searchOpt eq '제목'}">selected</c:if>>제목</option>
<option value="작성자" <c:if test="${requestScope.searchOpt eq '작성자'}">selected</c:if>>작성자</option>
</select>
<input type="text" name="keyword">
<input type="submit" value="검색하기">
</form>
<c:if test="${sessionScope.id ne NULL}">
<a href="BoardWrite.jsp">글쓰기</a>
</c:if>
<a href="MemberBoardMain.jsp">홈</a>
</body>
</html>