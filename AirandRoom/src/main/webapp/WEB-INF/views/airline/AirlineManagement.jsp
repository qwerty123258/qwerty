<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>노선관리</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>
<script src="https://code.highcharts.com/modules/export-data.js"></script>
<script src="https://code.highcharts.com/modules/accessibility.js"></script>


<style>




.highcharts-figure, .highcharts-data-table table {
    min-width: 360px; 
    max-width: 800px;
    margin: 1em auto;
}

.highcharts-data-table table {
	font-family: Verdana, sans-serif;
	border-collapse: collapse;
	border: 1px solid #EBEBEB;
	margin: 10px auto;
	text-align: center;
	width: 100%;
	max-width: 500px;
}
.highcharts-data-table caption {
    padding: 1em 0;
    font-size: 1.2em;
    color: #555;
}
.highcharts-data-table th {
	font-weight: 600;
    padding: 0.5em;
}
.highcharts-data-table td, .highcharts-data-table th, .highcharts-data-table caption {
    padding: 0.5em;
}
.highcharts-data-table thead tr, .highcharts-data-table tr:nth-child(even) {
    background: #f8f8f8;
}
.highcharts-data-table tr:hover {
    background: #f1f7ff;
}




        /* The Modal (background) */
        .modal {
            display: none; /* Hidden by default */
            position: fixed; /* Stay in place */
            z-index: 1; /* Sit on top */
            left: 0;
            top: 0;
            width: 100%; /* Full width */
            height: 100%; /* Full height */
            overflow: auto; /* Enable scroll if needed */
            background-color: rgb(0,0,0); /* Fallback color */
            background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
        }
    
        /* Modal Content/Box */
        .modal-content {
            background-color: #fefefe;
            margin: 15% auto; /* 15% from the top and centered */
            padding: 20px;
            border: 1px solid #888;
            width: 50%; /* Could be more or less, depending on screen size */                          
        }
        /* The Close Button */
        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }
        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }








.panel {
    background-color: #444;
    height: 34px;
    padding: 10px;
}
.panel a#login_pop, .panel a#join_pop {
    border: 2px solid #aaa;
    color: #fff;
    display: block;
    float: left;
    margin-left: 10px;
    padding: 5px 10px;
    text-decoration: none;
    text-shadow: 1px 1px #000;

    -webkit-border-radius: 10px;
    -moz-border-radius: 10px;
    -ms-border-radius: 10px;
    -o-border-radius: 10px;
    border-radius: 10px;
	font-family:'dotum';
}
a#login_pop:hover, a#join_pop:hover {
    border-color: #eee;
}
.overlay {
    background-color: rgba(0, 0, 0, 0.6);
    bottom: 0;
    cursor: default;
    left: 0;
    opacity: 0;
    position: fixed;
    right: 0;
    top: 0;
    visibility: hidden;
    z-index: 1;

    -webkit-transition: opacity .5s;
    -moz-transition: opacity .5s;
    -ms-transition: opacity .5s;
    -o-transition: opacity .5s;
    transition: opacity .5s;
}
.overlay:target {
    visibility: visible;
    opacity: 1;
}
.popup {
    background-color: #fff;
    border: 3px solid #fff;
    display: inline-block;
    left: 50%;
    opacity: 0;
    padding: 15px;
    position: fixed;
    text-align: justify;
    top: 40%;
    visibility: hidden;
    z-index: 10;

    -webkit-transform: translate(-50%, -50%);
    -moz-transform: translate(-50%, -50%);
    -ms-transform: translate(-50%, -50%);
    -o-transform: translate(-50%, -50%);
    transform: translate(-50%, -50%);

    -webkit-border-radius: 10px;
    -moz-border-radius: 10px;
    -ms-border-radius: 10px;
    -o-border-radius: 10px;
    border-radius: 10px;

    -webkit-box-shadow: 0 1px 1px 2px rgba(0, 0, 0, 0.4) inset;
    -moz-box-shadow: 0 1px 1px 2px rgba(0, 0, 0, 0.4) inset;
    -ms-box-shadow: 0 1px 1px 2px rgba(0, 0, 0, 0.4) inset;
    -o-box-shadow: 0 1px 1px 2px rgba(0, 0, 0, 0.4) inset;
    box-shadow: 0 1px 1px 2px rgba(0, 0, 0, 0.4) inset;

    -webkit-transition: opacity .5s, top .5s;
    -moz-transition: opacity .5s, top .5s;
    -ms-transition: opacity .5s, top .5s;
    -o-transition: opacity .5s, top .5s;
    transition: opacity .5s, top .5s;
}
.overlay:target+.popup {
    top: 50%;
    opacity: 1;
    visibility: visible;
}
.close {
    background-color: rgba(0, 0, 0, 0.8);
    height: 30px;
    line-height: 30px;
    position: absolute;
    right: 0;
    text-align: center;
    text-decoration: none;
    top: -15px;
    width: 30px;

    -webkit-border-radius: 15px;
    -moz-border-radius: 15px;
    -ms-border-radius: 15px;
    -o-border-radius: 15px;
    border-radius: 15px;
}
.close:before {
    color: rgba(255, 255, 255, 0.9);
    content: "X";
    font-size: 24px;
    text-shadow: 0 -1px rgba(0, 0, 0, 0.9);
}
.close:hover {
    background-color: rgba(64, 128, 128, 0.8);
}
.popup p, .popup div {
    margin-bottom: 10px;
}
.popup label {
    display: inline-block;
    text-align: left;
    width: 120px;
}
.popup input[type="text"], .popup input[type="password"] {
    border: 1px solid;
    border-color: #999 #ccc #ccc;
    margin: 0;
    padding: 2px;

    -webkit-border-radius: 2px;
    -moz-border-radius: 2px;
    -ms-border-radius: 2px;
    -o-border-radius: 2px;
    border-radius: 2px;
}
.popup input[type="text"]:hover, .popup input[type="password"]:hover {
    border-color: #555 #888 #888;
}



