<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>숙소 상세보기</title>
<style>
.mySlides {
		display: none;
		height: 100%;
		width: 100%;
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
	      .input-group{
	    position: absolute;
	
	    left: 0;
	
	    bottom: 0;
	
	    width: 100%;
		padding-bottom:20px;
	      }
</style>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
    	
    	
 	    $(window).scroll(function(){
	        
	        var docScrollY = $(document).scrollTop()
	   var styleObj = { 
	            'position': 'fixed', 
	            'top': '0' ,
	            'left': '0' ,
	            'width': '100%',
	            'z-index':'300'  
	        };

	        if( docScrollY > 280) {
	            $('#menubar').css(styleObj); 
	        }else{
	        
	        	$('#menubar').removeAttr("style");
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
                beforeShowDay : checkinDay,
                onClose: function(selectedDate) {
                	if($("#startDate").val()!=""){
                		 $("#endDate").attr("disabled",false);
                        var minDate = new Date(Date.parse(selectedDate));
                        minDate.setDate(minDate.getDate() + 1);
                        $("#endDate").datepicker("option", "minDate", minDate);
                    	var rno=${room.rno};
                    	$.ajax({
                    		type : "GET",
                    		url : "checkoutSetting",
                    		data : "rno=" + rno,
                    		dataType : "json",
                    		success : function(result) {
                    			for(var i=0; i<result.length; i++){
                    				checkoutstart[i]=result[i].checkindate;
                    				checkoutend[i]=result[i].checkoutdate;
                    				if(new Date(minDate).getDate()-1==new Date(checkoutstart[i]).getDate()){
                    					date2[i]=new Date(checkoutstart[i]).getDate();
                    					var year=new Date(selectedDate).getFullYear();
                    					var month=new Date(selectedDate).getMonth()+1;
                    					if (month < 10) {
                    						month = "0" + month; 
                    					}
                    					var minDay=Math.min.apply(null,date2);
                    					if(!minDay){
                    						minDay=date2[i];
                    					}
                    					var minDate2=year+"-"+month+"-"+minDay;
                    					$("#endDate").datepicker("option", "maxDate", "");
                    					$("#endDate").datepicker("option", "minDate", minDate2);
                    				}
                    				else if(new Date(minDate)<new Date(checkoutstart[i])){
                    					date3[i]=new Date(checkoutstart[i]).getDate();
                    					var year=new Date(selectedDate).getFullYear();
                    					var month=new Date(selectedDate).getMonth()+1;
                    					if (month < 10) {
                    						month = "0" + month; 
                    					}
                    					var minDay=Math.min.apply(null,date3);
                    					if(!minDay){
                    						minDay=date3[i];
                    					}
                    					var maxDate=year+"-"+month+"-"+minDay;
                    					if(new Date(maxDate)>new Date(Date.parse(selectedDate))){
                                            var minDate4 = new Date(Date.parse(selectedDate));
                                            minDate4.setDate(minDate4.getDate() + 1);
                        					$("#endDate").datepicker("option", "minDate", minDate4);
                        					$("#endDate").datepicker("option", "maxDate", maxDate);
                        					$("#endDate").val("");
                    					}
                    					else{
                    						$("#endDate").datepicker("option", "minDate", "");
                        					$("#endDate").datepicker("option", "maxDate", maxDate);
                    					}
                    				}
                    				else if(new Date(minDate)>new Date(checkoutstart[i])){
                    					
                    					var year=new Date(minDate).getFullYear();
                    					var month=new Date(minDate).getMonth()+1;
                    					if (month < 10) {
                    						month = "0" + month; 
                    					}
                    					var minDay=new Date(minDate).getDate();
                    					if (minDay < 10) {
                    						minDay = "0" + minDay; 
                    					}
                    					var minDate3=year+"-"+month+"-"+minDay;
                    					$("#endDate").datepicker("option", "maxDate", "");
                    					$("#endDate").datepicker("option", "minDate", minDate3);
                    				}
                    			}
                				$("#endDate").focus();
                    		},
                    		error : function() {
                    			alert("달력 설정 중 에러 발생");
                    		}
                    	});
                	}
                }    
           });
                $( "#endDate" ).datepicker({
                        changeMonth: true, 
                        changeYear: true,
                        nextText: '다음 달',
                        prevText: '이전 달', 
                        dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
                        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'], 
                        monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
                        monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
                        dateFormat: "yy-mm-dd",
                        beforeShowDay : checkoutDay,
                        onClose: function(selectedDate) {
                        	$("#startDate").datepicker( "option", "beforeShowDay", checkinDay );
                        	var html="";
                        	if(selectedDate!=""){
                            	html+="<button class='btn btn-success' onclick='booking()'>결제하기</button>";
                            	$("#bookingar").html(html);
                        	}
                        }
               });
    });
