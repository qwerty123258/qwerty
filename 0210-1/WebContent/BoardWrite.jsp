<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function writeboard(){
    var writer=document.getElementById("boardwriter").value;
    var password=document.getElementById("boardpassword").value;
    var title=document.getElementById("boardtitle").value;
	if(writer == "") {
		alert("작성자가 비어있습니다.");
		document.getElementById("boardwriter").focus();
	} 
	else if(password=="") {
		alert("비밀번호가 비어있습니다.");
		document.getElementById("boardpassword").focus();
}
	else if(title==""){
		alert("제목이 비어있습니다.");
		document.getElementById("boardtitle").focus();
	}
	else{
		document.getElementById("boardwrite").submit();
	}
}
</script>
</head>
<body>
<h2>
글 쓰기
</h2>
<form action="BoardWrite" method="post" id="boardwrite">
작성자 <input type="text" name="writer" id="boardwriter"><br>
비밀번호 <input type="password" name="bpassword" id="boardpassword"> <br><br>
제목
<input type="text" name="title" id="boardtitle"><br><br><br>
<textarea rows="10" cols="100" name="bcontent"></textarea>
</form>
<button onclick="writeboard()">작성완료</button>
<a href="BoardMain.jsp">홈</a>
<a href="PageList">글 목록보기</a>
</body>
</html>