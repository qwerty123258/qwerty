<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
function updateboard(){
	if(bimgFileCheck()){
		document.getElementById("updateboard").submit();
	}
}
function bimgFileCheck(){
    var bimgfile=document.getElementById("imgfile").value;
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
글 수정하기
</h2>
<form action="BoardUpdate?bnum=${requestScope.bnum}&page=${requestScope.page}" method="post" id="updateboard" enctype="multipart/form-data">
제목 <input type="text" name="title" value="${requestScope.title}"><br>
<br>
<textarea rows="10" cols="100" name="bcontent">${requestScope.bcontent}</textarea><br>
첨부 사진 변경 : <input type="file" name="bimgfile" id="imgfile">
첨부 파일 변경 : <input type="file" name="bfile"><br>
</form>
<button onclick="updateboard()">수정하기</button>
<a href="BoardDetail?board=${requestScope.bnum}&page=${requestScope.page}">뒤로가기</a>
</body>
</html>