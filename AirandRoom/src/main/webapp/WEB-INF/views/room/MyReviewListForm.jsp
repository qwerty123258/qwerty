<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
	<script>
	if('${check}'=='my'){
		MyReviewListForm('1');		
		
	}else{
	
		AllReviewListForm('1');				
		
		
	}
		var check = false;
		var page = 1;
		$(window).scroll(function() {
			let $window = $(this);
			let scrollTop = $window.scrollTop();
			let windowHeight = $window.height();
			let documentHeight = $(document).height();
			let scrollHeight = $(document).prop('scrollHeight');

			if (scrollTop + documentHeight >= scrollHeight) {
				page += 1;
				MyReviewListForm(page);
			}
		});
		function reviewDelete(revno) {
			$.ajax({
				type : "post",
				url : "reviewDelete",
				data : "revno=" + revno,
				dataType : "text",
				success : function(result) {
					if (result == "yes") {
						alert('삭제성공');
						location.reload();
					} else {
						alert('삭제실패');
					}
				},
				error : function() {

				}

			});
		}
		function good(revno) {
			$.ajax({
				type : "post",
				url : "reviewProperty",
				data : "revno=" + revno,
				dataType : "text",
				success : function(result) {
					if (result == "yes") {
						var check = document.getElementById(revno + "good");
						if (check.style.color =="red") {
							alert('y');
							check.style.color="black";
							$("#" + revno + "good").html("<i class='far fa-heart' ></i>");
						} else {
							alert('n');
							check.style.color="red";							
							$("#" + revno + "good").html("<i class='far fa-heart' ></i>");
						}
					} else {
						alert('실패');
					}
				},
				error : function() {
					alert('실패');
				}

			});

		}
		function reviewCommentList(revno) {
			$.ajax({
				type : "post",
				url : "reviewCommentList",
				data : "revno=" + revno + "&commentpage=" + 1,
				dataType : "json",
				success : function(result) {
					var save = "";
					save += "<table id='tableComment"+revno+"' class='table table-striped table-bordered table-hover'>";
					if (result.list.length > 3) {
						for (var i = 0; i < 3; i++) {
							save += "<tr>";
							
								save += "<td><a href='myReviewListForm?id=" + result.list[i].id +"&check=my' style='padding-right:20px;'>" + result.list[i].id + "</a>" + result.list[i].contents + "</td>";										
							
							
							save += "</tr>";
						}
					} 
					else {
						for (var i = 0; i < result.list.length; i++) {
							save += "<tr>";
							save += "<td><a href='myReviewListForm?id="+ result.list[i].id +"&check=my' style='padding-right:20px;'>" + result.list[i].id + "</a>" + result.list[i].contents + "</td>";
									save += "</tr>";
						}
					}
					save += "<a href='javascript:reviewView("+revno+")' style='padding-right:20px;'>댓글더보기</a>";
					save += "<tr>"
					save += "<td>";
					save += "<input type='text' id='"+revno+"input' style='width: 95%;'>";
					save += "<button onclick='reviewCommentWrite(" + revno
							+ ")'>완료</button>";
					save += "</td>"
					save += "</tr>"
					save += "</table>";
					$("#table" + revno).append(save);

				},
				error : function() {

				}
			});
		}

		function reviewCommentWrite(revno) {
			var comment = document.getElementById(revno + "input").value;
			document.getElementById(revno + "input").value = "";
			$.ajax({
				type : "post",
				url : "reviewCommentWrite",
				data : "revno=" + revno + "&contents=" + comment
						+ "&commentpage=" + 1,
				dataType : "json",
				success : function(result) {
					var save = "";
					save += "<table id='table'"+revno+" class='table table-striped table-bordered table-hover'>"
					if (result.list.length > 3) {
						for (var i = 0; i < 3; i++) {
							save += "<tr>";
							save += "<td><a herf='myReviewListForm?id=" + result.list[i].id +"&check=my' style='padding-right:20px;'>" + result.list[i].id + "</a>" + result.list[i].contents + "</td>";
									save += "</tr>";
					}
					} else {
						for (var i = 0; i < result.list.length; i++) {
							save += "<tr>";
							save += "<td><a herf='myReviewListForm?id=" + result.list[i].id +"&check=my' style='padding-right:20px;'>" + result.list[i].id + "</a>" + result.list[i].contents + "</td>";
							save += "</tr>";
							}
					}
					save += "<tr>"
					save += "<td>"
						save += "<input type='text' id='"+revno+"input' style='width: 95%;'>";
					save += "<button onclick='reviewCommentWrite(" + revno
							+ ")'>완료</button>";
					save += "</td>"
					save += "</tr>"
					save +="</table>";

					$("#tableComment" + revno).html(save);
				},
				error : function() {

				}

			});
		}
		function MyReviewListForm(page) {
			if (check == true) {
				return;
			}
		    $.ajax({
		        type:'post',
				url : "myReviewList",
				data : "page=" + page+"&id="+'${id}',
		        dataType : "json",
		        success : function(data){
		            var html = "<h3 style='padding-left:15px'>Air&Room</h3>";
		            var length=data.list.length;
		            if(length>0){
	                	for(var i=0; i<length; i++){
			            	html += "<table id='table"+data.list[i].revno+"' class='table table-striped table-bordered table-hover'>";
							html+="<tr><td>"+data.list[i].id+"</td></tr>";
							html+="<tr><td>";
							html += "<a href=reviewUpdateForm?revno="
									+ data.list[i].revno + ">후기 수정</a>";
							html += "<a href=javascript:reviewDelete("
									+ data.list[i].revno + ")><i class='far fa-trash-alt'></i></a></td></tr>";
							html +="<tr><td>"
							html +="<img style='width:100%; height:400px;' src='${pageContext.request.contextPath}/resources/fileUpload/"+data.list[i].rimgname+"'</td></tr>";
							html +="<tr><td>"+data.list[i].contents+"</td></tr>";
		                	html +="</table>";
		                	reviewCommentList(data.list[i].revno);
	                
	                	}
		            }
		            else{
		            	html+="<span>후기글이 존재하지 않습니다.</span>"
		            	
		            }
				            $("#reviewList").html(html);
		                },
		        error:function(request,status,error){
		            alert("리뷰 리스트를 불러오는 중 에러 발생");
		       }
		        
		    });
		}
		
		function AllReviewListForm(page) {
			if (check == true) {
				return;
			}
		    $.ajax({
		        type:'post',
				url : "allReviewList",
				data : "page=" + page+"&rno="+${check},
		        dataType : "json",
		        success : function(data){
		            var html = "<h3 style='padding-left:15px'>Air&Room</h3>";
		            var length=data.list.length;
		            if(length>0){
	                	for(var i=0; i<length; i++){
			            	html += "<table id='table"+data.list[i].revno+"' class='table table-striped table-bordered table-hover'>";
							html+="<tr><td>"+data.list[i].id+"</td></tr>";
							html+="<tr><td>";
							html += "<a href=reviewUpdateForm?revno="
									+ data.list[i].revno + ">후기 수정</a>";
							html += "<a href=javascript:reviewDelete("
									+ data.list[i].revno + ")><i class='far fa-trash-alt'></i></a></td></tr>";
							html +="<tr><td>"
							html +="<img style='width:100%; height:400px;' src='${pageContext.request.contextPath}/resources/fileUpload/"+data.list[i].rimgname+"'</td></tr>";
							html +="<tr><td>"+data.list[i].contents+"</td></tr>";
		                	html +="</table>";
		                	reviewCommentList(data.list[i].revno);
	                
	                	}
		            }
		            else{
		            	html+="<span>후기글이 존재하지 않습니다.</span>"
		            	
		            }
				            $("#reviewList").html(html);
		                },
		        error:function(request,status,error){
		            alert("리뷰 리스트를 불러오는 중 에러 발생");
		       }
		        
		    });
		}
		
		
	</script>
