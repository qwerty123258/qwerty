<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <script type="text/javascript" src="js/formpractice.js">
    </script>
    <script>
    function transfer(){
        var idreg = /^(?=.*[a-z])[A-Za-z\d]{6,20}$/;
        var id=document.getElementById("idinput").value;
        var passreg = /^(?=.*[a-z])(?=.*\d)[a-zA-Z\d$@$!%*#?&]{8,16}$/;
        var pass=document.getElementById("passinput").value;
        var pass2=document.getElementById("pass_checkinput").value;
        if(pass.match(passreg) && id.match(idreg) && pass==pass2){
        	document.getElementById("formTrans").submit();
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
    </script>
     <link rel="stylesheet" href="css/login.css">
     <link rel="stylesheet" href="css/form.css">
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
            <a href="Main.jsp">
                <img id="logo" src="images/logo.png">
            </a>
            <div class="mid">
            <form action="MemberAdd" method="post" id="formTrans" name="addForm">
            <table>
                <tr>
                    <tr>
                        <td>
                            아이디<input id="checkid" type="button" onclick="checkID()" value="ID중복확인">
                        </td>
                    </tr>
                    <td>
                     <input type="text" name="id" id="idinput" placeholder="6~10자리 대소문자,숫자 포함" onfocus="idinputfocus()" onblur="idinputblur()" onkeyup="idcheck()">
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
                        <input type="password" name="password" id="passinput" placeholder="8~16자리 소문자,숫자 포함" onfocus="passinputfocus()" onblur="passinputblur()" onkeyup="passcheck()">
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
            <input type="password" id="pass_checkinput" placeholder="비밀번호 확인" onfocus="passcheckinputfocus()" onblur="passcheckinputblur()" onkeyup="passconfirm()">
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
                        <input id="nameinput" name="name"onfocus="nameinputfocus()" onblur="nameinputblur()" type="text">
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
                    <td>
                        <input id="emailnameinput" name="emailID" onfocus="emailinputfocus()" onblur="emailinputblur()" type="text">
                    </td>
                    <td>
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
            </table>
         </form>
         </div>
         </div>
	</div>
	<div class="footer">
  		<button onclick="transfer()" class="btn">가입하기</button>
    </div>
</body>
</html>