</script>
<script>
var checkinstart=[];
var checkinend=[];
var checkoutstart=[];
var checkoutend=[];
var date2=[];
var date3=[];
function goBooking(){
	$("#datear").show();
	var rno=${room.rno};
	$.ajax({
		type : "GET",
		url : "checkinSetting",
		data : "rno=" + rno,
		dataType : "json",
		success : function(result) {
			for(var i=0; i<result.length; i++){
				checkinstart[i]=result[i].checkindate;
				checkinend[i]=result[i].checkoutdate;
			}
		},
		error : function() {
			alert("달력 설정 중 에러 발생");
		}
	});

}

function checkinDay(date){
    var dateRange= [];
    var dateString = jQuery.datepicker.formatDate('yy-mm-dd', date);
	for(var i=0; i<checkinstart.length;i++){
			    for (var d = new Date(checkinstart[i]); d <= new Date(checkinend[i]); d.setDate(d.getDate() + 1)) {
				        dateRange.push($.datepicker.formatDate('yy-mm-dd', d));
			    	}
			    }
    return [dateRange.indexOf(dateString) == -1];
}

function checkoutDay(date){
	var rno=${room.rno};
    var dateRange= [];
    var dateString = jQuery.datepicker.formatDate('yy-mm-dd', date);
	for(var i=0; i<checkoutstart.length;i++){
			    for (var d = new Date(checkoutstart[i]); d <= new Date(checkoutend[i]); d.setDate(d.getDate() + 1)) {
				        dateRange.push($.datepicker.formatDate('yy-mm-dd', d));
			    	}
			    }
    return [dateRange.indexOf(dateString) == -1];
}
function booking(){
	var rno=${room.rno};
	var rname='${room.rname}';
	var roomid='${room.id}'
	var price=${room.price};
	var startDate=$("#startDate").val();
	var endDate=$("#endDate").val();
	var checkinDate=new Date(startDate);
	var checkoutDate=new Date(endDate);
	var distance=checkoutDate-checkinDate;
	var day=distance/1000 / 60 / 60 / 24;
	var totalPrice=day*price;
    window.open("goRoomPay?rno="+rno +"&rname="+rname +"&price="+totalPrice +"&checkindate=" + startDate + "&checkoutdate=" + endDate +"&roomid=" + roomid, "PopupWin", "width=500,height=600");
}
function goInquireFormToRoom(id){

	var id=id;
	var popUrl = "goInquireForm?id="+id;	
	var popOption = "width=450, height=650, resizable=no, scrollbars=no, status=no;";
		window.open(popUrl,"",popOption);
	}
	
