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
    function deleteBoard(){
    	if(confirm("삭제하시겠습니까?")){
    		location.href="DeleteBoard?bno=${requestScope.bno}";
    	}
    }
    </script>
<script>
$(document).ready(function() {
	$('#comments').click(function(){
	    $.ajax({
	        type:'POST',
	        url : "<c:url value='AddComments?bno=${requestScope.bno}'/>",
	        data:$("#commentForm").serialize(),
	        success : function(data){
	            if(data=="Success") {
	            	console.log(Math.ceil($("#cCnt").text()/5));
	            	console.log(Math.ceil($("#cCnt").text()/5)+1);
	            	if(Math.ceil($("#cCnt").text()/5)<=$("#cCnt").text()/5){
		                getCommentList(Math.ceil($("#cCnt").text()/5)+1);
	            	}
	            	else{
		                getCommentList(Math.ceil($("#cCnt").text()/5));
	            	}
	                $("#comment").val("");
	            }
	            else if(data=="Fail"){
	            	alert('댓글 등록 실패');
	            }
	        },
	        error:function(request,status,error){
	        	
	       }
	        
	    });
	})
	
    getCommentList(1);
	fileView();
    
    function fileView(){
        $.ajax({
            type:'GET',
            url : "<c:url value='FileList?bno=${requestScope.bno}'/>",
            dataType : "json",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
            success : function(data){
                var html = "";
                var file = data.file.length;
                if(data.file.length > 0){
                    for(i=0; i<data.file.length; i++){
                        html += "<div>";
                        html += "<div><table class='table'><tr><td><h4><strong><a href=DownloadFile?price="+data.file[i].price+"&bfile="+data.file[i].bfile+"&bno="+${requestScope.bno}+"&bfno="+data.file[i].bfno+">파일 이름 : "+data.file[i].boriginfile+"</a></strong></h4></td>";
                        html += "<td>"+data.file[i].price+" 포인트 필요</td></tr>";
                        html += "</table></div>";
                        html += "</div>";
                    }
                } else {
                    html += "<div>";
                    html += "<div><table class='table'><h6><strong>등록된 파일이 없습니다.</strong></h6>";
                    html += "</table></div>";
                    html += "</div>";
                }
                $("#file").html(file);
                $("#fileList").html(html);
                
            },
            error:function(request,status,error){
                
           }
            
        });
    }
})
</script>
<script>
    function getCommentList(page){
        $.ajax({
            type:'GET',
            url : "<c:url value='CommentList?bno=${requestScope.bno}'/>",
            dataType : "json",
            data: "page=" + page,
            contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
            success : function(data){
            	$("#comment_table").find("caption").children().remove();
            	$("#page").children().remove();
        		$("#reply_list tr").next().remove();
        		
        		var page="<ul class='pagination pagination-sm'>";
        		var prevPage=data.paging[0].beginPage-1;
        		var nextPage=data.paging[0].endPage+1;
        		if(data.paging[0].page!=1){
        			page += "<li><a href='javascript:getCommentList("+1+")'>처음</a></li>";
        		}
        		else{
        			page += "<li class='disable'><a>처음</a></li>";
        		}
        		if(data.paging[0].beginPage!=1){
        			page +="<li><a href='javascript:getCommentList("+prevPage+")'>이전</a></li>";
        		}
        		else{
        			page +="<li class='disable'><a>이전</a></li>";
        			
        		}
        		for(var i=data.paging[0].beginPage; i<=data.paging[0].endPage; i++){
        			if(i==data.paging[0].page){
        				page+="<li><a style='color:red'><span>"+i+"</span></a></li>";
        			}
        			else{
        				page+="<li><a href='javascript:getCommentList("+i+")'>"+i+"</a></li>";
        				
        			}
        		}
        		
        		if(data.paging[0].endPage!=data.paging[0].totalPage){
        			page += "<li><a href='javascript:getCommentList("+nextPage+")'>다음</a></li>";
        		}
        		else{
        			page += "<li class='disable'><a>다음</a></li>";
        		}
        		if(data.paging[0].page<data.paging[0].totalPage){
        			page +="<li><a href='javascript:getCommentList("+data.paging[0].totalPage+")'>끝</a></li>";
        		}
        		else{
        			page +="<li class='disable'><a>끝</a></li>";
        		}
                var html = "";
                var cCnt = data.count;
                if(data.comment.length > 0){
                    html += "<tr><th>작성자</th><th>댓글 내용</th></tr>";
                    for(i=0; i<data.comment.length; i++){
                        html += "<tr><td style=width:15%>"+data.comment[i].id+"</td>";
                        html += "<td style=width:85%>"+data.comment[i].ccontent+"</td></tr>";
                    }
                } else {
                    html += "<td><h6><strong>등록된 댓글이 없습니다.</strong></h6></td>";
                }
                $("#cCnt").html(cCnt);
                $("#page").append(page);
                $("#reply_list").append(html);
                
            },
            error:function(request,status,error){
                
           }
            
        });
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
    <c:choose>
        <c:when test="${requestScope.category eq 'movie'}">
    		<li class="active"><a href="MovieList">영화</a></li>
        </c:when>
        <c:otherwise>
    		<li><a href="MovieList">영화</a></li>
        </c:otherwise>
    </c:choose>
        <c:choose>
        <c:when test="${requestScope.category eq 'drama'}">
 	<li class="active"><a href="DramaList">드라마</a></li>
        </c:when>
        <c:otherwise>
 	<li><a href="DramaList">드라마</a></li>
        </c:otherwise>
    </c:choose>
        <c:choose>
        <c:when test="${requestScope.category eq 'util'}">
    <li class="active"><a href="UtilList">유틸</a></li>
        </c:when>
        <c:otherwise>
    <li><a href="UtilList">유틸</a></li>
        </c:otherwise>
    </c:choose>
        <c:choose>
        <c:when test="${requestScope.category eq 'other'}">
    <li class="active"><a href="OtherList">기타</a></li>
        </c:when>
        <c:otherwise>
    <li><a href="OtherList">기타</a></li>
        </c:otherwise>
    </c:choose>
  </ul>
        </div>
        <div class="col-sm-3">
   			<jsp:include page="SideNav.jsp"></jsp:include>
        </div>
        <div class="col-sm-9">
        <c:if test="${requestScope.bno ne null}">
제목 : ${requestScope.title}<br>
작성자 : ${requestScope.id} 조회수 : ${requestScope.bview}  작성일 : ${requestScope.writedate}<br>
        <c:if test="${sessionScope.id eq requestScope.id}"><a href="ModifyBoard?bno=${requestScope.bno}">수정</a></c:if>
        
        <c:if test="${sessionScope.id eq requestScope.id || sessionScope.id eq 'qwerty123258'}"><a href="#" onclick="deleteBoard()">삭제</a></c:if><br><br>
        <div>
        <span><strong>Files</strong></span> <span id="file"></span>
                                <c:choose>
        <c:when test="${sessionScope.id eq null}">
        <div>
        	<p><strong>파일 다운로드를 하시려면 로그인을 하셔야 합니다.</strong></p><br><br>
        </div>
        </c:when>
        <c:otherwise>
        <div id="fileList">
        </div>
        </c:otherwise>
    </c:choose>
        </div>
<c:if test="${requestScope.bimgfile ne null}">
<img style=width:200px src="fileUpload/${requestScope.bimgfile}">
</c:if>
<pre>
${requestScope.content}
</pre>
    <form id="commentForm" name="commentForm" method="post">
        <div>
            <div>
                <span><strong>Comments</strong></span> <span id="cCnt"></span>
            </div>
            <div>
            <table class="table table-striped table-bordered" id="comment_table">
<tbody id="reply_list">
<tr>
</tr>
</tbody>
</table>
<div id="page" class="text-center">

</div> 
                        <c:choose>
        <c:when test="${sessionScope.id eq null}">
                <table class="table">                    
                    <tr>
                        <td>
                            <textarea style=width:100%; rows="3" cols="30" id="comment" name="comment" readonly="true">댓글을 작성하시려면 로그인 하세요.</textarea>
                        </td>
                    </tr>
                </table>
        </c:when>
        <c:otherwise>
                <table class="table">                    
                    <tr>
                        <td>
                            <textarea rows="3" style=width:100%; cols="30" id="comment" name="comment" placeholder="댓글을 입력하세요"></textarea>
                            <div>
                                <a href='#' id="comments" class="btn pull-right btn-success">등록</a>
                            </div>
                        </td>
                    </tr>
                </table>
        </c:otherwise>
    </c:choose>
            </div>
        </div>       
    </form>
    </c:if>         
	            <table class="table table-striped table-bordered table-hover">
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
	                    <c:choose>
	                    <c:when test="${board.bno eq requestScope.bno}">
	                    	<td><a href="BoardDetail?bno=${board.bno}&page=${paging.page}&category=${requestScope.category}" style=color:red>${board.title}</a></td>
	                    </c:when>
	                    <c:otherwise>
	                    	<td><a href="BoardDetail?bno=${board.bno}&page=${paging.page}&category=${requestScope.category}">${board.title}</a></td>
	                    </c:otherwise>
	                    </c:choose>
	                    <td>
	                    <div class="dropdown">
	                    <button class="btn btn-default dropdown-toggle" type="button" id="menu1" data-toggle="dropdown">${board.id}</button>
	                    <ul class="dropdown-menu" role="menu" aria-labelledby="menu1">
	                    <li role="presentation"><a role="menuitem" tabindex="-1" href="#" onclick="window.open('SelectDetailUser?id=${board.id}','상세보기','width=430,height=500,location=no,status=no,scrollbars=yes')";>상세보기</a></li>
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
                    <c:url var="action" value="BoardDetail"/>
<div class="text-center">
    <ul class="pagination pagination-sm pager">
                <c:choose>
        <c:when test="${paging.page==paging.beginPage}">
 <li class="disabled"><a>처음으로</a></li>
        </c:when>
        <c:otherwise>
 <li><a href="${action}?page=1&category=${requestScope.category}">처음으로</a></li>
        </c:otherwise>
    </c:choose>
     <c:choose>
      <c:when test="${paging.beginPage==1}">
            <li class="disabled" ><a>이전</a></li>
        </c:when>
        <c:otherwise>
   			 <li><a href="${action}?page=${paging.beginPage-1}&category=${requestScope.category}">이전</a></li>
        </c:otherwise>
    </c:choose>
    <c:forEach begin="${paging.beginPage}" end="${paging.endPage}" step="1" var="page">
    <c:choose>
        <c:when test="${paging.page==page}">
           <li class="active"><a>${page}</a></li>
        </c:when>
        <c:otherwise>
            <li><a href="${action}?page=${page}&category=${requestScope.category}">${page}</a></li>
        </c:otherwise>
    </c:choose>
    </c:forEach>
          <c:choose>
        <c:when test="${paging.endPage==paging.totalPage}">
            <li class="disabled"><a>다음</a></li>
        </c:when>
        <c:otherwise>
   			 <li><a href="${action}?page=${paging.endPage+1}&category=${requestScope.category}">다음</a></li>
        </c:otherwise>
    </c:choose>
                <c:choose>
        <c:when test="${paging.page==paging.totalPage || paging.totalPage eq 0}">
 <li class="disabled"><a>끝으로</a></li>
        </c:when>
        <c:otherwise>
 <li><a href="${action}?page=${paging.totalPage}&category=${requestScope.category}">끝으로</a></li>
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