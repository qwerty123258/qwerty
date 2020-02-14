<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
.mid{
display:block;
}
.textbox {
    position: relative;
}
.textbox input{
    font-size: 20px;
    height: 40px;
    width: 200px;
    outline: none;
    border: none;
    margin-bottom: 10px;
    background-color: #999;
    color:white;
}
.textbox label {
    position: absolute;  
    color: white; 
    cursor: text; 
    transition: 0.3s;
}
.loginar{
	float:right;
	margin-left:70%;
	display:flex;
	border:black solid 1px;
	padding :10px;
}
#loginbtn{
	width:80px;
	height:80px;
	padding:10px;
	cursor:pointer;
}
.loginbtnar{
	padding:10px;
}
.loginMain{
	float:right;
	margin-left:70%;
	display:block;
	border:black solid 1px;
	padding :10px;
	width:300px;
height:auto;
}
#profileimg{
width:80px;
height:80px;
}
.rightar{
display:flex;
padding:10px;
}
.infoFnar{
padding:10px;
}
</style>
    <script
src="https://code.jquery.com/jquery-3.4.1.js"
integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
crossorigin="anonymous">
    </script>
   <script>
    $(document).ready(function(){
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
function login(){
	loginForm.submit();
}
function enterkey(){
    if (window.event.keyCode == 13) { //로그인 버튼 말고 엔터키로 로그인 하는 경우
        login();
   }
}
function logout(){
	alert("로그아웃 되었습니다.");
	location.href="Member/Logout.jsp";                            
}
function check(){
alert("본인 확인을 위해 비밀번호를 확인합니다.");
location.href="Member/CheckMember.jsp";  
}
</script>
</head>
<body>
<h2>회원제 게시판 연습용 메인 페이지</h2>
<h3>Welcome!!
</h3>
            <div class="mid">
              <c:if test="${sessionScope.id eq NULL}">
                <div class="loginar">
                <form action="Login" method="post" id="loginForm">
                <div id="idbox" class="textbox"> 
                    <label for="id_input">아이디</label> 
                    <input type="text" autocomplete="off" name="id" id="id_input"> 
                </div>
                <div id="passbox" class="textbox"> 
                    <label for="pass_input">비밀번호</label> 
                    <input type="password" onkeyup="enterkey()" autocomplete="off" name="password" id="pass_input">
                </div>
                </form>
                <div class="loginbtnar">
					<button onclick="login()" id="loginbtn" type="submit">
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
                                 <button onclick="check()" class="btn">정보수정</button>
                <button onclick="logout()" class="btn">로그아웃</button>
                </div>
                </div>
                <p>
				${sessionScope.id}님
                </p>
                <div>
				<a href="BoardWrite.jsp"><button class="btn">글 쓰기</button></a>
				<a href="PageList"><button class="btn">글 보기</button></a>
				<a href="MailForm.jsp"><button class="btn">메일 전송</button></a>
                </div>
                </div>
                                </c:if>
                </div>
<c:if test="${sessionScope.id eq NULL}">
<a href="Member/Search.jsp">아이디/비밀번호 찾기</a>
<a href="Member/InsertMember.jsp">회원가입하기</a>
<a href="PageList">글 보기</a>
</c:if>
</body>
</html>