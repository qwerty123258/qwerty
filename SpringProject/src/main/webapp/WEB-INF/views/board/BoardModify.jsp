<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
			$("#writeForm").submit();
		});
		$('#cancelbtn').click(function() {
			location.href = "BoardDetail?bno=" + ${board.bno}+"&page=" + ${page};
		});
	})
</script>
<script>
function fileDelete(bfno,bfile){
	var bfno=bfno;
	var bfile=bfile;
	$("#"+bfno).remove();
	var html="<input type='hidden' name='delfilename' value='"+bfile+"'>";
	$("#del").append(html);
}
</script>
</head>
<body>
	<form action="Update" method="post" id="writeForm" enctype="multipart/form-data">
		<input type="hidden" name="bno" value="${board.bno}"> 
		<input type="hidden" name="page" value="${page}"> 
		<input type="hidden" name="keyword" value="${keyword}"> 
		<input type="hidden" name="searchOpt" value="${searchOpt}"> 
		제목 <input type="text" name="title" value="${board.title}"><br><br>
		첨부파일 리스트<br><br>
				<c:forEach var="file" items="${fileList}">
					<input type="hidden" name="bfno" value="${file.bfno}">
					<input type="hidden" name="beforeFilename" value="${file.bfilename}">
				<div id="${file.bfno}">
					파일 이름 : ${file.bfileoriname} <a href="#" onclick="fileDelete(${file.bfno},'${file.bfilename}')">삭제</a>
				</div>
				<div id="del">
				
				</div>
		</c:forEach>
		<br><br>
		파일수정<br><br>
		<input type="file" multiple="multiple" name="bfile"><br>
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