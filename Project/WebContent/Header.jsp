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
<style>
.header{
margin-left:50%;
margin-right:50%;
}
#logo{
width:250px;
height:250px;
}
.searchar{
text-align:center;
display:flex;
margin-left:50%;
margin-right:50%;
}
#search{
width:300px;
border-radius:10px;
}
input:focus {

outline:none;

}
#gosearch{
border-radius:10px;
border:none;
background-Color:skyblue;
}
</style>
<script>
$(document).ready(function() {
	$('#gosearch').click(function(){
		var keyword=$("#search").val();
		if(keyword.length<2){
			alert("검색어는 2자리 이상입니다.");
		}
		else{
			location.href="SearchList?keyword="+keyword;
		}
	})
})
</script>
<script>
function enterkey2(){
    if (window.event.keyCode == 13) { //로그인 버튼 말고 엔터키로 로그인 하는 경우
    	document.getElementById("gosearch").click();
   }
}
</script>
</head>
<body>
<a href="Main.jsp">
<div class="header">
<img id="logo" src="images/logo.png">
</div>
</a>
<div class="searchar">
<input type="text" id="search" placeholder="파일을 검색하세요" onkeyup="enterkey2()">
<button id="gosearch">검색</button>
</div>

</body>
</html>