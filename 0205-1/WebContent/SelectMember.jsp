<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!-- JSTL JavaServer Page Standard Tag Library -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
td,th{
width:150px;
text-align:center;
padding:5px;
border:black solid 1px;
}
</style>
</head>
<body>
<h2>Select.jsp 파일
</h2>
<table>
<tr>
<th>
아이디
</th>
<th>
비밀번호
</th>
<th>
이름
</th>
<th>
생년월일
</th>
<th>
성별
</th>
<th>
이메일
</th>
</tr>
<c:forEach var="result" items="${member}">
<tr>
<td>
${result.id}
</td>
<td>
${result.password}
</td>
<td>
${result.name}
</td>
<td>
${result.birth}
</td>
<td>
${result.gender}
</td>
<td>
${result.email}
</td>
</tr>
</c:forEach>
</table>
</body>
</html>