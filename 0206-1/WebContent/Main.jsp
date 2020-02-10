<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <link rel="stylesheet" href="css/login.css">
        <link href="https://fonts.googleapis.com/css?family=Yeon+Sung&display=swap" rel="stylesheet">
        <style>
*{
    font-family: 'Yeon Sung', cursive;
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
</script>
</head>
<body>
    <div class="header">
        <div class="top">
            <a href="Main.jsp">
                <img id="logo" src="images/logo.png">
            </a>
            <div class="mid">
                <h2>
                    로그인
                </h2>
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
                <div>
                    <button onclick="login()" id=free type="submit">
                        로그인
                    </button>
                </div>
                <br>
                <div id="log">
                    넷플릭스 회원이 아닌가요? <a href="InsertMember.jsp"><b>지금 가입하세요.</b></a>
                    <a href="Search.jsp">아이디/비밀번호 찾기</a>
                </div>
            </div>
        </div>
    </div>
    <div class="footer">
        <i>
            copyright@2020.02.27,qwerty123258
        </i>
    </div>
</body>
</html>