function goReportFormToRoom(id) {
	var id=id;
	var popUrl = "goReportForm?id="+id;	
	var popOption = "width=450, height=650, resizable=no, scrollbars=no, status=no;";
		window.open(popUrl,"",popOption);
}
function reviewView(revno){
	sysrevno=revno;
	$.ajax({
	    type:'get',
		url : "reviewView",
		data : "revno=" + revno+"&page="+commentpage,
	    dataType : "json",
	    async:false,
	    success : function(data){
	    	var plist="";
	    	var revcontent="";
	    	var revcomment="";
	    	var commentinput="";
	    	revcomment+="<table id='commentList' class='table table-bordered table-striped table-hover' style='height:100%;'>";
	    	revcomment+="<tbody>"
	    	plist+="<div class='w3-content w3-display-container' style='height:100%'>";
	    	for(var i=0; i<data.plist.length; i++){
				
				plist+=	"<img class='mySlides' src='${pageContext.request.contextPath}/resources/fileUpload/"+data.plist[i].rimgname+"' style='width: 100%; height:100%;'>";

	    	}
	    	for(var j=0; j<data.comment.length; j++){
	    		revcomment+="<tr style='text-align:left; height:50px;' id='comment"+data.comment[j].replyno+"'><td style='height:50px;'><img style='border-radius:16px; width:32px; height:32px; border-radius:16px;' onError='this.src=\"${pageContext.request.contextPath}/resources/img/default.webp\"'  src='${pageContext.request.contextPath}/resources/fileUpload/"+data.comment[j].imgname+"\' >"+"     "+data.comment[j].id+" : "+data.comment[j].contents;
        		if(data.comment[j].id=='${sessionScope.id}'||'${sessionScope.id}'=='admin'){
        			revcomment+="<i style='float:right;' onclick='reviewCommentDelete("+data.comment[j].replyno+","+data.comment[j].revno+")' class='far fa-trash-alt'></i></td></tr>";
        		}
	    	}
	    	commentinput+="<input type='text' style='width: 95%;' id='commentinput"+data.roomrev.revno+"'/> ";
	    	commentinput+="<button class='btn btn-info' onclick='writeComment("+data.roomrev.revno+")'><i class='far fa-paper-plane'>"+ "  " +"댓글 입력</button>";
	    	revcomment+="</tbody>";
			plist+=	"<button class='w3-button w3-none w3-display-left' onclick='plusDivs(-1)'>&#10094;</button>";
			plist+=	"<button class='w3-button w3-none w3-display-right' onclick='plusDivs(1)'>&#10095;</button>";
			plist+=	"</div>";
			revcontent+="<p>"+data.roomrev.id+"</p>";
			revcontent+="<pre style='text-align:left'>"+data.roomrev.contents+"</pre>";
			
			$("#commentinput").html(commentinput);
			$("#revcomment").html(revcomment);
			$("#revcontent").html(revcontent);
	    	$("#plist").html(plist);
	    	showDivs(slideIndex);

	    },
	    error:function(){
	    	alert("모달 창 정보를 불러오는 중 에러 발생");
	    },
	});
	$("#modal").show();
	$("#revcomment").scroll(function() {
		let $window = $(this);
		let scrollTop = $window.scrollTop();
		let windowHeight = $window.height();
		let documentHeight = $("#revcomment").height();
		let scrollHeight=$("#revcomment").prop('scrollHeight');
		let offsetHeight=$("#revcomment").prop('offsetHeight');
		if(offsetHeight+scrollTop>=scrollHeight){
			commentpage++;
			reviewViewScroll(sysrevno,commentpage);
		}

	});
	}
var commentpage=1;
var slideIndex = 1;
function plusDivs(n) {
showDivs(slideIndex += n);
}
function showDivs(n) {
var i;
var x = document.getElementsByClassName("mySlides");
if (n > x.length) {slideIndex = 1}
if (n < 1) {slideIndex = x.length} ;
for (i = 0; i < x.length; i++) {
x[i].style.display = "none";
}

x[slideIndex-1].style.display = "block";
}
function modalClose(){
commentpage=1;
$("#modal").hide();
$("#revcomment").html("");
$("#commentList").html("");
$("#revcontent").html("");
$("#plist").html("");
}
var sysrevno;

