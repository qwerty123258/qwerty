<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <script
src="https://code.jquery.com/jquery-3.4.1.js"
integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
crossorigin="anonymous">
    </script>
<script>
$(document).ready(function() {
    $('#addFile').click(function() {
        var fileIndex = $('#fileview tr').children().length;
        if(fileIndex>4){
    	alert("파일을 5개 이상 첨부 하실 수 없습니다.");
    }
        else{
            $('#fileview').append(
                    '<tr class="fileList"><td>'+
                    '   첨부 파일 : <input type="file" name="bfiles['+ fileIndex +']" />'+
                    '</td></tr>');
        }
    });     
});
</script>
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
<form action="BoardUpdate?bnum=${requestScope.bnum}&page=${requestScope.page}&commentpage=${requestScope.commentpage}" method="post" id="updateboard" enctype="multipart/form-data">
제목 <input type="text" name="title" value="${requestScope.title}"><br>
<br>
<textarea rows="10" cols="100" name="bcontent">${requestScope.bcontent}</textarea><br>
첨부 사진 변경 : <input type="file" name="bimgfile" id="imgfile">
첨부파일 변경 :
    <table id="fileview">
        <tr class="fileList">
            <td>첨부 파일 : <input name="bfiles[0]" type="file" /><input id="addFile" type="button" value="파일 추가" /></td>
        </tr>
    </table>
</form>
<button onclick="updateboard()">수정하기</button>
<a href="BoardDetail?bnum=${requestScope.bnum}&page=${requestScope.page}&commentpage=${requestScope.commentpage}">뒤로가기</a>
</body>
</html>