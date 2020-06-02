<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 좌측 메뉴</title>
</head>
<body>
<div>
<div class="container">
<p><a href="checkMembers">정보수정</a></p>
<p><a href="emailModify">이메일 수정</a></p>
<c:if test="${sessionScope.kind eq 'admin'}">
<p><a href="myPage">요청된 숙소 및 노선 등록리스트 보기</a></p>
<p><a href="memberList?page=1">회원 전체 조회</a></p>
<p><a href="selectReport">신고내역 보기</a></p>
</c:if>
<c:if test="${sessionScope.kind eq 'room'}">
<p><a href="roomManagement">숙소 관리하기</a></p>
</c:if>
<c:if test="${sessionScope.kind eq 'airline'}">
<p><a href="airlineManagement">노선 관리하기</a></p>
</c:if>
<c:if test="${sessionScope.kind eq 'room'}">
<p><a href="myPage">문의글 보기</a></p>
</c:if>
<c:if test="${sessionScope.kind eq 'normal'}">
<p><a href="myReviewListForm?id=${sessionScope.id}&check=my">작성한 리뷰보기</a></p>
<p><a href="myInquireList?id=${sessionScope.id}&page=1">문의 내역 보기</a></p>
<p><a href="myPage">예약 리스트보기</a></p>
<p><a href="checkDelMembers">회원탈퇴</a></p>
</c:if>
</div>
</div>
</body>
</html>