<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보수정</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
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
	function update() {
		var phoneFirst = $("#phoneFirst").val();
		var phoneSecond = $("#phoneSecond").val();
		var phoneLast = $("#phoneLast").val();
		var phoneval = $("#phone").val();
		var phone = phoneFirst + "-" + phoneSecond + "-" + phoneLast;
		var phonereg = /^\d{3}-\d{4}-\d{4}$/;
		if (!phone.match(phonereg)) {
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
		} else if (checkIMG()) {
			if (phone.match(phonereg)) {
				var formData = new FormData(document.getElementById("form")); // 
				$.ajax({
					type : 'POST',
					url : 'updateMember',
					processData : false, // 필수 
					contentType : false, // 필수 
					data : formData,
					success : function(data) {
						if (data == "Success") {
							alert("정보수정 완료");
							location.href = "myPage";
						} else {
							alert("정보수정 실패");
						}
					},
					error : function() {
						alert("실패");
					}
				});
			}
		}
	}
	function defaultIMG() {
		$('#picPreview')
				.attr('src',
						'${pageContext.request.contextPath}/resources/img/default.webp');
		$("#imgname").val("");
		$("#imgoriname").val("");
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
	<jsp:include page="../Nav.jsp"></jsp:include>
	<br>
	<div class="row">
		<div class="col-xs-3">
			<jsp:include page="SideNav.jsp"></jsp:include>
		</div>
		<div class="col-xs-9">
			<form id="form">
				연락처 수정<br> 
				<input type="text" id="phoneFirst" onkeyup="phonefirst()" value="${phonefirst}"> 
				<input type="text" id="phoneSecond" onkeyup="phonesecond()" value="${phonesecond}"> 
				<input type="text" id="phoneLast" onKeyup="phonecheck()" value="${phonelast}"><br> 
				<input type="hidden" id="phone" name="phone" value="${member.phone }">
				<span id="phone_text"></span><br>
				<c:if test="${sessionScope.kind ne airline}">
프로필 사진 수정<br>
					<input type="hidden" id="imgname" name="imgname" value="${member.imgname}">
					<input type="hidden" id="imgoriname" name="imgoriname" value="${member.imgoriname}">
					<img id="picPreview"
						src="${pageContext.request.contextPath}/resources/fileUpload/${member.imgname}"
						onError="this.src='${pageContext.request.contextPath}/resources/img/default.webp'">
					<input type="file" id="pic" name="pic" onchange="readURL(this)">
					<br>
					<button class="btn btn-info" type="button" onclick="defaultIMG()">기본이미지 사용</button>
				</c:if>
			</form>
			<br><br><br>
			<button class="btn btn-success" onclick="update()">수정하기</button>
		</div>
	</div>
</body>
</html>