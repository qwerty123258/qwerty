<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
	<script>
	 $(document).ready(function() {
	var id='${sessionScope.id}';
	var html="";
	if(id!=""){
		$.ajax({
			type : "POST",
			url : "MyProfile",
			dataType : "json",
			success : function(result) {
					var profileimg=result.profileimg;
					var src="${pageContext.request.contextPath}/resources/fileUpload/"+profileimg;
					html+="<img style='width:300px; height:300px' src='"+src+"'>";
					html+="<div>파일 이름 : "+result.oriprofileimg+"</div>";
					html+="<form action='ProfileUpdate' id='updateForm' method='POST' enctype='multipart/form-data'>";
					html+="<input type='hidden' name='bforiprofileimg' value='"+result.oriprofileimg+"'>";
					html+="<input type='hidden' name='bfprofileimg' value='"+profileimg+"'>";
					html+="<input type='file' id='imgfile' name='mfile'>";
					html+="</form>";
					html+="<button onclick='change()'>변경하기</button>";
					html+="<button onclick='deleteProfile(\""+profileimg+"\")'>삭제하기</button>";
					$("#profile").html(html);
			},
			error : function() {
				alert("실패");
			}
	})	
	}
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
	function change(){
		if(imgFileCheck()){
			$("#updateForm").submit();	
		}
	}
	function deleteProfile(img){
		if(confirm("삭제하시겠습니까?")){
		      location.href="DeleteProfile?bfprofileimg="+img;
		}
	}
	</script>
</head>
<body>
<div id="profile">
</div>
</body>
</html>