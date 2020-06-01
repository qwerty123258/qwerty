<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>문의글 답장 페이지입니다.</title>
    <script src="https://code.jquery.com/jquery-3.4.1.js"
    integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
    crossorigin="anonymous">
    </script>
    
<script>
function replyInquire(){
	var title = $("#title").val();
	var contents = $("#contents").val();
    var ifile = $("#ifile").val();
	if(title == "") {
		alert("제목을 입력해주세요.");
		$("#title").focus();
	} else if (contents == "") {
		alert("내용을 입력해주세요.");
		$("#contents").focus();		
	} else {
		var result = confirm("등록하시겠습니까?");
		if(result){
			var formData = new FormData(document.getElementById("form"));
		    $.ajax({
      			type : "POST",
				url : "replyInquire",
				data : formData,
				processData : false, 
				contentType : false,
		        dataType : "text",
		                success: function(result){
		                    alert("성공");
		                    window.opener.close();
		        			self.close();
		                }

		                });
		        }
	}
}
</script>
<style>
p, h1, form, button{border:0; margin:0; padding:0;}
.spacer{clear:both; height:1px;}
.myform{
	margin:10px;
	width:400px;
	height:600px;
	padding:10px;
}
#stylized{
	border:solid 2px #b7ddf2;
	background:#ebf4fb;
}
#stylized h1 {
	font-size:16px;
	font-weight:bold;
	margin-bottom:8px;
	font-family:nanumgothic,dotum;
}
#stylized p{
	font-size:11px;
	color:#666666;
	margin-bottom:20px;
	border-bottom:solid 1px #b7ddf2;
	padding-bottom:10px;
	font-family:dotum;
}
#stylized label{
	display:block;
	font-weight:bold;
	text-align:right;
	width:20px;
	float:left;
	font-family:tahoma;
}
#stylized .small{
	color:#666666;
	display:block;
	font-size:11px;
	font-weight:normal;
	text-align:right;
	width:140px;
	font-family:dotum;
	letter-spacing:-1px;
}
#stylized input{
float:left;
font-size:12px;
padding:4px 2px;
border:solid 1px #aacfe4;
width:200px;
margin:2px 0 20px 10px;
}
#stylized button{
clear:both;
margin-left:150px;
width:125px;
height:31px;
text-align:center;
line-height:31px;
background-color:#000;
color:#FFFFFF;
font-size:11px;
font-weight:bold;
font-family:tahoma;
}
</style>
</head>
<body>
<div id="stylized" class="myform">
<h1>${inquire.id}님에게</h1>
<br>
        <form id="form" method="POST" enctype="multipart/form-data">  
<label class="small">제목 :</label> <input type="text" name="title" id="title"/>
<p><textarea cols="50" rows="30" name="repcontent" id="contents"></textarea></p>
<div class="spacer"></div>
		<input type="hidden" value="${inquire.ino}" name="ino" id="ino"> 
		<input type="hidden" value="N" name="readuser" id="readuser">
        </form>
        <button onclick="javascript:replyInquire();">답장하기</button>  
</div>
</body>
</html>