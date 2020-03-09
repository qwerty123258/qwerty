<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
                <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/sidenav.js"></script>
<script>
$(document).ready(function() {
	var id='${sessionScope.id}';
	if(id!= ""){
		getPoint();
	}
		$('#loginbtn').click(function() {
	        var id = $('#id_input').val();
	        var password = $('#pw_input').val();
	      $.ajax({
	           type : "POST",
	             url : "Login",
	             data : "id=" + id + "&pw=" + password,
	             dataType : "text",
	           success : function(data, textStatus, xhr) {
	                if (data == 'blackList') {
	                     alert('당신은 블랙리스트입니다. 로그인이 불가능합니다.');
	              } else if(data=='certify') {
	            	  alert('이메일인증이 아직 완료되지 않으셨습니다. 로그인이 불가능합니다.');
	          	}
	              else if(data=='loginFail') {
	            	  alert('아이디 또는 비밀번호가 맞지 않습니다.');
	          	}
	              else if(data=='login') {
	            	  location.reload();
		}
	           },
	error : function(request, status, error) {
	alert("code:" + request.status + "\n" + "error:" + error);
	}
	})
	});
		$('#logout').click(function() {
	        $.ajax({
	            type : "POST",
	              url : "Logout",
	            success : function(data, textStatus, xhr) {
	            alert('로그아웃 되셨습니다.');
	            location.href="Main.jsp";

	            },
	error : function(request, status, error) {
	alert("code:" + request.status + "\n" + "error:" + error);
	}
	 })
		});
		$('#alluser').click(function() {
			location.href="SelectUser";
		});
		$('#modify').click(function() {
	            location.href="CheckPw.jsp";
		});
		$('#delete').click(function() {
	        location.href="CheckDelPw.jsp";
	});
		$('#write').click(function() {
	        $.ajax({
	            type : "POST",
	              url : "WriteAccessCheck",
	              dataType : "text",
	            success : function(data, textStatus, xhr) {
	                 if (data == 'no') {
	                      alert('글 작성권한이 없습니다. 먼저 판매자 자격 요청을 하세요.');
	                      location.href='RequestWrite.jsp';
	                 }
	               else if(data=='yes') {
	                   location.href='BoardWrite.jsp';
	 	}
	            },
	error : function(request, status, error) {
	alert("code:" + request.status + "\n" + "error:" + error);
	}
	 })
	});
		$('#check').click(function() {
	        $.ajax({
	            type : "POST",
	              url : "AttendanceCheck",
	              dataType : "text",
	            success : function(data, textStatus, xhr) {
	                 if (data == 'no') {
	                     $.ajax({
	                         type : "POST",
	                           url : "Attendance",
	                           dataType : "text",
	                         success : function(data, textStatus, xhr) {
										alert("출석체크로 " +data+" 포인트를 적립하셨습니다.");
										location.reload();
	                         },
	             error : function(request, status, error) {
	             alert("code:" + request.status + "\n" + "error:" + error);
	             }
	              });
	                 }
	               else if(data=='yes') {
	                   alert('오늘은 이미 출석체크를 하셨습니다.');
	 	}
	            },
	error : function(request, status, error) {
	alert("code:" + request.status + "\n" + "error:" + error);
	}
	 })
	});
		$('#requestWrite').click(function() {
	        location.href="RequestWrite.jsp";
	});
		$('#request').click(function() {
	        location.href="RequestList";
	});
		$('#point').click(function() {
	        location.href="Point.jsp";
	});
		$('#download').click(function() {
	        location.href="DownLoadList";
	});
		$('#report').click(function() {
	        location.href="ReportList";
	});
})
</script>
<script>
</script>
   <style>
   .sidenav{
   margin-left:5%;
   }
   #loginbtn{
   padding:10px;
   width:150px;
   outline:none;
   border:none;
   }
   </style>
</head>
<body>
<div class="container" style=height:800px>
<div class="sidenav">
<c:if test="${sessionScope.id eq NULL}">
<div>
<p>
아이디
</p>
<input type="text" style=width:150px; id="id_input">
<p>비밀번호</p>
<input type="password" onkeyup="enterkey()" style=width:150px; id="pw_input">
</div>
<br>
<button id="loginbtn" class="btn-primary">로그인 하기</button>
<p><a href="#" onclick="add()">회원 가입하기</a></p>
<p><a href="#" onclick="searchUserID()">아이디 찾기</a></p>
<p><a href="#" onclick="searchUserPw()">비밀번호 찾기</a></p>
</c:if>
<c:if test="${sessionScope.id  ne NULL}">
<br><br>
<div id="idar" style=padding:3px>${sessionScope.id}님</div>
<br>
<div id="grade" style=padding:3px></div>
<div id="savePoint" style=padding:3px></div>
<c:if test="${sessionScope.id  eq 'qwerty123258'}">
<p><a href="#" id="alluser">회원 전체조회</a></p>
<p><a href="#" id="request">요청확인</a></p>
<p><a href="#" id="report">신고글 보기</a></p>
</c:if>
<p><a href="#" id="check">출석체크</a></p>
<p><a href="#" onclick="window.open('Grade.jsp','등급','width=600,height=500,location=no,status=no,scrollbars=yes');">등급별 혜택</a></p>
<p><a href="#" id="download">내가 받은 자료</a></p>
<p><a href="#" id="point">포인트 충전</a></p>
<p><a href="#" id="write">컨텐츠 등록</a></p>
<c:if test="${sessionScope.id  ne 'qwerty123258'}">
<p><a href="#" id="requestWrite">컨텐츠 등록 자격 요청</a></p>
</c:if>
<p><a href="#" id="modify">정보수정</a></p>
<p><a href="#" id="delete">회원탈퇴</a></p>
<p><a href="#" id="logout">로그아웃</a></p>
</c:if>
</div>
</div>
</body>
</html>