<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>등록한 숙소 리스트</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
function roomsUpdateForm(rno){
	location.href = "roomsUpdateForm?rno="+rno;	
}

function roomBookingCancel(rno){
	$.ajax({
		type:"post",
		url:"roomBookingCancel",
		data:"rno="+rno,
		dataType:"text",
		success:function(result){
			if(result=="yes"){
				alert('삭제성공');
				location.reload();				
			}else if(result=="bFail"){
				alert('예약취소실패');											
			}else{			
				alert('삭제실패');				
			}	
		},error:function(){
			alert('접속실패');							
		}
		
	});
}


function myRoomsDelete(rno){
	$.ajax({
		type:"post",
		url:"myRoomsDelete",
		data:"rno="+rno,
		dataType:"text",
		success:function(result){
		if(result=="yes"){
			alert('삭제성공');
			location.reload();
		}else if(result=="booking"){
			var result = confirm("예약고객이 있습니다. 그래도 계속 진행하시겠습니까");
			if(result){
				roomBookingCancel(rno);
			}else{
		
			}
		}
		else{
			alert('삭제실패');										
		}
		},error:function(){
			alert('접속실패');				
		}
	});
}
function roomView(rno){
	var rno=rno;
	location.href="roomView?page=1&rno="+rno;
}
</script>
</head>
<body>
<jsp:include page="../Header.jsp"></jsp:include><br>
<jsp:include page="../Nav.jsp"></jsp:include>
<br>
<div class="row">
	<div class="col-xs-3">
		<jsp:include page="RoomSideNav.jsp"></jsp:include>
	</div>
	<div class="col-xs-9">
		<div class="row">
		<table class="table table-striped table-bordered table-hover">
			<thead>
				<tr>
					<th>숙소 등록 번호</th>
					<th>숙소 이름</th>
					<th>상세 위치</th>
					<th>승인 여부</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="room" items="${roomList}">
					<tr>
								<td>${room.rno}</td>
								<td onclick="roomView(${room.rno})">${room.rname}</td>
								<td>${room.address}</td>
								<td>${room.approval}</td>
								<td><a href="javascript:roomsUpdateForm(${room.rno})">수정</a></td>
								<td><a href="javascript:myRoomsDelete(${room.rno})">삭제</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<c:url var="action" value="/myRoomsList" />
		<div class="text-center">
			<ul class="pagination pagination-sm pager">
				<c:choose>
					<c:when test="${paging.page==paging.beginPage}">
						<li class="disabled"><a>처음으로</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${action}?page=1">처음으로</a></li>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${paging.beginPage==1}">
						<li class="disabled"><a>이전</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${action}?page=${paging.beginPage-1}">이전</a></li>
					</c:otherwise>
				</c:choose>
				<c:forEach begin="${paging.beginPage}" end="${paging.endPage}"
					step="1" var="page">
					<c:choose>
						<c:when test="${paging.page==page}">
							<li class="active"><a>${page}</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${action}?page=${page}">${page}</a></li>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:choose>
					<c:when test="${paging.endPage==paging.totalPage}">
						<li class="disabled"><a>다음</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${action}?page=${paging.endPage+1}">다음</a></li>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when
						test="${paging.page==paging.totalPage || paging.totalPage eq 0}">
						<li class="disabled"><a>끝으로</a></li>
					</c:when>
					<c:otherwise>
						<li><a href="${action}?page=${paging.totalPage}">끝으로</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
		</div>
	</div>
</div>
</body>
</html>