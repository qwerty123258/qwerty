<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<script>

function printClock() {
    
    var clock = document.getElementById("clock");            // ����� ��� ����
    var currentDate = new Date();                                     // ����ð�
    var calendar = currentDate.getFullYear() + "-" + (currentDate.getMonth()+1) + "-" + currentDate.getDate() // ���� ��¥
    var amPm = 'AM'; // �ʱⰪ AM
    var currentHours = addZeros(currentDate.getHours(),2); 
    var currentMinute = addZeros(currentDate.getMinutes() ,2);
    var currentSeconds =  addZeros(currentDate.getSeconds(),2);
    
    if(currentHours >= 12){ // �ð��� 12���� Ŭ �� PM���� ����, 12�� ����
    	amPm = 'PM';
    	currentHours = addZeros(currentHours - 12,2);
    }

    if(currentSeconds >= 50){// 50�� �̻��� �� ���� ��ȯ�� �ش�.
       currentSeconds = '<span style="color:#de1951;">'+currentSeconds+'</span>'
    }
    clock.innerHTML = "<div style='display:block;'><span style='font-size:50px;'>"+ amPm+"</span><br>"+currentHours+"<br>"+currentMinute+"<br>"+currentSeconds+"</div>"; //��¥�� ����� ��
    
    setTimeout("printClock()",1000);         // 1�ʸ��� printClock() �Լ� ȣ��
}

function addZeros(num, digit) { // �ڸ��� �����ֱ�
	  var zero = '';
	  num = num.toString();
	  if (num.length < digit) {
	    for (i = 0; i < digit - num.length; i++) {
	      zero += '0';
	    }
	  }
	  return zero + num;
}

</script>
<body>
<body onload="printClock()" >
	<div style="border:1px solid #dedede; float:right; width:100px; height:100px; line-height:100px; color:#666;font-size:50px; text-align:center;" id="clock">

	</div>
</body>

</html>