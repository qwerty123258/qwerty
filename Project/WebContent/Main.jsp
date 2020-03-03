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
$(document).ready(function() {
	getLikeTopList();
	
	function getLikeTopList(){
        $.ajax({
            type:'GET',
            url : "<c:url value='LikeTopList'/>",
            dataType : "json",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
            success : function(data){
                var html = "";
                if(data.like.length > 0){
                	html += "<table class='table table-striped table-bordered'><thead><tr><th>순위</th><th>좋아요 수</th><th>글 번호</th><th>카테고리</th><th>글 제목</th><th>작성자</th><th>작성일</th></tr></thead>";
                	for(var i=0; i<5; i++){
                    	
                        	html +="<tr><td>"+(i+1)+"</td><td>"+data.like[i].likes+"</td><td>"+data.like[i].bno+"</td><td>"+data.like[i].category+"</td><td>"+data.like[i].title+"</td><td>"+data.like[i].id+"</td><td>"+data.like[i].writedate+"</td></tr>";
                       
                	}
                	html +="</table>";
                }
                $("#likeTop").html(html);
            	getViewTopList();
            },
            error:function(request,status,error){
                
           }
            
        });
		
	}
	
	function getViewTopList(){
        $.ajax({
            type:'GET',
            url : "<c:url value='ViewTopList'/>",
            dataType : "json",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
            success : function(data){
                var html = "";
                if(data.view.length > 0){
                	html += "<table class='table table-striped table-bordered'><thead><tr><th>순위</th><th>조회 수</th><th>글 번호</th><th>카테고리</th><th>글 제목</th><th>작성자</th><th>작성일</th></tr></thead>";
                	for(var i=0; i<5; i++){
                    	
                        	html +="<tr><td>"+(i+1)+"</td><td>"+data.view[i].bview+"</td><td>"+data.view[i].bno+"</td><td>"+data.view[i].category+"</td><td>"+data.view[i].title+"</td><td>"+data.view[i].id+"</td><td>"+data.view[i].writedate+"</td></tr>";
                       
                	}
                	html +="</table>";
                    	
                    	
                    }
                $("#viewTop").html(html);
            	getLatestTopList();
                
            },
            error:function(request,status,error){
                
           }
            
        });
		
	}
	function getLatestTopList(){
        $.ajax({
            type:'GET',
            url : "<c:url value='LatestTopList'/>",
            dataType : "json",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
            success : function(data){
                var html = "";
                if(data.latest.length > 0){
                	var length=data.latest.length;
                	if(length<=15){
                    	html += "<table class='table table-striped table-bordered'><thead><tr><th>글 번호</th><th>카테고리</th><th>글 제목</th><th>작성자</th><th>조회 수</th><th>작성일</th></tr></thead>";
                    	for(var i=0; i<data.latest.length; i++){
                        	
                            	html +="<tr><td>"+data.latest[i].bno+"</td><td>"+data.latest[i].category+"</td><td>"+data.latest[i].title+"</td><td>"+data.latest[i].id+"</td><td>"+data.latest[i].bview+"</td><td>"+data.latest[i].writedate+"</td></tr>";
                           
                    	}
                    	html +="</table>";
                	}
                	else{
                    	html += "<table class='table table-striped table-bordered'><thead><tr><th>글 번호</th><th>카테고리</th><th>글 제목</th><th>작성자</th><th>조회 수</th><th>작성일</th></tr></thead>";
                    	for(var i=0; i<14; i++){
                        	
                            	html +="<tr><td>"+data.latest[i].bno+"</td><td>"+data.latest[i].category+"</td><td>"+data.latest[i].title+"</td><td>"+data.latest[i].id+"</td><td>"+data.latest[i].bview+"</td><td>"+data.latest[i].writedate+"</td></tr>";
                           
                    	}
                    	html +="</table>";
                	}
                $("#latestTop").html(html);
                
            }
            },
            error:function(request,status,error){
                
           }
            
        });
		
	}
})
</script>
<style>
</style>
</head>
<body>
<jsp:include page="Header.jsp"></jsp:include>
<div class="container">
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
        				<h3>좋아요 Top5</h3>
        				<div id="likeTop">
        			
        				</div>
        				<h3>조회수 Top5</h3>
        				<div id="viewTop">
        				
        				</div>
        				<h3>최신글</h3>
        				<div id="latestTop">
        				
        				</div>
        			     </div>
    </div>
</div>
  <jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>