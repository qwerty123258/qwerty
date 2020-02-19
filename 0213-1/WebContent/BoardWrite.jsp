<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/write.js"></script>
     <link rel="stylesheet" href="css/write.css?after">
</head>
    <script
src="https://code.jquery.com/jquery-3.4.1.js"
integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
crossorigin="anonymous">
    </script>
<script>
$(document).ready(function() {
    $('#addFile').click(
    		function fileadd() {
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
<body>
<div>
            <a id="logoar" href="MemberBoardMain.jsp">
                <img id="logo" src="images/logo.PNG">
            </a>
</div>
<div id="mid">
           <div id="titlear">
            <h2>글 쓰기</h2>
            </div>
            <div id="writebtnar">
<button class="btn" onclick="writeboard()">작성완료</button>
</div>
</div>
<form action="BoardWrite" method="post" id="boardwrite" enctype="multipart/form-data">
<table>
<tr>
<th>
작성자
</th>
<td>
 <input type="text" name="writer" id="boardwriter" value="${sessionScope.id}" readonly="true">
</td>
</tr>
<tr>
<th>
비밀번호
</th>
<td>
<input type="password" name="bpassword" id="boardpassword">
</td>
</tr>
<tr>
<th>
제목
</th>
<td>
<input type="text" name="title" id="boardtitle">
</td>
</tr>
<tr>
<th>
글 내용
</th>
<td>
<textarea rows="10" cols="100" name="bcontent"></textarea>
</td>
</tr>
</table>
    <table id="imgview">
        <tr>
        <td>
                사진 첨부 : <input type="file" name="bimgfile" id="bimgfile">
        </td>
        </tr>
     </table>
    <table id="fileview">
        <tr class="fileList">
            <td>첨부 파일 : <input name="bfiles[0]" type="file" /><input id="addFile" type="button" value="파일 추가" /></td>
        </tr>
    </table>
</form>
</body>
</html>