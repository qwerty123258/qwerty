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
			url = "FileDownLoad.jsp?bfile=${requestScope.bfile}";
			window.open(url,"파일 다운로드", "width=150, height=150, top=100,left=1600");
}
</script>
<style>
#title{

}
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
</style>
</head>
<body>
<div id="title">제목 : ${requestScope.title} <div id="modify"><a href="BoardModifyCheckPw?bnum=${requestScope.bnum}">수정하기</a></div></div>
<div>작성자 : ${requestScope.writer} <div id="bview">조회수 : ${requestScope.bview}</div></div>
<div id="writedate">
작성시간: ${requestScope.writedate}
</div>
<br>
<div id="file">
<a href="#" onclick="viewFile()">첨부파일</a>
</div>
<img src="fileUpload/${requestScope.bfile}">
<pre>
${requestScope.bcontent}
</pre><br><br><br>
<a href="PageList">글 목록</a> <a href="BoardDeleteCheckPw?bnum=${requestScope.bnum}">삭제 하기</a>
</body>
</html>