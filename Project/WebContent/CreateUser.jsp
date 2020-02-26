<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#createbtn_ar{
	width:100%
}
#createbtn{
width:100%;
}
</style>
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
function idreg(){
    var reg = /^(?=.*[a-z])[A-Za-z\d]{6,20}$/;
    var id=document.getElementById("id_input").value;
    if(id.match(reg)){
        document.getElementById("idtext").style.color="green";
        document.getElementById("idtext").innerHTML="사용가능"
    }
    if(!id.match(reg)){
        document.getElementById("idtext").style.color="red";
        document.getElementById("idtext").innerHTML="유효하지 않습니다."
    }
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
function pnreg(){
    var reg =  /^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))-[1-4][0-9]{6}$/;
    var personno=$('#personno_input').val();
    var personno2=$('#personno_input2').val();
    var personno3=personno+"-"+personno2;
    if(personno3.match(reg)){
        document.getElementById("pntext").style.color="green";
        document.getElementById("pntext").innerHTML="사용가능"
    }
    if(!personno3.match(reg)){
        document.getElementById("pntext").style.color="red";
        document.getElementById("pntext").innerHTML="유효하지 않습니다."
    }
}
</script>
<script>
$(document).ready(function(){
	$('#createbtn').click(function create() {
			var idreg = /^(?=.*[a-z])(?=.*\d)[A-Za-z\d]{6,20}$/;
			var pwreg = /^(?=.*[a-z])(?=.*\d)[a-zA-Z\d$@$!%*#?&]{8,16}$/;
		     var personnoreg= /^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))-[1-4][0-9]{6}$/;
			var pwcheck = $('#pwcheck_input').val();
				var idcheck=$('#idcheck_input').val();
			    var id = $('#id_input').val();
			     var password = $('#pw_input').val();
			     var personno=$('#personno_input').val();
			     var personno2=$('#personno_input2').val();
			     var personno3=personno+"-"+personno2;
			     var name=$('#name_input').val();
			     var email=$('#email_input').val();
			     var domain=$('#domain_input').val();
			     if(idcheck=='false'){
			    	 alert('ID중복체크를 하지 않으셨습니다.');
			     }
			     else if(password!=pwcheck){
			    	 alert('비밀번호가 일치하지 않습니다.');
			    	 $('#pw_input').focus();
			     }
			     else if(id==''){
			    	 alert('ID는 필수 입력입니다.');
			    	 $('#id_input').focus();
			     }
			     else if(password==''){
			    	 alert('비밀번호는 필수 입력입니다.');
			    	 $('#pw_input').focus();
			     }
			     else if(name==''){
			    	 alert('이름은 필수 입력입니다.');
			    	 $('#name_input').focus();
			     }
			     else if(personno=='' || personno2==''){
			    	 alert('주민등록 번호는 필수입니다.');
			    	 $('#personno_input').focus();
			     }
			     else if(email=='' || domain ==''){
			    	 alert('이메일은 필수입니다.');
			    	 $('#email_input').focus();
			     }
	     else if(id.match(idreg) && password.match(pwreg) && personno3.match(personnoreg)){
		   	  $.ajax({
			       type : "POST",
			         url : "CreateUsers",
			         data : "id=" + id + "&pw=" + password + "&name=" + name
			         + "&personno=" + personno + "&personno2=" + personno2
			         + "&email=" + email + "&domain=" + domain,
			         dataType : "text",
			       success : function(data, textStatus, xhr) {
			            if (data == 'addFail') {
			                 alert('회원가입 실패');
			          	}
			            else if(data=='personnoOverlap'){
			            	alert('이미 가입된 회원정보입니다.');
			            }
			            else if(data=='emailFail'){
				        	  alert('이메일 인증메일 발송 실패');
			            }
			            else if(data=='Success') {
			        	  alert('회원가입 성공');
	                      location.href = 'Main.jsp';
			}
			       },
			error : function(request, status, error) {
			alert("code:" + request.status + "\n" + "error:" + error);
			}
			})
	     }
	});
	$('#checkid').click(function checkUser() {
	    var id = $('#id_input').val();
		var idreg = /^(?=.*[a-z])(?=.*\d)[A-Za-z\d]{6,20}$/;
	    if(id==''){
	    	alert("ID를 입력하세요.");
	    	$('#id_input').focus();
	    }
	    else if(!id.match(idreg)){
	    	alert("유효하지 않은 아이디입니다.");
	    	$('#id_input').focus();
	    }
	    else{
	  	  $.ajax({
		       type : "POST",
		         url : "CheckUser",
		         data : "id=" + id,
		         dataType : "text",
		       success : function(data, textStatus, xhr) {
		            if (data == 'idOverlap') {
		                 alert('중복된 아이디입니다.');
		          	}
		            else if(data=='idnotOverlap'){
		            	alert('사용 가능한 아이디입니다.');
		            	$('#idcheck_input').val('true');
		            	
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
<h3> 회원가입
</h3>
<table>
<tr>
<td>
아이디 <button id="checkid" >아이디 중복확인</button>
</td>
</tr>
<tr>
<td>
<input type="text" id="id_input" onkeyup="idreg()">
<input type="hidden" id="idcheck_input" value="false">
<div id="idtext"></div>
</td>
</tr>
<tr>
<td>
비밀번호
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
비밀번호 확인
</td>
</tr>
<tr>
<td>
<input type="password" id="pwcheck_input">
</td>
</tr>
<tr>
<td>
이름
</td>
</tr>
<tr>
<td>
<input type="text" id="name_input">
</td>
</tr>
<tr>
<td>
주민등록번호
</td>
</tr>
<tr>
<td>
<input type="text" id="personno_input" onkeyup="pnreg()">
-
<input type="password" id="personno_input2" onkeyup="pnreg()">
<div id="pntext"></div>
</td>
</tr>
<tr>
<td>
이메일
</td>
</tr>
<tr>
<td>
<input type="text" id="email_input">
@
<input type="text" id="domain_input">
<select onchange="domainselect()" id="domainvalue">
  <option value="">직접입력</option>
  <option value="naver.com">네이버</option>
  <option value="hanmail.net">한메일</option>
  <option value="gmail.com">구글</option>
</select>
</td>
</tr>
<tr>
<td id="createbtn_ar">
<br>
<button id="createbtn">회원가입하기</button>
</td>
</table>
</body>
</html>