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
	$('#permisson').click(function(){
        $.ajax({
            type : "POST",
              url : "Permission?id=${requestScope.id}",
              dataType : "text",
            success : function(data, textStatus, xhr) {
                 if (data == 'no') {
                      alert('알수 없는 오류로 승인 실패');
                 }
               else if(data=='yes') {
                   window.close();
 	}
            },
error : function(request, status, error) {
alert("code:" + request.status + "\n" + "error:" + error);
}
 })	
	});
})
</script>
</head>
<body>
요청 번호 : ${requestScope.rno}<br>
제목 : ${requestScope.rtitle} 작성일 : ${requestScope.writedate}<br>
작성자 : 
${requestScope.id}<br><br>
<pre>
${requestScope.rcontent}
</pre>
<button id="permisson">승인하기</button>
</body>
</html>