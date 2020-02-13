<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <link href="https://fonts.googleapis.com/css?family=Yeon+Sung&display=swap" rel="stylesheet">
    <style>
    body{
color:white;
background-Color:black;
}
    *{
    font-family: 'Yeon Sung', cursive;
}
.btn{
    background-color: rgb(167,33,22);
    outline: none;
    border: none;
    color: white;
    text-align: center;
    padding: 10px;
    font-size: 15px;
    cursor: pointer;
    border-radius: 7px;
}
a{
color:white;
}
a:hover{
   background-color:rgba(255,255,255,0.3);
}
table{
margin:auto;
margin-top:15%;
}

    </style>
</head>
<body>
<table>
<form action="Search" method="post">
<tr>
<td>
이름
<br>
<input type="text" name="name" autocomplete="off"><br><br><br>
</td>
</tr>
<tr>
<td>
이메일
<br>
<input type="text" name="email" autocomplete="off"><br><br><br>
</td>
</tr>
<tr>
<td>
<input type="submit" value="아이디 찾기" class="btn">
<a href="SearchPassword.jsp">비밀번호 찾기</a>
<a href="Main.jsp">홈으로</a>
</td>
</tr>
</form>
</table>
</body>
</html>