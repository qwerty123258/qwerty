<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이메일 수정하기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
function sendVerifyCode() {
	var emailid = document.getElementById("emailid").value
	var emaildomain = document.getElementById("emaildomain").value
	var email = emailid + "@" + emaildomain;
	var url = "sendCode?email=" + email;
	window.open(url, 'pop01',
			'width=500, height=500, status=no, menubar=no, toolbar=no');

}
function domainselect() {
	var email = document.getElementById("domainvalue").value;
	document.getElementById("emaildomain").value = email;
}
function emailUpdate(){
	var emailid = $("#emailid").val();
	var emaildomain = $("#emaildomain").val();
	var certify = $("#certify").val();
	$("#email").val(emailid + "@" + emaildomain);
	var email = $("#email").val();
	$.ajax({
		type : "POST",
		url : "checkEmail",
		data : "email=" + email,
		dataType : "text",
		success : function(result) {
			if (result == "Overlap") {
				$("#checkEmailResult").val("중복된 이메일");
			} else if (result = "NotOverlap") {
				$("#checkEmailResult").val("중복되지 않은 이메일");
			}

		},
		error : function() {
			alert("이메일 중복 체크중 에러 발생");
		}

	});
	var checkEmailResult = $("#checkEmailResult").val();
	if (emailid == "" || emaildomain == "") {
		alert("이메일 주소를 입력하세요");
		$("#emailid").focus();
	}
	else if (certify == "인증안됨") {
		alert("이메일 인증이 완료되지 않았습니다.");
	} else if (checkEmailResult == "중복된 이메일") {
		alert("입력하신 이메일 주소는 이미 사용중입니다.");
		$("#emailid").val("");
		$("#emaildomain").val("");
		$("#emailid").focus();
	}
	else{
		$.ajax({
			type : 'POST',
			url : 'emailUpdate',
			data : "email="+email,
			success : function(data) {
				if (data == "Success") {
					alert("이메일 수정 완료");
					location.href = "main";
				} else {
					alert("이메일 수정 실패");
				}
			},
			error : function() {
				alert("이메일 수정 중 실패");
			}
		});
	}
	
}
</script>
</head>
<body>
<jsp:include page="../Header.jsp"></jsp:include><br>
<jsp:include page="../Nav.jsp"></jsp:include><br>
	<div class="row">
		<div class="col-xs-3">
			<jsp:include page="SideNav.jsp"></jsp:include>
		</div>
		<div class="col-xs-9">
				이메일 주소 변경<br>
		  <input type="text" id="emailid"> @ <input type="text" id="emaildomain"> 
		  <select onchange="domainselect()" id="domainvalue">
			<option value="">직접입력</option>
			<option value="naver.com">네이버</option>
			<option value="hanmail.net">한메일</option>
			<option value="gmail.com">구글</option>
		</select><br>
		<button class="btn btn-info" type="button" onclick="sendVerifyCode()">이메일 인증하기</button><br><br>
		<input type="hidden" value="인증안됨" id="certify"> 
		<input type="hidden" id="email" name="email"> 
		<input type="hidden" id="checkEmailResult"> 
		<button class="btn btn-success" onclick="emailUpdate()">수정하기</button>
		</div>
	</div>
</body>
</html>