<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>노선 조회하기</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
$(document).ready(function(){
	selectMyAirline(1);
	selectMyAirlinebk(1);
});
</script>
<script>
function deleteAirline(ano,aname) {
    var decisionResult = confirm('정말로 삭제하시겠습니까?');
    if(decisionResult == true) {
	    $.ajax({
	        type:'GET',
	        url : "deleteAirline",
	        data : "ano=" + ano,
	        dataType : "text",
	        success : function(data){
	        	if(data=="Success"){
	        		selectMyAirline(1);
	        	}
	        	else if(data=="Booking"){
	        		 if(confirm("해당 노선을 예약한 유저가 있습니다.\n 예약을 전부 취소하고 삭제하시겠습니까?")){
	        			    $.ajax({
	        			        type:'GET',
	        			        url : "deleteAirlineWithBooking",
	        			        data : "ano=" + ano + "&aname="+aname,
	        			        dataType : "text",
	        			        success : function(data){
	        			        	if(data=="Success"){
	        			        		selectMyAirline(1);
	        			        		selectMyAirlinebk(1);
	        			        	}
	        			        	else if(data=="Fail"){
	        			        		alert("노선 삭제 및 예약 취소 실패");
	        			        	}
	        			        },
	        			        error : function(){
	        		        		alert("노선 삭제 및 예약 취소 도중 에러 발생");
	        			        	
	        			        	}
	        			        })
	        		 }
	        	}
	        	else if(data=="Fail"){
	        		alert("노선 삭제 실패");
	        	}
	        },
	        error : function(){
        		alert("노선 삭제 도중 에러 발생");
	        	
	        	}
	        })
    } 
}


