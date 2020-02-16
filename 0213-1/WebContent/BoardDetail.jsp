<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function viewFile(){
	if(document.getElementById("filedownload").style.display=="block"){
		document.getElementById("filedownload").style.display="none";
	}
	else{
		document.getElementById("filedownload").style.display="block";
	}
}
</script>
<style>
#bview{
float:right;
}
#modify{
float:right;
}
#writedate{
float:right;
}
#file{
float:right;
}
#filedownload{
display:none;
position:absolute;
z-index:1000;
left:80%;
right:15%;
border:black solid 1px;
width:10%;
}
#contentar{
position:absolute;
z-index:900;
}
</style>
</head>
<body>
<div id="title">제목 : ${requestScope.title} 
<c:if test="${sessionScope.id eq requestScope.writer}">
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
<p>
첨부파일 이름
</p>
<b><a href="FileDownload?bfile=${requestScope.bfile}">${requestScope.bfile}</a></b>
</div>
<c:if test="${requestScope.bimgfile ne NULL}">
<img src="fileUpload/${requestScope.bimgfile}">
</c:if>
<div id="contentar">
<pre>
${requestScope.bcontent}
</pre>
</div><br><br><br>
<a href="PageList?page=${requestScope.page}">글 목록</a> 
<c:if test="${sessionScope.id eq requestScope.writer || sessionScope.id eq 'qwerty123258'}">
<a href="BoardDeleteCheckPw?bnum=${requestScope.bnum}">삭제 하기</a>
</c:if>
</body>
</html>