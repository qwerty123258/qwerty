<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<style>
#container {
  display: block;
  position: relative;
  padding-left: 35px;
  margin-top:10px;
  margin-bottom: 12px;
  cursor: pointer;
  font-size: 22px;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}

/* Hide the browser's default checkbox */
#container input {
  position: absolute;
  opacity: 0;
  cursor: pointer;
  height: 0;
  width: 0;
}

/* Create a custom checkbox */
.checkmark {
  position: absolute;
  top: 0;
  left: 0;
  height: 25px;
  width: 25px;
  background-color: #eee;
}

/* On mouse-over, add a grey background color */
#container:hover input ~ .checkmark {
  background-color: #ccc;
}

/* When the checkbox is checked, add a blue background */
#container input:checked ~ .checkmark {
  background-color: #2196F3;
}

/* Create the checkmark/indicator (hidden when not checked) */
.checkmark:after {
  content: "";
  position: absolute;
  display: none;
}

/* Show the checkmark when checked */
#container input:checked ~ .checkmark:after {
  display: block;
}

/* Style the checkmark/indicator */
#container .checkmark:after {
  left: 9px;
  top: 5px;
  width: 5px;
  height: 10px;
  border: solid white;
  border-width: 0 3px 3px 0;
  -webkit-transform: rotate(45deg);
  -ms-transform: rotate(45deg);
  transform: rotate(45deg);
}
</style>
<script>
function closePopup(name, value){
	var checkPopup;
	if($("input:checkbox[id='checkPopup']").is(":checked")){
		checkPopup=true;
	}
	else{
		checkPopup=false;
	}
	if(checkPopup){
		var todayDate = new Date();
		todayDate.setHours( 24 );
	    document.cookie = name + "=" + escape( value ) + "; path=/; expires=" +   todayDate.toGMTString() + ";";
	    self.close();
	}
	else{
	    self.close();
	}
}
function setPopup(){
	if($("input:checkbox[id='checkPopup']").prop("checked")==true){
		$("input:checkbox[id='checkPopup']").prop("checked", false);
	}
	else{
		$("input:checkbox[id='checkPopup']").prop("checked", true);
	}
}
</script>
</head>
<body>

<h2>공지사항</h2>
  <img style="width:400px;height:500px;" src="${pageContext.request.contextPath}/resources/img/_upload_mwEditor_202003_1584606528383_20200319172848.jpg">
  <br>
	코로나 사태 종식 희망!!<br><br>
	
	- Air & Room 일동<br><br>
  <div style="width:60%;">
    <label id="container"><a onclick="setPopup()" style="color:black;" href="#">하루동안 보지 않기.</a>
  <input type="checkbox" id="checkPopup">
  <span class="checkmark"></span>
</label>
  </div>
  <button style="float:right;" class="btn" onclick="closePopup('popup','no')">창 닫기</button>

</body>
</html>