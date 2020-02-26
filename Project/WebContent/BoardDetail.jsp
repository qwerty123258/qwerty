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
$(document).ready(function() {
	$('comments').click(function(){
	    $.ajax({
	        type:'POST',
	        url : "<c:url value='/AddComments'/>",
	        data:$("#commentForm").serialize(),
	        success : function(data){
	            if(data=="success")
	            {
	                getCommentList();
	                $("#comment").val("");
	            }
	        },
	        error:function(request,status,error){
	        	
	       }
	        
	    });
	})
	
    getCommentList();

    function getCommentList(){
        $.ajax({
            type:'GET',
            url : "<c:url value='CommentList'/>",
            dataType : "json",
            data:$("#commentForm").serialize(),
            contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
            success : function(data){
                var html = "";
                var cCnt = data.length;
                if(data.length > 0){
                    for(i=0; i<data.length; i++){
                        html += "<div>";
                        html += "<div><table class='table'><h6><strong>"+data[i].writer+"</strong></h6>";
                        html += data[i].comment + "<tr><td></td></tr>";
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
})
</script>
</head>
<body>
글 번호 : ${requestScope.bno}
제목 : ${requestScope.title}<br>
작성자 : ${requestScope.id} 조회수 : ${requestScope.bview}  작성일 : ${requestScope.writedate}<br><br><br>
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
            </div>
        </div>
        <input type="hidden" id="b_code" name="b_code" value="${result.code }" />        
    </form>
</div>
<div class="container">
    <form id="commentListForm" name="commentListForm" method="post">
        <div id="commentList">
        </div>
    </form>
</div>
</body>
</html>