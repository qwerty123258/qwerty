<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>
<script>
	$(document).ready(function() {
		$('#writebtn').click(function() {
			oEditors.getById["ir1"].exec("UPDATE_CONTENTS_FIELD", []);
			var params = $("#writeForm").serialize();
			$.ajax({
				type : "POST",
				url : "Update",
				data : params,
				dataType : "text",
				success : function(result) {
					if (result == "true") {
						location.href = "BoardDetail?bno=" + ${board.bno}+"&page=" + ${page};
					} else if (result == "false") {
						alert('수정 실패');
					}
				},
				error : function() {
					alert("실패");
				}

			});
		});
		$('#cancelbtn').click(function() {
			location.href = "BoardDetail?bno=" + ${board.bno}+"&page=" + ${page};
		});
	})
</script>
</head>
<body>
	<form action="Update" method="post" id="writeForm">
		<input type="hidden" name="bno" value="${board.bno}"> 
		<input type="hidden" name="page" value="${page}"> 
		제목 <input type="text" name="title" value="${board.title}">
			<br> 글내용<br>
		<textarea name="contents" id="ir1" rows="10" cols="100">${board.contents}</textarea>
	</form>
	<button id="writebtn">수정 완료</button>
	<button id="cancelbtn">취소</button>
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