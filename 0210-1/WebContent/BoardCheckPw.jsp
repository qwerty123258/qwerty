<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
function checkPw(){
	var pass="${requestScope.bpassword}";
	if(document.getElementById("checkPass").value==pass){
		document.getElementById("pwCheck").submit();
	}
	else{
		alert("작성시 비밀번호와 다릅니다.");
		document.getElementById("checkPass").focus();
	}
}
</script>
<body>
<h2>비밀번호 확인
</h2>
<form action="BoardModify?bnum=${requestScope.bnum}" id="pwCheck" method="post">
<input type="password" id="checkPass" name="bpassword">
</form>
<button onclick="checkPw()">체크</button>
</body>
</html>