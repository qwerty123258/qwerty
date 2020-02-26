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
글 번호
</td>
<td>
글 제목
</td>
<td>
작성자
</td>
<td>
작성날짜
</td>
</tr>
<c:forEach var="request" items="${requestList}">
<tr>
<td>
${request.rno}
</td>
<td>
<a href="#" onclick="window.open('RequestDetail?rno=${request.rno}','상세보기','width=430,height=500,location=no,status=no,scrollbars=yes');">
${request.rtitle}
</a>
</td>
<td>
${request.id}
</td>
<td>
${request.writedate}
</td>
</tr>
</c:forEach>
</table>
<div id="paging">
<c:url var="action" value="RequestList"/>
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