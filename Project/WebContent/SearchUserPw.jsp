<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/searchuser.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script>
$(document).ready(function(){
	$('#Pwcheck').click(function() {
	     var id=$('#Pwid_input').val();
	     var email=$('#Pwemail_input').val();
	     var domain=$('#Pwdomain_input').val();
	     var email2=email+"@"+domain;
            $.ajax({
                type : "POST",
                  url : "SearchUserPw",
                  data : "id=" + id+ "&email=" + email2,
                  dataType : "text",
                success : function(data, textStatus, xhr) {
                     if (data == 'searchFail') {
                          alert('가입된 정보와 일치하지 않습니다. 다시 입력해주세요.');
                     }
                   else {
                 	  	alert('비밀번호 찾기 결과를 이메일로 발송하였습니다. \n 이메일을 확인해주세요.');
                        location.href = 'Main.jsp';
     	}
                },
     error : function(request, status, error) {
     alert("code:" + request.status + "\n" + "error:" + error);
     }
     })
	});
})
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
       <h4><strong>비밀번호를 찾으실 아이디와 비밀번호를 전송 받으실 이메일 주소를 입력해주세요.</strong></h4>
아이디<br>
<input type="text" id="Pwid_input"><br>
이메일 주소<br>
<input type="text" id="Pwemail_input">
@
<input type="text" id="Pwdomain_input">
<select onchange="Pwdomainselect()" id="Pwdomainvalue">
  <option value="">직접입력</option>
  <option value="naver.com">네이버</option>
  <option value="hanmail.net">한메일</option>
  <option value="gmail.com">구글</option>
</select>
<button id="Pwcheck">확인</button>
        </div>
    </div>
  <jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>