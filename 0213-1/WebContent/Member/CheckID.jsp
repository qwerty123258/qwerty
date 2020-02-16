<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <jsp:useBean id="idcheck" class="service.MemberCheckOverlapService"/>
<% request.setCharacterEncoding("utf-8"); %>
<% String id = request.getParameter("id"); %>
<% boolean result = idcheck.checkOverlap(id); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<b><%=id %></b>
<%
	if(result){
%>		
		는(은) 이미  사용중인 id 입니다.<p/>
		<a href="#" onclick = "opener.document.addForm.id.focus(); window.close();">다시 입력하기</a>
		
<%
	} else {
%>
		는(은) 사용 가능 합니다.<p/>
		<a href="#" onclick = "opener.document.addForm.checkidresult.value=true; window.close();">사용하기</a>

<%	} %>
</body>
</html>