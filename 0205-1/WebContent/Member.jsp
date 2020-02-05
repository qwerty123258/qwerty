<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>
function transfer(){
	formTrans.submit();
}
</script>
<body>
<form action="MemberAdd" method="post" id="formTrans">
	아이디 : <input type="text" name="id" autocomplete="off"><br>
	비밀번호 : <input type="password" name="password" autocomplete="off"><br>
	이름 : <input type="text" name="name" autocomplete="off"><br>
	생년월일 : <input type="date" name="birth" autocomplete="off"><br> 
 	성별 : <select name="gender">
    	<option value="">선택하세요</option>
        <option value="남성">남성</option>
        <option value="여성">여성</option>
  	</select>
	<br>
	이메일주소 : <input type="text" name="emailID">
	<select name="emailDomain">
    	<option value="">선택하세요</option>
        <option value="naver.com">네이버</option>
        <option value="daum.net">다음</option>
  	</select>
</form>
<button onclick="transfer()">전송</button>
</body>
</html>