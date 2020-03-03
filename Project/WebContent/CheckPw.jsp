<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script>
$(document).ready(function(){
	$('#checkbtn').click(function() {
        var password = $('#pw_input').val();
      $.ajax({
           type : "POST",
             url : "ConfirmUser",
             data : "pw=" + password,
             dataType : "text",
           success : function(data, textStatus, xhr) {
                if (data == 'checkFail') {
                     alert('비밀번호가 맞지 않습니다.');
              }
              else if(data=='check') {
                   location.href = 'ModifyUser';
	}
           },
error : function(request, status, error) {
alert("code:" + request.status + "\n" + "error:" + error);
}
})

});
})
</script>
</head>
<body>
                        <jsp:include page="Header.jsp"></jsp:include>
<div class="container">
    <div class="row">
        <div class="col-sm-12">
  <ul class="nav nav-pills nav-justified">
    <li class="active"><a href="Main.jsp">Home</a></li>
    <li><a href="MovieList">영화</a></li>
 	<li><a href="DramaList">드라마</a></li>
    <li><a href="UtilList">유틸</a></li>
    <li><a href="OtherList">기타</a></li>
  </ul>
        </div>
        <div class="col-sm-3">
                        <jsp:include page="SideNav.jsp"></jsp:include>
        </div>
        <div class="col-sm-9">
        <h1>정보 수정</h1>
               비밀 번호 <br>
		<input type="password" id="pw_input">
		<button id="checkbtn">확인!</button>
        </div>
    </div>
</div>
  <jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>