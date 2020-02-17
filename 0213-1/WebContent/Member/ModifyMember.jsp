<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <link href="https://fonts.googleapis.com/css?family=Yeon+Sung&display=swap" rel="stylesheet">
    <link href="css/modify.css" rel="stylesheet">
            <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
            <script type="text/javascript" src="js/modify.js"></script>
                            <script
src="https://code.jquery.com/jquery-3.4.1.js"
integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
crossorigin="anonymous">
    </script>
</head>
<body>
            <a id="logoar" href="MemberBoardMain.jsp">
                <img id="logo" src="images/logo.PNG">
            </a>
            <h2>정보 변경</h2>
<form action="Update" id="update" enctype="multipart/form-data" method="post">
<br>프로필사진 변경 <br>
  <c:if test="${requestScope.mempicture ne NULL}">
    <img id="profileimg" src="fileUpload/${requestScope.mempicture}" width="130px" height="130px">
  </c:if>
  <input type="file" name="mempicture" id="picture"><br>
 아이디 <br>
 <input type="text" name="id" autocomplete="off" value="${sessionScope.id}" readonly="true"><br>
 이름 <br>
 <input type="text" name="name" autocomplete="off" value="${requestScope.name}">
<br> 이메일주소 <br>
  <input type="text" name="email" autocomplete="off" value="${requestScope.email}">
  <br> 폰 번호 변경 <br>
  <input type="text" name="phone" id="phoneinput" autocomplete="off" value="${requestScope.phone}">
  <br> 주소 변경 <br>
  <input type="text" id="sample5_address" placeholder="주소" name="address" value="${requestScope.address}">
  <input type="button" onclick="sample5_execDaumPostcode10()" value="주소 검색">
</form>
<br>
<button class="btn" onclick="update()">
수정 완료
</button>
</body>
</html>