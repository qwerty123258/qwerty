<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의 내역보기</title>
<script>
function replyView(ino){
	var popUrl = "replyView?ino="+ino;	

	var popOption = "width=450, height=650, resizable=no, scrollbars=no, status=no;";    //팝업창 옵션(optoin)

		window.open(popUrl,"",popOption);
}
function myInquire(ino){
	var popUrl = "myInquire?ino="+ino;	

	var popOption = "width=450, height=650, resizable=no, scrollbars=no, status=no;";    //팝업창 옵션(optoin)

		window.open(popUrl,"",popOption);
}
</script>
</head>
<body>
	<jsp:include page="../Header.jsp"></jsp:include><br>
	<jsp:include page="../Nav.jsp"></jsp:include><br>
	<div class="col-xs-12">
	<div class="col-xs-3">
		<jsp:include page="../member/SideNav.jsp"></jsp:include>
	</div>
	<div class="col-xs-9">
			<table class="table table-striped table-bordered table-hover">
	<thead>
	<tr>
	<th>
	문의번호
	</th>
	<th>
	문의글 제목
	</th>
	<th>
	문의 상대
	</th>
	<th>
	문의글 읽음유무
	</th>
	<th>
	문의글 답변 유무
	</th>
	<th>
	작성시간
	</th>
	</tr>
	</thead>
	<c:forEach var="inquire" items="${myInquireList}">
	<tr>
	<td>
	${inquire.ino}
	</td>
		<td onclick="myInquire(${inquire.ino})">
	${inquire.title}
	</td>
	<td>
	${inquire.otherid}
	</td>
		<c:if test="${inquire.read eq 'Y'}">
				<td>
	읽음
	</td>
	</c:if>
			<c:if test="${inquire.read eq 'N'}">
					<td>
	읽지않음
	</td>
		</c:if>
			<c:if test="${inquire.answer eq 'N'}">
					<td>
	답변 대기중
	</td>
		</c:if>
					<c:if test="${inquire.answer eq 'Y'}">
			<td>
	<a onclick="replyView(${inquire.ino})" href="#">답변 도착</a>
	</td>
		</c:if>
		<td>
	${inquire.writedate}
	</td>
	</tr>
	</c:forEach>
	</table>
					<c:url var="action" value="/myInquireList" />
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
</body>
</html>