<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <link href="https://fonts.googleapis.com/css?family=Yeon+Sung&display=swap" rel="stylesheet">
    <link href="../css/base.css" rel="stylesheet">
                <style>
    td,th{
    border:none;
    text-align:left;
    }
    </style>
</head>
<body>
            <a id="logoar" href="../MemberBoardMain.jsp">
                <img id="logo" src="../images/logo.PNG">
            </a>
<h2>아이디 찾기</h2>
<form action="../SearchID" method="post">
<table>
<tr>
<td>
이름
<br>
<input type="text" name="name" autocomplete="off">
</td>
</tr>
<tr>
<td>
이메일
<br>
<input type="text" name="email" autocomplete="off">
</td>
</tr>
<tr>
<td>
연락처
<br>
<input type="text" name="phone" autocomplete="off">
</td>
</tr>
<tr>
<td>
생년월일
<br>
<input type="date" name="birth" autocomplete="off">
</td>
</tr>
<tr>
<td>
<input type="submit" value="아이디 찾기" class="btn">
<a href="SearchPassword.jsp">비밀번호 찾기</a>
<a href="../MemberBoardMain.jsp">홈으로</a>
</td>
</tr>
</table>
</form>
</body>
</html>