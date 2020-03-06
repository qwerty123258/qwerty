<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
                <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/userlist.js"></script>
</head>
<body>
                        <jsp:include page="Header.jsp"></jsp:include>
    <div class="row">
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
<table class="table table-striped table-bordered table-hover">
<thead>
<tr>
<td>
아이디
</td>
<td>
이름
</td>
<td>
이메일 주소
</td>
<td>
포인트
</td>
<td>
회원등급
</td>
<td>
이메일 인증
</td>
<td>
업로드 자격
</td>
<td>
블랙리스트
</td>
</tr>
</thead>
<tbody>
<c:forEach var="user" items="${users}" >
<tr>
<td>
  <div class="dropdown">
    <button class="btn btn-default dropdown-toggle" type="button" id="menu1" data-toggle="dropdown">${user.ID}</button>
    <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
      <li role="presentation"><a role="menuitem" tabindex="-1" href="#" onclick="window.open('SelectDetailUser?id=${user.ID}','상세보기','width=430,height=500,location=no,status=no,scrollbars=yes');">상세보기</a></li>
      <li role="presentation"><a role="menuitem" tabindex="-1" href="#" onclick="deleteUser('${user.ID}')">탈퇴시키기</a></li>
          <c:choose>
        <c:when test="${user.blacklist eq 'N'}">
      <li role="presentation"><a role="menuitem" tabindex="-1" href="#" onclick="addBlack('${user.ID}')">블랙리스트 추가</a></li>
        </c:when>
        <c:otherwise>
      <li role="presentation"><a role="menuitem" tabindex="-1" href="#" onclick="removeBlack('${user.ID}')">블랙리스트 해제</a></li>
        </c:otherwise>
    </c:choose>
    </ul>
  </div>
</td>
<td>
${user.name}
</td>
<td>
${user.email}
</td>
<td>
${user.point}
</td>
<td>
${user.grade}
</td>
<td>
${user.certify}
</td>
<td>
${user.bfile}
</td>
<td>
${user.blacklist}
</td>
</tr>
</c:forEach>
</tbody>
</table>
<c:url var="action" value="/SelectUser"/>
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
            <li class="disabled" ><a>이전</a></li>
        </c:when>
        <c:otherwise>
   			 <li><a href="${action}?page=${paging.beginPage-1}">이전</a></li>
        </c:otherwise>
    </c:choose>
    <c:forEach begin="${paging.beginPage}" end="${paging.endPage}" step="1" var="page">
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
        <c:when test="${paging.page==paging.totalPage || paging.totalPage eq 0}">
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
  <jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>