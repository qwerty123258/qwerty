<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>노선 조회하기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
function deleteAirline(value) {
	var ano = value;
    var decisionResult = confirm('정말로 삭제하시겠습니까?');
    if(decisionResult == true) {
    location.href="deleteAirline?ano="+ano;
    } 
}
function deleteAirlinebk(abno,email) {
	var abno = abno;
	var email = email;
	console.log(abno+email);
    var decisionResult = confirm('예약 취소하시겠습니까?');
    if(decisionResult == true) {
    location.href="deleteAirlinebk?abno="+abno+"&email="+email;
    } else {
    	
    }
}
</script>
<style>
table.type11 {
    border-collapse: separate;
    border-spacing: 1px;
    text-align: center;
    line-height: 1.5;
    margin: 20px 10px;
}
table.type11 th {
    width: 155px;
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    color: #fff;
    background: #ce4869 ;
}
table.type11 td {
    width: 155px;
    padding: 10px;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
    background: #eee;
}
</style>
</head>
<body>
<jsp:include page="../Header.jsp"></jsp:include><br>
	<jsp:include page="../Nav.jsp"></jsp:include>
<div class="row">
	<div class="col-xs-3">
	<jsp:include page="AirlineSideNav.jsp"></jsp:include>
	</div>
	<div class="col-xs-9">
	<div class="row">
<h1>노선 조회 페이지 입니다.</h1>
<table class="type11">
    <thead>
    <tr>
        <th scope="cols">번호</th>
        <th scope="cols">이름</th>
        <th scope="cols">항공사</th>
        <th scope="cols">출발지</th>
        <th scope="cols">도착지</th>
        <th scope="cols">승인 유무</th>
        <th scope="cols">수정</th>
        <th scope="cols">삭제</th>
        <th scope="cols">일정 관리</th>
    </tr>
    </thead>
<c:forEach items="${selectAirline}" var="result">
<tbody>
<tr>
    <td>${result.ano}</td>
	<td>${result.aname}</td>
	<td>${result.airporttype}</td>
	<td>${result.startpoint}</td>
	<td>${result.endpoint}</td>
	<td><h4>승인상태 : ${result.approval}</h4></td>
	<td onclick="location.href='modifyAirline?ano=${result.ano}'">노선 수정</td>
	<td onclick="deleteAirline('${result.ano}')">노선 삭제</td>
	<td onclick="location.href='scheduleManagement?ano=${result.ano}'">일정관리</td>
</tr>
</tbody>
</c:forEach>
</table>
			<c:url var="action" value="/selectAirline" />
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
							<li><a href="${action}?page=${paging.beginPage-1}&startpoint=${airline.startpoint}&endpoint=${airline.endpoint}&atype=${atype}">이전</a></li>
						</c:otherwise>
					</c:choose>
					<c:forEach begin="${paging.beginPage}" end="${paging.endPage}"
						step="1" var="page">
						<c:choose>
							<c:when test="${paging.page==page}">
								<li class="active"><a>${page}</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="${action}?page=${page}&startpoint=${airline.startpoint}&endpoint=${airline.endpoint}&atype=${atype}">${page}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:choose>
						<c:when test="${paging.endPage==paging.totalPage}">
							<li class="disabled"><a>다음</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${action}?page=${paging.endPage+1}&startpoint=${airline.startpoint}&endpoint=${airline.endpoint}&atype=${atype}">다음</a></li>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when
							test="${paging.page==paging.totalPage || paging.totalPage eq 0}">
							<li class="disabled"><a>끝으로</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${action}?page=${paging.totalPage}&startpoint=${airline.startpoint}&endpoint=${airline.endpoint}&atype=${atype}">끝으로</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>

	
	
	
	
	
	
	


<table class="type11">
    <thead>
    <tr>
        <th scope="cols">번호</th>
        <th scope="cols">아이디</th>
        <th scope="cols">노선 이름</th>
        <th scope="cols">예약 날짜</th>
        <th scope="cols">예약 취소</th>
    </tr>
    </thead>
<c:forEach items="${selectAirlinebk}" var="value">
<tbody>
<tr>
    <td>${value.abno}</td>
	<td>${value.id}</td>
	<td>${value.aname}</td>
	<td>${value.bookingdate}</td>
	<td onclick="deleteAirlinebk('${value.abno}','${value.email}')">예약 취소</td><br>
</tr>
</tbody>
</c:forEach>
</table>

			<c:url var="action" value="/selectAirline" />
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
							<li><a href="${action}?page=${paging.beginPage-1}&startpoint=${airline.startpoint}&endpoint=${airline.endpoint}&atype=${atype}">이전</a></li>
						</c:otherwise>
					</c:choose>
					<c:forEach begin="${paging.beginPage}" end="${paging.endPage}"
						step="1" var="page">
						<c:choose>
							<c:when test="${paging.page==page}">
								<li class="active"><a>${page}</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="${action}?page=${page}&startpoint=${airline.startpoint}&endpoint=${airline.endpoint}&atype=${atype}">${page}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:choose>
						<c:when test="${paging.endPage==paging.totalPage}">
							<li class="disabled"><a>다음</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${action}?page=${paging.endPage+1}&startpoint=${airline.startpoint}&endpoint=${airline.endpoint}&atype=${atype}">다음</a></li>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when
							test="${paging.page==paging.totalPage || paging.totalPage eq 0}">
							<li class="disabled"><a>끝으로</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${action}?page=${paging.totalPage}&startpoint=${airline.startpoint}&endpoint=${airline.endpoint}&atype=${atype}">끝으로</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
			</div>
</body>
</html>