function deleteAirlinebk(abno,id) {
	var decisionResult = confirm('예약 취소하시겠습니까?');
	if(decisionResult) {
	    $.ajax({
	        type:'GET',
	        url : "deleteAirlinebk",
	        data : "abno=" + abno + "&id="+id,
	        dataType : "text",
	        success : function(data){
	        	if(data=="Success"){
	        		selectMyAirlinebk(1);
	        	}
	        	else if(data=="Fail"){
	        		alert("예약 취소 실패");
	        	}
	        },
	        error : function(){
        		alert("예약 취소 도중 에러 발생");
	        	
	        	}
	        })
	}
}
function modifyAirline(ano){
	location.href="modifyAirline?ano="+ano;
}
function scheduleManagement(ano){
	location.href="scheduleManagement?ano="+ano;
}
function selectMyAirline(page){
    $.ajax({
        type:'GET',
        url : "selectMyAirline",
        data : "page=" + page,
        dataType : "json",
        async : false,
        success : function(data){
            var html = "<h2>노선 등록 리스트</h2>";
            var length=data.list.length;
            if(length > 0){
                	html += "<table class='table table-striped table-bordered table-hover'><thead><tr><th>노선번호</th><th>노선이름</th><th>출발지</th><th>도착지</th><th>가격</th><th>승인유무</th><th>일정관리</th><th>수정</th><th>삭제</th></tr></thead>";
                	for(var i=0; i<length; i++){
						html+="<tr><td>"+data.list[i].ano+"</td><td>"+data.list[i].aname+"</td><td>"+data.list[i].startpoint+"</td><td>"+data.list[i].endpoint+"</td><td>"+data.list[i].aprice+"</td><td>"+data.list[i].approval+"</td><td><a href='#' onclick='scheduleManagement("+data.list[i].ano+")'>일정관리</a></td><td><a href='#' onclick='modifyAirline("+data.list[i].ano+")'>수정</a></td><td><a href='#' onclick='deleteAirline("+data.list[i].ano+",\""+data.list[i].aname+"\")'>삭제</a></td></tr>";
                        	var page="<div class='text-center'>";
                    		 page+="<ul class='pagination pagination-sm'>";
                    		var prevPage=data.paging.beginPage-1;
                    		var nextPage=data.paging.endPage+1;
                    		if(data.paging.page!=1){
                    			page += "<li><a href='javascript:selectMyAirline("+1+")'>처음</a></li>";
                    		}
                    		else{
                    			page += "<li class='disable'><a>처음</a></li>";
                    		}
                    		if(data.paging.beginPage!=1){
                    			page +="<li><a href='javascript:selectMyAirline("+prevPage+")'>이전</a></li>";
                    		}
                    		else{
                    			page +="<li class='disable'><a>이전</a></li>";
                    			
                    		}
                    		for(var j=data.paging.beginPage; j<=data.paging.endPage; j++){
                    			if(j==data.paging.page){
                    				page+="<li><a style='color:red'><span>"+j+"</span></a></li>";
                    			}
                    			else{
                    				page+="<li><a href='javascript:selectMyAirline("+j+")'>"+j+"</a></li>";
                    				
                    			}
                    		}
                    		
                    		if(data.paging.endPage!=data.paging.totalPage){
                    			page += "<li><a href='javascript:selectMyAirline("+nextPage+")'>다음</a></li>";
                    		}
                    		else{
                    			page += "<li class='disable'><a>다음</a></li>";
                    		}
                    		if(data.paging.page<data.paging.totalPage){
                    			page +="<li><a href='javascript:selectMyAirline("+data.paging.totalPage+")'>끝</a></li>";
                    		}
                    		else{
                    			page +="<li class='disable'><a>끝</a></li>";
                    		}
                	}
                	html +="</table>";
            		page+="</ul>";
            		page+="</div>";
                	
                }
            else{
            	html += "<table class='table table-striped table-bordered table-hover'><thead><tr><th>노선번호</th><th>노선이름</th><th>출발지</th><th>도착지</th><th>가격</th><th>승인유무</th><th>일정관리</th><th>수정</th><th>삭제</th></tr></thead>";
            	html += "<td colspan='9'>등록한 노선이 존재하지 않습니다.</td>"
            	html +="</table>";
            }
            $("#airlineListPage").html(page);
            $("#airlineList").html(html);
            
        },
        error:function(request,status,error){
            alert("노선 리스트를 불러오는 중 에러 발생");
       }
        
    });
	
}
function selectMyAirlinebk(page){
    $.ajax({
        type:'GET',
        url : "selectMyAirlinebk",
        data : "page=" + page,
        dataType : "json",
        async : false,
        success : function(data){
            var html = "<h2>노선 예약 리스트</h2>";
            var length=data.list.length;
            if(length > 0){
                	html += "<table class='table table-striped table-bordered table-hover'><thead><tr><th>예약번호</th><th>예약자</th><th>노선번호</th><th>노선이름</th><th>츨발날짜</th><th>출발시간</th><th>예약일자</th><th>예약취소</th></tr></thead>";
                	for(var i=0; i<length; i++){
						html+="<tr><td>"+data.list[i].abno+"</td><td>"+data.list[i].id+"</td><td>"+data.list[i].ano+"</td><td>"+data.list[i].aname+"</td><td>"+data.list[i].startdate+"</td><td>"+data.list[i].sctime+"</td><td>"+data.list[i].bookingdate+"</td><td><a href='#' onclick='deleteAirlinebk("+data.list[i].abno+",\""+data.list[i].id+"\")'>예약 취소</a></td></tr>";
                        	var page="<div class='text-center'>";
                    		 page+="<ul class='pagination pagination-sm'>";
                    		var prevPage=data.paging.beginPage-1;
                    		var nextPage=data.paging.endPage+1;
                    		if(data.paging.page!=1){
                    			page += "<li><a href='javascript:selectMyAirlinebk("+1+")'>처음</a></li>";
                    		}
                    		else{
                    			page += "<li class='disable'><a>처음</a></li>";
                    		}
                    		if(data.paging.beginPage!=1){
                    			page +="<li><a href='javascript:selectMyAirlinebk("+prevPage+")'>이전</a></li>";
                    		}
                    		else{
                    			page +="<li class='disable'><a>이전</a></li>";
                    			
                    		}
                    		for(var j=data.paging.beginPage; j<=data.paging.endPage; j++){
                    			if(j==data.paging.page){
                    				page+="<li><a style='color:red'><span>"+j+"</span></a></li>";
                    			}
                    			else{
                    				page+="<li><a href='javascript:selectMyAirlinebk("+j+")'>"+j+"</a></li>";
                    				
                    			}
                    		}
                    		
                    		if(data.paging.endPage!=data.paging.totalPage){
                    			page += "<li><a href='javascript:selectMyAirlinebk("+nextPage+")'>다음</a></li>";
                    		}
                    		else{
                    			page += "<li class='disable'><a>다음</a></li>";
                    		}
                    		if(data.paging.page<data.paging.totalPage){
                    			page +="<li><a href='javascript:selectMyAirlinebk("+data.paging.totalPage+")'>끝</a></li>";
                    		}
                    		else{
                    			page +="<li class='disable'><a>끝</a></li>";
                    		}
                	}
                	html +="</table>";
            		page+="</ul>";
            		page+="</div>";
                	
                }
            else{
            	html += "<table class='table table-striped table-bordered table-hover'><thead><tr><th>예약번호</th><th>예약자</th><th>노선번호</th><th>노선이름</th><th>츨발날짜</th><th>출발시간</th><th>예약일자</th><th>예약취소</th></tr></thead>";
            	html += "<td colspan='8'>예약된 기록이 존재하지 않습니다.</td>"
            	html +="</table>";
            }
            $("#airlineBookingListPage").html(page);
            $("#airlineBookingList").html(html);
            
        },
        error:function(request,status,error){
            alert("노선 예약 리스트를 불러오는 중 에러 발생");
       }
        
    });
	
}
</script>
</head>
<body>
<jsp:include page="../Header.jsp"></jsp:include><br>
<jsp:include page="../Nav.jsp"></jsp:include><br>
<div class="row">
	<div class="col-xs-3">
	<jsp:include page="AirlineSideNav.jsp"></jsp:include>
	</div>
	<div class="col-xs-9">
	<div class="row">
<div id="airlineList">

</div>
<div id="airlineListPage">

</div>
<div id="airlineBookingList">


</div>
<div id="airlineBookingListPage">


</div>
</div>
</div>
</div>
</body>
</html>