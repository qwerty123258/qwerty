<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
src="https://code.jquery.com/jquery-3.4.1.js"
integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
crossorigin="anonymous">
</script>
<script>
function add(){
	location.href="CreateUser.jsp";
}
function searchUserID(){
	location.href="SearchUserID.jsp";
}
function searchUserPw(){
	location.href="SearchUserPw.jsp";
}
</script>
<script>
    $(document).ready(function(){
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
                       location.href = 'Main.jsp';
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
                location.href='Main.jsp';

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
    	$('#boardList').click(function() {
            location.href="BoardList";
	});
    	$('#requestWrite').click(function() {
            location.href="RequestWrite.jsp";
	});
    	$('#request').click(function() {
            location.href="RequestList";
	});
    })
   </script>
</head>
<body>
<c:if test="${sessionScope.id eq NULL}">
아이디<br>
<input type="text" id="id_input"><br>
비밀번호<br>
<input type="password" id="pw_input">
<button id="loginbtn">로그인 하기</button><br>
<button id="boardList">글보기</button>
<button onclick="add()">회원 가입하기</button>
<button onclick="searchUserID()">아이디 찾기</button>
<button onclick="searchUserPw()">비밀번호 찾기</button>
</c:if>
<c:if test="${sessionScope.id  ne NULL}">
${sessionScope.id}님 환영합니다.
<c:if test="${sessionScope.id  eq 'qwerty123258'}">
<button id="alluser">회원 전체조회</button>
<button id="request">작성권한 요청확인</button>
</c:if>
<button id="write">글쓰기</button>
<c:if test="${sessionScope.id  ne 'qwerty123258'}">
<button id="requestWrite">작성 권한 요청 글쓰기</button>
</c:if>
<button id="boardList">글보기</button>
<button id="modify">정보수정</button>
<button id="delete">회원탈퇴</button>
<button id="logout">로그아웃</button>
</c:if>
</body>
</html>