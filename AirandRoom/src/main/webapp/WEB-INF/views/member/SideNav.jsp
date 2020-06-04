<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<link href='https://fonts.googleapis.com/css?family=Quicksand:400,700' rel='stylesheet' type='text/css' />
	<script type="text/javascript" src="https://code.jquery.com/jquery-2.0.0.min.js" /></script>
	
	<link href="https://netdna.bootstrapcdn.com/font-awesome/3.1.1/css/font-awesome.min.css" rel="stylesheet" />
<meta charset="UTF-8">
<title>마이페이지 좌측 메뉴</title>

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

<li><a href="checkMembers" class="green">정보수정</a></li>
<li><a href="emailModify" class="yellow">이메일 수정</a></li>
<c:if test="${sessionScope.kind eq 'admin'}">
<li><a href="myPage" class="yellow">요청된 숙소 및 노선 등록리스트 보기</a></li>
<li><a href="memberList?page=1" class="red">회원 전체 조회</a></li>
<li><a href="selectReport" class="green">신고내역 보기</a></li>
</c:if>
<c:if test="${sessionScope.kind eq 'room'}">
<li><a href="roomManagement" class="red">숙소 관리하기</a></li>
</c:if>
<c:if test="${sessionScope.kind eq 'airline'}">
<li><a href="airlineManagement" class="red">노선 관리하기</a></li>
</c:if>
<c:if test="${sessionScope.kind eq 'room'}">
<li><a href="myPage" class="green">문의글 보기</a></li>
</c:if>
<c:if test="${sessionScope.kind eq 'normal'}">
<li><a href="myReviewListForm?id=${sessionScope.id}&check=my" class="green">작성한 리뷰보기</a></li>
<li><a href="myInquireList?id=${sessionScope.id}&page=1" class="red">문의 내역 보기</a></li>
<li><a href="myPage" class="yellow">예약 리스트보기</a></li>
<li><a href="checkDelMembers" class="red">회원탈퇴</a></li>
</c:if>


</ul>

</body>
</html>