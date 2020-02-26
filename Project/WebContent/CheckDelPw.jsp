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
$(document).ready(function(){
	$('#checkbtn').click(function() {
        var password = $('#pw_input').val();
      $.ajax({
           type : "POST",
             url : "ConfirmUser",
             data : "pw=" + password,
             dataType : "text",
           success : function(data, textStatus, xhr) {
                if (data == 'checkFail') {
                     alert('비밀번호가 맞지 않습니다.');
              }
              else if(data=='check') {
            	  if(confirm('탈퇴를 진행하시겠습니까?')){
                      location.href = 'DeleteUser';
            	  }
            	  else{
                      alert('탈퇴 취소');
                      location.href="Main.jsp";
            	  }
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
<input type="password" id="pw_input">
<button id="checkbtn">확인!</button>
</body>
</html>