<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
                    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script>
    $(document).ready(function() {
        $('#writebtn').click(function(){
    	    if(imgFileCheck()){
   	    	 oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
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
<style>
#writebtn{
padding:10px;
border:none;
outline:none;
margin-left:45%;
margin-right:45%;
}

</style>
</head>
<body>
                        <jsp:include page="Header.jsp"></jsp:include>
    <div class="row">
        <div class="col-sm-12">
                  <ul class="nav nav-pills nav-justified">
      		<li><a href="Main.jsp">Home</a></li>
    <c:choose>
        <c:when test="${requestScope.category eq 'movie'}">
    		<li class="active"><a href="MovieList">영화</a></li>
        </c:when>
        <c:otherwise>
    		<li><a href="MovieList">영화</a></li>
        </c:otherwise>
    </c:choose>
        <c:choose>
        <c:when test="${requestScope.category eq 'drama'}">
 	<li class="active"><a href="DramaList">드라마</a></li>
        </c:when>
        <c:otherwise>
 	<li><a href="DramaList">드라마</a></li>
        </c:otherwise>
    </c:choose>
        <c:choose>
        <c:when test="${requestScope.category eq 'util'}">
    <li class="active"><a href="UtilList">유틸</a></li>
        </c:when>
        <c:otherwise>
    <li><a href="UtilList">유틸</a></li>
        </c:otherwise>
    </c:choose>
        <c:choose>
        <c:when test="${requestScope.category eq 'other'}">
    <li class="active"><a href="OtherList">기타</a></li>
        </c:when>
        <c:otherwise>
    <li><a href="OtherList">기타</a></li>
        </c:otherwise>
    </c:choose>
  </ul>
        </div>
        <div class="col-sm-3">
                                <jsp:include page="SideNav.jsp"></jsp:include>
        </div>
        <div class="col-sm-9">
        <form action="BoardUpdate?beforeimg=${requestScope.bimgfile}&bno=${requestScope.bno}" method="post" id="writeForm" enctype="multipart/form-data">
<select name="category" id="category_id">
<option value="">선택</option>>
  <option value="movie" <c:if test="${requestScope.category eq 'movie'}"> selected </c:if> >영화</option>
  <option <c:if test="${requestScope.category eq 'drama'}"> selected </c:if>value="drama">드라마</option>
  <option <c:if test="${requestScope.category eq 'util'}"> selected </c:if>value="util">유틸</option>
   <option <c:if test="${requestScope.category eq 'other'}"> selected </c:if> value="other">기타</option>
</select>
제목 <input type="text" name="title" value="${requestScope.title}" id="title_id"><br><br>
첨부 파일 수정<br>
<c:forEach var="file" items="${fileList}">
${file.bfno}
${file.boriginfile}
<input type="hidden" name="bfno" value="${file.bfno}">
<input type="hidden" name="boriginfile" value="${file.boriginfile}">
<input type="hidden" name="bfile" value="${file.bfile}">
<input type=text name=price value=${file.price}>
다른 파일 변경시 <input type="file" name="bfile[${file.bfno-1}]"><br>
</c:forEach>
<br>
첨부 사진 수정<br>
${requestScope.bimgfile}
<input type="file" name="bimgfile" id="imgfile"><br>
<textarea name="ir1" id="ir1" rows="10" cols="100">${requestScope.content}</textarea>
</form>
<button id="writebtn">작성 완료</button>
        </div>
    </div>
</body>
<script type="text/javascript">
var oEditors = [];
nhn.husky.EZCreator.createInIFrame({
 oAppRef: oEditors,
 elPlaceHolder: "ir1",
 sSkinURI: "se2/SmartEditor2Skin.html",
 fCreator: "createSEditor2"
});
</script>
</html>