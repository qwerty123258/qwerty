<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/table.css?ver=20200312-1"">
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<title>전체조회</title>
<script>
function showDetail(id){
	var id=id;
	var url="ShowDetail?id="+id;
	window.open(url, 'pop01', 'width=300, height=300, status=no, menubar=no, toolbar=no');
}
function showDetailAjax(id){
	var id=id;
	  $.ajax({
	       type : "POST",
	         url : "ShowDetailAjax",
	         data : "id=" + id,
	         dataType : "json",
	       success : function(result) {
	    	   $("#detailInfo").children().remove();
	    	   var html= "<thead><tr><th>아이디</th><th>비밀번호</th><th>이름</th><th>성별</th></tr></thead>";
	    	   html+="<tbody><tr><td>"+result.id+"</td><td>"+result.pw+"</td><td>"+result.name+"</td><td>"+result.gender+"</td></tr></tbody>";
	    	   html+="</table>";
	    	   $("#detailInfo").html(html);
	    	   
	       },
	error : function() {
	alert("실패");
	}
	});
	
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
	            <th>조회</th>
	            <th>삭제</th>
	            </tr>
	            </thead>
	                    <tbody>
	                    <c:forEach var="member" items="${memberList}">
	                    <tr>
	                    <td onclick="showDetail('${member.id}')">${member.id}</td>
	                    <td>${member.pw}</td>
	                    <td>${member.name}</td>
	                  <td>${member.gender}</td>
	                  <td><button onclick="showDetailAjax('${member.id}')">상세조회(ajax)</button></td>
	                  <td><a href="goAdminDelete?id=${member.id}">삭제</a></td>
	                  </tr>
	                    </c:forEach>
                    </tbody>
                    </table>
                    <table id="detailInfo">
                    
                    </table>
</body>
</html>