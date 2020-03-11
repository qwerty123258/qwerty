<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<html>
<head>
<title>회원가입</title>
<script>
$(document).ready(function(){
	$('#createbtn').click(function() {
		var id=$("#createid_input").val();
		var pw=$("#createpw_input").val();
		var name=$("#name_input").val();
		var gender=$("#gender_input").val();
			     if(id==''){
			    	 alert('ID는 필수 입력입니다.');
			    	 $('#createid_input').focus();
			     }
			     else if(pw==''){
			    	 alert('비밀번호는 필수 입력입니다.');
			    	 $('#createpw_input').focus();
			     }
			     else if(name==''){
			    	 alert('이름은 필수 입력입니다.');
			    	 $('#name_input').focus();
			     }
			     else if(gender==''){
			    	 alert('성별은 필수 입력입니다.');
			    	 $('#gender_input').focus();
			     }
	     else{
				$("#create").submit();
	     }
	});
})
	</script>
</head>
<body>
<form action="CreateMember" method="post" id="create">
<table>
<caption><h2> 회원가입</h2></caption>
<tr>
<td>
아이디
</td>
</tr>
<tr>
<td>
<input type="text" id="createid_input" name="id">
</td>
</tr>
<tr>
<td>
비밀번호
</td>
</tr>
<tr>
<td>
<input type="password" id="createpw_input" name="pw">
</td>
</tr>
<tr>
<td>
이름
</td>
</tr>
<tr>
<td>
<input type="text" id="name_input" name="name">
</td>
</tr>
<tr>
<td>
성별
</td>
</tr>
<tr>
<td>
<select name="gender" id="gender_input">
<option value="">선택</option>
  <option value="남자">남자</option>
  <option value="여자">여자</option>
</select>
</td>
</tr>
</table>
</form>
<button id="createbtn">회원가입하기</button>
</body>
</html>