function reviewViewScroll(revno,commentpage){
$.ajax({
    type:'get',
	url : "reviewViewScroll",
	data : "revno=" + revno+"&page="+commentpage,
    dataType : "json",
    async:false,
    success : function(data){
    	var plist="";
    	var revcontent="";
    	var revcomment="";
    	revcomment+="<table id='commentList' class='table table-bordered table-striped table-hover' style='height:100%;'>";
    	revcomment+="<tbody>"
    	for(var j=0; j<data.comment.length; j++){
    		revcomment+="<tr style='text-align:left; height:50px;' id='comment"+data.comment[j].replyno+"' ><td><img style='border-radius:16px; width:32px; height:32px; border-radius:16px;' onError='this.src=\"${pageContext.request.contextPath}/resources/img/default.webp\"'  src='${pageContext.request.contextPath}/resources/fileUpload/"+data.comment[j].imgname+"\' >"+"     "+data.comment[j].id+" : "+data.comment[j].contents;
    		if(data.comment[j].id=='${sessionScope.id}'||'${sessionScope.id}'=='admin'){
    			revcomment+="<i style='float:right;' onclick='reviewCommentDelete("+data.comment[j].replyno+","+data.comment[j].revno+")' class='far fa-trash-alt'></i></td></tr>";
    		}
    	}
    	revcomment+="</tbody>";
		
		$("#revcomment").html(revcomment);

    },
    error:function(){
    	alert("댓글을 불러오는 중 에러 발생");
    },
});
}

function writeComment(revno){
	if($("#commentinput"+revno).val()==""){
		alert('댓글내용을 기입해주세요');
		return;
	}
	var comment=$("#commentinput"+revno).val();
	$.ajax({
	    type:'POST',
		url : "writeCommentModal",
		data : "revno=" + revno+ "&comment="+comment,
	    dataType : "text",
	    async:false,
	    success : function(data){
	    	$("#commentinput"+revno).val("");
	    	if(data=="Success"){
	        	reviewViewScroll(revno,commentpage);
	    	}
	    	else{
	    		alert("댓글 작성 실패");
	    	}
	
	    },
	    error:function(){
	    	alert("댓글 작성 중 에러 발생");
	    }
	    });
			
	}


function reviewCommentDelete(replyno,revno){
	if(confirm("삭제하시겠습니까?")){
		$.ajax({
		    type:'POST',
			url : "reviewCommentDelete",
			data : "replyno=" + replyno,
		    dataType : "text",
		    async:false,
		    success : function(data){
		    	if(data=="Success"){
		        	reviewViewScroll(revno,commentpage);
		    	}
		    	else{
		    		alert("댓글 삭제 실패");
		    	}
		
		    },
		    error:function(){
		    	alert("댓글 삭제 중 에러 발생");
		    }
		    });	
	}
	
}


</script>
<style>
*{margin:0; padding:0; list-style:none;}
a{text-decoration:none; color:#666;}
a:hover{color:#1bc1a3;}
body, hmtl{background: #ecf0f1; font-family: 'Anton', sans-serif;}
#wrapper{
    width:600px;
    margin:50px auto;
    height:400px;
    position:relative;
    color:#fff;
    text-shadow:rgba(0,0,0,0.1) 2px 2px 0px;    
}

#slider-wrap{
    width:600px;
    height:400px;
    position:relative;
    overflow:hidden;
}

#slider-wrap ul#slider{
    width:100%;
    height:100%;
    
    position:absolute;
    top:0;
    left:0;     
}

#slider-wrap ul#slider li{
    float:left;
    position:relative;
    width:600px;
    height:400px;   
}

#slider-wrap ul#slider li > div{
    position:absolute;
    top:20px;
    left:35px;  
}

#slider-wrap ul#slider li > div h3{
    font-size:36px;
    text-transform:uppercase;   
}

#slider-wrap ul#slider li > div span{
    font-family: Neucha, Arial, sans serif;
    font-size:21px;
}

#slider-wrap ul#slider li img{
    display:block;
    width:100%;
  height: 100%;
}


