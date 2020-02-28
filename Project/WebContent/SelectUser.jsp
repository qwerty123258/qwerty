<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
                <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
src="https://code.jquery.com/jquery-3.4.1.js"
integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
crossorigin="anonymous">
</script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script>
$(document).ready(function(){

})
</script>
<script>
function deleteUser(id){
	var id=id;
	  $.ajax({
	       type : "POST",
	         url : "AdminDeleteUser",
	         data : "id=" + id,
	         dataType : "text",
	       success : function(data, textStatus, xhr) {
	            if (data == 'deleteFail') {
	                 alert('회원삭제 실패 \n 또는 가입된 회원이 아닙니다.');
	          	}
	            else if(data=='adminDelFail'){
		        	  alert('관리자는 삭제 하실 수 없습니다.');
	            }
	            else if(data=='deleteSuccess') {
	        	  alert('삭제성공');
              location.href = 'SelectUser';
	}
	       },
	error : function(request, status, error) {
	alert("code:" + request.status + "\n" + "error:" + error);
	}
	})
}
function addBlack(id){
	var id=id;
	  $.ajax({
	       type : "POST",
	         url : "AddBlackList",
	         data : "id=" + id,
	         dataType : "text",
	       success : function(data, textStatus, xhr) {
	            if (data == 'addFail') {
	                 alert('블랙리스트 추가 실패');
	          	}
	            else if(data=='adminAddFail'){
		        	  alert('관리자는 블랙리스트에 추가 하실 수 없습니다.');
	            }
	            else if(data=='addSuccess') {
	        	  alert('블랙리스트에 추가하였습니다.');
              location.href = 'SelectUser';
	}
	       },
	error : function(request, status, error) {
	alert("code:" + request.status + "\n" + "error:" + error);
	}
	})
}
function removeBlack(id){
	var id=id;
	  $.ajax({
	       type : "POST",
	         url : "RemoveBlackList",
	         data : "id=" + id,
	         dataType : "text",
	       success : function(data, textStatus, xhr) {
	            if (data == 'removeFail') {
	                 alert('블랙리스트 해제 실패');
	          	}
	            else if(data=='adminRemoveFail'){
		        	  alert('관리자는 해제 하실 수 없습니다.');
	            }
	            else if(data=='removeSuccess') {
	        	  alert('블랙리스트를 해제 하였습니다.');
              location.href = 'SelectUser';
	}
	       },
	error : function(request, status, error) {
	alert("code:" + request.status + "\n" + "error:" + error);
	}
	})
}
</script>
</head>
<body>
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
</body>
</html>