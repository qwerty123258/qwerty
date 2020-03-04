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
				<table class="table table-striped table-bordered">
				<thead>
				<tr>
				<th>
				글 번호
				</th>
				<th>
				글 제목
				</th>
				<th>
				파일 이름
				</th>
				<th>
				소모 비용
				</th>
				<th>
				다운로드 날짜
				</th>
				</tr>
				</thead>
				<c:forEach var="down" items="${down}">
				<tr>
				<td>
				${down.bno}
				</td>
								<td>
				${down.title}
				</td>
								<td>
				${down.bfile}
				</td>
								<td>
				${down.price}
				</td>
								<td>
				${down.writedate}
				</td>
				</tr>
				</c:forEach>
				
				</table>
        </div>
    </div>
  <jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>