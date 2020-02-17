<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
alert("수정 완료");
location.href="BoardDetail?page=${requestScope.page}&board=${requestScope.bnum}&$bimgfile=${requestScope.bimgfile}";
</script>
</head>
<body>

</body>
</html>