<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <link href="https://fonts.googleapis.com/css?family=Yeon+Sung&display=swap" rel="stylesheet">
            <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<style>
body{
background-Color:black;
color:white;
}
a{
color:white;
}
.btn{
    background-color: rgb(167,33,22);
    outline: none;
    border: none;
    color: white;
    text-align: center;
    padding: 10px;
    font-size: 15px;
    cursor: pointer;
    border-radius: 7px;
}
*{
    font-family: 'Yeon Sung', cursive;
}
</style>
<script>
function update(){
	var phone=document.getElementById("phoneinput").value;
	var phonereg=/^\d{3}-\d{4}-\d{4}$/;
    if(phone.match(phonereg)){
    	document.getElementById("update").submit();
    }
    else{
    	alert("휴대폰 번호가 유효하지 않은 형식입니다.");
    }
}
function imgFileCheck(){
    var imgfile=document.getElementById("picture").value;
	if (imgfile != "") {
	    var ext = imgfile.slice(imgfile.lastIndexOf(".") + 1).toLowerCase();
	    if (!(ext == "gif" || ext == "jpg" || ext == "png" || ext == "bmp")) {
	        alert("이미지파일 (.jpg, .png, .gif,.bmp ) 만 업로드 가능합니다.");
	        return false;
	}
	    else{
	    	return true;
	    }
}
	else{
		return true;
	}
}
</script>
</head>
<body>
<form action="Update" id="update" enctype="multipart/form-data" method="post">
<h2>정보 변경</h2>
<br>프로필사진 변경 <br>
  <c:if test="${requestScope.mempicture ne NULL}">
    <img src="fileUpload/${requestScope.mempicture}" width="130px" height="130px">
  </c:if>
  <input type="file" name="mempicture" id="picture" value="${requestScope.mempicture}"><br>
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
<br><br>
<a href="LoginMain.jsp">홈으로</a>
</body>
<script>
function sample5_execDaumPostcode10() {
new daum.Postcode({
    oncomplete: function(data) {
        var addr = data.address; // 최종 주소 변수
        document.getElementById("sample5_address").value = addr;
    }
}).open();
}
</script>
</html>