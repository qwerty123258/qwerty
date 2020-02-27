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
	                getCommentList();
	                
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
	
    getCommentList();
	fileView();

    function getCommentList(){
        $.ajax({
            type:'GET',
            url : "<c:url value='CommentList?bno=${requestScope.bno}'/>",
            dataType : "json",
            data:$("#commentForm").serialize(),
            contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
            success : function(data){
                var html = "";
                var cCnt = data.comment.length;
                if(data.comment.length > 0){
                    for(i=0; i<data.comment.length; i++){
                        html += "<div>";
                        html += "<div><table class='table'><tr><td><h4><strong>"+data.comment[i].id+"</strong></h4></td>";
                        html += "<td>"+data.comment[i].ccontent+"</td></tr>";
                        html += "</table></div>";
                        html += "</div>";
                    }
                } else {
                    html += "<div>";
                    html += "<div><table class='table'><h6><strong>등록된 댓글이 없습니다.</strong></h6>";
                    html += "</table></div>";
                    html += "</div>";
                }
                $("#cCnt").html(cCnt);
                $("#commentList").html(html);
                
            },
            error:function(request,status,error){
                
           }
            
        });
    }
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
                        html += "<div><table class='table'><tr><td><h4><strong><a href=DownloadFile?price="+data.file[i].price+"&bfile="+data.file[i].bfile+"&bno="+${requestScope.bno}+"&bfno="+data.file[i].bfno+">"+data.file[i].boriginfile+"</a></strong></h4></td>";
                        html += "<td>"+data.file[i].price+"</td></tr>";
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
</head>
<body>
제목 : ${requestScope.title}<br>
작성자 : ${requestScope.id} 조회수 : ${requestScope.bview}  작성일 : ${requestScope.writedate}<br>
        <c:if test="${sessionScope.id eq requestScope.id}"><a href="ModifyBoard?bno=${requestScope.bno}">수정</a></c:if>
        
        <c:if test="${sessionScope.id eq requestScope.id || sessionScope.id eq 'qwerty123258'}"><a href="#" onclick="deleteBoard()">삭제</a></c:if><br><br>
        <div>
        <span><strong>Files</strong></span> <span id="file"></span>
        </div>
        <div id="fileList">
        </div>
<c:if test="${requestScope.bimgfile ne null}">
<img src="fileUpload/${requestScope.bimgfile}">
</c:if>
<pre>
${requestScope.content}
</pre>
<div class="container">
    <form id="commentForm" name="commentForm" method="post">
    <br><br>
        <div>
            <div>
                <span><strong>Comments</strong></span> <span id="cCnt"></span>
            </div>
            <div>
                        <c:choose>
        <c:when test="${sessionScope.id eq null}">
                <table class="table">                    
                    <tr>
                        <td>
                            <textarea style="width: 1100px" rows="3" cols="30" id="comment" name="comment" readonly="true">댓글을 작성하시려면 로그인 하세요.</textarea>
                        </td>
                    </tr>
                </table>
        </c:when>
        <c:otherwise>
                <table class="table">                    
                    <tr>
                        <td>
                            <textarea style="width: 1100px" rows="3" cols="30" id="comment" name="comment" placeholder="댓글을 입력하세요"></textarea>
                            <br>
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
</div>
<div class="container">
        <div id="commentList">
        </div>
</div>
</body>
</html>