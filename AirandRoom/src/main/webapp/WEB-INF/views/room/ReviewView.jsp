<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<style>
.mySlides {
	display: none;
	height: 100%;
	width: 100%;
}
body,html{
width:100%;
height:auto;
}
.modal-dialog{
height:100%;
width:100%;
}
.modal-content{
height:100%;
width:100%;

}
#modal-reviewView{
margin:auto;
width:70%;
height:80%;

}
</style>
</head>
<body>
	<script>
	var totalPage;
var check="";
	
	var page = 1;
	
	var check = false;

		$("#commentList").scroll(function() {
			let $window = $(this);
			let scrollTop = $window.scrollTop();
			let windowHeight = $window.height();
			let documentHeight = $("#commentList").height();
			let scrollHeight=$("#commentList").prop('scrollHeight');
			if(documentHeight+scrollTop>=scrollHeight){
				page+= 1;
				reviewViewCommentList('${roomrev.revno}');
			}

		});

		function reviewViewCommentList(revno) {
			if (check == true) {
				return;
			}
			$.ajax({
				type : "post",
				url : "reviewCommentList",
				data : "revno=" + revno + "&commentpage=" + page,
				dataType : "json",
				async : false,
				success : function(result) {
					var save = "";
					save += "<ul id='comment'>";
					for (var i = 0; i < result.list.length; i++) {
						save += "<li>" 
						if(result.list[i].imgname==null){
							save +="<img style='width:32px; height:32px;' src='${pageContext.request.contextPath}/resources/img/default.webp' >";							
						}else{
							save +="<img style='width:32px; height:32px;' src='${pageContext.request.contextPath}/resources/fileUpload/"+result.list[i].imgname+"\' >";																
						}

							save+="<span style='padding:5px'>"+result.list[i].id+"</span>";
							
						save+="</li>";
						save += "<li>" +
							result.list[i].contents+"</li>";

						save += "<li><a href=javascript:reviewCommentDelete("
								+ result.list[i].replyno + ","
								+ result.list[i].revno + ")><i class='far fa-trash-alt'></i></a></li>";
					}
					save += "</ul>";
					$("#commentList").html(save);
					save = "";
					if (result.paging.totalPage==result.paging.page) {
						check = true;
					} 
					
					
					
				},
				error : function() {
					alert("댓글 불러오는 중 에러 발생");
				}
			});
		}

		function reviewViewCommentWrite(revno) {
			var comment = document.getElementById(revno + "input").value;
			document.getElementById(revno + "input").value = "";
			$.ajax({
				type : "post",
				url : "reviewCommentWrite",
				data : "revno=" + revno + "&contents=" + comment
						+ "&commentpage=" + page,
				dataType : "json",
				success : function(result) {
					if($(".commentsGrid ul").children().length==15){
						page++;
					}
					var save = "";
					for (var i = 0; i < result.list.length; i++) {
						save += "<ul>";
						save += "<li>" 
							if(result.list[i].imgname==null){
								save +="<img style='width:32px; height:32px;' src='${pageContext.request.contextPath}/resources/img/default.webp' >";							
							}else{
								save +="<img style='width:32px; height:32px;' src='${pageContext.request.contextPath}/resources/fileUpload/"+result.list[i].imgname+"\' >";																
							}

								save+="<span style='padding:5px'>"+result.list[i].id+"</span>";
							
							save+="</li>";
						save += "<li>" + result.list[i].contents+"</li>";

						save += "<li><a href=javascript:reviewCommentDelete("
								+ result.list[i].replyno + ","
								+ result.list[i].revno + ")><i class='far fa-trash-alt'></i></a></li>";

						save += "</ul>";

					}
					$(".commentsGrid").html(save);
					save = "";

				},
				error : function() {

				}

			});
		}
		function reviewCommentDelete(replyno, revno) {
			$.ajax({
				type : "post",
				url : "reviewCommentDelete",
				data : "revno=" + revno + "&replyno=" + replyno
						+ "&commentpage=" + page,
				dataType : "json",
				success : function(result) {
					var save = "";
					for (var i = 0; i < result.list.length; i++) {
						save += "<ul>";
						save += "<li>";
						if(result.list[i].imgname==null){
							save +="<img style='width:32px; height:32px;' src='${pageContext.request.contextPath}/resources/img/default.webp' >";							
						}else{
							save +="<img style='width:32px; height:32px;' src='${pageContext.request.contextPath}/resources/fileUpload/+"+result.list[i].imgname+"+\'>";																
						}

							save+="<span style='padding:5px'>"+result.list[i].id+"</span>";
						save+="</li>";
						save += "<li>" +
							result.list[i].contents
						
						+"</li>";
						save += "<li><a href=javascript:reviewCommentDelete("
								+ result.list[i].replyno + ","
								+ result.list[i].revno + ")><i class='far fa-trash-alt'></i></a></li>";
						save += "</ul>";
					}
					
					$(".commentsGrid").html(save);

					save = "";
				},
				error : function() {

				}

			});

		}
		
		function pressEnter(revno){
			   check=$("#"+${roomrev.revno}+"input").val();
		         if(check!=""){
	        if(event.keyCode == 13){
	        	reviewViewCommentWrite(revno);
	        }}
	    }
		
	</script>
	<div class="col-xs-12" style="padding:0px;">
	<div class="col-xs-9">
				<div class="w3-content w3-display-container">
				<c:forEach var="plist" items="${plist}">
					<img class="mySlides"
						src="${pageContext.request.contextPath}/resources/fileUpload/${plist.rimgname}"
						style="width: 100%">
				</c:forEach>
				<button class="w3-button w3-none w3-display-left"
					onclick="plusDivs(-1)">&#10094;</button>
				<button class="w3-button w3-none w3-display-right"
					onclick="plusDivs(1)">&#10095;</button>
			</div>
	</div>
	<div class="col-xs-3">
		<div class="row">
			<c:choose>
				<c:when test="${empty com.imgname}">
					<span> <img style='width: 32px; height: 32px;'
						src="${pageContext.request.contextPath}/resources/img/default.webp">
					</span>
				</c:when>
				<c:otherwise>
					<span> <img style='width: 40px; height: 40px;'
						src="${pageContext.request.contextPath}/resources/fileUpload/${com.imgname}">
					</span>
				</c:otherwise>
			</c:choose>
			<span> ${roomrev.id} </span>
			<div id="commentList" class="col-xs-12">
			
			댓글들인데요?
			</div>
				<div class="col-xs-12">
		<input class="inputar" type='text' id='${roomrev.revno}input' onkeyup="pressEnter(${roomrev.revno})">
	 <a href="javascript:reviewViewCommentWrite(${roomrev.revno})">
	 <span style="font-size : 32px; padding: 20px;">
	 <i class="far fa-paper-plane"></i>작성
	 </span>
	 </a>
	</div>
			
		</div>
	
	</div>
</div>



</body>
<style>
.inputar{
border-bottom: 1px solid #efefef;
 border-bottom: 1px solid rgba(var(- -ce3, 239, 239, 239), 1); 
 outline: none; 
 border-radius: 20px;
}

</style>
</html>