.table4_3 table {
font-size:large;
}
.table4_3 th {
	background-color:#87CEFA;
	color:#000000
}
.table4_3,.table4_3 th,.table4_3 td {
	font-size:0.95em;
	text-align:center;
	padding:4px;
	border-collapse:collapse;
}
.table4_3 th,.table4_3 td {
	border: 1px solid #bae3fc;
	border-width:1px 0 1px 0
}
.table4_3 tr {
	border: 1px solid #bae3fc;
}
.table4_3 tr:nth-child(odd){
	background-color:#d7eefd;
}
.table4_3 tr:nth-child(even){
	background-color:#fdfdfd;
}
</style>

</head>
<script>
$(document).ready(function(){
	defaultYear(${yearList}[0]);
});
</script>
<body>
<jsp:include page="../Header.jsp"></jsp:include><br>
	<jsp:include page="../Nav.jsp"></jsp:include>
	<div class="row">
		<div class="col-xs-3">
		<jsp:include page="AirlineSideNav.jsp"></jsp:include>
		</div>
		<div class="col-xs-9">
		<figure class="highcharts-figure">
    <div id="container">
       
    </div>
    <p class="highcharts-description">
 
    </p>
</figure>
<select onchange="year()" name="year">
    <option value="">년도 선택</option>
	<c:forEach var="year" items="${yearList}">
	<option value="${year}">${year}</option>
	</c:forEach>
</select>
<script>
function defaultYear(year) {
	$.ajax({
		type : "GET",
		url : "getAirlineMonth",
		data : "year=" + year,
		dataType : "json",
		success : function(result) {
			
			var length = result.length;
			for(i=0; i<length; i++) {
				graphInfo(result[0],year);
			}
		},
		error : function() {
			alert("통신 실패");
		}
	});

}

function year() {
	var year =  $("select[name='year']").val();
	$("select[name='month']").val("");
	$.ajax({
		type : "GET",
		url : "getAirlineMonth",
		data : "year=" + year,
		dataType : "json",
		success : function(result) {
			
			var length = result.length;
			var output = "";
			var output 
			for(i=0; i<length; i++) {
       	    	var output = "";
       			output +="<select onchange='graphInfo("+result[i]+","+year+")' name='month'>";
       			output +="<option value='' selected disabled >"+"월 별로 보기"+"</option>";
       			for (var i = 0; i < length; i++) {
           		output += "<option value='"+result[i]+"'>"+result[i]+"</option>";
       			}
           		output += "</select>"; 
       			$("#month").html(output);
			}
		},
		error : function() {
			alert("통신 실패");
		}
	});
}



