<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원목록</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
	function adminDel(id) {
		var id = id;
		if (confirm("정말로 탈퇴시키겠습니까?")) {
			$.ajax({
				type : "GET",
				url : "deleteMember",
				data : "id=" + id,
				dataType : "text",
				success : function(result) {
					if (result == "Admin") {
						alert("관리자는 삭제 못해요");
					} else if (result == "Success") {
						location.reload();
					} else if (result == "Fail") {
						alert("삭제 실패");
					}

				},
				error : function() {
					alert("실패");
				}
			})
		}

	}
</script>
</head>
<body>
	<jsp:include page="../Header.jsp"></jsp:include><br>
	<jsp:include page="../Nav.jsp"></jsp:include>
	<br>
	<div class="row">
		<div class="col-xs-3">
			<jsp:include page="SideNav.jsp"></jsp:include>
		</div>
		<div class="col-xs-9">
			<div class="row">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>아이디</th>
							<th>비밀번호</th>
							<th>이름</th>
							<th>이메일</th>
							<th>연락처</th>
							<th>회원종류</th>
							<th>탈퇴</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="member" items="${memberList}">
							<tr>
								<td>${member.id}</td>
								<td>${member.pw}</td>
								<td>${member.name}</td>
								<td>${member.email}</td>
								<td>${member.phone}</td>
								<td>${member.kind}</td>
								<td><a href="#" onclick="adminDel('${member.id}')">탈퇴시키기</a>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<c:url var="action" value="/memberList" />
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