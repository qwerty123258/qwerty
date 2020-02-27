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
<script>
$(document).ready(function(){
	$('#delete').click(function create() {
		var id=$('#id_input').val();
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
	});
})
</script>
</head>
<body>
<table>
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
<c:forEach var="user" items="${users}" >
<tr>
<td>
<a href="#" onclick="window.open('SelectDetailUser?id=${user.ID}','상세보기','width=430,height=500,location=no,status=no,scrollbars=yes');">
${user.ID}
</a>
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
</table>
삭제 대상
<input type="text" id="id_input">
<button id="delete">삭제하기</button>
<div id="paging">
<c:url var="action" value="/SelectUser"/>
            <c:choose>
        <c:when test="${paging.page==paging.beginPage}">
            <a>처음으로</a>
        </c:when>
        <c:otherwise>
 <a href="${action}?page=1">처음으로</a>
        </c:otherwise>
    </c:choose>
     <c:choose>
      <c:when test="${paging.beginPage==1}">
            <a>이전</a>
        </c:when>
        <c:otherwise>
   			 <a href="${action}?page=${paging.beginPage-1}">이전</a>
        </c:otherwise>
    </c:choose>
<c:forEach begin="${paging.beginPage}" end="${paging.endPage}" step="1" var="page">
    <c:choose>
        <c:when test="${paging.page==page}">
            ${page}
        </c:when>
        <c:otherwise>
            <a href="${action}?page=${page}">${page}</a>
        </c:otherwise>
    </c:choose>
    </c:forEach>
        <c:choose>
        <c:when test="${paging.endPage==paging.totalPage}">
            <a>다음</a>
        </c:when>
        <c:otherwise>
   			 <a href="${action}?page=${paging.endPage+1}">다음</a>
        </c:otherwise>
    </c:choose>
            <c:choose>
        <c:when test="${paging.page==paging.totalPage || paging.totalPage eq 0}">
            <a>끝으로</a>
        </c:when>
        <c:otherwise>
 <a href="${action}?page=${paging.totalPage}">끝으로</a>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>