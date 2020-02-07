<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <!-- JSTL JavaServer Page Standard Tag Library -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <link href="https://fonts.googleapis.com/css?family=Yeon+Sung&display=swap" rel="stylesheet">
    <script>
	function del(){
        if(confirm("삭제를 진행하시겠습니까?")){
            alert("삭제 진행");
            <c:forEach var="result" items="${member}">
            location.href="RemoveBlackList?id=${result.id}";
            </c:forEach>
        }
        else{
            alert("삭제 취소");
            location.href="LoginMain.jsp"
        }
	}
	function addBlackList(){
        if(confirm("블랙리스트에 추가하시겠습니까?")){
            alert("추가 진행");
            <c:forEach var="result" items="${member}">
            location.href="BlackList?id=${result.id}";
            </c:forEach>
        }
        else{
            alert("추가 취소");
            location.href="LoginMain.jsp"
        }
	}
    </script>
<style>
*{
    font-family: 'Yeon Sung', cursive;
}
td,th{
width:150px;
text-align:center;
padding:5px;
border:white solid 1px;
}
body{
background-Color:black;
color:white;
}
.btn{
    background-color: rgb(167,33,22);
    outline: none;
    border: none;
    color: white;
    text-align: center;
    padding: 10px;
    font-size: 15px;
    cursor: pointer;
    border-radius: 7px;
}
#home{
color:white;
}
a{
color:red;
}
</style>
</head>
<body>
<h2>상세 조회</h2>
<table>
<tr>
<th>
아이디
</th>
<th>
비밀번호
</th>
<th>
이름
</th>
<th>
생년월일
</th>
<th>
성별
</th>
<th>
이메일
</th>
<th>
블랙리스트
</th>
<th>
블랙리스트 추가
</th>
<th>
블랙리스트 제거
</th>
</tr>
<c:forEach var="result" items="${member}">
<tr>
<td>
${result.id}
</td>
<td>
${result.password}
</td>
<td>
${result.name}
</td>
<td>
${result.birth}
</td>
<td>
${result.gender}
</td>
<td>
${result.email}
</td>
<td>
${result.blacklist}
</td>
<td>
<a onclick="addBlackList()" href="#">추가</a>
</td>
<td>
<a onclick="del()" href="#">삭제</a>
</td>
</tr>
</c:forEach>
</table>
<br><br>
<a id="home" href="LoginMain.jsp">홈으로</a>
</body>
</html>