<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>노선 검색하기</title>

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="${pageContext.request.contextPath}/resources/js/autocomplete.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>]
<style>
table.type03 {
    border-collapse: collapse;
    text-align: left;
    line-height: 1.5;
    border-top: 1px solid #ccc;
    border-left: 3px solid #369;
  margin : 20px 10px;
}
table.type03 th {
    width: 147px;
    padding: 10px;
    font-weight: bold;
    vertical-align: top;
    color: #153d73;
    border-right: 1px solid #ccc;
    border-bottom: 1px solid #ccc;

}
table.type03 td {
    width: 349px;
    padding: 10px;
    vertical-align: top;
    border-right: 1px solid #ccc;
    border-bottom: 1px solid #ccc;
}
#modal{
	position:fixed;
	top:0;
	left:0;
	width:100%;
	height:100%;
	justify-content:center;
	align-items:center;
	display:flex;
	z-index:1500;
}

#overlay{
	background-Color:rgba(0,0,0,0.6);
		width:100%;
	height:100%;
		position:absolute;
}
#modalcontent{
	background-Color:white;
	text-align:center;
	position:relative;
	width:60%;
	height:90%;
	box-shadow : 0 10px 20px rgba(0,0,0,0.19), 0 6px 6px rgba(0,0,0,0.23);
	border-radious:5px;
	
}
.normal{
  background-color: #4CAF50; /* Green */
  border: none;
  color: white;
  padding: 8px 9px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 13px;
  margin: 4px 2px;
  cursor: pointer;
  width:25px;
}
.booking{
  opacity: 0.6;
  cursor: not-allowed;
}
.selected{
background-Color:blue;
}
.button {
  background-color: #4CAF50;
  border: none;
  color: white;
  padding: 15px 32px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  cursor: pointer;
}
</style>
<script>
function alterValue() {
	var value1 = $("#searchStartPoint").val();
	var value2 = $("#searchEndPoint").val();
	var alter = 0;
		alter = value1;
		$("#searchStartPoint").val(value2);
		$("#searchEndPoint").val(alter);
	
}
$(function() {  
	$("#searchStartPoint").autocomplete({
        source : function( request, response ) {
             $.ajax({
                    type: "GET",
                    url: "selectStartPoint",
                    dataType: "json",
                    data: "param=" + $("#searchStartPoint").val(),
                    success: function(data) {
                    	console.log("통신 성공");
                    	console.log(data);
                    	response(
                    			//난 5개만 보여줄래.
                            $.map(data.slice (0,5), function(item) {
                                return {
                                    value: item,

                                }
                            })
                        );
                    }
               });
            },
        select : function(event, ui) {
            console.log(ui);
            console.log(ui.item.value);

            
        },
        focus : function(event, ui) {
            return false;
        },
        minLength: 1,
        autoFocus: true,
        classes: {
            "ui-autocomplete": "highlight"
        },
        delay: 500,
        position: { my : "left top", at: "left bottom" },
        close : function(event){
            console.log(event);
        }
    });
    
});
$(function() {  
	$("#searchEndPoint").autocomplete({
        source : function( request, response ) {
             $.ajax({
                    type: "GET",
                    url: "selectEndPoint",
                    dataType: "json",
                    data: "param=" + $("#searchEndPoint").val(),
                    success: function(data) {
                    	console.log("통신 성공");
                    	console.log(data);
                    	response(
                    			//난 5개만 보여줄래
                            $.map(data.slice (0,5), function(item) {
                                return {
                                    value: item
                                }
                            })
                        );
                    }
               });
            },
        select : function(event, ui) {
            console.log(ui);
            console.log(ui.item.value);            
        },
        focus : function(event, ui) {
            return false;
        },
        minLength: 1,
        autoFocus: true,
        classes: {
            "ui-autocomplete": "highlight"
        },
        delay: 500,
        position: { my : "left top", at: "left bottom" },
        close : function(event){
            console.log(event);
        }
    });
    
});
function airlineSearch() {
	var searchStartPoint = $("#searchStartPoint").val();
	var searchEndPoint = $("#searchEndPoint").val();
	if (searchStartPoint == "") {
		alert("출발지를 입력해주세요.");
		$("#searchStartPoint").focus();
	} 
	else if (searchEndPoint == "") {
		alert("도착지를 입력해주세요.");
		$("#searchEndPoint").focus();
	}
	if(searchStartPoint != "" && searchEndPoint != ""){
		var html="";
		html+="<h1>노선 검색 결과 리스트 페이지입니다.</h1>";
		html+="<ul class='nav nav-tabs nav-justified'>";
		html+="<li class='active'><a data-toggle='pill' href='#home'>가는 편</a></li>";
		html+="<li><a data-toggle='pill' href='#menu1'>오는 편</a></li>";
		html+="</ul>";
		html+="<div class='tab-content'>";
		html+="<div id='home' class='tab-pane fade in active'>";
		html+="</div>";
		html+="<div id='menu1' class='tab-pane fade'>";
		html+="</div>";
		html+="</div>";
		$("#airlinechoice").html(html);
		oneway(1,searchStartPoint,searchEndPoint);
		roundtrip(1,searchStartPoint,searchEndPoint);
	}
}
function oneway(page,startpoint,endpoint){
	$.ajax({
        type:'GET',
        url : "oneway",
        data : "page=" + page +"&startpoint="+startpoint + "&endpoint="+endpoint,
        dataType : "json",
        async : false,
        success : function(data){
        	var page="";
        	var html="";
        	for(var i=0; i<data.list.length; i++){
            	html += "<table onclick='choice("+data.list[i].ano+",\""+data.list[i].aname+"\",\""+data.list[i].airporttype+"\",\""+data.list[i].startpoint+"\",\""+data.list[i].endpoint+"\",\""+data.list[i].aprice+"\",\""+data.list[i].id+"\")' class='type03'>";
            	html +="<tr>";
            	html +="<th scope='row'>노선 이름</th>";
            	html +="<td>"+data.list[i].aname+"</td>";
            	html +="</tr>";
            	html +="<tr>";
            	html +="<th scope='row'>항공사 종류</th>";
                html +="<td>"+data.list[i].airporttype+"</td>";
                html +="</tr>";
            	html +="<tr>";
            	html +="<th scope='row'>출발지</th>";
                html +="<td>"+data.list[i].startpoint+"</td>";
                html +="</tr>";
            	html +="<tr>";
            	html +="<th scope='row'>도착지</th>";
                html +="<td>"+data.list[i].endpoint+"</td>";
               html +="</tr>";
               html +="<tr>";
           	   html +="<th scope='row'>가격</th>";
               html +="<td>"+data.list[i].aprice+"</td>";
               html +="</tr>";     
     	       html+="</table>";
           	html +="<select class='oneway' onchange='javascript:onewaySortby();'>";
        	html +="<option value=''>선택하세요</option>";
        	html +="<option value='최단시간순편도'>최단시간순</option>";
        	html +="<option value='최대시간순편도'>최대시간순</option>";
        	html +="<option value='가격순정렬편도'>가격순정렬</option>";
        	html +="</select>"; 
        	}
    		var nextPage=data.paging.endPage+1;
    		if(data.paging.endPage!=data.paging.totalPage){
    			html += "<button onclick=oneway("+nextPage+")'>더 보기</button>";
    		}
    		else{
    			html += "<button style='cursor: not-allowed; opacity: 0.6;'>더 보기</button>";
    		}
        	$("#home").html(html);
        },
        error : function(){
        	alert("가는 편 리스트를 불러오는 중 에러 발생");
        }
	});
}
function roundtrip(page,startpoint,endpoint){
	$.ajax({
        type:'GET',
        url : "roundtrip",
        data : "page=" + page +"&endpoint="+startpoint + "&startpoint="+endpoint,
        dataType : "json",
        async : false,
        success : function(data){
        	var html="";
        	for(var i=0; i<data.list.length; i++){
            	html += "<table onclick='choice("+data.list[i].ano+",\""+data.list[i].aname+"\",\""+data.list[i].airporttype+"\",\""+data.list[i].startpoint+"\",\""+data.list[i].endpoint+"\",\""+data.list[i].aprice+"\",\""+data.list[i].id+"\")' class='type03'>";
            	html +="<tr>";
            	html +="<th scope='row'>노선 이름</th>";
            	html +="<td>"+data.list[i].aname+"</td>";
            	html +="</tr>";
            	html +="<tr>";
            	html +="<th scope='row'>항공사 종류</th>";
                html +="<td>"+data.list[i].airporttype+"</td>";
                html +="</tr>";
            	html +="<tr>";
            	html +="<th scope='row'>출발지</th>";
                html +="<td>"+data.list[i].startpoint+"</td>";
                html +="</tr>";
            	html +="<tr>";
            	html +="<th scope='row'>도착지</th>";
                html +="<td>"+data.list[i].endpoint+"</td>";
               html +="</tr>";
               html +="<tr>";
           	   html +="<th scope='row'>가격</th>";
               html +="<td>"+data.list[i].aprice+"</td>";
               html +="</tr>";     
     	       html+="</table>";
           	html +="<select id='roundtrip' onchange='javascript:roundtripSortby();'>";
        	html +="<option value=''>선택하세요</option>";
        	html +="<option value='최단시간순왕복'>최단시간순</option>";
        	html +="<option value='최대시간순왕복'>최대시간순</option>";
        	html +="<option value='가격순정렬왕복'>가격순정렬</option>";
        	html +="</select>"; 
        	}
       		var nextPage=data.paging.endPage+1;
    		if(data.paging.endPage!=data.paging.totalPage){
    			html += "<button onclick=oneway("+nextPage+")'>더 보기</button>";
    		}
    		else{
    			html += "<button style='cursor: not-allowed; opacity: 0.6;'>더 보기</button>";
    		}
        	$("#menu1").html(html);
        },
        error : function(){
        	alert("오는 편 리스트를 불러오는 중 에러 발생");
        }
	});
}
function onewaySortby() {
	var selectVal = $("select[class=oneway]").val();
	if(selectVal != null) {		
	$.ajax({
        type:'GET',
        url : "airlineSortBy",
        data : "page=1"+"&startpoint="+"${startpoint}" + "&endpoint="+"${endpoint}"+"&airlinetype="+selectVal,
        dataType : "json",
        async : false,
        success : function(data){
        	var page="";
        	var html="";
        	for(var i=0; i<data.list.length; i++){
            	html += "<table onclick='choice("+data.list[i].ano+",\""+data.list[i].aname+"\",\""+data.list[i].airporttype+"\",\""+data.list[i].startpoint+"\",\""+data.list[i].endpoint+"\",\""+data.list[i].aprice+"\",\""+data.list[i].id+"\")' class='type03'>";
            	html +="<tr>";
            	html +="<th scope='row'>노선 이름</th>";
            	html +="<td>"+data.list[i].aname+"</td>";
            	html +="</tr>";
            	html +="<tr>";
            	html +="<th scope='row'>항공사 종류</th>";
                html +="<td>"+data.list[i].airporttype+"</td>";
                html +="</tr>";
            	html +="<tr>";
            	html +="<th scope='row'>출발지</th>";
                html +="<td>"+data.list[i].startpoint+"</td>";
                html +="</tr>";
            	html +="<tr>";
            	html +="<th scope='row'>도착지</th>";
                html +="<td>"+data.list[i].endpoint+"</td>";
               html +="</tr>";
               html +="<tr>";
           	   html +="<th scope='row'>가격</th>";
               html +="<td>"+data.list[i].aprice+"</td>";
               html +="</tr>";     
     	       html +="</table>";
        	}
        	html +="<select class='oneway' onchange='javascript:onewaySortby();'>";
        	html +="<option value=''>선택하세요</option>";
        	html +="<option value='최단시간순편도'>최단시간순</option>";
        	html +="<option value='최대시간순편도'>최대시간순</option>";
        	html +="<option value='가격순정렬편도'>가격순정렬</option>";
        	html +="</select>";
        	$("#home").html(html);

        	var nextPage=data.paging.endPage+1;
    		if(data.paging.endPage!=data.paging.totalPage){
    			html += "<button onclick=oneway("+nextPage+")'>더 보기</button>";
    		}
    		else{
    			html += "<button style='cursor: not-allowed; opacity: 0.6;'>더 보기</button>";
    		}
        },
        error : function(){
        	alert("가는 편 리스트를 불러오는 중 에러 발생");
        }
	});	
	} else if (selectVal.includes('')) {
		return false;
	}
}	
function roundtripSortby() {
	var selectVal = $("select[id=roundtrip]").val();
	if(selectVal != null) {
	$.ajax({
        type:'GET',
        url : "airlineSortBy",
        data : "page=1"+"&startpoint="+"${startpoint}" + "&endpoint="+"${endpoint}"+"&airlinetype="+selectVal,
        dataType : "json",
        async : false,
        success : function(data){
        	var page="";
        	var html="";
        	for(var i=0; i<data.list.length; i++){
            	html += "<table onclick='choice("+data.list[i].ano+",\""+data.list[i].aname+"\",\""+data.list[i].airporttype+"\",\""+data.list[i].startpoint+"\",\""+data.list[i].endpoint+"\",\""+data.list[i].aprice+"\",\""+data.list[i].id+"\")' class='type03'>";
            	html +="<tr>";
            	html +="<th scope='row'>노선 이름</th>";
            	html +="<td>"+data.list[i].aname+"</td>";
            	html +="</tr>";
            	html +="<tr>";
            	html +="<th scope='row'>항공사 종류</th>";
                html +="<td>"+data.list[i].airporttype+"</td>";
                html +="</tr>";
            	html +="<tr>";
            	html +="<th scope='row'>출발지</th>";
                html +="<td>"+data.list[i].startpoint+"</td>";
                html +="</tr>";
            	html +="<tr>";
            	html +="<th scope='row'>도착지</th>";
                html +="<td>"+data.list[i].endpoint+"</td>";
               html +="</tr>";
               html +="<tr>";
           	   html +="<th scope='row'>가격</th>";
               html +="<td>"+data.list[i].aprice+"</td>";
               html +="</tr>";     
     	       html +="</table>";
        	}
        	html +="<select id='roundtrip' onchange='javascript:roundtripSortby();'>";
        	html +="<option value=''>선택하세요</option>";
        	html +="<option value='최단시간순왕복'>최단시간순</option>";
        	html +="<option value='최대시간순왕복'>최대시간순</option>";
        	html +="<option value='가격순정렬왕복'>가격순정렬</option>";
        	html +="</select>";
        	$("#menu1").html(html);
        	var nextPage=data.paging.endPage+1;
    		if(data.paging.endPage!=data.paging.totalPage){
    			html += "<button onclick=oneway("+nextPage+")'>더 보기</button>";
    		}
    		else{
    			html += "<button style='cursor: not-allowed; opacity: 0.6;'>더 보기</button>";
    		}
        },
        error : function(){
        	alert("가는 편 리스트를 불러오는 중 에러 발생");
        }
	});
	} else if (selectVal.includes('')) {
		return false;
	}

}
function goInquireFormToAirline(id){
	var id=id;
	var popUrl = "goInquireForm?id="+id;	
	var popOption = "width=450, height=650, resizable=no, scrollbars=no, status=no;";    //팝업창 옵션(optoin)
		window.open(popUrl,"",popOption);
	}
