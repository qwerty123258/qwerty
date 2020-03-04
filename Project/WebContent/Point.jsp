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
	$("#pointbtn").click(function() {
        var point = $("#addPoint").val();
      $.ajax({
           type : "POST",
             url : "Point",
             data : "point=" + point,
             dataType : "text",
           success : function(data, textStatus, xhr) {
                if (data == 'Fail') {
                     alert('충전 실패');
              }
              else if(data=='Success') {
            	  alert('충전완료');
                   location.href = 'Main.jsp';
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
    <div class="row">
        <div class="col-sm-12">
                        <jsp:include page="Header.jsp"></jsp:include>
        </div>
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
충전 포인트 입력 <br>
<input type="text" id="addPoint"><button id="pointbtn">충전하기</button>
        </div>
    </div>
  <jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>