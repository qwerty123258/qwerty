<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">	
<title>로그인</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://apis.google.com/js/platform.js" async defer></script>
<meta name="google-signin-client_id"
	content="20725507984-gsrbklib5246f9bve87ajhk1m7dlgdmm.apps.googleusercontent.com">
<script>
function goJoinForm(){
	location.href="welcome";
}
function login(){
	var id=$("#id").val();
	var pw=$("#pw").val();
	var checkAutoLogin;
	if($("input:checkbox[id='checkAutoLogin']").is(":checked")){
		checkAutoLogin=true;
	}
	else{
		checkAutoLogin=false;
	}
	$.ajax({
		type : "POST",
		url : "login",
		data : "id=" + id + "&pw="+pw + "&checkAutoLogin="+ checkAutoLogin,
		dataType : "text",
		success : function(result) {
			if(result=="Success"){
					location.href="main";
			}
			else if(result=="Fail"){
				alert("아이디 또는 비밀번호가 맞지 않습니다.");
			}
			else if(result=="report"){
				alert("신고누적으로 하루동안 로그인이 불가능합니다.");
			}
		},
		error : function() {
			alert("로그인 중 에러 발생");
		}
	})
	
}

function search(){
	location.href="goSearch";
}

function enter(){
    if (window.event.keyCode == 13) {
    	document.getElementById("login").click();
   }
}
function autoLogin(){
	if($("input:checkbox[id='checkAutoLogin']").prop("checked")==true){
		$("input:checkbox[id='checkAutoLogin']").prop("checked", false);
	}
	else{
		$("input:checkbox[id='checkAutoLogin']").prop("checked", true);
	}
}
</script>
<style>
#container {
  display: block;
  position: relative;
  padding-left: 35px;
  margin-top:10px;
  margin-bottom: 12px;
  cursor: pointer;
  font-size: 22px;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}

/* Hide the browser's default checkbox */
#container input {
  position: absolute;
  opacity: 0;
  cursor: pointer;
  height: 0;
  width: 0;
}

/* Create a custom checkbox */
.checkmark {
  position: absolute;
  top: 0;
  left: 0;
  height: 25px;
  width: 25px;
  background-color: #eee;
}

/* On mouse-over, add a grey background color */
#container:hover input ~ .checkmark {
  background-color: #ccc;
}

/* When the checkbox is checked, add a blue background */
#container input:checked ~ .checkmark {
  background-color: #2196F3;
}

/* Create the checkmark/indicator (hidden when not checked) */
.checkmark:after {
  content: "";
  position: absolute;
  display: none;
}

/* Show the checkmark when checked */
#container input:checked ~ .checkmark:after {
  display: block;
}

/* Style the checkmark/indicator */
#container .checkmark:after {
  left: 9px;
  top: 5px;
  width: 5px;
  height: 10px;
  border: solid white;
  border-width: 0 3px 3px 0;
  -webkit-transform: rotate(45deg);
  -ms-transform: rotate(45deg);
  transform: rotate(45deg);
}
#login{

padding:30px;
font-size:18px;
}
</style>
</head>
<body>
<jsp:include page="../Header.jsp"></jsp:include><br>
<jsp:include page="../Nav.jsp"></jsp:include><br>
<div class="container" style="margin:auto;">
<div class="row">
<div class="col-xs-4">
</div>
<div class="col-xs-4">
<div class="col-xs-10">
  <div class="input-group">
<span class="input-group-addon" style="width:100px;"><i class="glyphicon glyphicon-user">아이디</i></span> 
<input type="text" id="id" class="form-control" placeholder="아이디">
</div>
<br>
  <div class="input-group">
<span class="input-group-addon" style="width:100px;"><i class="glyphicon glyphicon-lock">비밀번호</i></span>
<input type="password" id="pw" onkeyup="enter()" class="form-control" placeholder="비밀번호">
</div>
<label id="container"><a onclick="autoLogin()" style="color:black;" href="#">자동로그인</a>
  <input type="checkbox" id="checkAutoLogin">
  <span class="checkmark"></span>
</label>
</div>
<div class="col-xs-2">
<button id="login" class="btn btn-success" onclick="login()">로그인</button>
</div>
<br>
<button class="btn btn-primary" onclick="goJoinForm()" style="padding:5px;"><span class="glyphicon glyphicon-user">회원가입하기</span></button>
<br><br>
<button onclick="search()" class="btn btn-info" style="padding:5px;"><i class="glyphicon glyphicon-search"></i>아이디/비밀번호 찾기</button>
<br>
<br>
<a class="g-signin2" data-onsuccess="onSignIn" style="width:100px"></a>
<br>
<a href="kakaoCreate">
<img src="${pageContext.request.contextPath}/resources/img/kakao_login_btn_medium.png">
</a>
</div>
<div class="col-xs-4">
</div>
 </div>
</div>
</body>
<script>
function onSignIn(googleUser) {
	var auth2 = gapi.auth2.getAuthInstance();
	auth2.disconnect();
	var profile = googleUser.getBasicProfile();
	var auth2 = gapi.auth2.getAuthInstance();
	location.href = "googleApiLogin?googleid=" + profile.getId();

}
</script>
</html>