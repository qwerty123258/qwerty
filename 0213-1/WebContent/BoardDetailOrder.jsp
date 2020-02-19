<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/detailboard.css" rel="stylesheet">
<script>
function viewFile(){
	if(document.getElementById("filedownload").style.display=="block"){
		document.getElementById("filedownload").style.display="none";
	}
	else{
		document.getElementById("filedownload").style.display="block";
	}
}
function closefilear(){
	if(document.getElementById("filedownload").style.display=="block"){
		document.getElementById("filedownload").style.display="none";
	}
	else{
		document.getElementById("filedownload").style.display="block";
	}
}
</script>
</head>
<body>
<a id="logoar" href="MemberBoardMain.jsp">
  <img id="logo" src="images/logo.PNG">
</a>

<div id="title">제목 : ${requestScope.title}
<div id="modify"> 
<c:if test="${sessionScope.id eq requestScope.writer && requestScope.writer ne null}">
<a href="BoardModifyCheckPw?bnum=${requestScope.bnum}&page=${requestScope.page}">수정하기</a></c:if>
<c:if test="${sessionScope.id eq requestScope.writer || sessionScope.id eq 'qwerty123258'}">
<a href="BoardDeleteCheckPw?bnum=${requestScope.bnum}">삭제 하기</a>
</c:if></div>
</div>
<div>작성자 : ${requestScope.writer} <div id="bview">조회수 : ${requestScope.bview}</div></div>
<div id="writedate">
작성시간: ${requestScope.writedate}
</div>
<br>
<c:if test="${requestScope.bfile ne NULL}">
<div id="file">
<a href="#" onclick="viewFile()">첨부파일</a>
</div>
</c:if>
<br><br>
<div id="main">
<div id="filedownload">
 <br>
<table>
<caption>
첨부파일 목록 <a id="close" onclick="closefilear()" href="#">닫기</a>
</caption>
<tr>
<c:forEach var="result" items="${bfile}">
<tr>
<td>
파일 이름 : <a href="FileDownload?bfile=${result}">${result}</a>
</td>
</tr>
</c:forEach>
</tr>
</table>
</div>
<div id="contentar">
<c:if test="${requestScope.bimgfile ne NULL}">
<img src="fileUpload/${requestScope.bimgfile}">
</c:if>
<pre>
${requestScope.bcontent}
</pre>
<a href="PageListOrder?page=${requestScope.page}">글 목록</a>
<br><br> 
<c:if test="${sessionScope.id ne null}">
덧글 쓰기
<form action="WriteComment?page=${requestScope.page}&bnum=${requestScope.bnum}&writer=${sessionScope.id}&commentpage=${commentpage}" method="post">
<table>
<tr class="commentar">
<td id="writerinfo">
<img id="profileimg" src="fileUpload/${sessionScope.mempicture}" onerror="this.src='fileUpload/profile.png'">
</td>
<td id="commentwritear">
<textarea id="commentwrite"  name="ccontent"></textarea>
</td>
<td id="commentbtnar">
<input id="commentbtn" type="submit" value="작성완료">
</td>
</tr>
</table>
</form>
</c:if>
<div>
<table>
<tr>
<th>
작성자
</th>
<th>
댓글 내용
</th>
<th>
작성 시간
</th>
</tr>
<c:forEach var="result" items="${commentList}">
<tr class="commentar">
<td id="commentwriterar">
${result.writer}
</td>
<td id="commentcontentar">
${result.ccontent}
<c:if test="${sessionScope.id eq result.writer}">
<a href="#">수정</a>
</c:if>
<c:if test="${sessionScope.id eq result.writer || sessionScope.id eq 'qwerty123258'}">
<a href="#">삭제</a>
</c:if>
</td>
<td id="commentwridatear">
${result.writedate}
</td>
</tr>
</c:forEach>
</table>
</div>
<div id="paging">
<c:url var="action" value="/BoardDetailOrder"/>
     <c:choose>
      <c:when test="${paging.beginPage==1}">
            <a>이전</a>
        </c:when>
        <c:otherwise>
   			 <a href="${action}?commentpage=${paging.beginPage-1}&bnum=${requestScope.bnum}&page=${requestScope.page}">이전</a>
        </c:otherwise>
    </c:choose>
<c:forEach begin="${paging.beginPage}" end="${paging.endPage}" step="1" var="commentpage">
    <c:choose>
        <c:when test="${paging.page==commentpage}">
            ${commentpage}
        </c:when>
        <c:otherwise>
            <a href="${action}?commentpage=${commentpage}&bnum=${requestScope.bnum}&page=${requestScope.page}">${commentpage}</a>
        </c:otherwise>
    </c:choose>
    </c:forEach>
        <c:choose>
        <c:when test="${paging.endPage==paging.totalPage}">
            <a>다음</a>
        </c:when>
        <c:otherwise>
   			 <a href="${action}?commentpage=${paging.endPage+1}&bnum=${requestScope.bnum}&page=${requestScope.page}">다음</a>
        </c:otherwise>
    </c:choose>
</div>
</div>
</div>
</html>