</script>
<div id="month">

</div>

       <a href="#" class="overlay" id="info" onClick='history.back()'></a>
        <div id ="popup" class="popup">

            <a class='close' onClick='history.back()'></a>
        </div>
		</div>
	</div>
</body>
    <script>
	var getDay;
	var getPrice;
    function graphInfo(getMonth,year){
    	
    		$.ajax({
    			type : "GET",
    			url : "getAirlinePrice",
    			data : "month=" + getMonth +
    			       "&year=" + year,
    			dataType : "json",
    			async:false,
    			success : function(result) {
    				getPrice=result;
    			},
    			error : function() {
    				alert("통신 실패");
    			}
    		});
    		$.ajax({
    			type : "GET",
    			url : "getAirlineDay",
    			data : "month=" + getMonth +
    			       "&year=" + year,
    			dataType : "json",
    			async:false,
    			success : function(result) {
    				getDay=result;
    				console.log(getMonth,getDay,getPrice);
    				graph(getMonth,getDay,getPrice,year);
    			},
    			error : function() {
    				alert("통신 실패");
    			}
    		});
    }
    function graph(getMonth,getDay,getPrice,year){
    	Highcharts.chart('container', {
    		title: {
    		text: getMonth+'월 항공권 판매추이'
    		},
    		credits: {
    		    enabled: false
    		},
    		xAxis: {
    			categories:getDay,
    		    title: {
    		        enabled: true,
    		        text: '일수'
    		    },
    		    labels: {
    		        formatter: function () {
    		            return Math.abs(this.value) + '일';
    		        }
    		    },
    		},
    		yAxis: {
    		  title: {
    		  text: '총판매액 (원)'
    		  },
    		labels: {
    		format: '{value}원'
    		// 여기서 value 는 series 에서의 data
    		},
    		type: 'logarithmic',
    		minorTickInterval: 0.1
    		},
    		tooltip: {
    		// 해당 점(y 좌표)를 클릭했을 때 나오는 설명창.
    		headerFormat: '<b>{series.name}</b><br />',
    		pointFormat: '{point.category}일에는 총 {point.y}원이 팔렸어요.',
    		},
    		plotOptions: {
    		    series: {
    		        cursor: 'pointer',
    		        events: {
    		            click: function (event) {
    		            	
    		            	var day = event.point.category;
    		            	
    		            	$.ajax({
    		            		type : "GET",
    		            		url : "airlinePerDayList",
    		            		dataType : "json",
    		            		data : "day="+day+
    		            		       "&month="+getMonth+
    		            		       "&year="+year,
    		            		success : function(result) {
    		               	    	var length = result.length;
    		               	    	var output = "<a class='close' onClick='history.back()'></a>";
    		               	    	output += "<h3>"+day+"일에는..</h3><br>";
    		               			output +="<table border='1' class='table4_3' width='600px' height='100px' font-size='large'>";
    		               			output +="<tr>";
    		               			output +="<th>노선 이름</th>";
    		               			output +="<th width='80';>갯수</th>";
    		               			output +="<th width='160'>합계</th>";
    		               			output +="</tr>";
    		               			for (var i = 0; i < length; i++) {
    		                   		output += "<tr>";
    		                   		output += "<td>"+result[i].aname+"</td>";
    		                   		output += "<td>"+result[i].caname+"</td>";
    		                   		output += "<td>"+result[i].sprice+"</td>";
    		                   		output += "</tr>";
    		               			}
    		                   		output += "</table><br>"; 
    		                   		console.log(event.point.y);
    		               			output += "<h4>총 합계 : "+event.point.y+"</h4>";
    		               			$("#popup").html(output);
    		                   		location.href="#info";
    		            		},
    		            		error : function() {
    		            			console.log("통신 실패")
    		            		}
    		            	});
    		            }
    		        }
    		    }
    		},
    		series: [{
    			name:'총 판매액',
    		    data:getPrice
    		}]

    	});
    }
</script>
</html>