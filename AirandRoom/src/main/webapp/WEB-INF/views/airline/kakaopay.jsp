<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>카카오 결제</title>
<style>
button{
outline:none;
border:none;
padding:10px;
}
</style>
<script>
function pay(){
	document.getElementById("form").submit();
	
}
</script>
</head>
<body>
<form action="airlinekakaoPay" id="form" method="post">
<input type="hidden" name="ano" value="${airlinebk.ano}">
<input type="hidden" name="airlineid" value="${airlineid}">
<input type="hidden" name="aname" value="${airlinebk.aname}">
<input type="hidden" name="price" value="${airlinebk.price}">
<input type="hidden" name="startdate" value="${airlinebk.startdate}">
<input type="hidden" name="endpoint" value="${airlinebk.endpoint}">
<input type="hidden" name="startpoint" value="${airlinebk.startpoint}">
<input type="hidden" name="sctime" value="${airlinebk.sctime}">
노선 이름 : ${airlinebk.aname} <br>
등록자 : ${airlineid}<br>
가격 : ${airlinebk.price}<br>
출발지 : ${airlinebk.startpoint}<br>
도착지 : ${airlinebk.endpoint}<br>
출발 날짜 : ${airlinebk.startdate} <br>
출발 시각 : ${airlinebk.sctime} <br>
선택한 좌석 : <c:forEach items="${airlinebk.seats}" var="result">
<input type="hidden" name="seats" value="${result}">
${result}
</c:forEach> <br>
<a href="#" onclick="pay()"><img src="${pageContext.request.contextPath}/resources/img/payment_icon_yellow_medium.png"></a>
</form>
</body>
</html>