<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>
Redirect.jsp
</h2>
<!--JSP 문법,EL 태그  -->
reqData1 : ${requestScope.reqData1}
<br>
reqData2 : ${requestScope.reqData2}
<br>
reqData3 : ${requestScope.reqData3}
<br>
seData1 : ${sessionScope.seData1}
<br>
seData2 : ${sessionScope.seData2}
<br>
seData3 : ${sessionScope.seData3}
</body>
</html>