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

function replyInquire(){
	var value1 = '${selectInquirePost.answer}';
	var value2 = 'Y';
	if(value1 == value2) {
		alert("이미 답변한 문의글입니다.");
		self.close();
	} else {
		var id = '${selectInquirePost.id}'; 
		var ino = ${selectInquirePost.ino};
		var popUrl = "replyInquireForm?&ino="+ino;	
		var popOption = "width=450, height=650, resizable=no, scrollbars=no, status=no;";
			window.open(popUrl,"",popOption);		
	} 
}

function check() {
	self.close();
}

function deleteInquire() {
	var test = '${selectInquirePost.answer}';
	var ino = '${selectInquirePost.ino}';
	var id = '${selectInquirePost.id}';
	var result = confirm("정말로 삭제하시겠습니까?");
	if (result && test == 'Y') {
		alert("답변한 문의글은 삭제할 수 없습니다.");
	}
	else if(result && test == 'N') {
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

<label class="small">제목 :</label> <input type="text" name="title" id="title" value="${selectInquirePost.title}" readonly/>

<p><textarea cols="50" rows="30" name="contents" id="contents" readonly>${selectInquirePost.contents}</textarea></p>

<div class="spacer"></div>
		<input type="hidden" value="${selectInquirePost.ino}" name="ino" id="ino">                  
                    
                    <button onclick="javascript:replyInquire();">답장하기</button>    
                    <button onclick="javascript:deleteInquire();">삭제하기</button>
                    <button onclick="javascript:check();">닫기</button>                                        
    </div>
        
                    
</div>
</body>
</html>