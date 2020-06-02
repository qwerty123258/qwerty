<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개별 문의글 보는 페이지입니다.</title>
    <script src="https://code.jquery.com/jquery-3.4.1.js"
    integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
    crossorigin="anonymous">
    </script>
    
<script>

function check() {
	self.close();
}


$(document).ready(function(){
    $("#mandu2").hide();
    $("#btn1").click(function(){
    	var test = '${inquire.read}';
    	if (test == 'Y') {
    		alert("상대가 이미 열람하였으므로 수정이 불가능합니다.");
    	} 
    	else {
    		$("#mandu1").remove();
    	    $("#stylized").append($("#mandu2"));
    	    $("#mandu2").show();
    	}
    });
});

function deleteInquire() {
	var test = '${inquire.read}';
	var ino = '${inquire.ino}';
	var id = '${inquire.id}';
	var result = confirm("정말로 삭제하시겠습니까?");
	if (result && test == 'Y') {
		alert("상대가 이미 글을 열람하여 문의글을 삭제 할 수 없습니다.");
	} else if(result && test == 'N') {
		$.ajax({
			url : "deleteInquire",
			data : "ino="+ino,
			dataType:"text",
			type : "GET",
			success : function(data){
				if(data=="Success"){
					self.close();
				    opener.location.reload();
				}
				else if(data=="Fail"){
					alert("문의글 삭제 실패");
				}
			},
			error :  function(){
				alert("문의글 삭제중 에러 발생");
			}
		});
	} else {
		return false;
	}
}


function modifyInquireForm(){
	var title = $("#title").val();
	var contents = $("#contents").val();
    var ifile = $("#ifile").val();
    var read = '${inquire.read}';
	if(title == "") {
		alert("제목을 입력해주세요.");
		$("#title").focus();
	} else if (contents == "") {
		alert("내용을 입력해주세요.");
		$("#contents").focus();		
	}
	else if(contents.length>500){
		alert("내용은 500자 이하로 입력해주세요.");
		$("#contents").focus();	
	}
	else if (read == 'Y'){
		alert("상대가 이미 문의글을 열람하였으므로 수정이 불가능합니다.")
	}
		else {
			var result = confirm("등록하시겠습니까?");
		
			if(result){
				var formData = new FormData(document.getElementById("form"));
				formData.append("read",read);
			    $.ajax({
	      			type : "POST",
					url : "modifyInquireForm",
					data : formData,
					processData : false, 
					contentType : false,
			        dataType : "text",
			                success: function(result){
			                	if(result>0){
				                    alert("성공");
				        			self.close();
				        		    opener.location.reload();
			                	}
			                	else{
			                		alert("수정 실패");
			                	}
			                },
			                error : function(){
			                	alert("수정 중 에러 발생");
			                }
			                });
			}
	}	
}
function contentlength(){
	$("#contentslength").html($("#contents").val().length+"/500");
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
<h1>${selectInquirePost.id}님의 문의 글</h1>
<br>
   <div id="mandu1">
<label class="small">제목 :</label> <input type="text" name="title" id="title" value="${inquire.title}" readonly/>
<p><textarea cols="50" rows="30" name="contents" id="contents" readonly>${inquire.contents}</textarea></p>
<div class="spacer"></div>                           
                    <button id="btn1">수정하기</button>   
                    <button onclick="javascript:deleteInquire();">삭제하기</button>
                    <button onclick="javascript:check();">닫기</button>                                        
    </div>                 
</div>
   <div id="mandu2">
<form id="form" method="POST" enctype="multipart/form-data">  
<label class="small">제목 :</label> <input type="text" name="title" id="title" value="${inquire.title}"/>
<p><textarea cols="50" onkeyup="contentlength()" rows="30" name="contents" id="contents">${inquire.contents}</textarea></p>
<div id="contentslength" style="height:20px; text-align:center;">0/500</div>
<label class="small">첨부파일 :</label> <input type="file" name="ifile" id="ifile">
<div class="spacer"></div>
		<input type="hidden" value="${inquire.ino}" name="ino" id="ino"> 
		<input type="hidden" value="admin" name="otherid" id="otherid">
        </form>      
        <button onclick="modifyInquireForm()">수정하기</button>
    </div>
</body>
</html>