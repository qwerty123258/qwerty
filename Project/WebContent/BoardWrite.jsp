<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script type="text/javascript" src="se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript" src="js/boardwrite.js"></script>
<script>
$(document).ready(function() {
    $('#addbtn').click(
   function fileadd() {
    if($('.fileList').length>1){
    	alert("파일을 3개 이상 첨부 하실 수 없습니다.");
    }
        else{
            $('#writeForm').append(
                    '<div class="col-sm-2 fileList">새 파일</div><div class="col-sm-10">'+
                    '   <input id="file['+ $('.fileList').length +']" type="file" name="bfile['+ $('.fileList').length +']" />'+
                    '	<input placeholder="파일 다운 포인트 지정" id="price['+ $('.fileList').length +']" type="text" name="price">'
                    + '</div>');
        }
    });
    $('#writebtn').click(function(){
	    var category = $('#category_id').val();
	    var title = $('#title_id').val();
	    var price = $('#price_id').val();
	    var file = $('#file_id').val();
	    if(category==''){
	    	alert('카테고리를 설정하세요');
	    }
	    else if(title==''){
	    	alert('제목이 비어있습니다.');
	    }
	    else if(price==''){
	    	alert('가격은 필수입니다.');
	    }
	    else if(file==''){
	    	alert('첨부파일이 비어있습니다.');
	    }
	    else if(imgFileCheck()){
	    	 oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
        	document.getElementById("writeForm").submit();
    	}
    });
})
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
    <li class="active"><a href="Main.jsp">Home</a></li>
    <li><a href="MovieList">영화</a></li>
 	<li><a href="DramaList">드라마</a></li>
    <li><a href="UtilList">유틸</a></li>
    <li><a href="OtherList">기타</a></li>
  </ul>
        </div>
        <div class="col-sm-3">
                        <jsp:include page="SideNav.jsp"></jsp:include>
        </div>
        <div class="col-sm-9">
 <div class="col-sm-12">
<form action="BoardWrite" method="post" id="writeForm" enctype="multipart/form-data">
<div class="col-sm-2">
<select name="category" id="category_id">
<option value="">선택</option>>
  <option value=영화>영화</option>
  <option value="드라마">드라마</option>
  <option value="유틸">유틸</option>
   <option value="기타">기타</option>
</select>
</div>
<div class="col-sm-9">
제목 : <input type="text" name="title" id="title_id">
</div>
<div class="col-sm-12">
<textarea name="ir1" id="ir1" rows="10" cols="100"></textarea>
</div>
<div class="col-sm-2">
사진 첨부
</div>
<div class="col-sm-10">
<input type="file" name="bimgfile" id="imgfile">
</div>
<div class="col-sm-2">
파일 첨부
</div>
<div class="col-sm-10 fileList">
<input type="file" id="file_id" name="bfile[0]">
<input id="price_id" type="text" name="price" placeholder="파일 다운 포인트 지정">
<input id="addbtn" type="button" value="파일추가">   
</div>
</form>
 <button id="writebtn">작성완료</button>
</div>
</div>
</div>
  <jsp:include page="Footer.jsp"></jsp:include>
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