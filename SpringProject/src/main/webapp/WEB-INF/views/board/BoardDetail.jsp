<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
	 $(document).ready(function() {
		 getBoardList('${page}');
		 getCommentList(1);
		 $("#comments").click(function(){
			 if(check){
					var bno=${board.bno};
				    $.ajax({
				        type:'POST',
				        url : "AddComments?bno="+bno,
				        data:$("#commentForm").serialize(),
				        dataType : "text",
				        success : function(data){
				            if(data=="Success") {
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
			 }
			 else{
				 alert("수정중에는 댓글 작성이 불가능합니다.");
			 }
		 });
	 })
	</script>
<script>
	function getBoardList(page){
		  $.ajax({
		       type : "GET",
		         url : "BoardList",
		         data: "page=" + page,
		         dataType : "json",
		       success : function(result) {
		    	   		var length=result.list.length;
		    	   		var html="";
		    	   		if(length>0){
		    	   			html+="<table class='boardTable'>";
							html+="<thead><tr><td>글 번호</td><td>타이틀</td><td>작성자</td><td>작성일</td><td>조회수</td></tr></thead>";
							for(var i=0; i<length; i++){
								html+="<tr onclick='goBoard("+result.list[i].bno+","+result.paging.page+")'>";
								html+="<td>"+result.list[i].bno+"</td>";
								if(${board.bno}==result.list[i].bno){
									html+="<td><span style='color:red'> 열람중! </span></td>";
								}
								else{
									html+="<td>"+result.list[i].title+"</td>";
								}
								html+="<td>"+result.list[i].id+"</td><td>"+result.list[i].writedate+"</td><td>"+result.list[i].bview+"</td></tr>";
							}
							html+="</table>";
			        		var page="<ul class='pagination'>";
			        		var prevPage=result.paging.beginPage-1;
			        		var nextPage=result.paging.endPage+1;
			        		if(result.paging.page!=1){
			        			page += "<li><a href='javascript:getBoardList("+1+")'>처음</a></li>";
			        		}
			        		else{
			        			page += "<li class='disable'><a>처음</a></li>";
			        		}
			        		if(result.paging.beginPage!=1){
			        			page +="<li><a href='javascript:getBoardList("+prevPage+")'>이전</a></li>";
			        		}
			        		else{
			        			page +="<li class='disable'><a>이전</a></li>";
			        			
			        		}
			        		for(var i=result.paging.beginPage; i<=result.paging.endPage; i++){
			        			if(i==result.paging.page){
			        				page+="<li><a style='color:red'><span>"+i+"</span></a></li>";
			        			}
			        			else{
			        				page+="<li><a href='javascript:getBoardList("+i+")'>"+i+"</a></li>";
			        				
			        			}
			        		}
			        		
			        		if(result.paging.endPage!=result.paging.totalPage){
			        			page += "<li><a href='javascript:getBoardList("+nextPage+")'>다음</a></li>";
			        		}
			        		else{
			        			page += "<li class='disable'><a>다음</a></li>";
			        		}
			        		if(result.paging.page<result.paging.totalPage){
			        			page +="<li><a href='javascript:getBoardList("+result.paging.totalPage+")'>끝</a></li>";
			        		}
			        		else{
			        			page +="<li class='disable'><a>끝</a></li>";
			        		}
							$("#boardList").html(html);
							$("#page").html(page);
		    	   		}
		    	   		else if(length<0){
		    	   			html+="<table class='boardTable'>";
		    	   			html+="<caption>작성된 글이 없습니다.</caption>";
							html+="</table>";
							$("#boardList").html(html);
		    	   		}
		    	   
		       },
		error : function() {
		alert("실패");
		}
		});
	}
	</script>
<script>
    function getCommentList(page){
    	var page=page;
    	var bno=${board.bno};
        $.ajax({
            type:'GET',
            url : "CommentList",
            dataType : "json",
            data: "page=" + page +"&bno="+bno,
            success : function(data){
            	$("#comment_table").find("caption").children().remove();
            	$("#commentPage").children().remove();
        		$("#reply_list").children().remove();
        		var page="<ul class='pagination'>";
        		var prevPage=data.paging.beginPage-1;
        		var nextPage=data.paging.endPage+1;
        		if(data.paging.page!=1){
        			page += "<li><a href='javascript:getCommentList("+1+")'>처음</a></li>";
        		}
        		else{
        			page += "<li class='disable'><a>처음</a></li>";
        		}
        		if(data.paging.beginPage!=1){
        			page +="<li><a href='javascript:getCommentList("+prevPage+")'>이전</a></li>";
        		}
        		else{
        			page +="<li class='disable'><a>이전</a></li>";
        			
        		}
        		for(var i=data.paging.beginPage; i<=data.paging.endPage; i++){
        			if(i==data.paging.page){
        				page+="<li><a style='color:red'><span>"+i+"</span></a></li>";
        			}
        			else{
        				page+="<li><a href='javascript:getCommentList("+i+")'>"+i+"</a></li>";
        				
        			}
        		}
        		
        		if(data.paging.endPage!=data.paging.totalPage){
        			page += "<li><a href='javascript:getCommentList("+nextPage+")'>다음</a></li>";
        		}
        		else{
        			page += "<li class='disable'><a>다음</a></li>";
        		}
        		if(data.paging.page<data.paging.totalPage){
        			page +="<li><a href='javascript:getCommentList("+data.paging.totalPage+")'>끝</a></li>";
        		}
        		else{
        			page +="<li class='disable'><a>끝</a></li>";
        		}
                var html = "";
                var cCnt = data.count;
                if(data.count > 0){
                    html += "<tr><th>작성자</th><th>댓글 내용</th></tr>";
                    for(i=0; i<data.comment.length; i++){
                    	var content=data.comment[i].contents;
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
                        	html += "<div style='float:right'>수정   삭제</div>";
                        }
                }
                }
                else {
                    html += "<td><h6><strong>등록된 댓글이 없습니다.</strong></h6></td>";
                }
                $("#cCnt").html(cCnt);
                $("#commentPage").append(page);
                $("#reply_list").append(html);
        },
            error:function(request,status,error){
                
           }
            
        });
    }
	
	</script>
<script>
var check=true;
    function deleteComment(cno){
    	var cno=cno;
    	if(confirm("삭제하시겠습니까?")){
    	  	  $.ajax({
    		       type : "POST",
    		         url : "DeleteComment",
    		         data : "cno=" + cno,
    		         dataType : "text",
    		       success : function(data, textStatus, xhr) {
    								if(data=='Success'){
    									console.log($("#cCnt").text()%5);
					            		console.log($("#commentPage").find("span").text());
					            		console.log(Math.ceil($("#cCnt").text()/5));
    					            	if($("#cCnt").text()%5==1){
    					            		getCommentList(Math.ceil($("#cCnt").text()/5)-1);
    					            	}
    					            	else{
    					            		if($("#commentPage").find("span").text()<Math.ceil($("#cCnt").text()/5)){
        						                getCommentList($("#commentPage").find("span").text());
        						                
    					            		}
    					            		else{
        						                getCommentList($("#commentPage").find("span").text());
    					            		}
    					            	}
    								}
    								else if(data=='Fail'){
    									alert('삭제 실패');
    								}
    		       },
    		error : function(request, status, error) {
    		alert("code:" + request.status + "\n" + "error:" + error);
    		}
    		})
    	}
    	else{
    		
    	}
	
    }
    function modifyComment(content,cno){
    	var content=content;
    	var cno=cno;
    	if(check){
        	var html="<textarea style=width:100%; rows='3' cols='30' id='updateText'>"+content+"</textarea>";
        	html += "<div style='float:right'>";
        	html += "<a href='javascript:updateComment(\""+content+"\","+cno+")'>완료</a>    ";
        	html += "<a href='javascript:cancle(\""+content+"\","+cno+")'>취소</a></div>";
        	$("#modify"+cno).html(html);
        	check=false;
    	}
    	else{
    		alert("한번에 여러 댓글을 수정하실 수 없습니다.");
    	}
    }
    function updateComment(content,cno){
    	var cno=cno;
    	var content=$('#updateText').val();
    	  $.ajax({
   	       type : "POST",
   	         url : "UpdateComment",
   	         data : "cno=" + cno + "&contents=" + content,
   	         dataType : "text",
   	       success : function(data, textStatus, xhr) {
   							if(data=='Success'){
   									getCommentList($("#commentPage").find("span").text());
   							    	check=true;
   							}
   							else if(data=='Fail'){
   								alert('수정 실패');
   							}
   	       },
   	error : function(request, status, error) {
   	alert("code:" + request.status + "\n" + "error:" + error);
   	}
   	})
    	
    }
    function cancle(content,cno){
    	var cno=cno;
    	var content=content;
    	var a="<a href='javascript:modifyComment(\""+content+"\","+cno+")'>수정</a>";
    	var b="<a href='javascript:deleteComment("+cno+")'>삭제</a>";
    	var html="";
        html +=  content;
        html +=  "<div style='float:right'>"+a+"    "+b+"</div>"
    	$("#modify"+cno).html(html);
    	check=true;
    }
	function goBoard(bno,page){
		var bno=bno;
		var page=page;
		location.href="BoardDetail?bno="+bno+"&page="+page;
	}
	function deleteBoard(bno){
		if(confirm("삭제 하시겠습니까?")){
			var bno=bno;
			  $.ajax({
			       type : "GET",
			         url : "DeleteBoard",
			         data: "bno=" + bno,
			         dataType : "text",
			       success : function(result) {
			    	   if(result=="true"){
			    		   location.href="goBoardList";
			    	   }
			    	   else if(result=="false"){
			    		   alert('삭제 실패');
			    	   }
			       },
					error : function() {
					alert("실패");
					}
			  
		});
		}
	}
	</script>
<style>
.boardTable td {
	border: black solid 1px;
	text-align: center;
}

.pagination {
	list-style: none;
}

.pagination li {
	display: inline;
}
</style>
</head>
<body>
	<div>
		<span>제목 : ${board.title}</span><span style="float: right">
		<c:if test="${sessionScope.id eq board.id}"><a href="goModify?bno=${board.bno}&page=${page}">수정</a></c:if> 
		<c:if test="${sessionScope.id eq board.id || sessionScope.id eq 'qwerty123258'}"><a href="#" onclick="deleteBoard('${board.bno}')">삭제</a></c:if></span>
	</div>
	<div style="float: right">
		<span>작성자 : ${board.id}   작성 시간 : ${board.writedate} 조회수 : ${board.bview}</span>
	</div>
	<br>
	<br>
	<div style="float: right">
		<c:forEach var="file" items="${fileList}">
	<div>
		<span>첨부 파일 : <a href="fileDownload?bfile=${file.bfilename}&bfileoriname=${file.bfileoriname}">${file.bfileoriname}</a></span>
	</div>
	</c:forEach>
	</div>
	<br>
		<br>
			<br>
				<br>
	<div>
		<pre>${board.contents}</pre>
	</div>
	<form id="commentForm" name="commentForm" method="post">
		<div>
			<br>
			<br>
			<div>
				<span><strong>Comments</strong></span> <span id="cCnt"></span>
			</div>
			<div>
				<table id="comment_table">
					<tbody id="reply_list">

					</tbody>
				</table>
				<div id="commentPage" class="text-center"></div>
                        <c:choose>
        <c:when test="${sessionScope.id eq null}">
                <table>                    
                    <tr>
                        <td>
                            <textarea style=width:100%; rows="3" cols="30" id="comment" name="contents" readonly="true">댓글을 작성하시려면 로그인 하세요.</textarea>
                        </td>
                    </tr>
                </table>
        </c:when>
        <c:otherwise>
                <table>                    
                    <tr>
                        <td>
                            <textarea rows="3" style=width:100%; cols="30" id="comment" name="contents" placeholder="댓글을 입력하세요"></textarea>
                            <div style=float:right>
                                <a href='#' id="comments">등록</a>
                            </div>
                        </td>
                    </tr>
                </table>
        </c:otherwise>
    </c:choose>
			</div>
		</div>
	</form>
	<br><br><br>
		<a href="home">홈으로</a><br><br>
	<div id="boardList"></div>
	<div id="page"></div>
</body>
</html>