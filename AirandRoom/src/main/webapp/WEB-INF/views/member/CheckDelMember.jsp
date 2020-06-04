<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 탈퇴전 본인 확인</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
	function checkDelMember() {
		var pw = $("#pw").val();
		$.ajax({
			type : "POST",
			url : "checkDelMember",
			data : "pw=" + pw,
			dataType : "text",
			success : function(result) {
				if (result == "Fail") {
					alert("비밀번호가 다릅니다.");
					$("pw").val("");
					$("pw").focus();
				} else if (result == "Success") {
					if (confirm('탈퇴를 진행하시겠습니까?')) {
						$.ajax({
							type : "GET",
							url : "deleteMember",
							dataType : "text",
							success : function(result) {
								if (result == "Success") {
									alert("회원 탈퇴가 완료되었습니다.");
									location.href = "main";
								} else {
									alert("회원 탈퇴 실패 다시 진행해주세요.");
								}
							},
							error : function() {
								alert("회원 탈퇴 진행중 에러 발생");
							}
						});
					}

				}

			},
			error : function() {
				alert("본인 확인 중 에러 발생");
			}

		})
	}
</script>
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
		본인확인을 위하여 비밀번호를 입력하여 주세요.<br>
			<br> <input type="password" id="pw">
			<button class="btn btn-success" onclick="checkDelMember()">확인하기</button>
		</div>

	</div>
</body>
</html>