function goReportFormToAirline(id) {
	var id=id;
	var popUrl = "goReportForm?id="+id;	
	var popOption = "width=450, height=650, resizable=no, scrollbars=no, status=no;";    //팝업창 옵션(optoin)
		window.open(popUrl,"",popOption);
}
function choice(ano,aname,airporttype,startpoint,endpoint,aprice,id){
	$("#modal").show();
	var inquire="";
	inquire+="<button class='btn btn-info' onclick='javascript:goInquireFormToAirline(\""+id+"\");'>문의하기</button>";
	inquire+="<button class='btn btn-danger' onclick='javascript:goReportFormToAirline(\""+id+"\");'>신고하기</button>";
	$("#inquire").html(inquire);
	$("#selectNum").change(function(){
		selectNum=$("#selectNum").val();
		$("#ticketNum").val(selectNum);
		$("#pay").hide();
		if(selectNum<1){
			alert("매수는 0 이하 일 수 없습니다.");
			$("#selectNum").val("");
			$("#ticketNum").val("");
			$("#selectNum").focus();
		}
		if($("#seatList").children().hasClass("selected")){
			if(selectNum-$(".selected").length<1){
				$("#seatList").children().removeClass("selected");
				$("#info").html("");
				seats=[];
			}
			selectNum=selectNum-$(".selected").length
		}
		});
        $.datepicker.setDefaults($.datepicker.regional['ko']);
        $( "#startDate" ).datepicker({
            changeMonth: true, 
            changeYear: true,
            nextText: '다음 달',
            prevText: '이전 달', 
            dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
            dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'], 
            monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
            monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
            dateFormat: "yy-mm-dd",
            minDate:0,
            onClose: function(selectedDate) {
                	var html="";
                	$("#info").html("");
                	$("#seatList").html("");
                	selectNum=$("#selectNum").val();
                	seats=[];
                	if($("#startDate").val()!=""){
                    	$.ajax({
                    		type : "GET",
                    		url : "timeSetting",
                    		data : "ano=" + ano,
                    		dataType : "json",
                    		success : function(result) {
                    			for(var i=0; i<result.length; i++){
                    				html+="<a href='javascript:getSeats(\""+result[i].sctime+"\","+ano+","+aprice+",\""+id+"\",\""+aname+"\",\""+startpoint+"\",\""+endpoint+"\")'>"+result[i].sctime+"</a><br>";
                    			}
                    			$("#timeList").html(html);
          							
                    		},
                    		error : function() {
                    			alert("스케줄 설정 중 에러 발생");
                    		}
                    	});
                	}
            	} 
       });
}
function modalClose(){
	$("#datear").hide();
	$("#seatList").html("");
	$("#timeList").html("");
	$("#info").html("");
	seats=[];
	selectNum=1;
	$("#modal").hide();
}
function goBooking(){
	$("#datear").show();
}
var selectNum=1;
var seats=[];
function select(sno,srow,scolumn,sctime,ano,aprice,id,aname,startpoint,endpoint,startdate){
	var html="";
	var seat = scolumn+"행"+srow+"열";
		if($("#"+sno).hasClass("selected")) {
			$("#"+sno).removeClass("selected");
			selectNum++;
			seats.splice(seats.indexOf(seat),1);
			html += "<p>매수 :"+$("#ticketNum").val()+ "</p>";
			html += "<p>총 가격 :"+(aprice * $(".selected").length)+ "</p>";
			html +="<p>선택 좌석 : "+seats+"</p>"
			$("#info").html(html);
	}
		else{
			if(selectNum<1){
				alert("더 이상 선택하실 수 없습니다.");
			}
			else{
				$("#"+sno).addClass("selected");
				seats.push(seat);
				selectNum--;
				var price=(aprice * $(".selected").length);
				html += "<p>매수 :"+$("#ticketNum").val()+ "</p>";
				html += "<p>총 가격 :"+price+ "</p>";
				html +="<p>선택 좌석 : "+seats+"</p>"
				if(selectNum==0){
					html+="<button id='pay' onclick='goAirlinePay(\""+$('#ticketNum').val()+"\",\""+price+"\",\""+sctime+"\","+ano+",\""+id+"\",\""+aname+"\",\""+startpoint+"\",\""+endpoint+"\",\""+startdate+"\")'>예매</button>";
				}
				$("#info").html(html);
			}
		}	
}
function goAirlinePay(num,price,sctime,ano,airlineid,aname,startpoint,endpoint,startDate){
	var num=num;
	var price=price;
	window.open("goAirlinePay?ano="+ano +"&aname="+aname +"&price="+price +"&startdate=" + startDate + "&ticketnum="+num + "&seats="+seats + "&airlineid="+airlineid +"&sctime=" + sctime + "&startpoint="+startpoint+ "&endpoint="+endpoint, "PopupWin", "width=500,height=600");
}
function getSeats(sctime,ano,aprice,id,aname,startpoint,endpoint){
	var sctime=sctime;
	var startDate=$("#startDate").val();
	var html="";
	seats=[];
	selectNum=$("#selectNum").val();
	$("#info").html("");
  	if($("#startDate").val()!=""){
	$.ajax({
		type : "GET",
		url : "seatSetting",
		data : "ano=" + ano +"&sctime="+sctime+"&startdate="+startDate,
		dataType : "json",
		success : function(result) {
						for(var i=0; i<result.length; i++){
							if(result[i].bookingdate){
								html+="<button class='normal booking'>"+result[i].scolumn+"</button>";
							}
							if(!result[i].bookingdate){
								html+="<button onclick='select("+result[i].sno+",\""+result[i].scolumn+"\",\""+result[i].srow+"\",\""+sctime+"\","+ano+","+aprice+",\""+id+"\",\""+aname+"\",\""+startpoint+"\",\""+endpoint+"\",\""+startDate+"\")' id='"+result[i].sno+"' class='normal'>"+result[i].scolumn+"</button>";
							}
		                     if(result[i].scolumn==result.length/result[i].maxsrow){
								html+="<br>";
							}
						}
			$("#seatList").html(html);
					
		},
		error : function() {
			alert("스케줄 설정 중 에러 발생");
		}
	});
  	}
  	else{
  		alert("출발 날짜가 비어있습니다.");
  		$("#startDate").focus();
  		$("#seatList").html("");
  	}
}
</script>




