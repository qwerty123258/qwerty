<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<script type="text/javascript" src="js/create.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>
#createbtn_ar{
	width:100%
}
#createbtn{
width:100%;
}
#checkid{
float:right;
}
</style>
<script>
$(document).ready(function(){
	$('#createbtn').click(function create() {
			var idreg = /^(?=.*[a-z])(?=.*\d)[A-Za-z\d]{6,20}$/;
			var pwreg = /^(?=.*[a-z])(?=.*\d)[a-zA-Z\d$@$!%*#?&]{8,16}$/;
		     var personnoreg= /^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))-[1-4][0-9]{6}$/;
			var pwcheck = $('#pwcheck_input').val();
				var idcheck=$('#idcheck_input').val();
			    var id = $('#createid_input').val();
			     var password = $('#createpw_input').val();
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
			    	 $('#createpw_input').focus();
			     }
			     else if(id==''){
			    	 alert('ID는 필수 입력입니다.');
			    	 $('#createid_input').focus();
			     }
			     else if(password==''){
			    	 alert('비밀번호는 필수 입력입니다.');
			    	 $('#createpw_input').focus();
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
	                      location.reload();
			}
			       },
			error : function(request, status, error) {
			alert("code:" + request.status + "\n" + "error:" + error);
			}
			})
	     }
	});
	$('#checkid').click(function checkUser() {
	    var id = $('#createid_input').val();
		var idreg = /^(?=.*[a-z])(?=.*\d)[A-Za-z\d]{6,20}$/;
	    if(id==''){
	    	alert("ID를 입력하세요.");
	    	$('#createid_input').focus();
	    }
	    else if(!id.match(idreg)){
	    	alert("유효하지 않은 아이디입니다.");
	    	$('#createid_input').focus();
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
                        <jsp:include page="Header.jsp"></jsp:include>
    <div class="row">
        <div class="col-sm-12">
  <ul class="nav nav-pills nav-justified">
    <li class="active"><a href="Main.jsp">Home</a></li>
    <li><a href="MovieList">영화</a></li>
 	<li><a href="DramaList">드라마</a></li>
    <li><a href="UtilList">유틸</a></li>
    <li><a href="OtherList">기타</a></li>
  </ul>
        </div>
        <div class="col-sm-3">
                        <jsp:include page="SideNav.jsp"></jsp:include>
        </div>
        <div class="col-sm-9">
<table>
<caption><h2>파일 천국 회원가입</h2></caption>
<tr>
<td>
아이디 <button id="checkid" >중복확인</button>
</td>
</tr>
<tr>
<td>
<input type="text" id="createid_input" onkeyup="idreg()">
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
<input type="password" id="createpw_input" onkeyup="pwreg()">
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
<pre>회원가입 후 이메일 주소로 인증링크가 전송 됩니다.
인증을 하지 않을시 회원가입이 더 이상 진행되지 않습니다!!</pre>
</td>
</tr>
<tr>
<td id="createbtn_ar">
<br>
<button id="createbtn">회원가입하기</button>
</td>
</table>
        </div>
    </div>

  <jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>