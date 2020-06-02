<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>신고글 보기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
function selectReportPost(repno){
	
	var num = repno;
	var popUrl = "selectReportPost?repno="+num;	

	var popOption = "width=450, height=650, resizable=no, scrollbars=no, status=no;";    //팝업창 옵션(optoin)

		window.open(popUrl,"",popOption);

	}
</script>

<style>





table.type11 {
    border-collapse: separate;
    border-spacing: 1px;
    text-align: center;
    line-height: 1.5;
    margin: 20px 10px;
}
table.type11 th {
    width: 155px;
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    color: #fff;
    background: #EFE5E5 ;
}
table.type11 td {
    width: 155px;
    padding: 3px;
    vertical-align: top;
    border-bottom: 1px solid #ccc;
    background: #FAF8F8;
}
.button {
  background-color: #4CAF50;
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
}






        /* The Modal (background) */
        .modal {
            display: none; /* Hidden by default */
            position: fixed; /* Stay in place */
            z-index: 1; /* Sit on top */
            left: 0;
            top: 0;
            width: 100%; /* Full width */
            height: 100%; /* Full height */
            overflow: auto; /* Enable scroll if needed */
            background-color: rgb(0,0,0); /* Fallback color */
            background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
        }
    
        /* Modal Content/Box */
        .modal-content {
            background-color: #fefefe;
            margin: 15% auto; /* 15% from the top and centered */
            padding: 20px;
            border: 1px solid #888;
            width: 50%; /* Could be more or less, depending on screen size */                          
        }
        /* The Close Button */
        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }
        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }








.panel {
    background-color: #444;
    height: 34px;
    padding: 10px;
}
.panel a#login_pop, .panel a#join_pop {
    border: 2px solid #aaa;
    color: #fff;
    display: block;
    float: left;
    margin-left: 10px;
    padding: 5px 10px;
    text-decoration: none;
    text-shadow: 1px 1px #000;

    -webkit-border-radius: 10px;
    -moz-border-radius: 10px;
    -ms-border-radius: 10px;
    -o-border-radius: 10px;
    border-radius: 10px;
	font-family:'dotum';
}
a#login_pop:hover, a#join_pop:hover {
    border-color: #eee;
}
.overlay {
    background-color: rgba(0, 0, 0, 0.6);
    bottom: 0;
    cursor: default;
    left: 0;
    opacity: 0;
    position: fixed;
    right: 0;
    top: 0;
    visibility: hidden;
    z-index: 1;

    -webkit-transition: opacity .5s;
    -moz-transition: opacity .5s;
    -ms-transition: opacity .5s;
    -o-transition: opacity .5s;
    transition: opacity .5s;
}
.overlay:target {
    visibility: visible;
    opacity: 1;
}
.popup {
    background-color: #fff;
    border: 3px solid #fff;
    display: inline-block;
    left: 50%;
    opacity: 0;
    padding: 15px;
    position: fixed;
    text-align: justify;
    top: 40%;
    visibility: hidden;
    z-index: 10;

    -webkit-transform: translate(-50%, -50%);
    -moz-transform: translate(-50%, -50%);
    -ms-transform: translate(-50%, -50%);
    -o-transform: translate(-50%, -50%);
    transform: translate(-50%, -50%);

    -webkit-border-radius: 10px;
    -moz-border-radius: 10px;
    -ms-border-radius: 10px;
    -o-border-radius: 10px;
    border-radius: 10px;

    -webkit-box-shadow: 0 1px 1px 2px rgba(0, 0, 0, 0.4) inset;
    -moz-box-shadow: 0 1px 1px 2px rgba(0, 0, 0, 0.4) inset;
    -ms-box-shadow: 0 1px 1px 2px rgba(0, 0, 0, 0.4) inset;
    -o-box-shadow: 0 1px 1px 2px rgba(0, 0, 0, 0.4) inset;
    box-shadow: 0 1px 1px 2px rgba(0, 0, 0, 0.4) inset;

    -webkit-transition: opacity .5s, top .5s;
    -moz-transition: opacity .5s, top .5s;
    -ms-transition: opacity .5s, top .5s;
    -o-transition: opacity .5s, top .5s;
    transition: opacity .5s, top .5s;
}
.overlay:target+.popup {
    top: 50%;
    opacity: 1;
    visibility: visible;
}
.close {
    background-color: rgba(0, 0, 0, 0.8);
    height: 30px;
    line-height: 30px;
    position: absolute;
    right: 0;
    text-align: center;
    text-decoration: none;
    top: -15px;
    width: 30px;

    -webkit-border-radius: 15px;
    -moz-border-radius: 15px;
    -ms-border-radius: 15px;
    -o-border-radius: 15px;
    border-radius: 15px;
}
.close:before {
    color: rgba(255, 255, 255, 0.9);
    content: "X";
    font-size: 24px;
    text-shadow: 0 -1px rgba(0, 0, 0, 0.9);
}
.close:hover {
    background-color: rgba(64, 128, 128, 0.8);
}
.popup p, .popup div {
    margin-bottom: 10px;
}
.popup label {
    display: inline-block;
    text-align: left;
    width: 120px;
}
.popup input[type="text"], .popup input[type="password"] {
    border: 1px solid;
    border-color: #999 #ccc #ccc;
    margin: 0;
    padding: 2px;

    -webkit-border-radius: 2px;
    -moz-border-radius: 2px;
    -ms-border-radius: 2px;
    -o-border-radius: 2px;
    border-radius: 2px;
}
.popup input[type="text"]:hover, .popup input[type="password"]:hover {
    border-color: #555 #888 #888;
}


