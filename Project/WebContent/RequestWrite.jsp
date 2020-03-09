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
<script type="text/javascript" src="se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<script>
$(document).ready(function() {
	$('#writebtn').click(function(){
		var title=$('#title_input').val();
		if(title==''){
			alert('제목이 비어있습니다.');
		}
		else{
	    	 oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
        	document.getElementById("writeForm").submit();
		}
		
	});
})
</script>
<style>
#writebtn{
padding:10px;
border:none;
outline:none;
margin-left:45%;
margin-right:45%;
}

</style>
</head>
<body>
                        <jsp:include page="Header.jsp"></jsp:include>
    <div class="row">
        <div class="col-md-12">
  <ul class="nav nav-pills nav-justified">
    <li class="active"><a href="Main.jsp">Home</a></li>
    <li><a href="MovieList">영화</a></li>
 	<li><a href="DramaList">드라마</a></li>
    <li><a href="UtilList">유틸</a></li>
    <li><a href="OtherList">기타</a></li>
  </ul>
        </div>
        <div class="col-md-3">
                        <jsp:include page="SideNav.jsp"></jsp:include>
        </div>
        <div class="col-md-9">
<form action="RequestWrite" method="post" id="writeForm">
제목<br>
<input type="text" id="title_input" name="title"><br>
내용<br>
<textarea name="ir1" id="ir1" rows="10" cols="100"></textarea>
</form>
<button id="writebtn">작성완료</button>
        </div>
    </div>
  <jsp:include page="Footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
 oAppRef: oEditors,
 elPlaceHolder: "ir1",
 sSkinURI: "se2/SmartEditor2Skin.html",
 fCreator: "createSEditor2"
});
</script>
</html>