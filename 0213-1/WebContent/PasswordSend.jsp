<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
alert("이메일 주소로 비밀번호를 전송하였습니다.");
location.href="MailSend?sender=${requestScope.sender}&receiver=${requestScope.receiver}&title=${requestScope.title}&content=${requestScope.content}";
</script>
</head>
<body>

</body>
</html>