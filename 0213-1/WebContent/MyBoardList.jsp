<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
     <link rel="stylesheet" href="css/base.css">
</head>
<body>
            <a id="logoar" href="MemberBoardMain.jsp">
                <img id="logo" src="images/logo.PNG">
            </a>
<table width="100%">
<tr>
<th>
글 번호
</th>
<th id="title">
글 제목
</th>
<th>
작성시간
</th>
</tr>
<c:forEach var="result" items="${board}">
<tr>
<td>
${result.bnum}
</td>
<td id="title">
<a href="BoardDetail?board=${result.bnum}&page=${paging.page}">
${result.title}
</a>
</td>
<td>
${result.writedate}
</td>
</tr>
</c:forEach>
</table>
<div id="paging">
<c:url var="action" value="/MyBoardList"/>
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
<c:if test="${sessionScope.id ne NULL}">
<a href="BoardWrite.jsp">글쓰기</a>
</c:if>
</body>
</html>