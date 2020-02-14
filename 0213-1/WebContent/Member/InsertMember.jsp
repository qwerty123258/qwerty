<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>Insert title here</title>
        <script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
    <script>
    function transfer(){
        var idreg = /^(?=.*[a-z])[A-Za-z\d]{6,20}$/;
        var id=document.getElementById("idinput").value;
        var passreg = /^(?=.*[a-z])(?=.*\d)[a-zA-Z\d$@$!%*#?&]{8,16}$/;
        var phonereg=/^\d{3}-\d{4}-\d{4}$/;
        var pass=document.getElementById("passinput").value;
        var pass2=document.getElementById("pass_checkinput").value;
        var phone=document.getElementById("inputphone").value;
        var idcheckresult=document.getElementById("idresult").value;
        if(pass.match(passreg) && id.match(idreg) && pass==pass2 && phone.match(phonereg)){
        	if(imgFileCheck()){
        		if(idcheckresult=="true"){
                	document.getElementById("formTrans").submit();
        		}
        		else if(idcheckresult==""){
        			alert("아이디 중복확인을 하셔야합니다.");
        		}
        	}
        }
        else if(pass!=pass2){
        	alert("비밀번호가 일치하지 않습니다.");
        }
        else{
        	alert("입력된 값이 올바르지 않습니다.");
        }
    }
    function checkID(){
    	if(addForm.id.value == "") {
    		alert("id를 입력하시오.");
    		addForm.id.focus();
    	} else {
    		{
    			url = "CheckID.jsp?id=" + addForm.id.value;
    			window.open(
    					url,
    					"아이디 중복확인", "toolbar=no, width=350, height=150, top=150, left=150");
    		}
    }
    }
    function imgFileCheck(){
        var imgfile=document.getElementById("picture").value;
    	if (imgfile != "") {
    	    var ext = imgfile.slice(imgfile.lastIndexOf(".") + 1).toLowerCase();
    	    if (!(ext == "gif" || ext == "jpg" || ext == "png" || ext == "bmp")) {
    	        alert("이미지파일 (.jpg, .png, .gif,.bmp ) 만 업로드 가능합니다.");
    	        return false;
    	}
    	    else{
    	    	return true;
    	    }
    }
    	else{
    		return false;
    	}
    }
    function idcheck(){
        var reg = /^(?=.*[a-z])[A-Za-z\d]{6,20}$/;
        var id=document.getElementById("idinput").value;
        if(id.match(reg)){
            document.getElementById("idtext").style.color="green";
            document.getElementById("idtext").innerHTML="사용가능"
        }
        if(!id.match(reg)){
            document.getElementById("idtext").style.color="red";
            document.getElementById("idtext").innerHTML="유효하지 않습니다."
        }
    }
    function passcheck(){
        var reg = /^(?=.*[a-z])(?=.*\d)[a-zA-Z\d$@$!%*#?&]{8,16}$/;
        var pass=document.getElementById("passinput").value;
        if(pass.match(reg)){
            document.getElementById("passtext").style.color="green";
            document.getElementById("passtext").innerHTML="사용가능"
        }
        if(!pass.match(reg)){
            document.getElementById("passtext").style.color="red";
            document.getElementById("passtext").innerHTML="유효하지 않습니다."
        }
    }
    function eminput(){
        var email=document.getElementById("emailvalue").value;
        document.getElementById("emailinput").value=email;
    }
    function passconfirm(){
        var pass=document.getElementById("passinput").value;
        var pass2=document.getElementById("pass_checkinput").value;
        if(pass==pass2){
            document.getElementById("passtext").style.color="green";
            document.getElementById("passtext").innerHTML="비밀번호가 일치합니다."
        }
        if(pass!=pass2){
            document.getElementById("passtext").style.color="red";
            document.getElementById("passtext").innerHTML="비밀번호가 일치하지 않습니다."
        }
    }
    function phonecheck(){
    	var phone=document.getElementById("inputphone").value;
    	var phonereg=/^\d{3}-\d{4}-\d{4}$/;
        if(phone.match(phonereg)){
            document.getElementById("phonetext").innerHTML="사용가능";
            document.getElementById("phonetext").style.color="green";
        }
        else if(!phone.match(phonereg)){
            document.getElementById("phonetext").innerHTML="유효하지 않습니다.";
            document.getElementById("phonetext").style.color="red";
        }
    }
    </script>
     <link rel="stylesheet" href="../css/login.css">
     <link rel="stylesheet" href="../css/create.css">
     <style>
     #checkid{
     width:80px;
     float:right;
     }
     </style>
    <link href="https://fonts.googleapis.com/css?family=Yeon+Sung&display=swap" rel="stylesheet">
</head>
<body>
<div class="header">
        <div class="top">
            <a href="Member/MemberBoardMain.jsp">
                <img id="logo" src="../images/logo.png">
            </a>
            <div class="mid">
            <form action="../MemberAdd" method="post" id="formTrans" name="addForm" enctype="multipart/form-data">
            <table>
                <tr>
                    <tr>
                        <td>
                            아이디<input id="checkid" type="button" onclick="checkID()" value="ID중복확인">
                        </td>
                    </tr>
                    <td>
                     <input type="text" name="id" id="idinput" placeholder="6~10자리 대소문자,숫자 포함" onkeyup="idcheck()">
                        <td>
                           <input id="idresult" name="checkidresult" type="hidden" value="">
                        </td>
                    </td>
                    <td>
                        <div id="idtext"></div>
                    </td>
                </tr>
                <tr>
                    <tr>
                        <td>
                            비밀번호
                        </td>
                    </tr>
                    <td>
                        <input type="password" name="password" id="passinput" placeholder="8~16자리 소문자,숫자 포함" onkeyup="passcheck()">
                    </td>
                    <td>
                        <div id="passtext"></div>
                    </td>
                </tr>
                <tr>
                <td>
                비밀번호 확인
                </td>
                <tr>
                <td>
            <input type="password" id="pass_checkinput" placeholder="비밀번호 확인" onkeyup="passconfirm()">
                </td>
                </tr>
                </tr>
                <tr>
                    <tr>
                        <td>
                            이름
                        </td>
                    </tr>
                    <td>
                        <input id="nameinput" name="name" type="text">
                    </td>
                </tr>
                <tr>
                    <tr>
                        <td>
                            생년월일
                        </td>
                    </tr>
                    <td>
                        <input type="date" name="birth">
                    </td>
                </tr>
                <tr>
                    <tr>
                        <td>
                            성별
                        </td>
                    </tr>
                    <td>
                        <select name="gender">
                            <option value="남성">남성</option>
                            <option value="여성">여성</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <tr>
                        <td>
                            본인확인 이메일
                        </td>
                    </tr>
                    <tr>
                    <td>
                        <input id="emailnameinput" name="emailID" type="text">
                        @
                        <input type="text" id="emailinput" name="emailDomain">
                    </td>
                    <td>
                        <select onchange="eminput()" id="emailvalue">
                            <option value="">직접입력</option>
                            <option value="naver.com">네이버</option>
                            <option value="hanmail.net">한메일</option>
                            <option value="gmail.com">구글</option>
                        </select>
                    </td>
                    </tr>
                 <tr>
                 <td>
                 	 주소
                 	 </td>
                 	 </tr>
                 	 <tr>
                    <td>
                        <input type="text" id="sample5_address" placeholder="주소" name="address">
                        <input type="button" onclick="sample5_execDaumPostcode10()" value="주소 검색">
                    </td>
                </tr>
                <tr>
                <td>
                휴대폰 번호
                </td>
                </tr>
                             <tr>
                <td>
              <input type="text" placeholder="-포함 입력!!" name="phone" id="inputphone" onkeyup="phonecheck()">
                </td>
                                    <td>
                        <div id="phonetext"></div>
                    </td>
                </tr>
                <tr>
                <td>
                프로필 사진 등록하기
                </td>
                </tr>
                <tr>
                <td>
                <input type="file" name="mempicture" id="picture">
                </td>
                </tr>
            </table>
         </form>
         </div>
         </div>
	</div>
	<div class="footer">
  		<button onclick="transfer()" class="btn">가입하기</button>
    </div>
</body>
<script>
function sample5_execDaumPostcode10() {
new daum.Postcode({
    oncomplete: function(data) {
        var addr = data.address; // 최종 주소 변수
        document.getElementById("sample5_address").value = addr;
    }
}).open();
}
</script>
</html>