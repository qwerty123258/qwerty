<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

	<script>
		function roomsBookingDelete(rbno) {
			$.ajax({
				type : "post",
				url : "roomsBookingDelete",
				data : "rbno=" + rbno,
				dataType : "text",
				success : function(result) {
					if (result == "yes") {
						alert('삭제성공');
						location.reload();
					} else {
						alert('삭제실패');
					}

				},
				error : function() {
					alret('접속실패');
				}

			});

		}
		function checkOut(rbno,id) {
			$.ajax({
				type : "post",
				url : "checkOut",
				data : "rbno=" + rbno,
				dataType : "text",
				success : function(result) {
					if (result == "yes") {
						alert('체크아웃성공');
						window.open("guestEvaluationForm?id="+id+"&rbno="+rbno, "PopupWin", "width=500,height=600");
location.reload();
					} else {
						alert('체크아웃실패');
					}

				},
				error : function() {

				}
			});
		}
		

		function grade(rbno,id) {
	window.open("guestEvaluationForm?id="+id+"&rbno="+rbno, "PopupWin", "width=500,height=600");
						location.reload();
		}				
		
		  function gradeView(id){     
		      window.open("gradeView?page=1&id="+id, "PopupWin", "width=500,height=600");
		   
		  }
	</script>
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
							<th>예약 번호</th>
							<th>예약자</th>
							<th>예약 숙소</th>
							<th>예약 일자</th>
							<th>체크인 일자</th>
							<th>체크아웃 일자</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="booking" items="${list}">
							<tr>
								<td>${booking.rbno}</td>
								
								
								<td><a data-toggle="modal" href="gradeView?page=1&id=${booking.id}" data-target="#modal-gradeView" role="button" data-backdrop="static" >${booking.id}</a></td>
								<td>${booking.rname}</td>
								<td>${booking.bookingdate}</td>
								<td>${booking.checkindate}</td>
								<td>${booking.checkoutdate}</td>
								<td><a href="javascript:roomsBookingDelete(${booking.rbno})">삭제</a></td>
								<c:if test="${booking.checks eq 'N'}">
									<td><a href="javascript:checkOut(${booking.rbno},'${booking.id}')">체크아웃</a></td>								
								</c:if>								
								<c:if test="${booking.checks eq 'Y'}">
								<c:if test="${booking.grade eq 'N'}">
								
									<td><a href="javascript:grade(${booking.rbno},'${booking.id}')">고객평가</a></td>								
								</c:if>
								</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<c:url var="action" value="/selectMyRoombk" />
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
<div id="modal-gradeView" class="modal fade" tabindex="-1"
		role="dialog" aria-labelledby="테스트정보 등록" aria-describedby="테스트 모달" >
		<div class="modal-dialog" style="width: 60%; top: 10%; margin:auto;">
			<div class="modal-content"></div>
		</div>
		</div>
</body>
</html>