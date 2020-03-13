<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<html>
<head>
<title>회원가입</title>
<script>
	$(document).ready(function() {
		$('#createbtn').click(function() {
			var id = $("#createid_input").val();
			var pw = $("#createpw_input").val();
			var name = $("#name_input").val();
			var gender = $("#gender_input").val();
			var idcheck = $('#idcheck_input').val();
			if (id == '') {
				alert('ID는 필수 입력입니다.');
				$('#createid_input').focus();
			} else if (pw == '') {
				alert('비밀번호는 필수 입력입니다.');
				$('#createpw_input').focus();
			} else if (name == '') {
				alert('이름은 필수 입력입니다.');
				$('#name_input').focus();
			} else if (gender == '') {
				alert('성별은 필수 입력입니다.');
				$('#gender_input').focus();
			} else if (idcheck == 'false') {
				alert('중복체크를 하세요');
			} else if (idcheck == 'true') {
				$("#create").submit();
			}
		});
		$('#kakaobtn').click(function() {
			var id = $("#createid_input").val();
			var name = $("#name_input").val();
			var gender = $("#gender_input").val();
			var idcheck = $('#idcheck_input').val();
			if (id == '') {
				alert('ID는 필수 입력입니다.');
				$('#createid_input').focus();
			} else if (name == '') {
				alert('이름은 필수 입력입니다.');
				$('#name_input').focus();
			} else if (gender == '') {
				alert('성별은 필수 입력입니다.');
				$('#gender_input').focus();
			} else if (idcheck == 'true') {
				$("#create").submit();
			}
		});
		$('#naverbtn').click(function() {
			var id = $("#createid_input").val();
			var name = $("#name_input").val();
			var gender = $("#gender_input").val();
			var idcheck = $('#idcheck_input').val();
			if (id == '') {
				alert('ID는 필수 입력입니다.');
				$('#createid_input').focus();
			} else if (name == '') {
				alert('이름은 필수 입력입니다.');
				$('#name_input').focus();
			} else if (gender == '') {
				alert('성별은 필수 입력입니다.');
				$('#gender_input').focus();
			} else if (idcheck == 'true') {
				$("#create").submit();
			}
		});
	})
</script>
<script>
	function idOverlap() {
		var id = $("#createid_input").val();
		$.ajax({
			type : "POST",
			url : "CheckUser",
			data : "id=" + id,
			dataType : "text",
			success : function(result) {
				if (result == 'idOverlap') {
					$('#id_text').css("color", "red");
					$('#id_text').html("아이디 중복");
					$('#idcheck_input').val('false');
				} else if (result == 'idNotOverlap') {
					$('#id_text').css("color", "black");
					$('#id_text').html("아이디 사용가능");
					$('#idcheck_input').val('true');

				}

			},
			error : function() {
				alert("실패");
			}
		});
	}
</script>
</head>
<body>
	<form action="CreateMember" method="post" id="create">
		<table>
			<c:choose>
				<c:when test="${kakaoid ne null}">
					<tr>
						<td>아이디</td>
					</tr>
					<tr>
						<td><input type="text" name="id" id="createid_input" onkeyup="idOverlap()" autocomplete="off"> 
						<input type="hidden" name="kakaoid" id="kakaoid" value="${kakaoid}">
						<input type="hidden" id="idcheck_input" value="false"></td>
					</tr>
					<tr>
						<td><span id="id_text"></span></td>
					</tr>
					<tr>
						<td>이름</td>
					</tr>
					<tr>
						<td><input type="text" id="name_input" name="name" autocomplete="off"></td>
					</tr>
					<tr>
						<td>성별</td>
					</tr>
					<tr>
						<td><select name="gender" id="gender_input">
								<option value="">선택</option>
								<option value="남자">남자</option>
								<option value="여자">여자</option>
						</select></td>
					</tr>
				</c:when>
				<c:when test="${naverid ne null}">
					<tr>
						<td>아이디</td>
					</tr>
					<tr>
						<td><input type="text" name="id" id="createid_input" onkeyup="idOverlap()" autocomplete="off"> 
						<input type="hidden" name="naverid" id="naverid" value="${naverid}">
						<input type="hidden" id="idcheck_input" value="false"></td>
					</tr>
					<tr>
						<td><span id="id_text"></span></td>
					</tr>
					<tr>
						<td>이름</td>
					</tr>
					<tr>
						<td><input type="text" id="name_input" name="name" autocomplete="off"></td>
					</tr>
					<tr>
						<td>성별</td>
					</tr>
					<tr>
						<td><select name="gender" id="gender_input">
								<option value="">선택</option>
								<option value="남자">남자</option>
								<option value="여자">여자</option>
						</select></td>
					</tr>
				</c:when>
				<c:otherwise>
					<tr>
						<td>아이디</td>
					</tr>
					<tr>
						<td><input type="text" name="id" id="createid_input" onkeyup="idOverlap()" autocomplete="off"> 
						<input type="hidden" id="idcheck_input" value="false"></td>
					</tr>
					<tr>
						<td><span id="id_text"></span></td>
					</tr>
					<tr>
						<td>비밀번호</td>
					</tr>
					<tr>
						<td><input type="password" id="createpw_input" name="pw">
						</td>
					</tr>
					<tr>
						<td>이름</td>
					</tr>
					<tr>
						<td><input type="text" id="name_input" name="name" autocomplete="off"></td>
					</tr>
					<tr>
						<td>성별</td>
					</tr>
					<tr>
						<td><select name="gender" id="gender_input">
								<option value="">선택</option>
								<option value="남자">남자</option>
								<option value="여자">여자</option>
						</select></td>
					</tr>
				</c:otherwise>
			</c:choose>
		</table>
	</form>
	<c:choose>
		<c:when test="${kakaoid ne null}">
			<button id="kakaobtn">회원가입하기</button>
		</c:when>
		<c:when test="${naverid ne null}">
			<button id="naverbtn">회원가입하기</button>
		</c:when>
		<c:otherwise>
			<button id="createbtn">회원가입하기</button>
		</c:otherwise>
	</c:choose>
</body>
</html>