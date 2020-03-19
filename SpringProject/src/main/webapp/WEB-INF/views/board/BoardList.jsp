<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script>
	 $(document).ready(function() {
		 getBoardList(1);
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
							html+="<thead><tr><td>글 번호</td><td>타이틀</td><td>작성자</td><td>작성일</td><td><a href='#' onclick='viewOrder(1)'>조회수</a></td></tr></thead>";
							for(var i=0; i<length; i++){
								html+="<tr><td>"+result.list[i].bno+"</td><td onclick='goBoard("+result.list[i].bno+","+result.paging.page+")'>"+result.list[i].title+"</td><td>"+result.list[i].id+"</td><td>"+result.list[i].writedate+"</td><td>"+result.list[i].bview+"</td></tr>";
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
	function goBoard(bno,page){
		var bno=bno;
		var page=page;
		location.href="BoardDetail?bno="+bno+"&page="+page;
	}
	function viewOrder(page){
		var page=page;
		  $.ajax({
		       type : "GET",
		         url : "BoardListOrder",
		         data: "page=" + page,
		         dataType : "json",
		       success : function(result) {
		    	   		var length=result.list.length;
		    	   		var html="";
		    	   		if(length>0){
		    	   			html+="<table class='boardTable'>";
							html+="<thead><tr><td>글 번호</td><td>타이틀</td><td>작성자</td><td>작성일</td><td><a href='#' onclick='getBoardList(1)'>조회수</a></td></tr></thead>";
							for(var i=0; i<length; i++){
								html+="<tr><td>"+result.list[i].bno+"</td><td onclick='goBoard("+result.list[i].bno+","+result.paging.page+")'>"+result.list[i].title+"</td><td>"+result.list[i].id+"</td><td>"+result.list[i].writedate+"</td><td>"+result.list[i].bview+"</td></tr>";
							}
							html+="</table>";
			        		var page="<ul class='pagination'>";
			        		var prevPage=result.paging.beginPage-1;
			        		var nextPage=result.paging.endPage+1;
			        		if(result.paging.page!=1){
			        			page += "<li><a href='javascript:viewOrder("+1+")'>처음</a></li>";
			        		}
			        		else{
			        			page += "<li class='disable'><a>처음</a></li>";
			        		}
			        		if(result.paging.beginPage!=1){
			        			page +="<li><a href='javascript:viewOrder("+prevPage+")'>이전</a></li>";
			        		}
			        		else{
			        			page +="<li class='disable'><a>이전</a></li>";
			        			
			        		}
			        		for(var i=result.paging.beginPage; i<=result.paging.endPage; i++){
			        			if(i==result.paging.page){
			        				page+="<li><a style='color:red'><span>"+i+"</span></a></li>";
			        			}
			        			else{
			        				page+="<li><a href='javascript:viewOrder("+i+")'>"+i+"</a></li>";
			        				
			        			}
			        		}
			        		
			        		if(result.paging.endPage!=result.paging.totalPage){
			        			page += "<li><a href='javascript:viewOrder("+nextPage+")'>다음</a></li>";
			        		}
			        		else{
			        			page += "<li class='disable'><a>다음</a></li>";
			        		}
			        		if(result.paging.page<result.paging.totalPage){
			        			page +="<li><a href='javascript:viewOrder("+result.paging.totalPage+")'>끝</a></li>";
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
	function search(page){
		var keyword=$("#keyword").val();
		var searchOpt=$("#searchOpt").val();
		var page=page;
		  $.ajax({
		       type : "GET",
		         url : "SearchList",
		         data: "page=" + page + "&keyword=" + keyword + "&searchOpt=" + searchOpt,
		         dataType : "json",
		       success : function(result) {
		    	   		var length=result.list.length;
		    	   		var html="";
		    	   		if(length>0){
		    	   			html+="<table class='boardTable'>";
							html+="<thead><tr><td>글 번호</td><td>타이틀</td><td>작성자</td><td>작성일</td><td>조회수</td></tr></thead>";
							for(var i=0; i<length; i++){
								html+="<tr><td>"+result.list[i].bno+"</td><td onclick='goBoard("+result.list[i].bno+","+result.paging.page+")'>"+result.list[i].title+"</td><td>"+result.list[i].id+"</td><td>"+result.list[i].writedate+"</td><td>"+result.list[i].bview+"</td></tr>";
							}
							html+="</table>";
			        		var page="<ul class='pagination'>";
			        		var prevPage=result.paging.beginPage-1;
			        		var nextPage=result.paging.endPage+1;
			        		if(result.paging.page!=1){
			        			page += "<li><a href='javascript:search("+1+")'>처음</a></li>";
			        		}
			        		else{
			        			page += "<li class='disable'><a>처음</a></li>";
			        		}
			        		if(result.paging.beginPage!=1){
			        			page +="<li><a href='javascript:search("+prevPage+")'>이전</a></li>";
			        		}
			        		else{
			        			page +="<li class='disable'><a>이전</a></li>";
			        			
			        		}
			        		for(var i=result.paging.beginPage; i<=result.paging.endPage; i++){
			        			if(i==result.paging.page){
			        				page+="<li><a style='color:red'><span>"+i+"</span></a></li>";
			        			}
			        			else{
			        				page+="<li><a href='javascript:search("+i+")'>"+i+"</a></li>";
			        				
			        			}
			        		}
			        		
			        		if(result.paging.endPage!=result.paging.totalPage){
			        			page += "<li><a href='javascript:search("+nextPage+")'>다음</a></li>";
			        		}
			        		else{
			        			page += "<li class='disable'><a>다음</a></li>";
			        		}
			        		if(result.paging.page<result.paging.totalPage){
			        			page +="<li><a href='javascript:search("+result.paging.totalPage+")'>끝</a></li>";
			        		}
			        		else{
			        			page +="<li class='disable'><a>끝</a></li>";
			        		}
							$("#boardList").html(html);
							$("#page").html(page);
		    	   		}
		    	   		else if(length<0){
		    	   			html+="<table class='boardTable'>";
		    	   			html+="<caption>검색된 글이 없습니다.</caption>";
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
	<style>
	.boardTable td{
	border:black solid 1px;
	text-align:center;
	}
	.pagination{
	list-style:none;
	}
	.pagination li{
	display:inline;
	}
	</style>
</head>
<body>
<div id="boardList">
</div>
<div id="page">
</div>

<select id="searchOpt">
  <option value="title">제목</option>
    <option value="writer">작성자</option>
  <option value="titlecontents">제목+내용</option>
</select>
<input type="text" id="keyword">
<button onclick="search(1)">검색</button>

	<a href="home">홈으로</a>
</body>
</html>