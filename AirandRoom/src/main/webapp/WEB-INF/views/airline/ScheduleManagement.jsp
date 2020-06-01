<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비행일정 관리</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<link rel="stylesheet" href="//cdn.rawgit.com/fgelinas/timepicker/master/jquery.ui.timepicker.css">
<script src='//cdn.rawgit.com/fgelinas/timepicker/master/jquery.ui.timepicker.js'></script>
<style>
.ui-timepicker { font-size: 15px; width: 100px; }
.ui-timepicker-table td a{
width:30px;
}
</style>
<script>
function goScheduleAdd(){
	var ano=${ano};
	location.href="goScheduleAdd?ano="+ano;
	
}
function scheduleModify(scno,ano){
	var html="";
	html+="출발 시각";
	html+="<input type='text' id='startTime' class='timepicker'>";
	html+="<input type='hidden' id='scno' value='"+scno+"'>";
	html+="<input type='hidden' id='ano' value='"+ano+"'>";
	html+="<button onclick='scheduleUpdate("+scno+","+ano+")'>수정하기</button>";
	$("#scheduleModify").html(html);
    $('.timepicker').timepicker();

}
function scheduleUpdate(scno,ano){
	var scno=scno;
	var startTime=$("#startTime").val();
	$.ajax({
		type : "GET",
		url : "scheduleUpdate",
		data : "scno=" + scno +"&sctime=" + startTime + "&ano="+ano,
		dataType : "text",
		success : function(result) {
			if(result=="Fail"){
					alert("수정 실패");
			}
			else if(result=="Success"){
				alert("수정 성공");
				location.reload();
			}
		},
		error : function() {
			alert("일정 수정 중 에러 발생");
		}
	});
}
function scheduleDelete(scno){
	if(confirm("정말로 삭제하시겠습니까?")){
		$.ajax({
			type : "GET",
			url : "scheduleDelete",
			data : "scno=" + scno,
			dataType : "text",
			success : function(result) {
				if(result=="Fail"){
						alert("삭제 실패");
				}
				else if(result=="Success"){
					alert("삭제 성공");
					location.reload();
				}
			},
			error : function() {
				alert("일정 삭제 중 에러 발생");
			}
		});
	}

	
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
	<div class="row">
					<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>일정 번호</th>
							<th>출발 시각</th>
							<th>노선 번호</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="schedule" items="${scheduleList}">
							<tr>
							<td>
						<div class='dropdown'>
	                    <button class='btn btn-default dropdown-toggle' type='button' id='menu1' data-toggle='dropdown'>${schedule.scno}</button>
	                    <ul class='dropdown-menu' role='menu' aria-labelledby='menu1'>
	                    <li role='presentation'><a role='menuitem' tabindex='-1' href='#' onclick="scheduleModify(${schedule.scno},${schedule.ano})">일정 수정하기</a></li>
	                    <li role='presentation'><a role='menuitem' tabindex='-1' href='#' onclick="scheduleDelete(${schedule.scno})">일정 삭제하기</a></li>
	                    </ul>
	                    </div>
	                    </td>
								<td>${schedule.sctime}</td>
								<td>${schedule.ano}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
	</div>
	<button onclick="goScheduleAdd()">일정 추가하기</button>
	</div>
	</div>
	<div id="scheduleModify">
	
	</div>
</body>
</html>