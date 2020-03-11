<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세조회</title>
</head>
<body>
        	            <table>
	            <thead>
	            <tr>
	            <th>아이디</th>
	            <th>비밀번호</th>
	            <th>이름</th>
	            <th>성별</th>
	            </tr>
	            </thead>
	                    <tbody>
	                    <c:forEach var="member" items="${memberList}">
	                    <tr>
	                    <td>${member.id}</td>
	                    <td>${member.pw}</td>
	                    <td>${member.name}</td>
	                  <td>${member.gender}</td>
	                  </tr>
	                    </c:forEach>
                    </tbody>
                    </table>
</body>
</html>