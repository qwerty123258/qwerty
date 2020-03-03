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
<script>
$(document).ready(function() {
    $('#addbtn').click(
    		function fileadd() {
        if($('.fileList').length>2){
    	alert("파일을 3개 이상 첨부 하실 수 없습니다.");
    }
        else{
            $('#writeTable #addFileList').append(
                    '<tr><td class="fileList">'+
                    '   첨부 파일 : <input id="file['+ $('.fileList').length +']" type="file" name="bfile['+ $('.fileList').length +']" />'+
                    '	파일 가격 : <input id="price['+ $('.fileList').length +']" type="text" name="price">'+
                    '</td></tr>');
            $("#writebtn").attr('id','writebtn1'); 
            $("#addbtn").attr('id','addbtn1'); 
        }
    });
    $('#addbtn1').click(
    		function fileadd() {
        if($('.fileList').length>2){
    	alert("파일을 3개 이상 첨부 하실 수 없습니다.");
    }
        else{
            $('#writeTable #addFileList').append(
                    '<tr><td class="fileList">'+
                    '   첨부 파일 : <input id="file['+ $('.fileList').length +']" type="file" name="bfile['+ $('.fileList').length +']" />'+
                    '	파일 가격 : <input id="price['+ $('.fileList').length +']" type="text" name="price">'+
                    '</td></tr>');
            $("#writebtn1").attr('id','writebtn2'); 
            $("#addbtn1").attr('id','addbtn2'); 
        }
    });
    $('#addbtn2').click(
    		function fileadd() {
        if($('.fileList').length>2){
    	alert("파일을 3개 이상 첨부 하실 수 없습니다.");
    }
        else{
            $('#writeTable #addFileList').append(
                    '<tr><td class="fileList">'+
                    '   첨부 파일 : <input id="file['+ $('.fileList').length +']" type="file" name="bfile['+ $('.fileList').length +']" />'+
                    '	파일 가격 : <input id="price['+ $('.fileList').length +']" type="text" name="price">'+
                    '</td></tr>');
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
        	document.getElementById("writeForm").submit();
    	}
    });
    $('#writebtn1').click(function(){
	    var category = $('#category_id').val();
	    var title = $('#title_id').val();
	    var price = $('#price_id').val();
	    var file = $('#file_id').val();
	    var file1=document.getElementById("file[1]").value;
	    var price1=document.getElementById("price[1]").value;
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
	    else if(file1==''){
	    	alert('첨부파일이 비어있습니다.');
	    }
	    else if(price1==''){
	    	alert('가격은 필수입니다.');
	    }
	    else if(imgFileCheck()){
        	document.getElementById("writeForm").submit();
    	}
    });
    
    $('#writebtn1').click(function(){
	    var category = $('#category_id').val();
	    var title = $('#title_id').val();
	    var price = $('#price_id').val();
	    var file = $('#file_id').val();
	    var file1=document.getElementById("file[1]").value;
	    var price1=document.getElementById("price[1]").value;
	    var file2=document.getElementById("file[2]").value;
	    var price2=document.getElementById("price[2]").value;
	    if(category==''){
	    	alert('카테고리를 설정하세요');
	    }
	    else if(title==''){
	    	alert('제목이 비어있습니다.');
	    }
	    else if(file==''){
	    	alert('첨부파일이 비어있습니다.');
	    }
	    else if(price==''){
	    	alert('가격은 필수입니다.');
	    }
	    else if(file1==''){
	    	alert('첨부파일이 비어있습니다.');
	    }
	    else if(price1==''){
	    	alert('가격은 필수입니다.');
	    }
	    else if(file2==''){
	    	alert('첨부파일이 비어있습니다.');
	    }
	    else if(price2==''){
	    	alert('가격은 필수입니다.');
	    }
	    else if(imgFileCheck()){
        	document.getElementById("writeForm").submit();
    	}
    });
});
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
                        <jsp:include page="Header.jsp"></jsp:include>
<div class="container">
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
<form action="BoardWrite" method="post" id="writeForm" enctype="multipart/form-data">
<table id="writeTable">
<tr>
<td>
<select name="category" id="category_id">
<option value="">선택</option>>
  <option value="movie">영화</option>
  <option value="drama">드라마</option>
  <option value="util">유틸</option>
   <option value="other">기타</option>
</select>
    제목 <input type="text" name="title" id="title_id">
</td>
</tr>
<tr class="imgList">
<td>
사진 첨부 <input type="file" name="bimgfile" id="imgfile">
</td>
</tr>
<tr>
<td class="fileList">
파일 첨부 <input type="file" id="file_id" name="bfile[0]"> 파일 가격 <input id="price_id" type="text" name="price"> <input id="addbtn" type="button" value="파일추가">
</td>
</tr>
<tr id="addFileList">
</tr>
<tr>
<td>
내용 <br><textarea name="content" rows="10" cols="50"></textarea>
</td>
</tr>
</table>
</form>
<button id="writebtn">작성 완료</button>
        </div>
    </div>
</div>
  <jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>