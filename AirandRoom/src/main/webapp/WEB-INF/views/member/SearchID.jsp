<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디/비밀번호 찾기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
function searchID(){
	var name=$("#name").val();
	var email=$("#email").val();
	$.ajax({
		type : "POST",
		url : "searchID",
		data : "name=" + name + "&email="+email,
		dataType : "text",
		success : function(result) {
			if(result=="Fail"){
					alert("가입된 이력이 없습니다.");
			}
			else{
				alert("찾으신 아이디는 " +result + " 입니다.");
			}

		},
		error : function() {
			alert("아이디 찾기 중 에러 발생");
		}
	});
}
function searchPW(){
	var id=$("#id").val();
	var email=$("#email").val();
	$.ajax({
		type : "POST",
		url : "searchPW",
		data : "id=" + id + "&email=" + email,
		dataType : "text",
		success : function(result){
			if(result=="Success"){
				alert("입력한 이메일로 해당 아이디의 비밀번호를 전송해드렸습니다.");
				location.href="main";
			}
			else{
				
				alert("입력하신 정보가 올바르지 않습니다 .다시 입력해주세요.");
				$("#id").val("");
				$("#email").val("");
				$("#id").focus();
			}
		},
		error : function() {
			alert("비밀번호 찾기 중 에러 발생");
		}
	});
}
</script>
</head>
<body>
<jsp:include page="../Header.jsp"></jsp:include><br>
<jsp:include page="../Nav.jsp"></jsp:include><br>
<div class="container">
  <ul class="nav nav-tabs nav-justified">
    <li class="active"><a data-toggle="pill" href="#home">아이디 찾기</a></li>
    <li><a data-toggle="pill" href="#menu1">비밀번호 찾기</a></li>
  </ul>
  <div class="tab-content">
    <div id="home" class="tab-pane fade in active">
    <h3>입력한 정보에 맞는 아이디를 알려줍니다.</h3>
        <br><br>
이름<br>
<input type="text" id="name"><br>
이메일 주소<br>
<input type="text" id="email">
<button onclick="searchID()">찾기</button>
    </div>
    <div id="menu1" class="tab-pane fade">
    <h3>입력한 정보에 맞는 비밀번호를 이메일로 전송해드립니다.</h3>
    <br><br>
아이디<br>
<input type="text" id="id"><br>
이메일 주소<br>
<input type="text" id="email">
<button onclick="searchPW()">비밀번호 찾기</button>
    </div>
  </div>
  </div>
</body>
</html>