</style>
</head>
<body>
<jsp:include page="../Header.jsp"></jsp:include><br>
<jsp:include page="../Nav.jsp"></jsp:include><br>
	<div class="row">
<div class="col-xs-3">
<jsp:include page="../member/SideNav.jsp"></jsp:include>
</div>
<div class="col-xs-9">
<c:forEach items="${reportList}" var="result">
<table class="type11">
    <thead>
    <tr>
        <th>번호</th>
        <th>제목</th>
        <th>신고 접수 유무</th>
    </tr>
    </thead>
    <tr>
        <td>${result.repno}</td>
        <td><a onclick="javascript:selectReportPost('${result.repno}');">${result.title}</a></td>
        <td>
<c:choose>   
   <c:when test="${result.acceptreport eq 'Y'}">
       <h4>처리완료</h4>
   </c:when>         
   <c:when test="${result.read eq 'Y'}">
       <h4>신고 미처리</h4>
   </c:when>
      <c:when test="${result.read eq 'N'}">
       <h4>접수 대기중</h4>
   </c:when>
</c:choose>
         </td>
    </tr>
</table>
</c:forEach>
			<c:url var="action" value="/selectReport" />
			<div class="text-center">
				<ul class="pagination pagination-sm pager">
					<c:choose>
						<c:when test="${paging.page==paging.beginPage}">
							<li class="disabled"><a>처음으로</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${action}?page=1&otherid=${id}&kind=${kind}">처음으로</a></li>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when test="${paging.beginPage==1}">
							<li class="disabled"><a>이전</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${action}?page=${paging.beginPage-1}&otherid=${id}&kind=${kind}">이전</a></li>
						</c:otherwise>
					</c:choose>
					<c:forEach begin="${paging.beginPage}" end="${paging.endPage}"
						step="1" var="page">
						<c:choose>
							<c:when test="${paging.page==page}">
								<li class="active"><a>${page}</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="${action}?page=${page}&otherid=${id}&kind=${kind}">${page}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<c:choose>
						<c:when test="${paging.endPage==paging.totalPage}">
							<li class="disabled"><a>다음</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${action}?page=${paging.endPage+1}&otherid=${id}&kind=${kind}">다음</a></li>
						</c:otherwise>
					</c:choose>
					<c:choose>
						<c:when
							test="${paging.page==paging.totalPage || paging.totalPage eq 0}">
							<li class="disabled"><a>끝으로</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="${action}?page=${paging.totalPage}&otherid=${id}&kind=${kind}">끝으로</a></li>
						</c:otherwise>
					</c:choose>
				</ul>
			</div>
</div>
</div>
			



</body>
</html>