/*btns*/
.btns{
    position:absolute;
    width:50px;
    height:60px;
    top:50%;
    margin-top:-25px;
    line-height:57px;
    text-align:center;
    cursor:pointer; 
    background:rgba(0,0,0,0.1);
    z-index:100;
    -webkit-user-select: none;  
    -moz-user-select: none; 
    -khtml-user-select: none; 
    -ms-user-select: none;
    -webkit-transition: all 0.1s ease;
    -moz-transition: all 0.1s ease;
    -o-transition: all 0.1s ease;
    -ms-transition: all 0.1s ease;
    transition: all 0.1s ease;
}

.btns:hover{
    background:rgba(0,0,0,0.3); 
}

#next{right:-50px; border-radius:7px 0px 0px 7px;}
#previous{left:-50px; border-radius:0px 7px 7px 7px;}
#counter{
    top: 30px; 
    right:35px; 
    width:auto;
    position:absolute;
}

#slider-wrap.active #next{right:0px;}
#slider-wrap.active #previous{left:0px;}


/*bar*/
#pagination-wrap{
    min-width:20px;
    margin-top:350px;
    margin-left: auto; 
    margin-right: auto;
    height:15px;
    position:relative;
    text-align:center;
}

#pagination-wrap ul {
    width:100%;
}

#pagination-wrap ul li{
    margin: 0 4px;
    display: inline-block;
    width:5px;
    height:5px;
    border-radius:50%;
    background:#fff;
    opacity:0.5;
    position:relative;
  top:0;
  
  
}

#pagination-wrap ul li.active{
  width:12px;
  height:12px;
  top:3px;
    opacity:1;
    box-shadow:rgba(0,0,0,0.1) 1px 1px 0px; 
}




/*Header*/
h1, h2{text-shadow:none; text-align:center;}
h1{ color: #666; text-transform:uppercase;  font-size:36px;}
h2{ color: #7f8c8d; font-family: Neucha, Arial, sans serif; font-size:18px; margin-bottom:30px;} 




/*ANIMATION*/
#slider-wrap ul, #pagination-wrap ul li{
    -webkit-transition: all 0.3s cubic-bezier(1,.01,.32,1);
    -moz-transition: all 0.3s cubic-bezier(1,.01,.32,1);
    -o-transition: all 0.3s cubic-bezier(1,.01,.32,1);
    -ms-transition: all 0.3s cubic-bezier(1,.01,.32,1);
    transition: all 0.3s cubic-bezier(1,.01,.32,1); 
}

      #map {
        height: 400px;
        width: 500px;
      }
      /* Optional: Makes the sample page fill the window. */
      #infowindow-content .title {
        font-weight: bold;
      }

      #infowindow-content {
        display: none;
      }

      #map #infowindow-content {
        display: inline;
      }
      div p {
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  width: 200px;
  height: 30px;
}
</style>
</head>
<body>
  <jsp:include page="../Header.jsp"></jsp:include>
   <div id="menubar"><jsp:include page="../Nav.jsp"></jsp:include> </div>
   
   
   <div class="row">
      <div class="col-xs-12">
         <div id="wrapper">
      <div id="slider-wrap">
          <ul id="slider">
          <c:forEach var="plist" items="${plist}">
             <li>             
      <img src="${pageContext.request.contextPath}/resources/fileUpload/${plist.rimgname}" style="width:600px">
             </li>
             </c:forEach>             
          </ul>
           <!--controls-->
          <div class="btns" id="next"><i class="fa fa-arrow-right"></i></div>
          <div class="btns" id="previous"><i class="fa fa-arrow-left"></i></div>
          <div id="counter"></div>
          <div id="pagination-wrap">
            <ul>
            </ul>
          </div>
          <!--controls-->      
      </div>
   </div>
   <div class="container">
      <div id="map" style="margin:auto;"></div>
         <div id="infowindow-content">
        <span id="place-name" class="title"></span>
        <br> <span id="place-address"></span>
   </div>
   <br><br>
   <div class="col-xs-12">
   <div class="col-xs-3">
   </div>
   <div class="col-xs-6" style="text-align:center; margin:auto;">
   	<p>${room.contents}</p>
