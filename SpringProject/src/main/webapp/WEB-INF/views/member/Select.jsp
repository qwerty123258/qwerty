<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<title>전체조회</title>
<script>
function showDetail(id){
	var id=id;
	var url="ShowDetail?id="+id;
	window.open(url, 'pop01', 'width=500, height=500, status=no, menubar=no, toolbar=no');
}
function goAdminDelete(id){
	var id= id;
	$.ajax({
		type : "POST",
		url : "goAdminDelete",
		data : "id=" + id,
		dataType : "text",
		success : function(result) {
			if(result=="Admin"){
				alert("관리자는 삭제 못해요");
			}
			else if(result=="Success"){
				location.reload();
			}
			else if(result=="Fail"){
				alert("삭제 실패");
			}

		},
		error : function() {
			alert("실패");
		}
})	
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
	            <th>이메일</th>
	            <th>삭제</th>
	            </tr>
	            </thead>
	                    <tbody>
	                    <c:forEach var="member" items="${memberList}">
	                    <tr>
	                    <td onclick="showDetail('${member.id}')">${member.id}</td>
	                    <td>${member.pw}</td>
	                    <td>${member.name}</td>
	                  <td>${member.emailaddress}</td>
	                  <td><a href="#" onclick="goAdminDelete('${member.id}')">삭제</a></td>
	                  </tr>
	                    </c:forEach>
                    </tbody>
                    </table>
</body>
</html>