</head>
<body>
<jsp:include page="../Header.jsp"></jsp:include><br>
<jsp:include page="../Nav.jsp"></jsp:include>
				<h1>항공권 검색 페이지 입니다.</h1>
				<br>

    <input type="text" name="startpoint" id="searchStartPoint" placeholder="출발지">
    <input type="button" onclick="alterValue()" value="출발지,도착지 바꾸기">   
    <input type="text" name="endpoint" id="searchEndPoint" placeholder="도착지">
    
    <br>
    <button onclick="airlineSearch()">검색하기</button>
    <div id="airlinechoice">
    </div>
      								<div id="modal" style="display:none;">
		<div id="overlay" onclick="modalClose()"></div>
			<div id="modalcontent">
					<h1>Air & Room <button style="float:right; margin-right:10px;" onclick="modalClose()">모달 닫기</button></h1>
					<div id="inquire"></div>
<button onclick="goBooking()">
예매하기
</button>
<div id="datear" style="display:none">
출발 날짜
<input type="text" id="startDate" autocomplete="off">
매수
<input type="number" id="selectNum" value="1" autocomplete="off">
<input type="hidden" id="ticketNum" value="1">
</div>
<div id="timeList">

</div>
<div id="seatList">

</div>
<div id="info">

</div>
			</div>
	</div>
</body>
</html>