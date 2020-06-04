<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>노선 관리 좌측 페이지</title>
<style>

	body {
		margin:0;
		padding:0;
		font-family:Quicksand;
		font-weight:700;
	}

	ul.form {
		position:relative;
		top:20px;
		background:#fff;
		width:260px;
		margin:auto;
		padding:0;
		list-style: none;
		overflow:hidden;
		
		-webkit-border-radius: 5px;
		-moz-border-radius: 5px;
		border-radius: 5px;	
		
		-webkit-box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
		-moz-box-shadow: 1px 1px 10px rgba(0, 0, 0, 0.1);
		box-shadow:  1px 1px 10px rgba(0, 0, 0, 0.1);	
	}

	.form li a {
		width:260px;
		padding-left:10px;
		height:50px;
		line-height:50px;
		display:block;
		overflow:hidden;
		position:relative;
		text-decoration:none;
		text-transform:uppercase;
		font-size:14px;
		color:#686868;
		
		-webkit-transition:all 0.2s linear;
		-moz-transition:all 0.2s linear;
		-o-transition:all 0.2s linear;
		transition:all 0.2s linear;			
	}

	.form li a:hover {
        width:100%;
		background:#efefef;
	}

	.form li a.green {
		border-left:5px solid #008747;
	}

	.form li a.yellow {
			border-left:5px solid #fecf54;
	}
		
	.form li a.red {
			border-left:5px solid #cf2130;
	}

	.form li:first-child a:hover, .form li:first-child a {
		-webkit-border-radius: 5px 5px 0 0;
		-moz-border-radius: 5px 5px 0 0;
		border-radius: 5px 5px 0 0;
	}

	.form li:last-child a:hover, .form li:last-child a {
		-webkit-border-radius: 0 0 5px 5px;
		-moz-border-radius: 0 0 5px 5px;
		border-radius: 0 0 5px 5px;
	}



</style>
</head>
<body>
<ul class="form" style="padding:0px;">
<li><a class='green' href="goCreateAirline">노선 등록하기</a></li>
<li><a class='yellow' href="selectAirline">노선 조회하기</a></li>
</ul>
</body>
</html>