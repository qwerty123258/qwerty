<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이름 입력창</title>
<script>
function insertName(){
	nameInsert.submit();
}
</script>
</head>
<body>
	<h2>InputDB.jsp 파일
	</h2>
	<form action="inputServer" method="get" id="nameInsert">
         <label for="id_input">이름</label> 
         <input type="text" id="id_input" name="data1" autocomplete="off"> 
	</form>
	<button onclick="insertName()">제출</button>
</body>
</html>