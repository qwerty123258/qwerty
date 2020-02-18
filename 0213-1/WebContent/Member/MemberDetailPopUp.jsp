<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#profileimg{
width:80px;
height:80px;
}
pre{
font-size:20px;
}
</style>
</head>
<body>
<c:forEach var="result" items="${member}">
<h2>프로필
</h2>
<img id="profileimg" src="fileUpload/${result.mempicture}" onerror="this.src='fileUpload/profile.png'">
<pre>
아이디 : ${result.id}    글 개수 : ${requestScope.count}
이름 : ${result.name}   댓글 개수 : ${requestScope.commentcount}
성별 : ${result.gender}
연락처 : ${result.phone}
이메일 주소 : ${result.email}
주소 : ${result.address}
블랙리스트 : ${result.blacklist}
</pre>
</c:forEach>
</body>
</html>