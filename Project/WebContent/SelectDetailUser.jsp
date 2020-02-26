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
<td>
비밀번호 :  ${requestScope.pw}
</td>
</tr>
<tr>
<td>
이름 :  ${requestScope.name}
</td>
</tr>
<tr>
<td>
주민등록번호 :  ${requestScope.personno}
</td>
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