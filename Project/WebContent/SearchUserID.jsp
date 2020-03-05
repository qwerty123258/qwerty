<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<script>
$(document).ready(function(){
	$('#IDcheck').click(function() {
	    var reg =  /^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))-[1-4][0-9]{6}$/;
	    var name=$('#IDname_input').val();
	    var personno=$('#IDpersonno_input').val();
	    var personno2=$('#IDpersonno_input2').val();
	    var personno3=personno+"-"+personno2;
	    if(personno3.match(reg)){
            $.ajax({
                type : "POST",
                  url : "SearchUserID",
                  data : "name=" + name+ "&personno=" + personno3,
                  dataType : "text",
                success : function(data, textStatus, xhr) {
                     if (data == 'searchFail') {
                          alert('가입된 ID가 없습니다.');
                     }
                   else {
                 	  	alert("찾으신 ID는 "+ data);
                        location.href = 'Main.jsp';
     	}
                },
     error : function(request, status, error) {
     alert("code:" + request.status + "\n" + "error:" + error);
     }
     })
	    }
	    else{
	    	alert("형식에 맞지 않습니다.");
	    }
	});
})
function IDpnreg(){
    var reg =  /^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))-[1-4][0-9]{6}$/;
    var personno=$('#IDpersonno_input').val();
    var personno2=$('#IDpersonno_input2').val();
    var personno3=personno+"-"+personno2;
    if(personno3.match(reg)){
        document.getElementById("IDpntext").style.color="green";
        document.getElementById("IDpntext").innerHTML="사용가능"
    }
    if(!personno3.match(reg)){
        document.getElementById("IDpntext").style.color="red";
        document.getElementById("IDpntext").innerHTML="유효하지 않습니다."
    }
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
        <h4><strong>본인인증을 위하여 이름과 주민등록번호를 입력하여 주세요.</strong></h4>
이름<br>
<input type="text" id="IDname_input"><br>
주민등록번호<br>
<input type="text" id="IDpersonno_input" onkeyup="pnreg()">
-
<input type="password" id="IDpersonno_input2" onkeyup="IDpnreg()"><br>
<div id="IDpntext"></div>
<button id="IDcheck">확인</button>
        </div>
    </div>
  <jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>