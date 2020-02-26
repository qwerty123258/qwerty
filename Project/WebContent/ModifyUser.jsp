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
function domainselect(){
    var email=document.getElementById("domainvalue").value;
    document.getElementById("domain_input").value=email;
}
function pwreg(){
    var reg = /^(?=.*[a-z])(?=.*\d)[a-zA-Z\d$@$!%*#?&]{8,16}$/;
    var pw=document.getElementById("pw_input").value;
    if(pw.match(reg)){
        document.getElementById("pwtext").style.color="green";
        document.getElementById("pwtext").innerHTML="사용가능"
    }
    if(!pw.match(reg)){
        document.getElementById("pwtext").style.color="red";
        document.getElementById("pwtext").innerHTML="유효하지 않습니다."
    }
}
</script>
<script>
$(document).ready(function(){
	$('#update').click(function(){
        var password = $('#pw_input').val();
        var name =$('#name_input').val();
        var email =$('#email_input').val();
        var domain =$('#domain_input').val();
        var email2=email+"@"+domain;
		var pwreg = /^(?=.*[a-z])(?=.*\d)[a-zA-Z\d$@$!%*#?&]{8,16}$/;
        if(password==""){
        	alert('비밀번호가 비어있습니다.');
        	$('#pw_input').focus();
        }
        else if(password.match(pwreg)){
            $.ajax({
                type : "POST",
                  url : "UpdateUser",
                  data : "pw=" + password+ "&name=" + name+ "&email=" + email2,
                  dataType : "text",
                success : function(data, textStatus, xhr) {
                     if (data == 'updateFail') {
                          alert('수정에 실패하였습니다.');
                     }
                   else if(data=='update') {
                 	  alert('수정 완료~');
                        location.href = 'Main.jsp';
     	}
                },
     error : function(request, status, error) {
     alert("code:" + request.status + "\n" + "error:" + error);
     }
     })
        }
	});
	
})
</script>

</head>
<body>
<table>
<tr>
<td>
아이디
</td>
</tr>
<tr>
<td>
${sessionScope.id}
</td>
</tr>
<tr>
<td>
비밀번호 수정
</td>
</tr>
<tr>
<td>
<input type="password" id="pw_input" onkeyup="pwreg()">
<div id="pwtext"></div>
</td>
</tr>
<tr>
<td>
(개명시) 이름 수정
</td>
</tr>
<tr>
<td>
<input type="text" id="name_input" value="${requestScope.name}">
</td>
</tr>
<tr>
<td>
이메일 주소 수정
</td>
</tr>
<tr>
<td>
<input type="text" id="email_input" value="${requestScope.email}">
@
<input type="text" id="domain_input" value="${requestScope.domain}">
<select onchange="domainselect()" id="domainvalue">
  <option value="">직접입력</option>
  <option value="naver.com">네이버</option>
  <option value="hanmail.net">한메일</option>
  <option value="gmail.com">구글</option>
</select>
</td>
</tr>
</table>
<button id="update">수정하기</button>
</body>
</html>