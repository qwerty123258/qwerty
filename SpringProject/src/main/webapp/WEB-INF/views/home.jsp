<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
	<title>메인 페이지</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script>
	 $(document).ready(function() {
	var id='${sessionScope.id}';
	var html="";
	if(id!=""){
		$.ajax({
			type : "POST",
			url : "MyProfile",
			dataType : "json",
			success : function(result) {
					var profileimg=result.profileimg;
					var src="${pageContext.request.contextPath}/resources/fileUpload/"+profileimg;
					html+="<img style='width:100px; height:100px' src='"+src+"'>";
					$("#profile").html(html);
			},
			error : function() {
				alert("실패");
			}
	})	
	}
	 });
	</script>
	<script>
	function login(){
		var id=$("#id_input").val();
		var pw=$("#pw_input").val();
		$.ajax({
			type : "POST",
			url : "Login",
			data : "id=" + id + "&pw="+pw,
			dataType : "text",
			success : function(result) {
				if (result == 'Success') {
					location.reload();
				}
				 else if (result == 'Fail') {
					alert("아이디 또는 비밀번호가 틀렸습니다.");
				}
			},
			error : function() {
				alert("실패");
			}
	})
	}
	function logout(){
		location.href="Logout";
	}
	function writeArticle(){
		location.href="goWrite";
	}
	function goBoardList(){
		location.href="goBoardList";
	}
	function changeProfile(){
		location.href="goChangeProfile";
	}
	function goSelect(){
		location.href="goSelect";
	}
	</script>
</head>
<body>
<h1>
	Hello world!  
</h1>
<c:if test="${sessionScope.id eq null}">
아이디<br>
<input type="text" id="id_input"><br>
비밀번호<br>
<input type="password" id="pw_input"><br>
<button onclick="login()">로그인</button>
<a href="goCreateMember">회원가입하기</a>
<h4>카카오로 로그인</h4>
<a href="KakaoCreate">
<img src="${pageContext.request.contextPath}/resources/img/kakao_account_login_btn_medium_narrow.png">
</a>
<h4>네이버로 로그인</h4>
<a href="NaverCreate">
<img style=width:222px;height:49px; src="${pageContext.request.contextPath}/resources/img/네이버 아이디로 로그인_완성형_Green.PNG">
</a>
</c:if>
<c:if test="${sessionScope.id ne null}">
<div id="profile">
</div>
${sessionScope.id} 님 어서 오세요?<br>
<c:if test="${sessionScope.id eq 'qwerty123258'}">
<button onclick="goSelect()">
회원 조회
</button>
</c:if>
<button onclick="changeProfile()">
프로필 사진 변경
</button>
<button onclick="writeArticle()">
글쓰기
</button>
<button onclick="goBoardList()">
글보기
</button>
<button onclick="logout()">
로그아웃
</button>
</c:if>
</body>
</html>
