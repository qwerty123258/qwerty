<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
                    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
.highlight{
background-color: yellow; 
color: black;
border-bottom:black solid 1px;
}
</style>
<script>
$(document).ready(function (){
	var keyword="${requestScope.keyword}";
	var result=$(".title:contains('"+keyword+"')");
	console.log(result.length);
	if(result.length>0){
		$(".title:contains('"+keyword+"')").each(function (){
			//타이틀 클래스에서 키워드를 포함하고 있으면 반복
		    var regex = new RegExp(keyword,'gi');
			//키워드를 검색함.
		    //gi 수정자는 문자열에서 모든 정규 표현식을 대소 문자를 구분하지 않고 검색하는 데 사용된다.
		    $(this).html( $(this).text().replace(regex, "<span class='highlight'>"+keyword+"</span>") )
		})
	}
		else{
			var html="<table><tr><td>검색결과가 없습니다.</td></tr></table>";
			$("#searchTable").html(html);
		}
});
function goBoard(bno,category){
	var bno=bno;
	var category=category
	location.href="BoardDetail?bno="+bno+"&category="+category+"&keyword=${requestScope.keyword}";
}
</script>
</head>
<body>
<jsp:include page="Header.jsp"></jsp:include>
    <div class="row">
        <div class="col-sm-12">
  <ul class="nav nav-pills nav-justified">
    <li class="active"><a href="Main.jsp">Home</a></li>
    <li><a href="MovieList">영화</a></li>
 	<li><a href="DramaList">드라마</a></li>
    <li><a href="UtilList">유틸</a></li>
    <li><a href="OtherList">기타</a></li>
  </ul>
        </div>
        <div class="col-sm-3">
                        <jsp:include page="SideNav.jsp"></jsp:include>
        </div>
        <div class="col-sm-9">
        <div id="searchTable">
                <table class="table table-striped table-bordered table-hover">
        <thead>
        <tr>
        <th>
        글 번호
        </th>
        <th>
        카테고리
        </th>
                <th>
        글 제목
        </th>
                <th>
        작성자
        </th>
                <th>
        작성일
        </th>
        <th>
			조회수
        </th>
        </tr>
        </thead>
        <tbody>
                <c:forEach var="board" items="${boardList}">
                <tr onclick="goBoard(${board.bno},'${board.category}')">
        <td>
        ${board.bno}
        </td>
                <td>
                ${board.category}
        </td>
                <td class="title">
                ${board.title}
        </td>
                <td>
                ${board.id}
        </td>
                <td>
                ${board.writedate}
        </td>
                <td>
                ${board.bview}
        </td>
        </tr>
        </c:forEach>
        </tbody>
        </table>
        </div>
				<c:url var="action" value="SearchList"/>
<div class="text-center">
    <ul class="pagination pagination-sm pager">
                <c:choose>
        <c:when test="${paging.page==paging.beginPage}">
 <li class="disabled"><a>처음으로</a></li>
        </c:when>
        <c:otherwise>
 <li><a href="${action}?page=1">처음으로</a></li>
        </c:otherwise>
    </c:choose>
     <c:choose>
      <c:when test="${paging.beginPage==1}">
            <li class="disabled" ><a>이전</a></li>
        </c:when>
        <c:otherwise>
   			 <li><a href="${action}?page=${paging.beginPage-1}">이전</a></li>
        </c:otherwise>
    </c:choose>
    <c:forEach begin="${paging.beginPage}" end="${paging.endPage}" step="1" var="page">
    <c:choose>
        <c:when test="${paging.page==page}">
           <li class="active"><a>${page}</a></li>
        </c:when>
        <c:otherwise>
            <li><a href="${action}?page=${page}">${page}</a></li>
        </c:otherwise>
    </c:choose>
    </c:forEach>
          <c:choose>
        <c:when test="${paging.endPage==paging.totalPage}">
            <li class="disabled"><a>다음</a></li>
        </c:when>
        <c:otherwise>
   			 <li><a href="${action}?page=${paging.endPage+1}">다음</a></li>
        </c:otherwise>
    </c:choose>
                <c:choose>
        <c:when test="${paging.page==paging.totalPage || paging.totalPage eq 0}">
 <li class="disabled"><a>끝으로</a></li>
        </c:when>
        <c:otherwise>
 <li><a href="${action}?page=${paging.totalPage}">끝으로</a></li>
        </c:otherwise>
    </c:choose>
</ul>
</div>
    	</div>
    </div>
  <jsp:include page="Footer.jsp"></jsp:include>
</body>
</body>
</html>