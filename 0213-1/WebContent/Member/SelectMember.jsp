<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!-- JSTL JavaServer Page Standard Tag Library -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <link href="https://fonts.googleapis.com/css?family=Yeon+Sung&display=swap" rel="stylesheet">
<style>
*{
    font-family: 'Yeon Sung', cursive;
}
body{
        background-color: rgb(245,246,247);
}
table{
border:black solid 1px;
border-collapse:collapse;
}
td,th{
width:150px;
text-align:center;
padding:5px;
border:black solid 1px;
}
a{
color:red;
}
.btn{
    background-color: rgb(167,33,22);
    outline: none;
    border: none;
    color: black;
    text-align: center;
    padding: 10px;
    font-size: 15px;
    cursor: pointer;
    border-radius: 7px;
}
#home{
color:black;
}
#select{
color:green;
}
#logoar{
    margin-right: 80%;
    width: 140px;
    height: 50px;
    margin-top: 20px;
}
#logo{
    width: 140px;
    height: 50px;
    margin-top: 20px;
}
</style>
</head>
<body>
            <a id="logoar" href="MemberBoardMain.jsp">
                <img id="logo" src="images/logo.PNG">
            </a>
<h2>회원 목록 조회
</h2>
<table>
<tr>
<th>
아이디
</th>
<th>
비밀번호
</th>
<th>
이름
</th>
<th>
생년월일
</th>
<th>
성별
</th>
<th>
이메일
</th>
<th>
조회
</th>
<th>
삭제
</th>
</tr>
<c:forEach var="result" items="${member}">
<tr>
<td>
${result.id}
</td>
<td>
${result.password}
</td>
<td>
${result.name}
</td>
<td>
${result.birth}
</td>
<td>
${result.gender}
</td>
<td>
${result.email}
</td>
<td>
<a id="select" href="Detail?id=${result.id}">조회</a>
</td>
<td>
<c:if test="${result.id ne 'qwerty123258'}">
<a href="../Delete?id=${result.id}">삭제</a>
</c:if>
</td>
</tr>
</c:forEach>
</table>
</body>
</html>