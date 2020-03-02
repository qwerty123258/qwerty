<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
                <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script>
function deleteUser(id){
	var id=id;
	if(confirm("탈퇴시키겠습니까?")){
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
	              location.href = 'BoardList?page=${requestScope.page}';
		}
		       },
		error : function(request, status, error) {
		alert("code:" + request.status + "\n" + "error:" + error);
		}
		})
	}
	else{
		alert("탈퇴 취소");
	}
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
	        	  location.reload();
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
	        	  location.reload();
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
<div class="container">
    <div class="row">
        <div class="col-sm-12">
                        <jsp:include page="Header.jsp"></jsp:include>
        </div>
        <div class="col-sm-12">
  <ul class="nav nav-pills nav-justified">
    <li><a href="Main.jsp">Home</a></li>
    <li><a href="MovieList">영화</a></li>
 	<li><a href="DramaList">드라마</a></li>
    <li><a href="UtilList">유틸</a></li>
    <li class="active"><a href="OtherList">기타</a></li>
  </ul>
        </div>
        <div class="col-sm-3">
                        <jsp:include page="SideNav.jsp"></jsp:include>
        </div>
        <div class="col-sm-9">
        	            <table class='table table-striped table-bordered table-hover'>
	            <thead>
	            <tr>
	            <th>카테고리</th>
	            <th>글 제목</th>
	            <th>작성자</th>
	            <th>조회수</th>
	            <th> 작성날짜</th>
	            </tr>
	            </thead>
	                    <tbody>
	                    <c:forEach var="board" items="${boardList}">
	                    <tr>
	                    <td>${board.category}</td>
	                    <td><a href="BoardDetail?bno=${board.bno}&page=${paging.page}&category=${board.category}">${board.title}</a></td>
	                    <td>
	                    <div class='dropdown'>
	                    <button class='btn btn-default dropdown-toggle' type='button' id='menu1' data-toggle='dropdown'>${board.id}</button>
	                    <ul class='dropdown-menu' role='menu' aria-labelledby='menu1'>
	                    <li role='presentation'><a role='menuitem' tabindex='-1' href='#' onclick="window.open('SelectDetailUser?id=${board.id}','상세보기','width=430,height=500,location=no,status=no,scrollbars=yes')">상세보기</a></li>
	                    <c:if test="${sessionScope.id eq 'qwerty123258'}"><li role='presentation'><a role='menuitem' tabindex='-1' href='#' onclick="deleteUser('${board.id}')">탈퇴시키기</a></li>
	                    	                              <c:choose>
        <c:when test="${board.blacklist eq 'N'}">
      <li role="presentation"><a role="menuitem" tabindex="-1" href="#" onclick="addBlack('${board.id}')">블랙리스트 추가</a></li>
        </c:when>
        <c:otherwise>
      <li role="presentation"><a role="menuitem" tabindex="-1" href="#" onclick="removeBlack('${board.id}')">블랙리스트 해제</a></li>
        </c:otherwise>
    </c:choose>
	                    
	                    </c:if></ul>
	                    </div>
	                  </td>
	                  <td>${board.bview}</td>
	                  <td>${board.writedate}</td>
	                  </tr>
	                    </c:forEach>
                    </tbody>
                    </table>
                    <c:url var="action" value="OtherList"/>
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
</div>
  <jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>