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
<button class="close" data-dismiss="modal" aria-label="Close"><i class="far fa-times-circle" style="font-size:30px;"></i></button>

${id}님의 평가목록
<br>

평가점수:${grade}
	<br>
	<div>
	<div>
			<div>

		<table class="table table-striped table-bordered table-hover">
				
					<thead>
					
						<tr>
							<th>내용</th>
							<th>점수</th>
							
						</tr>
					</thead>
					<tbody>
						<c:forEach var="grade" items="${list}">
							<tr>
								<td>${grade.contents}</td>
								<td>${grade.grade}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<c:url var="action" value="/gradeView" />
				<div class="text-center">
					<ul class="pagination pagination-sm pager">
						<c:choose>
							<c:when test="${paging.page==paging.beginPage}">
								<li class="disabled"><a>처음으로}</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="${action}?page=1&id=${id}">처음으로</a></li>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${paging.beginPage==1}">
								<li class="disabled"><a>이전</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="${action}?page=${paging.beginPage-1}&id=${id}">이전</a></li>
							</c:otherwise>
						</c:choose>
						<c:forEach begin="${paging.beginPage}" end="${paging.endPage}"
							step="1" var="page">
							<c:choose>
								<c:when test="${paging.page==page}">
									<li class="active"><a>${page}</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="${action}?page=${page}&id=${id}">${page}</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:choose>
							<c:when test="${paging.endPage==paging.totalPage}">
								<li class="disabled"><a>다음</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="${action}?page=${paging.endPage+1}&id=${id}">다음</a></li>
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