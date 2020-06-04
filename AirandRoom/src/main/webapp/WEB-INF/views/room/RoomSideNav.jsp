<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>숙소관리 좌측 메뉴</title>
	<link href='https://fonts.googleapis.com/css?family=Quicksand:400,700' rel='stylesheet' type='text/css' />
	
	<link href="https://netdna.bootstrapcdn.com/font-awesome/3.1.1/css/font-awesome.min.css" rel="stylesheet" />
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
<li><a class='green' href="roomsRegForm">숙소 등록하기</a></li>
<li><a class='yellow' href="myRoomsList?page=1">숙소 조회하기</a></li>
<li><a class='red' href="selectMyRoombk?page=1">예약 리스트</a></li>
</ul>
</body>
</html>