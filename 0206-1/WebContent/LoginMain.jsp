<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<script>
function logout(){
		alert("로그아웃 되었습니다.");
		location.href="Logout.jsp";                            
}
function check(){
	alert("본인 확인을 위해 비밀번호를 확인합니다.");
	location.href="CheckMember.jsp";  
}
</script>
 <script> //뒤로가기 방지
 window.history.forward();
 function noBack(){window.history.forward();}
</script>
</head>
<body onload="noBack();" onpageshow="if(event.persisted) noBack();" onunload="">
    <div class="header">
        <div class="top">
            <a href="LoginMain.jsp">
                <img id="logo" src="images/logo.png">
            </a>
            <div class="mid">
                <h2>
				${sessionScope.id}님 환영합니다.
                </h2>
                <div>
                <c:if test="${sessionScope.id eq 'qwerty123258'}">
                <a href="MemberSelect"><button class="btn">전체조회</button></a>
				</c:if>
				<button onclick="check()" class="btn">정보수정</button>
				<button onclick="logout()" class="btn">로그아웃</button>
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