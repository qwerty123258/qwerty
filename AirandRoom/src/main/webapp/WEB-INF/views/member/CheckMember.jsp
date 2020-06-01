<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보수정전 본인확인</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
	function checkMember() {
		var pw = $("#pw").val();
		$.ajax({
			type : "POST",
			url : "checkMember",
			data : "pw=" + pw,
			dataType : "text",
			success : function(result) {
				if (result == "Fail") {
					alert("비밀번호가 다릅니다.");
					$("pw").val("");
					$("pw").focus();
				} else if (result == "Success") {
					location.href = "modifyMembers";
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
			본인 확인을 위하여 비밀번호를 입력하세요. <br><br><br> <input type="password" id="pw">
			<button class="btn btn-success" onclick="checkMember()">확인하기</button>
		</div>
	</div>
</body>
</html>