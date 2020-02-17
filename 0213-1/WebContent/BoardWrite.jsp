<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/write.js"></script>
     <link rel="stylesheet" href="css/write.css">
</head>
<body>
<div>
            <a id="logoar" href="MemberBoardMain.jsp">
                <img id="logo" src="images/logo.PNG">
            </a>
</div>
<div id="mid">
           <div id="titlear">
            <h2>글 쓰기</h2>
            </div>
            <div id="writebtnar">
<button class="btn" onclick="writeboard()">작성완료</button>
</div>
</div>
<form action="BoardWrite" method="post" id="boardwrite" enctype="multipart/form-data">
<table>
<tr>
<th>
작성자
</th>
<td>
 <input type="text" name="writer" id="boardwriter" value="${sessionScope.id}" readonly="true">
</td>
</tr>
<tr>
<th>
비밀번호
</th>
<td>
<input type="password" name="bpassword" id="boardpassword">
</td>
</tr>
<tr>
<th>
제목
</th>
<td>
<input type="text" name="title" id="boardtitle">
</td>
</tr>
<tr>
<th>
글 내용
</th>
<td>
<textarea rows="10" cols="100" name="bcontent"></textarea>
</td>
</tr>
</table>
<div id="bottom">
사진 첨부 <input type="file" name="bimgfile" id="bimgfile">
파일 첨부 <input type="file" name="bfile" id="file">
</div>
</form>
</body>
</html>