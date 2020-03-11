<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/table.css">
<meta charset="UTF-8">
<title>전체조회</title>
<script>
function showDetail(id){
	var id=id;
	var url="ShowDetail?id="+id;
	window.open(url, 'pop01', 'width=300, height=300, status=no, menubar=no, toolbar=no');
}
</script>
</head>
<body>
        	            <table id="member">
	            <thead>
	            <tr>
	            <th>아이디</th>
	            <th>비밀번호</th>
	            <th>이름</th>
	            <th>성별</th>
	            <th>삭제</th>
	            </tr>
	            </thead>
	                    <tbody>
	                    <c:forEach var="member" items="${memberList}">
	                    <tr onclick="showDetail('${member.id}')">
	                    <td>${member.id}</td>
	                    <td>${member.pw}</td>
	                    <td>${member.name}</td>
	                  <td>${member.gender}</td>
	                  <td><a href="goAdminDelete?id=${member.id}">삭제</a></td>
	                  </tr>
	                    </c:forEach>
                    </tbody>
                    </table>
</body>
</html>