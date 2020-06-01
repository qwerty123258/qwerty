<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script>
function setCookie(name, value) {
	var todayDate = new Date();
	todayDate.setHours( 24 );
    document.cookie = name + "=" + escape( value ) + "; path=/; expires=" +   todayDate.toGMTString() + ";";
    self.close();
}
</script>
</head>
<body>

<h2>코로나 극복 화이팅</h2>

  <img style="width:400px;height:500px;" src="${pageContext.request.contextPath}/resources/img/노지선.jpg">
  <br>
  <a href="javascript:setCookie('popup', 'no')">24시간동안 안띄우기</a>

</body>
</html>