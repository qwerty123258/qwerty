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
                        <script
src="https://code.jquery.com/jquery-3.4.1.js"
integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
crossorigin="anonymous">
                        </script>
     <link rel="stylesheet" href="../css/create.css">
     <script type="text/javascript" src="../js/create.js"></script>
    <link href="https://fonts.googleapis.com/css?family=Yeon+Sung&display=swap" rel="stylesheet">
</head>
<body>
<div class="header">
        <div>
            <a id="logoar" href="../MemberBoardMain.jsp">
                <img id="logo" src="../images/logo.PNG">
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
                    <tr>
                    <td>
                     <input type="text" name="id" id="idinput" placeholder="6~20자리 소문자,숫자 포함" onkeyup="idcheck()">
                     <div id="idtext"></div>
                           <input id="idresult" name="checkidresult" type="hidden" value="">
                    </td>
                    </tr>
                <tr>
                    <tr>
                        <td>
                            비밀번호
                        </td>
                    </tr>
                    <tr>
                    <td>
                        <input type="password" name="password" id="passinput" placeholder="8~16자리 소문자,숫자 포함" onkeyup="passcheck()">
                        <div id="passtext"></div>
                    </td>
                </tr>
                <tr>
                <td>
                비밀번호 확인
                </td>
                </tr>
                <tr>
                <td>
            <input type="password" id="pass_checkinput" placeholder="비밀번호 확인" onkeyup="passconfirm()">
                </td>
                </tr>
                    <tr>
                        <td>
                            이름
                        </td>
                        </tr>
                        <tr>
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
                    <tr>
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
                    <tr>
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
                프로필 사진 등록하기 <input type="file" name="mempicture" id="picture">
                </td>
                </tr>
                <tr>
                <td>
                <img id="profileimg" src="fileUpload/${requestScope.mempicture}" width="130px" height="130px"><br>
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