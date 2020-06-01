<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

    <script src="https://code.jquery.com/jquery-3.4.1.js"
    integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
    crossorigin="anonymous">
    </script>

<script>

$(document).ready(function(){
    $("#mandu2").hide();
    $("#btn1").click(function(){
    $("#mandu1").remove();
    $("#stylized").append($("#mandu2"));
    $("#mandu2").show();   	
    });
});

function modifyInquireForm(){
	var title = $("#title").val();
	var contents = $("#contents").val();
    var ifile = $("#ifile").val();
    var read = '${selectReplyInquire.read}';
    var kind = '${kind}';
	if(title == "") {
		alert("제목을 입력해주세요.");
		$("#title").focus();
	} else if (contents == "") {
		alert("내용을 입력해주세요.");
		$("#contents").focus();		
	} else if (read=='Y') {
		alert("고객이 읽은 답장은 수정할 수 없습니다.");
	} else {
		var result = confirm("등록하시겠습니까?");
		if(result){
			var formData = new FormData(document.getElementById("form"));
			formData.append("kind",kind);
		    $.ajax({
      			type : "POST",
				url : "modifyInquireForm",
				data : formData,
				processData : false, 
				contentType : false,
		        dataType : "text",
		                success: function(result){
		                    alert("성공");
		        		    opener.location.reload();
		        			self.close();
		                }
		                });
		        }
		else{
		   alert("만두");			
		}
	}	
}

function deleteReplyInquire() {
	var ino = '${selectReplyInquire.ino}';
	var id = '${id}';
	var kind = '${kind}';
	var result = confirm("정말로 삭제하시겠습니까?");
    var read = '${selectReplyInquire.read}';
    if (read == 'Y') {
    	alert("고객이 읽은 답장은 삭제할 수 없습니다.");
    }
    else if (result) {
		alert("삭제 완료");
		location.href="deleteInquire?ino="+ino+"&id="+id+"&kind="+kind;
		self.close();
	    opener.location.reload();
	} else {
		return false;
	}
}

function check() {	
	self.close();
}
function check2() {
	history.go();
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
<h1>${selectReplyInquire.id}님의 답변입니다.</h1>
<br>

     <div id="mandu1">
        <form id="form" method="POST" enctype="multipart/form-data">  


<label class="small">제목 :</label> <input type="text" name="title" id="title" value="${selectReplyInquire.title}" readonly/>

<p><textarea cols="50" rows="30" name="contents" id="contents" readonly>${selectReplyInquire.contents}</textarea></p>

<label class="small">첨부파일 :</label> <input type="file" name="ifile" id="ifile">


<div class="spacer"></div>
		<input type="hidden" value="${selectReplyInquire.ino}" name="ino" id="ino"> 
        </form>
        
                    <button onclick="javascript:check();">확인완료</button>
        
                    <c:if test="${kind eq 'admin' || kind eq 'airline' || id eq 'room'}">
                    <button id="btn1">수정하기</button>
                    <button onclick="javascript:deleteReplyInquire();">삭제하기</button>
                    </c:if>
                    
     </div>
                    
</div>


     <div id="mandu2">
        <form id="form" method="POST" enctype="multipart/form-data">  


<label class="small">제목 :</label> <input type="text" name="title" id="title" value="${selectReplyInquire.title}"/>

<p><textarea cols="50" rows="30" name="contents" id="contents">${selectReplyInquire.contents}</textarea></p>

<label class="small">첨부파일 :</label> <input type="file" name="ifile" id="ifile">


<div class="spacer"></div>
		<input type="hidden" value="${selectReplyInquire.ino}" name="ino" id="ino">
		<input type="hidden" value="N" name="read" id="read">
		<input type="hidden" value="${selectReplyInquire.id}" name="id" id="id">
				 
        </form>
        
                    <c:if test="${kind eq'airline' || kind eq 'room'}">
                    <button onclick="javascript:check2();">취소하기</button>
                   
                    <button onclick="modifyInquireForm()" id="btn1">수정하기</button>
                    </c:if>
                    
     </div>

</body>
</html>