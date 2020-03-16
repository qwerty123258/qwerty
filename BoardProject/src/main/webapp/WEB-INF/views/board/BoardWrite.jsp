<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>
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
	<form action="Write" method="post" id="writeForm">
		제목 <input type="text" name="title"><br> 작성자 <input
			type="text" name="id"><br> 글 내용<br>
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
				fCreator : "createSEditor2"
			});
</script>
</html>