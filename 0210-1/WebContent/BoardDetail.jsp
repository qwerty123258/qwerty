<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
</style>
</head>
<body>
<div id="title">제목 : ${requestScope.title} <div id="modify"><a href="BoardCheckPw?bnum=${requestScope.bnum}">수정하기</a></div></div>
<div>작성자 : ${requestScope.writer} <div id="bview">조회수 : ${requestScope.bview}</div></div>
<div id="writedate">
작성시간: ${requestScope.writedate}
</div>
<br><br>
<pre>
${requestScope.bcontent}
</pre><br><br><br>
<a href="BoardList">글 목록</a> <a href="BoardDelete?bnum=${requestScope.bnum}">삭제 하기</a>
</body>
</html>