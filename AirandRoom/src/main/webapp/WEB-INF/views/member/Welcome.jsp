<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>가입안내</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
<jsp:include page="../Header.jsp"></jsp:include><br>
<jsp:include page="../Nav.jsp"></jsp:include>
<div class="container">
  <h2>환영합니다.</h2>
  <p>사이트 이용목적에 따라 회원가입을 다르게 하실 수 있습니다.</p>
  <ul class="nav nav-tabs nav-justified">
    <li class="active"><a data-toggle="pill" href="#home">일반 사용자 회원가입</a></li>
    <li><a data-toggle="pill" href="#menu1">숙소 사업자 회원가입</a></li>
    <li><a data-toggle="pill" href="#menu2">항공사 회원가입</a></li>
  </ul>
  <div class="tab-content">
    <div id="home" class="tab-pane fade in active">
      <h3>일반 사용자 회원가입</h3>
      <p>일반 사용자는 전반적인 사이트의 이용이 가능합니다.</p>
      <a href="createMembers?kind=normal">회원가입</a>
    </div>
    <div id="menu1" class="tab-pane fade">
      <h3>숙소 사업자 회원가입</h3>
      <p>숙소 사업자로 회원가입을 하시게되면 일반 사용자에게는 제공되지 않은 숙소 등록,수정 등 숙소 제공 전반에 걸친 기능에 접근권한이 있습니다.</p>
      <a href="createMembers?kind=room">회원가입</a>
    </div>
    <div id="menu2" class="tab-pane fade">
      <h3>항공사 회원가입</h3>
      <p>항공사 회원가입을 하시게되면 노선 등록,일정 등록 및 수정 등 항공권 제공 전반에 걸친 기능에 접근권한이 있습니다.</p>
      <a href="createMembers?kind=airline">회원가입</a>
    </div>
  </div>
</div>
</body>
</html>