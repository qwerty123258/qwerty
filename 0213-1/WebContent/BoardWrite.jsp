<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function writeboard(){
    var writer=document.getElementById("boardwriter").value;
    var password=document.getElementById("boardpassword").value;
    var title=document.getElementById("boardtitle").value;
	if(writer == "") {
		alert("작성자가 비어있습니다.");
		document.getElementById("boardwriter").focus();
	} 
	else if(password=="") {
		alert("비밀번호가 비어있습니다.");
		document.getElementById("boardpassword").focus();
}
	else if(title==""){
		alert("제목이 비어있습니다.");
		document.getElementById("boardtitle").focus();
	}
	else{
		if(bimgFileCheck()){
			document.getElementById("boardwrite").submit();
		}
	}
}
function bimgFileCheck(){
    var bimgfile=document.getElementById("bimgfile").value;
	if (bimgfile != "") {
	    var ext = bimgfile.slice(bimgfile.lastIndexOf(".") + 1).toLowerCase();
	    if (!(ext == "gif" || ext == "jpg" || ext == "png" || ext == "bmp")) {
	        alert("이미지파일 (.jpg, .png, .gif,.bmp ) 만 업로드 가능합니다.");
	        return false;
	}
	    else{
	    	return true;
	    }
}
	else{
		return true;
	}
}
</script>
</head>
<body>
<h2>
글 쓰기
</h2>
<form action="BoardWrite" method="post" id="boardwrite" enctype="multipart/form-data">
작성자 <input type="text" name="writer" id="boardwriter" value="${sessionScope.id}" readonly="true"><br>
비밀번호 <input type="password" name="bpassword" id="boardpassword"> <br><br>
제목
<input type="text" name="title" id="boardtitle"><br><br><br>
<textarea rows="10" cols="100" name="bcontent"></textarea><br>
사진 첨부 <input type="file" name="bimgfile" id="bimgfile">
파일 첨부 <input type="file" name="bfile" id="file">
</form>
<button onclick="writeboard()">작성완료</button>
<a href="LoginMain.jsp">홈</a>
<a href="PageList">글 목록보기</a>
</body>
</html>