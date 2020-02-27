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
        $('#writebtn').click(function(){
    	    if(imgFileCheck()){
            	document.getElementById("writeForm").submit();
        	}
        });
    })
    </script>
    <script>
function imgFileCheck(){
    var imgfile=document.getElementById("imgfile").value;
	if (imgfile != "") {
	    var ext = imgfile.slice(imgfile.lastIndexOf(".") + 1).toLowerCase();
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
<form action="BoardUpdate?beforeimg=${requestScope.bimgfile}&bno=${requestScope.bno}" method="post" id="writeForm" enctype="multipart/form-data">
<select name="category" id="category_id">
<option value="">선택</option>>
  <option value="movie" <c:if test="${requestScope.category eq 'movie'}"> selected </c:if> >영화</option>
  <option <c:if test="${requestScope.category eq 'drama'}"> selected </c:if>value="drama">드라마</option>
  <option <c:if test="${requestScope.category eq 'util'}"> selected </c:if>value="util">유틸</option>
   <option <c:if test="${requestScope.category eq 'other'}"> selected </c:if> value="other">기타</option>
</select>
제목 <input type="text" name="title" value="${requestScope.title}" id="title_id"><br><br>
첨부 파일 수정<br><br>
<c:forEach var="file" items="${fileList}">
${file.bfno}
${file.boriginfile}
<input type="hidden" name="bfno" value="${file.bfno}">
<input type="hidden" name="boriginfile" value="${file.boriginfile}">
<input type="hidden" name="bfile" value="${file.bfile}">
<input type=text name=price value=${file.price}>
다른 파일 변경시 <input type="file" name="bfile[${file.bfno-1}]"><br>
</c:forEach>
<br><br>
첨부 사진 수정<br><br>
${requestScope.bimgfile}
<input type="file" name="bimgfile" id="imgfile"><br><br><br>
내용 <br><textarea name="content" rows="10" cols="50">${requestScope.content}</textarea>
</form>
<button id="writebtn">작성 완료</button>
</body>
</html>