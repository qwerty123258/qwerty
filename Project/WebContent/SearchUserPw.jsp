<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function domainselect(){
    var email=document.getElementById("domainvalue").value;
    document.getElementById("domain_input").value=email;
}
</script>
<script
src="https://code.jquery.com/jquery-3.4.1.js"
integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
crossorigin="anonymous">
</script>
<script>
$(document).ready(function(){
	$('#check').click(function() {
	     var id=$('#id_input').val();
	     var email=$('#email_input').val();
	     var domain=$('#domain_input').val();
	     var email2=email+"@"+domain;
            $.ajax({
                type : "POST",
                  url : "SearchUserPw",
                  data : "id=" + id+ "&email=" + email2,
                  dataType : "text",
                success : function(data, textStatus, xhr) {
                     if (data == 'searchFail') {
                          alert('가입된 정보와 일치하지 않습니다. 다시 입력해주세요.');
                     }
                   else {
                 	  	alert('비밀번호 찾기 결과를 이메일로 발송하였습니다. \n 이메일을 확인해주세요.');
                        location.href = 'Main.jsp';
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
아이디<br>
<input type="text" id="id_input">
이메일 주소<br>
<input type="text" id="email_input">
@
<input type="text" id="domain_input">
<select onchange="domainselect()" id="domainvalue">
  <option value="">직접입력</option>
  <option value="naver.com">네이버</option>
  <option value="hanmail.net">한메일</option>
  <option value="gmail.com">구글</option>
</select>
<button id="check">확인</button>
</body>
</html>