<button onclick="goBooking()" class="btn btn-info">예약하기</button><br>
 </div>
   
<div class="col-xs-3">
   
   </div>
   <div class="col-xs-12">
   <div class="col-xs-3">
   </div>
   <div class="col-xs-6" style="text-align:center; margin:auto;">
<div id="datear" style="display:none">
<div class="col-xs-6"><br>
체크인 날짜<br>
<input type="text" id="startDate" autocomplete="off">
</div>
<div class="col-xs-6"><br>
체크아웃 날짜<br>
<input type="text" id="endDate" autocomplete="off" disabled="true">
</div><br>

 </div>
 <br><br><br><br>
      <button class="btn btn-info" onclick="javascript:goInquireFormToRoom('${room.id}');">문의하기</button>
      --
     <button class="btn btn-danger" onclick="javascript:goReportFormToRoom('${room.id}');">신고하기</button>
 
   </div><br><br><br>
<div class="col-xs-3">
   </div>			
			
</div>
<div id="bookingar">

</div>   

			</div>
			

				<div class="col-xs-12">
					<c:if test="${!empty grade}">
					<h2>평점:${grade}</h2>
								</c:if>
				</div>
				</div>
			<div class="col-xs-12" style="height:100px;"></div>	
	<div class="col-xs-12">
	<div class="col-xs-3"></div>
	<div class="col-xs-3">
		
		<c:if test="${!empty revList[0]}">
							
									
						
							<img style='border-radius: 16px; width:32px; height:32px;' src='${pageContext.request.contextPath}/resources/fileUpload/${revList[0].imgname}' onError='this.src="${pageContext.request.contextPath}/resources/img/default.webp"'>																																					
<a style='margin-left:10px;' href="myReviewListForm?id=${revList[0].id}&check=my">${revList[0].id}</a><br><br>
<p>${revList[0].contents}</p>
	<br><br><br>
	<a href="javascript:reviewView(${revList[0].revno})">리뷰 상세 보기</a>
	</c:if>
	</div>
	
	<div class="col-xs-3">
	<c:if test="${!empty revList[1]}">
										
						
							<img style='border-radius:16px; width:32px; height:32px;' src='${pageContext.request.contextPath}/resources/fileUpload/${revList[1].imgname}' onError='this.src="${pageContext.request.contextPath}/resources/img/default.webp"'>																										
<a style='margin-left:10px;' href="myReviewListForm?id=${revList[1].id}&check=my">${revList[1].id}</a><br><br>
<p>${revList[1].contents}</p>
	<br><br><br>
	<a href="javascript:reviewView(${revList[1].revno})">리뷰 상세 보기</a>
	</c:if>
	</div>	
	<div class="col-xs-3"></div>
	</div>
	<div class="col-xs-12">
	<div class="col-xs-3"></div>
	<div class="col-xs-3">
	<c:if test="${!empty revList[2]}">
		<div class="col-xs-12" style="height:100px;"></div>									
						
							<img style='border-radius:16px; width:32px; height:32px;' src='${pageContext.request.contextPath}/resources/fileUpload/${revList[2].imgname}' onError='this.src="${pageContext.request.contextPath}/resources/img/default.webp"'>																										
	<a style='margin-left:10px;' href="myReviewListForm?id=${revList[2].id}&check=my">${revList[2].id}</a><br><br>
	<p>${revList[2].contents}</p>
	
	<br><br><br>
	<a href="javascript:reviewView(${revList[2].revno})">리뷰 상세 보기</a>
	</c:if>
