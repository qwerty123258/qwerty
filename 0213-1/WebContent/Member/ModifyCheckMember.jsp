<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <link href="https://fonts.googleapis.com/css?family=Yeon+Sung&display=swap" rel="stylesheet">
<style>
.btn{
    background-color: rgb(0,200,80);
    outline: none;
    border: none;
    color: white;
    text-align: center;
    padding: 10px;
    font-size: 15px;
    cursor: pointer;
    border-radius: 7px;
}
*{
    font-family: 'Yeon Sung', cursive;
}
#home{
color:white;
}
</style>
<script>
function check(){
	pwcheck.submit();
}
</script>
</head>
<body>
<h2>비밀 번호 확인
</h2>
<form action="../Check" method="post" id="pwcheck">
비밀번호
<input type="password" name="password">
</form>
<br>
<button class="btn" onclick="check()">확인하기</button>
<br><br>
<a id="home" href="../MemberBoardMain.jsp">홈으로</a>
</body>
</html>