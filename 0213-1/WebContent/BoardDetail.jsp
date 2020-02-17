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
<c:if test="${sessionScope.id eq requestScope.writer && requestScope.writer ne null}">
<div id="modify"><a href="BoardModifyCheckPw?bnum=${requestScope.bnum}&page=${requestScope.page}">수정하기</a></div>
</c:if></div>
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
<div id="filedownload">
첨부파일 이름 <a id="close" onclick="closefilear()" href="#">닫기</a><br>
<a href="FileDownload?bfile=${requestScope.bfile}">${requestScope.bfile}</a>
</div>
<div id="contentar">
<c:if test="${requestScope.bimgfile ne NULL}">
<img src="fileUpload/${requestScope.bimgfile}">
</c:if>
<pre>
${requestScope.bcontent}
</pre>
</div>
<div class="footer">
<c:if test="${sessionScope.id ne null}">
덧글 쓰기
<form action="WriteComment?page=${requestScope.page}&bnum=${requestScope.bnum}" method="post">
<input type="text" name="writer" value="${sessionScope.id}" readonly="true">
<textarea name="ccontent"></textarea>
<input type="submit" value="작성완료">
</form>
</c:if>
<table>
<c:forEach var="result" items="${commentList}">
<tr>
<td>
${result.cnum}
</td>
<td>
${result.writer}
</td>
<td>
${result.ccontent}
</td>
<td>
${result.writedate}
</td>
</tr>
</c:forEach>
</table>
<a href="PageList?page=${requestScope.page}">글 목록</a> 
<c:if test="${sessionScope.id eq requestScope.writer || sessionScope.id eq 'qwerty123258'}">
<a href="BoardDeleteCheckPw?bnum=${requestScope.bnum}">삭제 하기</a>
</c:if>
</div>
</html>