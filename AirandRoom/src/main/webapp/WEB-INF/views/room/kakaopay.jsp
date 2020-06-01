<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<form action="kakaoPay" id="form" method="post">
<input type="hidden" name="rno" value="${roombk.rno}">
<input type="hidden" name="roomid" value="${roomid}">
<input type="hidden" name="rname" value="${roombk.rname}">
<input type="hidden" name="price" value="${roombk.price}">
<input type="hidden" name="checkindate" value="${roombk.checkindate}">
<input type="hidden" name="checkoutdate" value="${roombk.checkoutdate}">
숙소 이름 : ${roombk.rname} <br>
등록자 : ${roomid}<br>
가격 : ${roombk.price}<br>
체크인 날짜 : ${roombk.checkindate} <br>
체크아웃 날짜 : ${roombk.checkoutdate} <br>
<a href="#" onclick="pay()"><img src="${pageContext.request.contextPath}/resources/img/payment_icon_yellow_medium.png"></a>
</form>
</body>
</html>