<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>


<script>
function sendReportForm(){
	var title = $("#title").val();
	var contents = $("#contents").val();
    var rfile = $("#rfile").val();
    var id = $("#id").val();
    var otherId = $("#otherId").val();
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
				url : "sendReportForm",
				data : formData,
				processData : false, 
				contentType : false,
		        dataType : "text",
		                success: function(result){
		                    alert("성공");
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

<br>

<h3>신고하기</h3>

        <form id="form" method="POST" enctype="multipart/form-data">  


<br>
      
<label class="small">제목 :</label> <input type="text" name="title" id="title" />

<p><textarea cols="50" rows="30" placeholder="내용을 입력해주세요." name="contents" id="contents"></textarea></p>

<label class="small">첨부파일 :</label> <input type="file" name="reportfile" id="rfile">


<div class="spacer"></div>

        <input type="hidden" value="${otherId}" name="otherid" id="otherid">		
        <input type="hidden" value="${id}" name="id" id="id"> 
		<input type="hidden" value="N" name="read" id="read">
        </form>
        
                    <button onclick="sendReportForm()">전송</button>
        
</div>


</body>
</html>