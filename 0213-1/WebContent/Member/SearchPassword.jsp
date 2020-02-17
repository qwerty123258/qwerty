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
            <h2>비밀번호 찾기</h2>
<form action="../SearchPassword" method="post">
<table>
<tr>
<td>
아이디<br>
<input type="text" name="id" autocomplete="off"><br><br><br>
</td>
</tr>
<tr>
<td>
이메일<br>
<input type="text" name="email" autocomplete="off"><br><br><br>
</td>
</tr>
<tr>
<td>
<br>
<input type="submit" value="비밀번호 찾기" class="btn">
<a href="Search.jsp">아이디찾기</a>
</td>
</tr>
</table>
</form>
</body>
</html>