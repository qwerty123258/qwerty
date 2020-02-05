<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function selectDB(){
	location.href="selectDB";
}
</script>
</head>
<body>
	<h2>InputDB.jsp 파일
	</h2>
	<form action="inputServer">
	data1 : <input type="text" name="data1">
	data2 : <input type="text" name="data2">
	<input type="submit">
	</form>
	<h2>내용 조회하기</h2>
	<a href="selectDB">DB조회 링크</a>
	<button onclick="selectDB()">DB조회</button>
</body>
</html>