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
background-Color:black;
color:white;
}
a{
color:white;
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
*{
    font-family: 'Yeon Sung', cursive;
}
</style>
<script>
function update(){
	document.getElementById("update").submit();
}
</script>
</head>
<body>
<form action="Update" id="update">
<h2>정보 변경</h2>
 아이디 <br>
 <input type="text" name="id" autocomplete="off" value="${sessionScope.id}" readonly="true"><br>
 이름 <br>
 <input type="text" name="name" autocomplete="off" value="${requestScope.name}"><br>
 이메일주소 <br>
  <input type="text" name="email" autocomplete="off" value="${requestScope.email}">
</form>
<br>
<button class="btn" onclick="update()">
수정 완료
</button>
<br><br>
<a href="LoginMain.jsp">홈으로</a>
</body>
</html>