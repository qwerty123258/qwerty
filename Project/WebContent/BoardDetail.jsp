<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
                    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/detail.js"></script>
       <script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script>
$(document).ready(function() {
	$('#comments').click(function(){
	    $.ajax({
	        type : "POST",
	          url : "TodayComment",
	          dataType : "text",
	        success : function(data, textStatus, xhr) {
							if(data=='yes'){
								commentWrite('${requestScope.bno}');
							}
							else{
								randomPoint();
								commentWrite('${requestScope.bno}');
							}
	        },
	error : function(request, status, error) {
	alert("code:" + request.status + "\n" + "error:" + error);
	}
	});
	})
	
	fileView('${requestScope.bno}');
  
})
</script>
<script>
    function getCommentList(page){
    	var page=page;
        $.ajax({
            type:'GET',
            url : "CommentList?bno=${requestScope.bno}",
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
                    	var content=data.comment[i].ccontent;
                    	var cno = data.comment[i].cno;
                    	var a="<a href='javascript:modifyComment(\""+content+"\","+cno+")'>수정</a>";
                    	var b="<a href='javascript:deleteComment("+data.comment[i].cno+")'>삭제</a>";
                        html += "<tr><td style=width:15%>"+data.comment[i].id+"</td>";
                        html += '<td id="modify'+cno+'"style=width:85%>';
                        html +=  content;
                    	if('${sessionScope.id}'==data.comment[i].id){
                    		html+="<div style='float:right'>"
                    		html+=a+"   "+b +"</div>";
                    	}
                    	else if('${sessionScope.id}'=='qwerty123258'){
                        	html+="<div style='float:right'>";
                        	html+="수정   "+b + "</div>";
                        }
                        else{
                        	html += "<div style='float:right'>수정   삭제</div>"
                        }
                        html +=  '</td></tr>';
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
</script>
</head>
<body>
                        <jsp:include page="Header.jsp"></jsp:include>
    <div class="row">
        <div class="col-sm-12">
          <ul class="nav nav-pills nav-justified">
      		<li><a href="Main.jsp">Home</a></li>
    <c:choose>
        <c:when test="${requestScope.category eq '영화'}">
    		<li class="active"><a href="MovieList">영화</a></li>
        </c:when>
        <c:otherwise>
    		<li><a href="MovieList">영화</a></li>
        </c:otherwise>
    </c:choose>
        <c:choose>
        <c:when test="${requestScope.category eq '드라마'}">
 	<li class="active"><a href="DramaList">드라마</a></li>
        </c:when>
        <c:otherwise>
 	<li><a href="DramaList">드라마</a></li>
        </c:otherwise>
    </c:choose>
        <c:choose>
        <c:when test="${requestScope.category eq '유틸'}">
    <li class="active"><a href="UtilList">유틸</a></li>
        </c:when>
        <c:otherwise>
    <li><a href="UtilList">유틸</a></li>
        </c:otherwise>
    </c:choose>
        <c:choose>
        <c:when test="${requestScope.category eq '기타'}">
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
        
        <c:if test="${sessionScope.id eq requestScope.id || sessionScope.id eq 'qwerty123258'}"><a href="#" onclick="deleteBoard('${requestScope.bno}','${requestScope.category}')">삭제</a></c:if><br><br>
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
<div class="col-sm-6">
<span id="likeCount"></span>
</div>
<div class="col-sm-6">
<span id="reportCount"></span>
</div>
<c:if test="${sessionScope.id ne null}">
<div id="like" class="col-sm-6">
          <c:choose>
<c:when test="${sessionScope.id eq requestScope.likeuser}">
<a href="javascript:LikeCancel('${requestScope.bno}')"><i class="fas fa-thumbs-up fa-5x"></i></a>
        </c:when>
        <c:otherwise>
<a href="javascript:Like('${requestScope.bno}')"><i class="far fa-thumbs-up fa-5x"></i></a>
        </c:otherwise>
    </c:choose>
</div>
<div id="report" class="col-sm-6">
          <c:choose>
        <c:when test="${sessionScope.id eq requestScope.reportuser}">
<a href="javascript:ReportCancel('${requestScope.bno}')"><i class="fas fa-thumbs-down fa-5x"></i></a>
        </c:when>
        <c:otherwise>
<a href="javascript:Report('${requestScope.bno}')"><i class="far fa-thumbs-down fa-5x"></i></a>
        </c:otherwise>
    </c:choose>
</div>
</c:if>
    <form id="commentForm" name="commentForm" method="post">
        <div class="col-sm-12">
            <br><br>
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
	                     <c:if test="${requestScope.keyword ne null}">
	                    <c:choose>
	                    <c:when test="${board.bno eq requestScope.bno}">
	                    	<td><a href="BoardDetail?bno=${board.bno}&page=${paging.page}&category=${requestScope.category}&keyword=${requestScope.keyword}" style=color:red>${board.title}</a></td>
	                    </c:when>
	                    <c:otherwise>
	                    	<td><a href="BoardDetail?bno=${board.bno}&page=${paging.page}&category=${requestScope.category}&keyword=${requestScope.keyword}">${board.title}</a></td>
	                    </c:otherwise>
	                    </c:choose>
	                    </c:if>
	               		<c:if test="${requestScope.keyword eq null}">
	               			                    <c:choose>
	                    <c:when test="${board.bno eq requestScope.bno}">
	                    	<td><a href="BoardDetail?bno=${board.bno}&page=${paging.page}&category=${requestScope.category}" style=color:red>${board.title}</a></td>
	                    </c:when>
	                    <c:otherwise>
	                    	<td><a href="BoardDetail?bno=${board.bno}&page=${paging.page}&category=${requestScope.category}">${board.title}</a></td>
	                    </c:otherwise>
	                    </c:choose>
	               		</c:if>
	                    <td>
	                    ${board.id}
	                  </td>
	                  <td>${board.bview}</td>
	                  <td>${board.writedate}</td>
	                  </tr>
	                    </c:forEach>
                    </tbody>
                    </table>
<c:if test="${requestScope.keyword ne null}">
                    <c:url var="action" value="BoardDetail"/>
<div class="text-center">
    <ul class="pagination pagination-sm pager">
                <c:choose>
        <c:when test="${paging.page==paging.beginPage}">
 <li class="disabled"><a>처음으로</a></li>
        </c:when>
        <c:otherwise>
 <li><a href="${action}?page=1&category=${requestScope.category}&keyword=${requestScope.keyword}">처음으로</a></li>
        </c:otherwise>
    </c:choose>
     <c:choose>
      <c:when test="${paging.beginPage==1}">
            <li class="disabled" ><a>이전</a></li>
        </c:when>
        <c:otherwise>
   			 <li><a href="${action}?page=${paging.beginPage-1}&category=${requestScope.category}&keyword=${requestScope.keyword}">이전</a></li>
        </c:otherwise>
    </c:choose>
    <c:forEach begin="${paging.beginPage}" end="${paging.endPage}" step="1" var="page">
    <c:choose>
        <c:when test="${paging.page==page}">
           <li class="active"><a>${page}</a></li>
        </c:when>
        <c:otherwise>
            <li><a href="${action}?page=${page}&category=${requestScope.category}&keyword=${requestScope.keyword}">${page}</a></li>
        </c:otherwise>
    </c:choose>
    </c:forEach>
          <c:choose>
        <c:when test="${paging.endPage==paging.totalPage}">
            <li class="disabled"><a>다음</a></li>
        </c:when>
        <c:otherwise>
   			 <li><a href="${action}?page=${paging.endPage+1}&category=${requestScope.category}&keyword=${requestScope.keyword}">다음</a></li>
        </c:otherwise>
    </c:choose>
                <c:choose>
        <c:when test="${paging.page==paging.totalPage || paging.totalPage eq 0}">
 <li class="disabled"><a>끝으로</a></li>
        </c:when>
        <c:otherwise>
 <li><a href="${action}?page=${paging.totalPage}&category=${requestScope.category}&keyword=${requestScope.keyword}">끝으로</a></li>
        </c:otherwise>
    </c:choose>
</ul>
</div>
</c:if>
<c:if test="${requestScope.keyword eq null}">
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
</c:if>
        
        </div>
    </div>
      <jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>