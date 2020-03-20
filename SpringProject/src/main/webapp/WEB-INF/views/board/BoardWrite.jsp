<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<title>글 쓰기</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/se2/js/HuskyEZCreator.js" charset="utf-8"></script>
<script>
	$(document).ready(function() {
		$('#writebtn').click(function() {
			oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
			$('#writeForm').submit();
		});
	})
</script>
</head>
<body>
	<form action="Write" method="post" id="writeForm" enctype="multipart/form-data">
		제목 <input type="text" name="title"><br>
	     파일  <input type="file" multiple="multiple" name="bfile"> <br>
	     글 내용<br>
		<textarea name="contents" id="ir1" rows="10" cols="100"></textarea>
	</form>
	<button id="writebtn">글쓰기</button>
</body>
<script type="text/javascript">
	var oEditors = [];
	nhn.husky.EZCreator
			.createInIFrame({
				oAppRef : oEditors,
				elPlaceHolder : "ir1",
				sSkinURI : "${pageContext.request.contextPath}/resources/se2/SmartEditor2Skin.html",
		        htParams : {
		            // 툴바 사용 여부 (true:사용/ false:사용하지 않음)
		            bUseToolbar : true,            
		            // 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
		            bUseVerticalResizer : true,    
		            // 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
		            bUseModeChanger : true,
		        },
				fCreator : "createSEditor2"
		    });
</script>
</html>