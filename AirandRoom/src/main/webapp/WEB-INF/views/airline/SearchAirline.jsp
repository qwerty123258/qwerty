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
	width:50%;
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
.disabled{

cursor:default;
opacity:0.6;
pointer-events:none;

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
		html+="<ul class='nav nav-tabs nav-justified'>";
		html+="<li class='active'><a data-toggle='pill' href='#home'>가는 편</a></li>";
		html+="<li><a data-toggle='pill' href='#menu1'>오는 편</a></li>";
		html+="</ul>";
		html+="<div class='tab-content'>";
		html+="<div id='home' class='tab-pane fade in active'>";
		html+="<div id='homecontent'></div>";
		html+="<div id='homecontentorder'></div>";
		html+="</div>";
		html+="<div id='menu1' class='tab-pane fade'>";
		html+="<div id='menu1content'></div>";
		html+="<div id='menu1contentorder'></div>";
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
        	var html="";
        	var order="";
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
        	}
           	order +="<select id='oneway' onchange='javascript:onewaySortby(\""+startpoint+"\",\""+endpoint+"\");'>";
        	order +="<option value=''>선택하세요</option>";
        	order +="<option value='최단시간순편도'>최단시간순</option>";
        	order +="<option value='최대시간순편도'>최대시간순</option>";
        	order +="<option value='가격순정렬편도'>가격순정렬</option>";
        	order +="</select>"; 
    		var nextPage=data.paging.endPage+1;
    		if(data.paging.endPage!=data.paging.totalPage){
    			html += "<button onclick=oneway("+nextPage+")'>더 보기</button>";
    		}
    		else{
    			html += "<button style='cursor: not-allowed; opacity: 0.6;'>더 보기</button>";
    		}
        	$("#homecontentorder").html(order);
        	$("#homecontent").html(html);
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
        	var order="";
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
        	}
           	order +="<select id='roundtrip' onchange='javascript:roundtripSortby(\""+startpoint+"\",\""+endpoint+"\");'>";
           	order +="<option value=''>선택하세요</option>";
           	order +="<option value='최단시간순왕복'>최단시간순</option>";
           	order +="<option value='최대시간순왕복'>최대시간순</option>";
           	order +="<option value='가격순정렬왕복'>가격순정렬</option>";
           	order +="</select>"; 
       		var nextPage=data.paging.endPage+1;
    		if(data.paging.endPage!=data.paging.totalPage){
    			html += "<button onclick=oneway("+nextPage+")'>더 보기</button>";
    		}
    		else{
    			html += "<button style='cursor: not-allowed; opacity: 0.6;'>더 보기</button>";
    		}
        	$("#menu1contentorder").html(order);
        	$("#menu1content").html(html);
        },
        error : function(){
        	alert("오는 편 리스트를 불러오는 중 에러 발생");
        }
	});
}
function onewaySortby(startpoint,endpoint) {
	var selectVal = $("select[id=oneway]").val();
	if(selectVal != null) {		
	$.ajax({
        type:'GET',
        url : "airlineSortBy",
        data : "page=1"+"&startpoint="+startpoint + "&endpoint="+endpoint+"&airlinetype="+selectVal,
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
        	var nextPage=data.paging.endPage+1;
    		if(data.paging.endPage!=data.paging.totalPage){
    			html += "<button onclick=oneway("+nextPage+")'>더 보기</button>";
    		}
    		else{
    			html += "<button style='cursor: not-allowed; opacity: 0.6;'>더 보기</button>";
    		}
        	$("#homecontent").html(html);
        },
        error : function(){
        	alert("가는 편 리스트를 불러오는 중 에러 발생");
        }
	});	
	} else if (selectVal.includes('')) {
		return false;
	}
}	
function roundtripSortby(startpoint,endpoint) {
	var selectVal = $("select[id=roundtrip]").val();
	if(selectVal != null) {
	$.ajax({
        type:'GET',
        url : "airlineSortBy",
        data : "page=1"+"&startpoint="+startpoint + "&endpoint="+endpoint+"&airlinetype="+selectVal,
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
        	var nextPage=data.paging.endPage+1;
    		if(data.paging.endPage!=data.paging.totalPage){
    			html += "<button onclick=oneway("+nextPage+")'>더 보기</button>";
    		}
    		else{
    			html += "<button style='cursor: not-allowed; opacity: 0.6;'>더 보기</button>";
    		}
        	$("#menu1content").html(html);
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
	var html="";
	html += "<table class='type03' style='width:100%'>";
	html +="<tr style='width:100%'>";
	html +="<th scope='row'>노선 이름</th>";
	html +="<td>"+aname+"</td>";
	html +="</tr>";
	html +="<tr>";
	html +="<th scope='row'>항공사 종류</th>";
    html +="<td>"+airporttype+"</td>";
    html +="</tr>";
	html +="<tr>";
	html +="<th scope='row'>출발지</th>";
    html +="<td>"+startpoint+"</td>";
    html +="</tr>";
	html +="<tr>";
	html +="<th scope='row'>도착지</th>";
    html +="<td>"+endpoint+"</td>";
   html +="</tr>";
   html +="<tr>";
	   html +="<th scope='row'>가격</th>";
   html +="<td>"+aprice+"</td>";
   html +="</tr>";     
    html +="</table>";
	var inquire="";
	inquire+="<button class='btn btn-info' style='margin-right:60px;' onclick='javascript:goInquireFormToAirline(\""+id+"\");'>문의하기</button>";
	inquire+="<button class='btn btn-danger' style='margin-left:60px;'onclick='javascript:goReportFormToAirline(\""+id+"\");'>신고하기</button>";
	inquire+="<br>";
	$("#inquire").html(inquire);
	$("#ticketinfo").html(html);
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
                    			if(result.length>0){
                    				var date=new Date();
                    				var year=date.getFullYear();
                    				var month=date.getMonth()+1;
                    				if(month<10){
                    					month="0"+month;
                    				}
                    				var day=date.getDate();
                    				if(day<10){
                    					day="0"+day;
                    				}
                    				var nowdate=year+"-"+month+"-"+day;
                        			for(var i=0; i<result.length; i++){
											var schedule=new Date(nowdate+" "+result[i].sctime);
											if(selectedDate==nowdate && new Date()>schedule){
												html+="<a href='#' class='disabled'>출발 시각 : "+result[i].sctime+"</a><br>";
											}
                        					else{
                        						html+="<a href='javascript:getSeats(\""+result[i].sctime+"\","+ano+","+aprice+",\""+id+"\",\""+aname+"\",\""+startpoint+"\",\""+endpoint+"\")'>출발 시각 : "+result[i].sctime+"</a><br>";
                        					}
                        			}	
                    			}
                    			else{
                    				html+="<br><br><p>일정에 맞는 출발 스케줄이 존재하지 않습니다.</p>";
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
	$("#startDate").val("");
	$("#ticketNum").val(1);
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
					html+="<button id='pay' class='btn btn-success' onclick='goAirlinePay(\""+$('#ticketNum').val()+"\",\""+price+"\",\""+sctime+"\","+ano+",\""+id+"\",\""+aname+"\",\""+startpoint+"\",\""+endpoint+"\",\""+startdate+"\")'>결제</button>";
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
		<div class="container">

    <div class="group">      
      <input type="text" name="startpoint" id="searchStartPoint" class="searchbar" required>
      <span class="highlight"></span>
      <span class="bar"></span>
      <label>출발지</label>
    </div>
   
   
       <div class="group">      
    
        <input type="button" id="btn" onclick="alterValue()" value="출발지,도착지 바꾸기">   
        </div>
    
        <div class="group">      
      <input type="text" name="endpoint" id="searchEndPoint" class="searchbar" required>
      <span class="highlight"></span>
      <span class="bar"></span>
      <label>도착지</label>
    </div>
    <button id="button5" onclick="airlineSearch()">검색하기</button>
    </div>
    <div id="airlinechoice">
    </div>
      								<div id="modal" style="display:none;">
		<div id="overlay" onclick="modalClose()"></div>
			<div id="modalcontent">
			<h1>Air & Room<i class="far fa-times-circle" style="float:right; margin-right:10px;" onclick="modalClose()"></i></h1>
			<div id="ticketinfo"></div>
			<div id="inquire"></div>
<button style='float:left; margin-left:15px;' class="btn btn-info" onclick="goBooking()">
예매하기
</button>
<br><br><br>
<div id="datear" style="display:none">
출발 날짜
<input type="text" id="startDate" autocomplete="off">
매수
<input type="number" id="selectNum" value="1" autocomplete="off">
<input type="hidden" id="ticketNum" value="1">
</div>
<br>
<div id="timeList">

</div>
<div id="seatList">

</div>
<div id="info">

</div>
			</div>
	</div>
</body>
<style>
* { box-sizing:border-box; }

/* basic stylings ------------------------------------------ */
body 				 { background:url(https://scotch.io/wp-content/uploads/2014/07/61.jpg); }
.container 		{ 
  font-family:'Roboto';
  width:600px; 
  margin:30px auto 0; 
  display:block; 
  background:#FFF;
  padding:10px 50px 50px;
}
h2 		 { 
  text-align:center; 
  margin-bottom:50px; 
}
h2 small { 
  font-weight:normal; 
  color:#888; 
  display:block; 
}


/* form starting stylings ------------------------------- */
.group 			  { 
  position:relative; 
  margin-bottom:45px; 
}
.searchbar 				{
  font-size:18px;
  padding:10px 10px 10px 5px;
  display:block;
  width:400px;
  border:none;
  border-bottom:1px solid #757575;
}

#btn {
  font-size:18px;
  padding:10px 10px 10px 5px;
  display:block;
  width:400px;
  border:none;
  border-bottom:1px solid #757575;

}
input:focus 		{ outline:none; }

/* LABEL ======================================= */
label 				 {
  color:#999; 
  font-size:18px;
  font-weight:normal;
  position:absolute;
  pointer-events:none;
  left:5px;
  top:10px;
  transition:0.2s ease all; 
  -moz-transition:0.2s ease all; 
  -webkit-transition:0.2s ease all;
}

/* active state */
input:focus ~ label, input:valid ~ label 		{
  top:-20px;
  font-size:14px;
  color:#5264AE;
}

/* BOTTOM BARS ================================= */
.bar 	{ position:relative; display:block; width:400px; }
.bar:before, .bar:after 	{
  content:'';
  height:2px; 
  width:0;
  bottom:1px; 
  position:absolute;
  background:#5264AE; 
  transition:0.2s ease all; 
  -moz-transition:0.2s ease all; 
  -webkit-transition:0.2s ease all;
}
.bar:before {
  left:50%;
}
.bar:after {
  right:50%; 
}

/* active state */
input:focus ~ .bar:before, input:focus ~ .bar:after {
  width:50%;
}

/* HIGHLIGHTER ================================== */
.highlight {
  position:absolute;
  height:60%; 
  width:100px; 
  top:25%; 
  left:0;
  pointer-events:none;
  opacity:0.5;
}

/* active state */
input:focus ~ .highlight {
  -webkit-animation:inputHighlighter 0.3s ease;
  -moz-animation:inputHighlighter 0.3s ease;
  animation:inputHighlighter 0.3s ease;
}

/* ANIMATIONS ================ */
@-webkit-keyframes inputHighlighter {
	from { background:#5264AE; }
  to 	{ width:0; background:transparent; }
}
@-moz-keyframes inputHighlighter {
	from { background:#5264AE; }
  to 	{ width:0; background:transparent; }
}
@keyframes inputHighlighter {
	from { background:#5264AE; }
  to 	{ width:0; background:transparent; }
}

#button5 {
  background-color: #555555;
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

@import "compass/css3";

#showcase {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0 auto;  
  width: 1000px;
  height: 700px;
}

section {
  display: inline-block;
  position: relative;
  width: 25%;
  height: 100%;
  background-size: cover;
  &:after {
    content: '';
    position: absolute;
    top: 0; 
    width: 100%;
    height: 100%;
    @include transition(all .5s);
  }
}





@import "compass/css3";

#showcase {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0 auto;  
  width: 1000px;
  height: 700px;
}

section {
  display: inline-block;
  position: relative;
  width: 25%;
  height: 100%;
  background-size: cover;
  &:after {
    content: '';
    position: absolute;
    top: 0; 
    width: 100%;
    height: 100%;
    @include transition(all .5s);
  }
}




<!---->

@import url('https://fonts.googleapis.com/css?family=Roboto:700');

body {
  margin:0px;
  font-family:'Roboto';
}

#container2 {
  color:#999;
  text-transform: uppercase;
  font-size:36px;
  font-weight:bold;
  padding-top:30px;
  padding-left:200px;  
  width:100%;
  bottom:45%;
  display:block;
}

#flip {
  height:50px;
  overflow:hidden;
}

#flip > div > div {
  color:#fff;
  padding:0px 12px 12px 12px;
  height:45px;
  margin-bottom:45px;
  display:inline-block;
}

#flip div:first-child {
  animation: show 5s linear infinite;

#flip div div {
  background:#42c58a;
}
#flip div:first-child div {
  background:#4ec7f3;
}
#flip div:last-child div {
  background:#DC143C;
}
@keyframes show {
  0% {margin-top:-270px;}
  5% {margin-top:-180px;}
  33% {margin-top:-180px;}
  38% {margin-top:-90px;}
  66% {margin-top:-90px;}
  71% {margin-top:0px;}
  99.99% {margin-top:0px;}
  100% {margin-top:-270px;}
}




</style>
</html>