</head>
<body>
	<jsp:include page="../Header.jsp"></jsp:include><br>
	<jsp:include page="../Nav.jsp"></jsp:include>
	<br>
								<div id="modal" style="display:none;">
		<div id="overlay" onclick="modalClose()"></div>
			<div id="modalcontent">
					<h1>Air & Room <i class="far fa-times-circle" style="float:right; margin-right:10px;" onclick="modalClose()"></i></h1>
					<div class="col-xs-8" style="height:90%;">
					<div id="plist" style="height:100%;">

					
					</div>
					</div>
					<div class="col-xs-4" style="height:90%">
						<div id="revcontent" style="height:auto">
						
						</div>
						<div id="revcomment" style='overflow-y:auto; overflow-x:hidden; width:100%; height:55%;'>
						
						
						</div>
						<div id="commentinput" class="input-group">
						
						</div>
					</div>

			</div>
	</div>
	<div class="row">
		<div class="col-xs-3">
			<jsp:include page="../member/SideNav.jsp"></jsp:include>
		</div>
		<div class="col-xs-9">
		<div id="reviewList" class="col-xs-8" style="margin:auto;">
		</div>
		</div>
		</div>
</body>
<script>
	var commentpage=1;
	var slideIndex = 1;
	function plusDivs(n) {
	  showDivs(slideIndex += n);
	}
	function showDivs(n) {
	  var i;
	  var x = document.getElementsByClassName("mySlides");
	  if (n > x.length) {slideIndex = 1}
	  if (n < 1) {slideIndex = x.length} ;
	  for (i = 0; i < x.length; i++) {
	    x[i].style.display = "none";
	  }
	
	  x[slideIndex-1].style.display = "block";
	}
	function modalClose(){
		commentpage=1;
		$("#modal").hide();
		$("#revcomment").html("");
		$("#commentList").html("");
		$("#revcontent").html("");
		$("#plist").html("");
	}
	var sysrevno;
	function reviewView(revno){
		console.log(commentpage);
		sysrevno=revno;
		console.log(sysrevno);
	    $.ajax({
	        type:'get',
			url : "reviewView",
			data : "revno=" + revno+"&page="+commentpage,
	        dataType : "json",
	        async:false,
	        success : function(data){
	        	var plist="";
	        	var revcontent="";
	        	var revcomment="";
	        	var commentinput="";
	        	revcomment+="<table id='commentList' class='table table-bordered table-striped table-hover' style='height:100%;'>";
	        	revcomment+="<tbody style='height:100%;'>"
	        	plist+="<div class='w3-content w3-display-container' style='height:100%'>";
	        	for(var i=0; i<data.plist.length; i++){
					
					plist+=	"<img class='mySlides' src='${pageContext.request.contextPath}/resources/fileUpload/"+data.plist[i].rimgname+"' style='width: 100%; height:100%;'>";
	
	        	}
	        	for(var j=0; j<data.comment.length; j++){
	        		revcomment+="<tr style='text-align:left' id='comment"+data.comment[j].replyno+"' style='height:50px;'><td><img style='width:32px; height:32px;' onError='this.src=\"${pageContext.request.contextPath}/resources/img/default.webp\"'  src='${pageContext.request.contextPath}/resources/fileUpload/"+data.comment[j].imgname+"\' >"+"     "+data.comment[j].id+" : "+data.comment[j].contents+"</td></tr>";
	        	}
	        	commentinput+="<input type='text' style='width: 95%;' id='commentinput"+data.roomrev.revno+"'/> ";
	        	commentinput+="<button class='btn btn-info' onclick='writeComment("+data.roomrev.revno+")'><i class='far fa-paper-plane'>"+ "  " +"댓글 입력</button>";
	        	revcomment+="</tbody>";
				plist+=	"<button class='w3-button w3-none w3-display-left' onclick='plusDivs(-1)'>&#10094;</button>";
				plist+=	"<button class='w3-button w3-none w3-display-right' onclick='plusDivs(1)'>&#10095;</button>";
				plist+=	"</div>";
				revcontent+="<p>"+data.roomrev.id+"</p>";
				revcontent+="<pre style='text-align:left'>"+data.roomrev.contents+"</pre>";
				
				$("#commentinput").html(commentinput);
				$("#revcomment").html(revcomment);
				$("#revcontent").html(revcontent);
	        	$("#plist").html(plist);
	        	showDivs(slideIndex);
	
	        },
	        error:function(){
	        	alert("모달 창 정보를 불러오는 중 에러 발생");
	        },
	    });
		$("#modal").show();
		$("#revcomment").scroll(function() {
			let $window = $(this);
			let scrollTop = $window.scrollTop();
			let windowHeight = $window.height();
			let documentHeight = $("#revcomment").height();
			let scrollHeight=$("#revcomment").prop('scrollHeight');
			let offsetHeight=$("#revcomment").prop('offsetHeight');
			if(offsetHeight+scrollTop>=scrollHeight){
				commentpage++;
				reviewViewScroll(sysrevno,commentpage);
			}
	
		});
	}
	function reviewViewScroll(revno,commentpage){
	    $.ajax({
	        type:'get',
			url : "reviewViewScroll",
			data : "revno=" + revno+"&page="+commentpage,
	        dataType : "json",
	        async:false,
	        success : function(data){
	        	var plist="";
	        	var revcontent="";
	        	var revcomment="";
	        	revcomment+="<table id='commentList' class='table table-bordered table-striped table-hover' style='height:100%;'>";
	        	revcomment+="<tbody style='height:100%;'>"
	        	for(var j=0; j<data.comment.length; j++){
	        		revcomment+="<tr style='text-align:left' id='comment"+data.comment[j].replyno+"' style='height:50px;'><td><img style='width:32px; height:32px;' onError='this.src=\"${pageContext.request.contextPath}/resources/img/default.webp\"'  src='${pageContext.request.contextPath}/resources/fileUpload/"+data.comment[j].imgname+"\' >"+"     "+data.comment[j].id+" : "+data.comment[j].contents+"</td></tr>";
	        	}
	        	revcomment+="</tbody>";
				
				$("#revcomment").html(revcomment);
	
	        },
	        error:function(){
	        	alert("댓글을 불러오는 중 에러 발생");
	        },
	    });
	}
	
	function writeComment(revno){
	var comment=$("#commentinput"+revno).val();
	$.ajax({
	    type:'POST',
		url : "writeCommentModal",
		data : "revno=" + revno+ "&comment="+comment,
	    dataType : "text",
	    async:false,
	    success : function(data){
	    	$("#commentinput"+revno).val("");
	    	if(data=="Success"){
	        	reviewCommentWriteList(revno);
	        	reviewViewScroll(revno,commentpage);
	    	}
	    	else{
	    		alert("댓글 작성 실패");
	    	}
	
	    },
	    error:function(){
	    	alert("댓글 작성 중 에러 발생");
	    }
	    });
			
	}
	function reviewCommentWriteList(revno) {
		$.ajax({
			type : "post",
			url : "reviewCommentList",
			data : "revno=" + revno + "&commentpage=" + 1,
			dataType : "json",
			success : function(result) {
				var save = "";
				save += "<table id='table'"+revno+" class='table table-striped table-bordered table-hover'>"
				if (result.list.length > 3) {
					for (var i = 0; i < 3; i++) {
						save += "<tr>";
						save += "<td><a herf='myReviewListForm?page=1' style='padding-right:20px;'>" + result.list[i].id + "</a>" + result.list[i].contents + "</td>";
						save += "</tr>";
				}
				} else {
					for (var i = 0; i < result.list.length; i++) {
						save += "<tr>";
						save += "<td><a herf='myReviewListForm?page=1' style='padding-right:20px;'>" + result.list[i].id + "</a>" + result.list[i].contents + "</td>";
						save += "</tr>";
						}
				}
				save += "<tr>"
				save += "<td>"
					save += "<input type='text' id='"+revno+"input' style='width: 95%;'>";
				save += "<button onclick='reviewCommentWrite(" + revno
						+ ")'>댓글작성</button>";
				save += "</td>"
				save += "</tr>"
				save +="</table>";
	
				$("#tableComment" + revno).html(save);
			},
			error : function() {
	
			}
	
		});
	}
	
	</script>
	<style>
	.mySlides {
		display: none;
		height: 100%;
		width: 100%;
	}
	#modal{
		position:fixed;
		top:0;
		left:0;
		width:100%;
		height:100%;
		justify-content:center;
		align-items:center;
		display:flex;
		z-index:1500;
	}
	
	#overlay{
		background-Color:rgba(0,0,0,0.6);
			width:100%;
		height:100%;
			position:absolute;
	}
	#modalcontent{
		background-Color:white;
		text-align:center;
		position:relative;
		width:60%;
		height:90%;
		box-shadow : 0 10px 20px rgba(0,0,0,0.19), 0 6px 6px rgba(0,0,0,0.23);
		border-radious:5px;
		
	}
	      .input-group{
	    position: absolute;
	
	    left: 0;
	
	    bottom: 0;
	
	    width: 100%;
		padding-bottom:20px;
	      }
	</style>
</html>