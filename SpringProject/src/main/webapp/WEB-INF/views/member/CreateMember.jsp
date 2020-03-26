<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
function domainselect(){
    var email=document.getElementById("domainvalue").value;
    document.getElementById("domain_input").value=email;
}
function idOverlap() {
	var id = $("#id_input").val();
	var idreg = /^(?=.*[a-z])[A-Za-z\d]{5,20}$/;
	$.ajax({
		type : "POST",
		url : "CheckMember",
		data : "id=" + id,
		dataType : "text",
		success : function(result) {
			if (result == 'idOverlap') {
				$('#id_text').css("color", "red");
				$('#id_text').html("아이디 중복");
				$('#idCheck').val('false');
			} else if (result == 'idNotOverlap') {
				if(id.match(idreg)){
					$('#id_text').css("color", "black");
					$('#id_text').html("아이디 사용가능");
					$('#idCheck').val('true');
				}
				else{
					$('#id_text').css("color", "red");
					$('#id_text').html("아이디 사용불가");
				}
				

			}

		},
		error : function() {
			alert("실패");
		}
	});
}
function goCreate(){
	var id=$("#id_input").val();
	var pw=$("#pw_input").val();
	var pwcheck=$("#pwcheck_input").val();
	var name=$("#name_input").val();
	var idcheck=$("#idCheck").val();
	var emailinput=$("#email_input").val();
	var domaininput=$("#domain_input").val();
	var idreg = /^(?=.*[a-z])[A-Za-z\d]{5,20}$/;
	var pwreg = /^(?=.*[a-z])(?=.*\d)[a-zA-Z\d$@$!%*#?&]{8,16}$/;
	if(id==""){
		alert("아이디를 입력하세요");
		$("#id_input").focus();
	}
	else if(pw==""){
		alert("비밀번호를 입력하세요");
		$("#pw_input").focus();
	}
	else if(pwcheck==""){
		alert("비밀번호 확인을 해야합니다.");
		$("#pwcheck_input").focus();
	}
	else if(pw!=pwcheck){
		alert("입력하신 비밀번호가 서로 같지 않습니다.");
		$("#pwcheck_input").val("");
		$("#pwcheck_input").focus();
	}
	else if(name==""){
		alert("이름을 입력하세요");
		$("#name_input").focus();
	}
	else if(emailinput=="" || domaininput==""){
		alert("이메일 주소를 입력하세요");
		$("#email_input").focus();
	}
	else if(idcheck=="false"){
		alert("중복된 아이디입니다.");
	}
	else if(!id.match(idreg) && pw.match(pwreg)){
		alert("유효하지 않은 아이디입니다.");
		$("#id_input").val("");
		$("#id_text").html("");
		$("#idCheck").val("");
		$("#id_input").focus();
	}
	else if(id.match(idreg) && !pw.match(pwreg)){
		alert("유효하지 않은 비밀번호입니다.");
		$("#pw_input").val("");
		$("#pwcheck_input").val("");
		$("#pw_text").html("");
		$("#pw_input").focus();
	}
	else if(imgFileCheck()){
			if(id.match(idreg) && pw.match(pwreg)){
				$("#createForm").submit();
			}
		}
	}
function goCreateKakao(){
	var id=$("#id_input").val();
	var name=$("#name_input").val();
	var idcheck=$("#idCheck").val();
	var emailinput=$("#email_input").val();
	var domaininput=$("#domain_input").val();
	var idreg = /^(?=.*[a-z])[A-Za-z\d]{5,20}$/;
	if(id==""){
		alert("아이디를 입력하세요");
		$("#id_input").focus();
	}
	else if(name==""){
		alert("이름을 입력하세요");
		$("#name_input").focus();
	}
	else if(emailinput=="" || domaininput==""){
		alert("이메일 주소를 입력하세요");
		$("#email_input").focus();
	}
	else if(idcheck=="false"){
		alert("중복된 아이디입니다.");
	}
	else if(!id.match(idreg)){
		alert("유효하지 않은 아이디입니다.");
		$("#id_input").val("");
		$("#id_text").html("");
		$("#idCheck").val("");
		$("#id_input").focus();
	}
	else if(imgFileCheck()){
			if(id.match(idreg)){
				$("#createForm").submit();
			}
		}
	}
function goCreateNaver(){
	var id=$("#id_input").val();
	var name=$("#name_input").val();
	var idcheck=$("#idCheck").val();
	var emailinput=$("#email_input").val();
	var domaininput=$("#domain_input").val();
	var idreg = /^(?=.*[a-z])(?=.*\d)[A-Za-z\d]{6,20}$/;
	if(id==""){
		alert("아이디를 입력하세요");
		$("#id_input").focus();
	}
	else if(name==""){
		alert("이름을 입력하세요");
		$("#name_input").focus();
	}
	else if(emailinput=="" || domaininput==""){
		alert("이메일 주소를 입력하세요");
		$("#email_input").focus();
	}
	else if(idcheck=="false"){
		alert("중복된 아이디입니다.");
	}
	else if(!id.match(idreg)){
		alert("유효하지 않은 아이디입니다.");
		$("#id_input").val("");
		$("#id_text").html("");
		$("#idCheck").val("");
		$("#id_input").focus();
	}
	else if(imgFileCheck()){
			if(id.match(idreg)){
				$("#createForm").submit();
			}
		}
	}
function imgFileCheck(){
    var imgfile=document.getElementById("imgfile").value;
	if (imgfile != "") {
	    var ext = imgfile.slice(imgfile.lastIndexOf(".") + 1).toLowerCase();
	    if (!(ext == "gif" || ext == "jpg" || ext == "png" || ext == "bmp")) {
	        alert("이미지파일 (.jpg, .png, .gif,.bmp ) 만 업로드 가능합니다.");
	        return false;
	}
	    else{
	    	return true;
	    }
}
	else{
		return true;
	}
}
function pwCheck(){
	var pw=$("#pw_input").val();
	var pwcheck=$("#pwcheck_input").val();
	var pwreg = /^(?=.*[a-z])(?=.*\d)[a-zA-Z\d$@$!%*#?&]{8,16}$/;
	if(!pw.match(pwreg)){
		$('#pw_text').css("color", "red");
		$('#pw_text').html("비밀번호 사용불가");
	}
	else{
		$('#pw_text').css("color", "black");
		$('#pw_text').html("비밀번호 사용가능");
		if(pw==pwcheck){
			$('#pwcheck_text').css("color", "black");
			$('#pwcheck_text').html("비밀번호 일치");
		}
		else{
			$('#pwcheck_text').css("color", "red");
			$('#pwcheck_text').html("비밀번호 불일치");
		}
	}
}
</script>
</head>
<body>
<form action="CreateMember" method="post" enctype="multipart/form-data" id="createForm">
			<c:choose>
				<c:when test="${kakaoid ne null}">
				아이디<br>
<input type="text" name="id" id="id_input" autocomplete="off" onkeyup="idOverlap()">
<span id="id_text"></span><br>
<input type="hidden" id="idCheck" value="false">
<input type="hidden" name="kakaoid" id="kakaoid" value="${kakaoid}">
				</c:when>
				<c:when test="${naverid ne null}">
				아이디<br>
<input type="text" name="id" id="id_input" autocomplete="off" onkeyup="idOverlap()">
<span id="id_text"></span><br>
<input type="hidden" id="idCheck" value="false">
<input type="hidden" name="naverid" id="naverid" value="${naverid}">
				</c:when>
				<c:otherwise>
				아이디<br>
<input type="text" name="id" id="id_input" autocomplete="off" onkeyup="idOverlap()">
<span id="id_text"></span><br>
<input type="hidden" id="idCheck" value="false">
비밀번호<br>
<input type="password" name="pw" id="pw_input" autocomplete="off" onkeyup="pwCheck()">
<span id="pw_text"></span><br>
비밀번호 확인<br>
<input type="password" id="pwcheck_input" autocomplete="off" onkeyup="pwCheck()">
<span id="pwcheck_text"></span><br>
				</c:otherwise>
				</c:choose>
				이름<br>
<input type="text" name="name" id="name_input" autocomplete="off"><br>
이메일<br>
<input type="text" name="email" id="email_input" autocomplete="off">
@
<input type="text" name="domain" id="domain_input">
<select onchange="domainselect()" id="domainvalue">
  <option value="">직접입력</option>
  <option value="naver.com">네이버</option>
  <option value="hanmail.net">한메일</option>
  <option value="gmail.com">구글</option>
</select><br>
프로필 사진<br>
<input type="file" id="imgfile" name="mfile">
</form>
	<c:choose>
		<c:when test="${kakaoid ne null}">
			<button onclick="goCreateKakao()">회원가입하기</button>
		</c:when>
		<c:when test="${naverid ne null}">
			<button onclick="goCreateNaver()">회원가입하기</button>
		</c:when>
		<c:otherwise>
			<button onclick="goCreate()">회원가입하기</button>
		</c:otherwise>
	</c:choose>
</body>
</html>