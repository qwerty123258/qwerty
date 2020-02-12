<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("utf-8"); %>
<% String bfile = request.getParameter("bfile"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
*{
text-align:center;
}
</style>
</head>
<body>
첨부파일 이름<br><br>
<b><a href="FileDownload?bfile=<%=bfile %>"><%=bfile %></a></b>
<br><br>
<div id="close">
<a href="#" onclick = "window.close();">닫기</a>
</div>
</body>
</html>