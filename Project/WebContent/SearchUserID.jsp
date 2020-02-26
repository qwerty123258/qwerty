<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
src="https://code.jquery.com/jquery-3.4.1.js"
integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
crossorigin="anonymous">
</script>
<script>
$(document).ready(function(){
	$('#check').click(function() {
	    var reg =  /^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))-[1-4][0-9]{6}$/;
	    var name=$('#name_input').val();
	    var personno=$('#personno_input').val();
	    var personno2=$('#personno_input2').val();
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
function pnreg(){
    var reg =  /^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))-[1-4][0-9]{6}$/;
    var personno=$('#personno_input').val();
    var personno2=$('#personno_input2').val();
    var personno3=personno+"-"+personno2;
    if(personno3.match(reg)){
        document.getElementById("pntext").style.color="green";
        document.getElementById("pntext").innerHTML="사용가능"
    }
    if(!personno3.match(reg)){
        document.getElementById("pntext").style.color="red";
        document.getElementById("pntext").innerHTML="유효하지 않습니다."
    }
}
</script>
</head>
<body>
이름
<input type="text" id="name_input"><br>
주민등록번호<br>
<input type="text" id="personno_input" onkeyup="pnreg()">
-
<input type="password" id="personno_input2" onkeyup="pnreg()"><br>
<div id="pntext"></div>
<button id="check">확인</button>
</body>
</html>