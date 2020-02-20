<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
body{
    background-color: rgb(245,246,247);
}
</style>
     <link rel="stylesheet" href="css/main.css">
    <script
src="https://code.jquery.com/jquery-3.4.1.js"
integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
crossorigin="anonymous">
    </script>
   <script>
    $(document).ready(function(){
    	$('#loginbtn').click(function() {
            var id = $('#id_input').val();
             var password = $('#pass_input').val();
          $.ajax({
               type : "POST",
                 url : "Login",
                 data : "id1=" + id + "&password1=" + password,
                 dataType : "text",
               success : function(data, textStatus, xhr) {
                    if (data == 'loginFail') {
                         alert('로그인에 실패하였습니다.');
                  } else if(data=='loginSuccess') {
                       location.href = 'MemberBoardMain.jsp';
    	}
               },
   error : function(request, status, error) {
   alert("code:" + request.status + "\n" + "error:" + error);
   }
    })
   });
       $("#idbox input").focus(function(){
        $("#idbox label").css("font-size","4px");
       });
       $("#idbox input").focusout(function(){
        $("#idbox label").css("font-size","");
       });
       $("#passbox input").focus(function(){
        $("#passbox label").css("font-size","4px");
       });
       $("#passbox input").focusout(function(){
        $("#passbox label").css("font-size","");
       });
    });
    </script>
<script>
function enterkey(){
    if (window.event.keyCode == 13) { //로그인 버튼 말고 엔터키로 로그인 하는 경우
    	document.getElementById("loginbtn").click();
   }
}
function logout(){
	alert("로그아웃 되었습니다.");
	location.href="Member/Logout.jsp";                            
}
function modifyCheck(){
alert("본인 확인을 위해 비밀번호를 확인합니다.");
location.href="Member/ModifyCheckMember.jsp";  
}
function deleteCheck(){
	alert("본인 확인을 위해 비밀번호를 확인합니다.");
	location.href="Member/DeleteCheckMember.jsp";  
	}
	function viewMyBoard(){
		location.href="ViewMyBoard?id=${sessionScope.id}";
	}
</script>
</head>
<body>
            <a id="logoar" href="MemberBoardMain.jsp">
                <img id="logo" src="images/logo.PNG">
            </a>
            <div class="mid">
            <h2>회원제 게시판 연습용 메인</h2>
            <iframe src="PageList"></iframe>
            <a href="PageList"><button class="btn">전체글 보기</button></a>
            <br><br>
            글 검색하기
            <form action="BoardSearch">
<select name="searchOpt">
<option value="제목" <c:if test="${requestScope.searchOpt eq '제목'}">selected</c:if>>제목</option>
<option value="작성자" <c:if test="${requestScope.searchOpt eq '작성자'}">selected</c:if>>작성자</option>
</select>
<input type="text" name="keyword" value="${requestScope.keyword}">
<input type="submit" value="검색하기">
</form>
              <c:if test="${sessionScope.id eq NULL}">
                <div class="loginar">
                <form id="loginForm">
                <div id="idbox" class="textbox"> 
                    <label for="id_input">아이디</label> 
                    <input type="text" autocomplete="off" name="id" id="id_input"> 
                </div>
                <div id="passbox" class="textbox"> 
                    <label for="pass_input">비밀번호</label> 
                    <input type="password" onkeyup="enterkey()" autocomplete="off" name="password" id="pass_input">
                </div>
                                <a href="Member/Search.jsp">아이디/비밀번호 찾기</a>
								<a href="Member/InsertMember.jsp">회원가입</a>
                </form>
                <div class="loginbtnar">
					<button id="loginbtn" type="submit">
                        로그인
                   </button> 
                </div>
                </div>
                </c:if>
                <c:if test="${sessionScope.id ne NULL}">
                <div class="loginMain">
                <div class="rightar">
                                <div class="profile">
                  <img id="profileimg" src="fileUpload/${sessionScope.mempicture}" onerror="this.src='fileUpload/profile.png'" >
                </div>
                                                                <div class="infoFnar">
                                <c:if test="${sessionScope.id eq 'qwerty123258'}">
                 <a href="MemberSelect"><button class="btn">전체조회</button></a>
                                 				</c:if>
                                 <button onclick="modifyCheck()" class="btn">정보수정</button>
                                 <button onclick="deleteCheck()" class="btn">회원탈퇴</button>
                <button onclick="logout()" class="btn">로그아웃</button>
                </div>
                </div>
                <p>
				${sessionScope.id}님
                </p>
                <div>
				<a href="BoardWrite.jsp"><button class="btn">글 쓰기</button></a>
				<a href="PageList"><button class="btn">글 보기</button></a>
				                                 <button onclick="viewMyBoard()" class="btn">작성글 보기</button>
				<a href="MailForm.jsp"><button class="btn">메일 전송</button></a>
                </div>
                </div>
                                </c:if>
                </div>
</body>
</html>