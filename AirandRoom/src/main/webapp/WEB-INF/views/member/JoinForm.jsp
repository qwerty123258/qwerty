<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
	function domainselect() {
		var email = document.getElementById("domainvalue").value;
		document.getElementById("emaildomain").value = email;
	}
	function checkIMG() {
		var imgfile = document.getElementById("pic").value;
		if (imgfile != "") {
			var ext = imgfile.slice(imgfile.lastIndexOf(".") + 1).toLowerCase();
			if (!(ext == "gif" || ext == "jpg" || ext == "png" || ext == "bmp")) {
				alert("이미지파일 (.jpg, .png, .gif,.bmp ) 만 업로드 가능합니다.");
				return false;
			} else {
				return true;
			}
		} else {
			return true;
		}
	}
	function readURL(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				$('#picPreview').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	function sendVerifyCode() {
		var emailid = document.getElementById("emailid").value
		var emaildomain = document.getElementById("emaildomain").value
		var email = emailid + "@" + emaildomain;
		$.ajax({
			type : "POST",
			url : "checkEmail",
			data : "email=" + email,
			dataType : "text",
			success : function(result) {
				if (result == "Overlap") {
					alert("해당 이메일 주소는 이미 사용중입니다.");
				} else if (result = "NotOverlap") {
					var url = "sendCode?email=" + email;
					window.open(url, 'pop01',
							'width=500, height=500, status=no, menubar=no, toolbar=no');
				}

			},
			error : function() {
				alert("이메일 중복 체크중 에러 발생");
			}

		});

	}
	function checkID() {
		var id = document.getElementById("id").value;
		$.ajax({
			type : "GET",
			url : "checkID",
			data : "id=" + id,
			dataType : "text",
			success : function(result) {
				if (result == "Overlap") {
					$("#checkID").html("중복된 아이디");
					$("#checkIDResult").val("중복된 아이디");
				} else if (result = "NotOverlap") {
					$("#checkID").html("중복되지 않은 아이디");
					$("#checkIDResult").val("중복되지 않은 아이디");
				}

			},
			error : function() {
				alert("에러 발생");
			}
		})
	}
	function pwcheck() {
		var pw = $("#pw").val();
		var pwCheck = $("#pwCheck").val();
		var pwreg = /^(?=.*[a-z])(?=.*\d)[a-zA-Z\d$@$!%*#?&]{8,16}$/;
		if (!pw.match(pwreg)) {
			$('#pw_text').css("color", "red");
			$('#pw_text').html("비밀번호 사용불가");
		} else {
			$('#pw_text').css("color", "black");
			$('#pw_text').html("비밀번호 사용가능");
			if (pw == pwCheck) {
				$('#pwCheck_text').css("color", "black");
				$('#pwCheck_text').html("비밀번호 일치");
			} else {
				$('#pwCheck_text').css("color", "red");
				$('#pwCheck_text').html("비밀번호 불일치");
			}
		}
	}
	function joinMember() {
		var id = $("#id").val();
		var pw = $("#pw").val();
		var pwCheck = $("#pwCheck").val();
		var name = $("#name").val();
		var checkIDResult = $("#checkIDResult").val();
		var emailid = $("#emailid").val();
		var emaildomain = $("#emaildomain").val();
		var idreg = /^(?=.*[a-z])[A-Za-z\d]{5,20}$/;
		var pwreg = /^(?=.*[a-z])(?=.*\d)[a-zA-Z\d$@$!%*#?&]{8,16}$/;
		var phoneFirst = $("#phoneFirst").val();
		var phoneSecond = $("#phoneSecond").val();
		var phoneLast = $("#phoneLast").val();
		var phone = phoneFirst + "-" + phoneSecond + "-" + phoneLast;
		var phonereg = /^\d{3}-\d{4}-\d{4}$/;
		var certify = $("#certify").val();
		$("#email").val(emailid + "@" + emaildomain);
		var email = $("#email").val();
		var phoneval = $("#phone").val();
		if (id == "") {
			alert("아이디를 입력하세요");
			$("#id").focus();
		}
		else if(pw == "") {
				alert("비밀번호를 입력하세요");
				$("#pw").focus();
			}
			else if (pwCheck == "") {
				alert("비밀 번호 확인을 해야합니다.");
				$("#pwCheck").focus();
			} else if (pw != pwCheck) {
				alert("입력하신 비밀번호가 서로 같지 않습니다.");
				$("#pwCheck").val("");
				$("#pwCheck").focus();
			}
			else if (id.match(idreg) && !pw.match(pwreg)) {
				alert("유효하지 않은 비밀번호입니다.");
				$("#pw").val("");
				$("#pwCheck").val("");
				$("#pw_text").html("");
				$("#pw").focus();
			}
		 else if (name == "") {
			alert("이름을 입력하세요");
			$("#name").focus();
		} else if (emailid == "" || emaildomain == "") {
			alert("이메일 주소를 입력하세요");
			$("#emailid").focus();
		} else if (!phone.match(phonereg)) {
			alert("유효한 번호가 아닙니다.");
			$("#phoneFirst").val("");
			$("#phoneSecond").val("");
			$("#phoneLast").val("");
			$("#phone_text").html("");
			$("#phoneFirst").focus();
		} else if (phoneval == "") {
			alert("번호를 다시 입력해주세요.");
			$("#phoneFirst").val("");
			$("#phoneSecond").val("");
			$("#phoneLast").val("");
			$("#phone_text").html("");
			$("#phoneFirst").focus();
		} else if (checkIDResult == "중복된 아이디") {
			alert("중복된 아이디입니다.");
		} else if (!id.match(idreg) && pw.match(pwreg)) {
			alert("유효하지 않은 아이디입니다.");
			$("#id").val("");
			$("#checkID").html("");
			$("#checkIDResult").val("");
			$("#id").focus();
		} else if (certify == "인증안됨") {
			alert("이메일 인증이 완료되지 않았습니다.");
		} else if (checkIMG()) {
				if (id.match(idreg) && pw.match(pwreg) && phone.match(phonereg)) {
					var formData = new FormData(document.getElementById("form")); // 
					$.ajax({
						type : 'POST',
						url : 'createMember',
						processData : false, // 필수 
						contentType : false, // 필수 
						data : formData,
						success : function(data) {
							if (data == "Success") {
								alert("회원가입 완료");
								location.href = "main";
							} else {
								alert("회원가입 실패");
							}
						},
						error : function() {
							alert("실패");
						}
					});
				}
		}
	}
	function phonefirst() {
		var phoneFirst = $("#phoneFirst").val();
		if (phoneFirst.length > 2) {
			$("#phoneSecond").focus();
		}
	}
	function phonesecond() {
		var phoneSecond = $("#phoneSecond").val();
		if (phoneSecond.length > 3) {
			$("#phoneLast").focus();
		}
	}
	function phonecheck() {
		var phoneFirst = $("#phoneFirst").val();
		var phoneSecond = $("#phoneSecond").val();
		var phoneLast = $("#phoneLast").val();
		var phone = phoneFirst + "-" + phoneSecond + "-" + phoneLast;
		var phonereg = /^\d{3}-\d{4}-\d{4}$/;
		if (phone.match(phonereg)) {
			$("#phone_text").html("사용가능");
			$("#phone").val(phone);
		} else {
			$("#phone_text").html("유효한 형식의 번호가 아닙니다.");
		}
	}
	function joinKakaoMember(){
	var id = $("#id").val();
	var name = $("#name").val();
	var checkIDResult = $("#checkIDResult").val();
	var emailid = $("#emailid").val();
	var emaildomain = $("#emaildomain").val();
	var idreg = /^(?=.*[a-z])[A-Za-z\d]{5,20}$/;
	var phoneFirst = $("#phoneFirst").val();
	var phoneSecond = $("#phoneSecond").val();
	var phoneLast = $("#phoneLast").val();
	var phone = phoneFirst + "-" + phoneSecond + "-" + phoneLast;
	var phonereg = /^\d{3}-\d{4}-\d{4}$/;
	var certify = $("#certify").val();
	$("#email").val(emailid + "@" + emaildomain);
	var email = $("#email").val();
	var phoneval = $("#phone").val();
	if (id == "") {
		alert("아이디를 입력하세요");
		$("#id").focus();
	}
	 else if (name == "") {
		alert("이름을 입력하세요");
		$("#name").focus();
	} else if (emailid == "" || emaildomain == "") {
		alert("이메일 주소를 입력하세요");
		$("#emailid").focus();
	} else if (!phone.match(phonereg)) {
		alert("유효한 번호가 아닙니다.");
		$("#phoneFirst").val("");
		$("#phoneSecond").val("");
		$("#phoneLast").val("");
		$("#phone_text").html("");
		$("#phoneFirst").focus();
	} else if (phoneval == "") {
		alert("번호를 다시 입력해주세요.");
		$("#phoneFirst").val("");
		$("#phoneSecond").val("");
		$("#phoneLast").val("");
		$("#phone_text").html("");
		$("#phoneFirst").focus();
	} else if (checkIDResult == "중복된 아이디") {
		alert("중복된 아이디입니다.");
	} else if (!id.match(idreg)) {
		alert("유효하지 않은 아이디입니다.");
		$("#id").val("");
		$("#checkID").html("");
		$("#checkIDResult").val("");
		$("#id").focus();
	} else if (certify == "인증안됨") {
		alert("이메일 인증이 완료되지 않았습니다.");
	}else if (checkIMG()) {
			if (id.match(idreg) && phone.match(phonereg)) {
				var formData = new FormData(document.getElementById("form")); // 
				$.ajax({
					type : 'POST',
					url : 'createMember',
					processData : false, // 필수 
					contentType : false, // 필수 
					data : formData,
					success : function(data) {
						if (data == "Success") {
							alert("회원가입 완료");
							location.href = "main";
						} else {
							alert("회원가입 실패");
						}
					},
					error : function() {
						alert("실패");
					}
				});
			}
	}
}
	function joinGoogleMember(){
		var id = $("#id").val();
		var name = $("#name").val();
		var checkIDResult = $("#checkIDResult").val();
		var emailid = $("#emailid").val();
		var emaildomain = $("#emaildomain").val();
		var idreg = /^(?=.*[a-z])[A-Za-z\d]{5,20}$/;
		var phoneFirst = $("#phoneFirst").val();
		var phoneSecond = $("#phoneSecond").val();
		var phoneLast = $("#phoneLast").val();
		var phone = phoneFirst + "-" + phoneSecond + "-" + phoneLast;
		var phonereg = /^\d{3}-\d{4}-\d{4}$/;
		var certify = $("#certify").val();
		$("#email").val(emailid + "@" + emaildomain);
		var email = $("#email").val();
		var phoneval = $("#phone").val();
		if (id == "") {
			alert("아이디를 입력하세요");
			$("#id").focus();
		}
		 else if (name == "") {
			alert("이름을 입력하세요");
			$("#name").focus();
		} else if (emailid == "" || emaildomain == "") {
			alert("이메일 주소를 입력하세요");
			$("#emailid").focus();
		} else if (!phone.match(phonereg)) {
			alert("유효한 번호가 아닙니다.");
			$("#phoneFirst").val("");
			$("#phoneSecond").val("");
			$("#phoneLast").val("");
			$("#phone_text").html("");
			$("#phoneFirst").focus();
		} else if (phoneval == "") {
			alert("번호를 다시 입력해주세요.");
			$("#phoneFirst").val("");
			$("#phoneSecond").val("");
			$("#phoneLast").val("");
			$("#phone_text").html("");
			$("#phoneFirst").focus();
		} else if (checkIDResult == "중복된 아이디") {
			alert("중복된 아이디입니다.");
		} else if (!id.match(idreg)) {
			alert("유효하지 않은 아이디입니다.");
			$("#id").val("");
			$("#checkID").html("");
			$("#checkIDResult").val("");
			$("#id").focus();
		} else if (certify == "인증안됨") {
			alert("이메일 인증이 완료되지 않았습니다.");
		}
		else if (checkIMG()) {
				if (id.match(idreg) && phone.match(phonereg)) {
					var formData = new FormData(document.getElementById("form")); // 
					$.ajax({
						type : 'POST',
						url : 'createMember',
						processData : false, // 필수 
						contentType : false, // 필수 
						data : formData,
						success : function(data) {
							if (data == "Success") {
								alert("회원가입 완료");
								location.href = "main";
							} else {
								alert("회원가입 실패");
							}
						},
						error : function() {
							alert("실패");
						}
					});
				}
		}
		
	}
</script>
<style>
#picPreview {
	width: 100px;
	height: 100px;
}
</style>
</head>
<body>
	<jsp:include page="../Header.jsp"></jsp:include><br>
	<jsp:include page="../Nav.jsp"></jsp:include><br>
	<div class="container" id="container2">
		<div class="col-xs-4">
		</div>
				<div class="col-xs-4">
		<div class="row">
		<div class="col-xs-12">
					<h1>회원가입</h1>
					<div style="text-align:right;">
							<c:choose>
		<c:when test="${kakaoid ne null}">
			<button class="btn btn-warning" onclick="joinKakaoMember()">회원가입하기</button>
		</c:when>
		<c:when test="${googleid ne null}">
			<button class="btn" onclick="joinGoogleMember()">회원가입하기</button>
		</c:when>
		<c:otherwise>
		<button class="btn btn-info" onclick="joinMember()">회원가입하기</button>
		</c:otherwise>
	</c:choose>
					
					</div>
		</div>
	<br>
	<form id="form" enctype="multipart/form-data">
				<c:choose>
				<c:when test="${kakaoid ne null}">
				<div class="form-group">
						아이디<br> 
		<input class="form-control" type="text" id="id" name="id" onkeyup="checkID()"><br>
		<span id="checkID"></span><br> 
		<input type="hidden" id="checkIDResult">
<input type="hidden" name="kakaoid" id="kakaoid" value="${kakaoid}">	
				</div>
				</c:when>
				<c:when test="${googleid ne null}">
												<div class="form-group">
										아이디<br> 
		<input type="text"  class="form-control" id="id" name="id" onkeyup="checkID()"><br>
		<span id="checkID"></span><br> 
		<input type="hidden" id="checkIDResult">
<input type="hidden" name="googleid" id="googleid" value="${googleid}">
								
								</div>
				</c:when>
				<c:otherwise>
								<div class="form-group">
														아이디<br> 
		<input class="form-control" type="text" id="id" name="id" onkeyup="checkID()"><br>
		<span id="checkID"></span><br> 
		<input type="hidden" id="checkIDResult">
										 비밀번호<br> 
		 <input class="form-control" type="password" id="pw" name="pw" onkeyup="pwcheck()"><br> 
		 <span id="pw_text"></span><br> 
		 비밀번호 확인<br> 
		 <input  class="form-control" type="password" id="pwCheck" onkeyup="pwcheck()"><br> 
		 <span id="pwCheck_text"></span><br>
								</div>
				</c:otherwise>
				</c:choose>
												<div class="form-group">
														 이름<br> 
		 <input type="text" class="form-control" id="name" name="name">
		 </div>
		 이메일<br>
		<div class="col-xs-10" style="padding:0px;">
			<div class="col-xs-7" style="padding:0px;">
			<input class="form-control" type="text" id="emailid">
			</div>
			<div class="col-xs-5" style="padding:0px;">
			<input class="form-control" type="text" id="emaildomain">
			</div>
		</div> 
		 <div class="col-xs-2">
		 <div class="col-xs-2">
		 <select onchange="domainselect()" id="domainvalue">
			<option value="">직접입력</option>
			<option value="naver.com">네이버</option>
			<option value="hanmail.net">한메일</option>
			<option value="gmail.com">구글</option>
		</select>
		</div>
		 </div>
		<input type="hidden" value="인증안됨" id="certify"> 
		<input type="hidden" id="email" name="email"> 
		<br><br>
		<button type="button" class="btn btn-success" onclick="sendVerifyCode()">이메일 인증하기</button><br><br>
				연락처 
		<div class="form-group">
		<div class="col-xs-4" style="padding:0px;">
				<input type="text" class="form-control" id="phoneFirst" onkeyup="phonefirst()"> 
		
		</div>
				<div class="col-xs-4" style="padding:0px;">
		<input type="text" class="form-control" id="phoneSecond" onkeyup="phonesecond()"> 
		
		</div>
				<div class="col-xs-4" style="padding:0px;">

		<input type="text" class="form-control" id="phoneLast" onKeyup="phonecheck()">
		
		</div> 
		<input type="hidden" id="phone" name="phone">
				<span id="phone_text"></span>
		</div> <br>
		<div>
		프로필 사진 설정
			
			<img id="picPreview" src="#" alt="your image"
				onError="this.src='${pageContext.request.contextPath}/resources/img/default.webp'" />
			<br>
			<br>
			<input type="file" name="pic" id="pic" onchange="readURL(this);">
		<input type="hidden" id="kind" name="kind" value="${kind}">
												
												</div>
	</form>
	<br>
		
		</div>
		</div>
				<div class="col-xs-4">
		</div>
	 </div>
</body>
</html>