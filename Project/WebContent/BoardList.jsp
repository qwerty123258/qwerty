<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
                <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
$(document).ready(function() {
	
	getBoardList();
	
	function getBoardList(){
	    $.ajax({
	        type:'GET',
	        url : "<c:url value='BoardList'/>",
	        dataType : "json",
	        contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
	        success : function(data){
	            var html = "";
	            var cCnt = data.board.length;
	            html += "<div>";
	            html += "<div><table class='table table-striped table-bordered table-hover'>";
	            html += "<thead><tr><th>카테고리</th><th>글 제목</th><th>작성자</th><th>조회수</th><th> 작성날짜</th></tr></thead>";
	            if(data.board.length > 0){
	                for(i=0; i<data.board.length; i++){
	                    html += "<tbody><tr><td>"+data.board[i].category+"</td>";
	                    html += "<td><a href=BoardDetail?bno="+data.board[i].bno+">"+data.board[i].title+"</td><td>";
	                    html += "<div class='dropdown'>";
	                    html += "<button class='btn btn-default dropdown-toggle' type='button' id='menu1' data-toggle='dropdown'>"+data.board[i].id+"</button>";
	                    html += "<ul class='dropdown-menu' role='menu' aria-labelledby='menu1'>";
	                    html += "<li role='presentation'>";
	                    html += "<a role='menuitem' tabindex='-1' href='#' onclick='window.open('SelectDetailUser?id="+data.board[i].id+"','상세보기','width=430,height=500,location=no,status=no,scrollbars=yes')';>상세보기</a></li>";
	                    html += "<c:if test="${sessionScope.id eq 'qwerty123258'}+"><li role='presentation'><a role='menuitem' tabindex='-1' href='#' onclick='deleteUser("+data.board[i].id+")'>탈퇴시키기</a></li></c:if></ul></div>";
	                    html += "</td><td>"+data.board[i].bview+"</td><td>"+data.board[i].writedate+"</td></tr>";
	                }
                    html += "</tbody></table></div>";
                    html += "</div>";
	            } else {
	                html += "<tbody><h6><strong>등록된 게시글이 없습니다.</strong></h6>";
	                html += "</tbody></table></div>";
	                html += "</div>";
	            }
	            $("#cCnt").html(cCnt);
	            $("#boardList").html(html);
	            
	        },
	        error:function(request,status,error){
	            
	       }
	        
	    });
	}
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
              location.href = 'BoardList?page=${requestScope.page}';
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
<div id="boardList">

</div>
<c:url var="action" value="BoardList"/>
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