</div>
	<div class="col-xs-3">
	<c:if test="${!empty revList[3]}">
							
						
							<img style='border-radius:16px; width:32px; height:32px;' src='${pageContext.request.contextPath}/resources/fileUpload/${revList[3].imgname}' onError='this.src="${pageContext.request.contextPath}/resources/img/default.webp"'>																										
	<a style='margin-left:10px;' href="myReviewListForm?id=${revList[3].id}&check=my">${revList[3].id}</a><br><br>
	<p>${revList[3].contents}</p>
	<br><br><br>
	<a href="javascript:reviewView(${revList[3].revno})">리뷰 상세 보기</a>
	</c:if>
	</div>	
	<div class="col-xs-3"></div>
	</div>

	<div class="col-xs-12" style="height:100px;"></div>	
	<div class="col-xs-12">
	<div class="col-xs-3">
	
	</div>	
	<div class="col-xs-3">
	<a href="myReviewListForm?id=${sessionScope.id}&check=${room.rno}">리뷰 전체 보기</a><br>
	</div>	
	<div class="col-xs-3">
	
	</div>	
	<div class="col-xs-3">
	
	</div>	
	
	</div>	
	
		</div>
		</div>
		<div id="modal" style="display:none;">
		<div id="overlay" onclick="modalClose()"></div>
			<div id="modalcontent">
			
					<h1>Air & Room <i style="float:right; margin-right:10px;" onclick="modalClose()" class="far fa-times-circle"></i></h1>
					<div class="col-xs-8" style="height:90%;">
					<div id="plist" style="height:100%;">

					
					</div>
					</div>
					<div class="col-xs-4" style="height:90%">
						<div id="revcontent" style="height:auto">
						
						</div>
						<div id="revcomment" style='overflow-y:auto; overflow-x:hidden; width:100%; height:55%;'>
						
						
						</div>
						<div id="commentinput" class="input-group">
						
						</div>
					</div>

			</div>
	</div>
	<div class="col-xs-12" style="height:100px;"></div>
	
<jsp:include page="../Footer.jsp"></jsp:include>
   
</body>
<script>
function initMap() {
    var map = new google.maps.Map(document.getElementById('map'), {
       center : {
          lat : ${room.latitude},
          lng : ${room.longitude}
       },
       zoom : 17
    });
    var infowindow = new google.maps.InfoWindow();
    var infowindowContent = document.getElementById('infowindow-content');
    infowindow.setContent(infowindowContent);
    var marker = new google.maps.Marker({
       map : map,
       position:map.center
    });
    infowindowContent.children['place-name'].textContent = '${room.rname}';
    infowindowContent.children['place-address'].textContent = '${room.address}';
    infowindow.open(map, marker);
 }

var pos = 0;
var totalSlides = $('#slider-wrap ul li').length;
var sliderWidth = $('#slider-wrap').width();
$(document).ready(function(){
    $('#slider-wrap ul#slider').width(sliderWidth*totalSlides);
    $('#next').click(function(){
        slideRight();
    });
    $('#previous').click(function(){
        slideLeft();
    });
    var autoSlider = setInterval(slideRight, 3000);
    $.each($('#slider-wrap ul li'), function() { 
       var li = document.createElement('li');
       $('#pagination-wrap ul').append(li);    
    });
    countSlides();
    pagination();
    $('#slider-wrap').hover(
      function(){ $(this).addClass('active'); clearInterval(autoSlider); }, 
      function(){ $(this).removeClass('active'); autoSlider = setInterval(slideRight, 3000); }
    );
});
function slideLeft(){
    pos--;
    if(pos==-1){
    	pos = totalSlides-1; 
    }
    $('#slider-wrap ul#slider').css('left', -(sliderWidth*pos));    
    countSlides();
    pagination();
}
function slideRight(){
    pos++;
    if(pos==totalSlides){
    	pos = 0; 
    	}
    $('#slider-wrap ul#slider').css('left', -(sliderWidth*pos));   
    countSlides();
    pagination();
}
function countSlides(){
    $('#counter').html(pos+1 + ' / ' + totalSlides);
}
function pagination(){
    $('#pagination-wrap ul li').removeClass('active');
    $('#pagination-wrap ul li:eq('+pos+')').addClass('active');
}
</script>
   <script
      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD7ZjWhUw8nBnn5lNMyZiaCRu3tMZXKXCY&libraries=places&callback=initMap"
      async defer></script>
</html>