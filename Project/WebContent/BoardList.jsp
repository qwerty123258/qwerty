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
<table>
<tr>
<td>
카테고리
</td>
<td>
글 번호
</td>
<td>
글 제목
</td>
<td>
작성자
</td>
<td>
조회수
</td>
<td>
작성날짜
</td>
</tr>
<c:forEach var="board" items="${boardList}">
<tr>
<td>
${board.category}
</td>
<td>
${board.bno}
</td>
<td>
<a href="BoardDetail?bno=${board.bno}">
${board.title}
</a>
</td>
<td>
${board.id}
</td>
<td>
${board.bview}
</td>
<td>
${board.writedate}
</td>
</tr>
</c:forEach>
</table>
<div id="paging">
<c:url var="action" value="BoardList"/>
            <c:choose>
        <c:when test="${paging.page==paging.beginPage}">
            <a>처음으로</a>
        </c:when>
        <c:otherwise>
 <a href="${action}?page=1">처음으로</a>
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
            <a href="${action}?page=${page}">${page}</a>
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
 <a href="${action}?page=${paging.totalPage}">끝으로</a>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>