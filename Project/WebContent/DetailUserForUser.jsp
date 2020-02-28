<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<table>
<tr>
<td>
아이디 : ${requestScope.id}
</td>
</tr>
<tr>
</tr>
<tr>
<td>
이름 :  ${requestScope.name}
</td>
</tr>
<tr>
</tr>
<tr>
<td>
이메일 :  ${requestScope.email}
</td>
</tr>
<tr>
<td>
포인트 :  ${requestScope.point}
</td>
</tr>
<tr>
<td>
등급 :  ${requestScope.grade}
</td>
</tr>
</table>
</body>
</html>