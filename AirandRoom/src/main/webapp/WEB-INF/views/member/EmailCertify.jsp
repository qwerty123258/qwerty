<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인증코드 입력</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
function reSend(email){
	var email=email;
	$.ajax({
		type : "GET",
		url : "reSendCode",
		data : "email=" + email,
		dataType : "text",
		success : function(result) {
			if(result=="Success"){
				alert("전송 완료");
			}

		},
		error : function() {
			alert("코드 전송 실패");
		}
})
	
}

function emailCertify(){
    var code=document.getElementById("code").value;
	$.ajax({
		type : "GET",
		url : "emailCertify",
		data : "code=" + code,
		dataType : "text",
		success : function(result) {
			if(result=="Success"){
				alert("인증 완료");
				opener.document.getElementById("certify").value = "인증완료";
				window.close();

			}
			else{
				alert("인증번호가 다름");
			}

		},
		error : function() {
			alert("코드 전송 실패");
		}
	})
}
</script>
</head>
<body>
이메일로 인증코드 4자리를 발송하였습니다.<br>

인증코드 4자리를 입력하여주세요.<br>

<input type="text" name="code" id="code">
<button onclick="emailCertify()">인증하기</button>
<button onclick="reSend('${email}')">재전송</button>
</body>
</html>