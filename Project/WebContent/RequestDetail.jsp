<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script>
$(document).ready(function() {
	$('#permisson').click(function(){
        $.ajax({
            type : "POST",
              url : "Permission?id=${requestScope.id}",
              dataType : "text",
            success : function(data, textStatus, xhr) {
                 if (data == 'no') {
                      alert('승인 실패');
                 }
               else if(data=='yes') {
                   $.ajax({
                       type : "POST",
                         url : "Deny?rno=${requestScope.rno}",
                         dataType : "text",
                       success : function(data, textStatus, xhr) {
                            if (data == 'no') {
                                 alert('요청 글 삭제 실패');
                            }
                          else if(data=='yes') {
                        	  opener.location.reload();
                              window.close();
            	}
                       },
           error : function(request, status, error) {
           alert("code:" + request.status + "\n" + "error:" + error);
           }
            })	
 	}
            },
error : function(request, status, error) {
alert("code:" + request.status + "\n" + "error:" + error);
}
 })	
	});
	
	$('#deny').click(function(){
        $.ajax({
            type : "POST",
              url : "Deny?rno=${requestScope.rno}",
              dataType : "text",
            success : function(data, textStatus, xhr) {
                 if (data == 'no') {
                      alert('거절 실패');
                 }
               else if(data=='yes') {
            	   opener.location.reload();
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
<button id="permisson">승인하기</button> <button id="deny">거절하기</button>
</body>
</html>