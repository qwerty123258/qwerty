<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function mailSend(){
    var sendpeople=document.getElementById("send").value;
    var receivepeople=document.getElementById("receive").value;
	if(sendpeople == "") {
		alert("보내는 사람이 비어있습니다.");
		document.getElementById("boardwriter").focus();
	} 
	else if(receivepeople=="") {
		alert("받는 사람이 비어있습니다.");
		document.getElementById("boardpassword").focus();
}
	else{
		document.getElementById("sendEmail").submit();
	}
}
</script>
</head>
<body>
<form action="MailSend" method="post" id="sendEmail">
제목 : <input type="text" name="title"><br>
보내는 사람 : <input type="text" name="sender" id="send"><br>
받는 사람 : <input type="text" name="receiver" id="receive">
<br>
<textarea name="content" cols="100" rows="30"></textarea> 
</form>
<button onclick="mailSend()">
보내기
</button>
embwgsioufadwmpt
</body>
</html>