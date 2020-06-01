<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비행 시간표 추가하기</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<link rel="stylesheet" href="//cdn.rawgit.com/fgelinas/timepicker/master/jquery.ui.timepicker.css">
<script src='//cdn.rawgit.com/fgelinas/timepicker/master/jquery.ui.timepicker.js'></script>
<script type="text/javascript">
    $(document).ready(function () {
            $('.timepicker').timepicker();
    });
</script>
<style>
.ui-timepicker { font-size: 15px; width: 100px; }
.ui-timepicker-table td a{
width:30px;
}
</style>
<script>
function scheduleAdd(){
	var startTime=$("#startTime").val();
	var ano=$("#ano").val();
	$.ajax({
		type : "GET",
		url : "scheduleAdd",
		data : "sctime=" + startTime +"&ano=" + ano, 
		dataType : "text",
		success : function(result) {
			if(result=="Success"){
				alert("시간표 추가 성공");
				location.href="airlineManagement";
			}
			else if(result=="Overlap"){
				alert("중복된 시간표 추가");
			}
		},
		error : function() {
			alert("시간표 추가중 에러 발생");
		}
	});
}
</script>
</head>
<body>
<jsp:include page="../Header.jsp"></jsp:include><br>
	<jsp:include page="../Nav.jsp"></jsp:include><br>
<div class="row">
	<div class="col-xs-3">
	<jsp:include page="AirlineSideNav.jsp"></jsp:include>
	</div>
	<div class="col-xs-9">
	출발 시각
	<input type="text" id="startTime" class='timepicker'>
	<input type="hidden" id="ano" value="${ano}"><br>
	<button onclick="scheduleAdd()">추가하기</button>
	</div>
</div>
</body>
</html>