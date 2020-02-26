<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <script
src="https://code.jquery.com/jquery-3.4.1.js"
integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
crossorigin="anonymous">
    </script>
<script>
$(document).ready(function() {
	$('#writebtn').click(function(){
		var title=$('#title_input').val();
		if(title==''){
			alert('제목이 비어있습니다.');
		}
		else{
        	document.getElementById("writeForm").submit();
		}
		
	});
})
</script>
</head>
<body>
<form action="RequestWrite" method="post" id="writeForm">
제목<br>
<input type="text" id="title_input" name="title"><br>
내용<br>
<textarea name="content" rows="10" cols="50"></textarea>
</form>
<button id="writebtn">작성완료</button>
</body>
</html>