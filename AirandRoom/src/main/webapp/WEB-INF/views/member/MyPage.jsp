<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<script>
$(document).ready(function(){
	var kind='${sessionScope.kind}';
	if(kind=="normal"){
		customerRoomsBookingList(1);
		customerAirlinesBookingList(1);
	}
	else if(kind=="admin"){
		adminRoomList(1);
		adminAirlineList(1);
	}
	else if(kind=="airline" || kind=="room"){
		inquiryList(1);
	}
});

</script>
<script>
var sysdate= new Date();
var sysYear=sysdate.getFullYear();
var sysMonth=sysdate.getMonth()+1;
var sysDay=sysdate.getDate();
if(sysMonth<10){
	sysMonth=0+""+sysMonth;
}
if(sysDay<10){
	sysDay=0+""+sysDay;
}
var nowDate=sysYear+"-"+sysMonth+"-"+sysDay;
function checkOut(rbno, rno) {
	$.ajax({
		type : "post",
		url : "checkOut",
		data : "rbno=" + rbno ,
		dataType : "text",
		success : function(result) {
			if (result == "yes") {
				window.open("reviewWriteForm?rno=" + rno, "PopupWin",
						"width=500,height=600");
				customerRoomsBookingList(1);

			} else {
				alert('체크아웃실패');
			}

		},
		error : function() {
			alert("체크아웃 도중 에러 발생");
		}
	});
}

function bookingDelete(rbno){
	if(confirm("예약을 취소하시겠습니까???")){
		$.ajax({
			type:"post",
			url:"bookingDelete",
			data:"rbno="+rbno,
			dataType:"text",
			success:function(result){
				if (result == "Success") {
					customerRoomsBookingList(1);
				} else if(result=="Fail") {
					alert('예약 취소 실패');
				}
			},
			error : function() {
				alert('예약 취소 중 에러 발생');
			}
			
		});
	}
	
}
function roomApproval(rno){
	var rno=rno;
	$.ajax({
		type : "POST",
		url : "roomApproval",
		data : "rno=" + rno,
		dataType : "text",
		success : function(result) {
			if(result=="Success"){
				adminRoomList(1);
			}
			else if(result="Fail"){
					alert("승인 실패");
			}

		},
		error : function() {
			alert("승인 중 에러 발생");
		}
	})
}
function roomDeny(rno){
	var rno=rno;
	if(confirm("거절하시겠습니까?")){
		$.ajax({
			type : "POST",
			url : "roomDeny",
			data : "rno=" + rno,
			dataType : "text",
			success : function(result) {
				if(result=="Success"){
					adminRoomList(1);
				}
				else if(result="Fail"){
						alert("거절 실패");
				}

			},
			error : function() {
				alert("거절 중 에러 발생");
			}
		})
	}
}
function airlineApproval(ano){
	var ano=ano;
	$.ajax({
		type : "POST",
		url : "airlineApproval",
		data : "ano=" + ano,
		dataType : "text",
		success : function(result) {
			if(result=="Success"){
				adminAirlineList(1);
			}
			else if(result="Fail"){
					alert("승인 실패");
			}

		},
		error : function() {
			alert("승인 중 에러 발생");
		}
	})
}
function airlineDeny(rno){
	var ano=ano;
	if(confirm("거절하시겠습니까?")){
		$.ajax({
			type : "POST",
			url : "airlineDeny",
			data : "rno=" + rno,
			dataType : "text",
			success : function(result) {
				if(result=="Success"){
					adminAirlineList(1);
				}
				else if(result="Fail"){
						alert("거절 실패");
				}

			},
			error : function() {
				alert("거절 중 에러 발생");
			}
		})
	}
}
function airlineBookingCancle(abno,startdate){
	var startdate=startdate;
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
	var gap=new Date(startdate) - new Date(nowdate);
	var second = 24 * 60 * 60 * 1000;
	if(gap/second<7){
		alert("당일 및 7일전에는 예약 취소가 불가능합니다.");
	}
	else{
	    $.ajax({
	        type:'GET',
	        url : "deleteAirlinebkByUser",
	        data : "abno=" + abno,
	        dataType : "text",
	        success : function(data){
	        	if(data=="Success"){
	        		customerAirlinesBookingList(1);
	        	}
	        	else if(data=="Fail"){
	        		alert("예약 취소 실패");
	        	}
	        },
	        error : function(){
	    		alert("예약 취소 도중 에러 발생");
	        	
	        	}
	        });
	}
}
function customerRoomsBookingList(page){
    $.ajax({
        type:'GET',
        url : "customerRoomsBookingList",
        data : "page=" + page,
        dataType : "json",
        async : false,
        success : function(data){
            var html = "<h2>숙소 예약리스트</h2>";
            var length=data.list.length;
            if(length > 0){
                	html += "<table class='table table-striped table-bordered table-hover'><thead><tr><th>예약번호</th><th>숙소 번호</th><th>체크인날짜</th><th>체크아웃날짜</th><th>예약날짜</th><th>총 가격</th><th>체크아웃</th><th>예약취소</th></tr></thead>";
                	for(var i=0; i<length; i++){
                		if(data.list[i].checkout=="체크아웃 불가" && data.list[i].cancle=="취소 불가"){
                    		html +="<tr><td>"+data.list[i].rbno+"</td><td>"+data.list[i].rno+"</td><td>"+data.list[i].checkindate+"</td><td>"+data.list[i].checkoutdate+"</td><td>"+data.list[i].bookingdate+"</td><td>"+data.list[i].price+"</td><td>체크아웃 불가</td><td>취소 불가</td></tr>";
                		}
                		else if(data.list[i].checkout=="체크아웃 불가" && data.list[i].cancle=="취소 가능"){
                    		html +="<tr><td>"+data.list[i].rbno+"</td><td>"+data.list[i].rno+"</td><td>"+data.list[i].checkindate+"</td><td>"+data.list[i].checkoutdate+"</td><td>"+data.list[i].bookingdate+"</td><td>"+data.list[i].price+"</td><td>체크아웃 불가</td><td onclick='bookingDelete(\""+data.list[i].rbno+"\")'>취소 가능</td></tr>";
                		}
                		else if(data.list[i].checkout=="체크아웃 가능" && data.list[i].cancle=="취소 불가"){
                    		html +="<tr><td>"+data.list[i].rbno+"</td><td>"+data.list[i].rno+"</td><td>"+data.list[i].checkindate+"</td><td>"+data.list[i].checkoutdate+"</td><td>"+data.list[i].bookingdate+"</td><td>"+data.list[i].price+"</td>";
                    		
                    		 
                    		if(data.list[i].checks=="N"){
                    			html +="<td onclick='checkOut("+data.list[i].rbno+","+data.list[i].rno+")'>체크아웃 가능</td>";
                    		}
                    		else{
                    			html +="<td>체크아웃 완료</td>";
                    			
                    		}
                    		html+="<td>취소 불가</td></tr>";
                		}
                        	var page="<div class='text-center'>";
                    		 page+="<ul class='pagination pagination-sm'>";
                    		var prevPage=data.paging.beginPage-1;
                    		var nextPage=data.paging.endPage+1;
                    		if(data.paging.page!=1){
                    			page += "<li><a href='javascript:customerRoomsBookingList("+1+")'>처음</a></li>";
                    		}
                    		else{
                    			page += "<li class='disable'><a>처음</a></li>";
                    		}
                    		if(data.paging.beginPage!=1){
                    			page +="<li><a href='javascript:customerRoomsBookingList("+prevPage+")'>이전</a></li>";
                    		}
                    		else{
                    			page +="<li class='disable'><a>이전</a></li>";
                    			
                    		}
                    		for(var j=data.paging.beginPage; j<=data.paging.endPage; j++){
                    			if(j==data.paging.page){
                    				page+="<li><a style='color:red'><span>"+j+"</span></a></li>";
                    			}
                    			else{
                    				page+="<li><a href='javascript:customerRoomsBookingList("+j+")'>"+j+"</a></li>";
                    				
                    			}
                    		}
                    		
                    		if(data.paging.endPage!=data.paging.totalPage){
                    			page += "<li><a href='javascript:customerRoomsBookingList("+nextPage+")'>다음</a></li>";
                    		}
                    		else{
                    			page += "<li class='disable'><a>다음</a></li>";
                    		}
                    		if(data.paging.page<data.paging.totalPage){
                    			page +="<li><a href='javascript:customerRoomsBookingList("+data.paging.totalPage+")'>끝</a></li>";
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
            	html += "<table class='table table-striped table-bordered table-hover'><thead><tr><th>예약번호</th><th>숙소 번호</th><th>체크인날짜</th><th>체크아웃날짜</th><th>예약날짜</th><th>총 가격</th></tr></thead>";
            	html += "<td colspan='6'>숙소 예약 기록이 존재하지 않습니다.</td>"
            	html +="</table>";
            }
            $("#roomBookingListPage").html(page);
            $("#roomBookingList").html(html);
            
        },
        error:function(request,status,error){
            alert("숙소 예약 리스트를 불러오는 중 에러 발생");
       }
        
    });
	
}

function customerAirlinesBookingList(page){
    $.ajax({
        type:'GET',
        url : "customerAirlinesBookingList",
        data : "page=" + page,
        dataType : "json",
        success : function(data){
            var html = "<h2>항공권 예약리스트</h2>";
            var length=data.list.length;
            if(length > 0){
                	html += "<table class='table table-striped table-bordered table-hover'><thead><tr><th>예약번호</th><th>노선 번호</th><th>출발날짜</th><th>출발시간</th><th>예약날짜</th><th>가격</th><th>예약취소</th></tr></thead>";
                	for(var i=0; i<length; i++){
                        	html +="<tr><td>"+data.list[i].abno+"</td><td>"+data.list[i].ano+"</td><td>"+data.list[i].startdate+"</td><td>"+data.list[i].sctime+"</td><td>"+data.list[i].bookingdate+"</td><td>"+data.list[i].aprice+"</td><td><a href='#' onclick='airlineBookingCancle("+data.list[i].abno+",\""+data.list[i].startdate+"\")'>예약취소</a></td></tr>";
                        	var page="<div class='text-center'>";
                    		page+="<ul class='pagination pagination-sm'>";
                    		var prevPage=data.paging.beginPage-1;
                    		var nextPage=data.paging.endPage+1;
                    		if(data.paging.page!=1){
                    			page += "<li><a href='javascript:customerAirlinesBookingList("+1+")'>처음</a></li>";
                    		}
                    		else{
                    			page += "<li class='disable'><a>처음</a></li>";
                    		}
                    		if(data.paging.beginPage!=1){
                    			page +="<li><a href='javascript:customerAirlinesBookingList("+prevPage+")'>이전</a></li>";
                    		}
                    		else{
                    			page +="<li class='disable'><a>이전</a></li>";
                    			
                    		}
                    		for(var j=data.paging.beginPage; j<=data.paging.endPage; j++){
                    			if(j==data.paging.page){
                    				page+="<li><a style='color:red'><span>"+j+"</span></a></li>";
                    			}
                    			else{
                    				page+="<li><a href='javascript:customerAirlinesBookingList("+j+")'>"+j+"</a></li>";
                    				
                    			}
                    		}
                    		
                    		if(data.paging.endPage!=data.paging.totalPage){
                    			page += "<li><a href='javascript:customerAirlinesBookingList("+nextPage+")'>다음</a></li>";
                    		}
                    		else{
                    			page += "<li class='disable'><a>다음</a></li>";
                    		}
                    		if(data.paging.page<data.paging.totalPage){
                    			page +="<li><a href='javascript:customerAirlinesBookingList("+data.paging.totalPage+")'>끝</a></li>";
                    		}
                    		else{
                    			page +="<li class='disable'><a>끝</a></li>";
                    		}
                    		page+="</ul>";
                    		page+="</div>";
                	}
                	html +="</table>";
                	
                }
            else{
            	html += "<table class='table table-striped table-bordered table-hover'><thead><tr><th>예약번호</th><th>숙소 번호</th><th>체크인날짜</th><th>체크아웃날짜</th><th>예약날짜</th><th>총 가격</th><th>예약취소</th></tr></thead>";
            	html += "<td colspan='7'>항공권 예약 기록이 존재하지 않습니다.</td>"
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
function adminRoomList(page){
    $.ajax({
        type:'GET',
        url : "adminRoomList",
        data : "page=" + page,
        dataType : "json",
        async : false,
        success : function(data){
            var html = "<h2>숙소 등록 요청리스트</h2>";
            var length=data.list.length;
            if(length > 0){
                	html += "<table class='table table-striped table-bordered table-hover'><thead><tr><th>숙소번호</th><th>숙소이름</th><th>국가</th><th>지역</th><th>상세위치</th><th>가격</th><th>승인</th><th>거절</th></tr></thead>";
                	for(var i=0; i<length; i++){
							html+="<tr><td>"+data.list[i].rno+"</td><td>"+data.list[i].rname+"</td><td>"+data.list[i].nation+"</td><td>"+data.list[i].region+"</td><td>"+data.list[i].address+"</td><td>"+data.list[i].price+"</td><td onclick='roomApproval("+data.list[i].rno+")'>승인</td><td onclick='roomDeny("+data.list[i].rno+")'>거절</td></tr>";
							var page="<div class='text-center'>";
                    		 page+="<ul class='pagination pagination-sm'>";
                    		var prevPage=data.paging.beginPage-1;
                    		var nextPage=data.paging.endPage+1;
                    		if(data.paging.page!=1){
                    			page += "<li><a href='javascript:adminRoomList("+1+")'>처음</a></li>";
                    		}
                    		else{
                    			page += "<li class='disable'><a>처음</a></li>";
                    		}
                    		if(data.paging.beginPage!=1){
                    			page +="<li><a href='javascript:adminRoomList("+prevPage+")'>이전</a></li>";
                    		}
                    		else{
                    			page +="<li class='disable'><a>이전</a></li>";
                    			
                    		}
                    		for(var j=data.paging.beginPage; j<=data.paging.endPage; j++){
                    			if(j==data.paging.page){
                    				page+="<li><a style='color:red'><span>"+j+"</span></a></li>";
                    			}
                    			else{
                    				page+="<li><a href='javascript:adminRoomList("+j+")'>"+j+"</a></li>";
                    				
                    			}
                    		}
                    		
                    		if(data.paging.endPage!=data.paging.totalPage){
                    			page += "<li><a href='javascript:adminRoomList("+nextPage+")'>다음</a></li>";
                    		}
                    		else{
                    			page += "<li class='disable'><a>다음</a></li>";
                    		}
                    		if(data.paging.page<data.paging.totalPage){
                    			page +="<li><a href='javascript:adminRoomList("+data.paging.totalPage+")'>끝</a></li>";
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
            	html += "<table class='table table-striped table-bordered table-hover'><thead><tr><th>숙소번호</th><th>숙소이름</th><th>국가</th><th>지역</th><th>상세위치</th><th>가격</th><th>승인</th><th>거절</th></tr></thead>";
            	html += "<td colspan='8'>숙소 등록 신청 기록이 존재하지 않습니다.</td>"
            	html +="</table>";
            }
            $("#roomListPage").html(page);
            $("#roomList").html(html);
            
        },
        error:function(request,status,error){
            alert("숙소 등록 신청 리스트를 불러오는 중 에러 발생");
       }
        
    });
	
}
function adminAirlineList(page){
    $.ajax({
        type:'GET',
        url : "adminAirlineList",
        data : "page=" + page,
        dataType : "json",
        async : false,
        success : function(data){
            var html = "<h2>노선 등록 요청리스트</h2>";
            var length=data.list.length;
            if(length > 0){
                	html += "<table class='table table-striped table-bordered table-hover'><thead><tr><th>노선번호</th><th>노선이름</th><th></th><th>출발지</th><th>도착지</th><th>가격</th><th>승인</th><th>거절</th></tr></thead>";
                	for(var i=0; i<length; i++){
						html+="<tr><td>"+data.list[i].ano+"</td><td>"+data.list[i].aname+"</td><td>"+data.list[i].startpoint+"</td><td>"+data.list[i].endpoint+"</td><td>"+data.list[i].aprice+"</td><td onclick='airlineApproval("+data.list[i].ano+")'>승인</td><td onclick='airlineDeny("+data.list[i].ano+")'>거절</td></tr>";
                        	var page="<div class='text-center'>";
                    		 page+="<ul class='pagination pagination-sm'>";
                    		var prevPage=data.paging.beginPage-1;
                    		var nextPage=data.paging.endPage+1;
                    		if(data.paging.page!=1){
                    			page += "<li><a href='javascript:adminAirlineList("+1+")'>처음</a></li>";
                    		}
                    		else{
                    			page += "<li class='disable'><a>처음</a></li>";
                    		}
                    		if(data.paging.beginPage!=1){
                    			page +="<li><a href='javascript:adminAirlineList("+prevPage+")'>이전</a></li>";
                    		}
                    		else{
                    			page +="<li class='disable'><a>이전</a></li>";
                    			
                    		}
                    		for(var j=data.paging.beginPage; j<=data.paging.endPage; j++){
                    			if(j==data.paging.page){
                    				page+="<li><a style='color:red'><span>"+j+"</span></a></li>";
                    			}
                    			else{
                    				page+="<li><a href='javascript:adminAirlineList("+j+")'>"+j+"</a></li>";
                    				
                    			}
                    		}
                    		
                    		if(data.paging.endPage!=data.paging.totalPage){
                    			page += "<li><a href='javascript:adminAirlineList("+nextPage+")'>다음</a></li>";
                    		}
                    		else{
                    			page += "<li class='disable'><a>다음</a></li>";
                    		}
                    		if(data.paging.page<data.paging.totalPage){
                    			page +="<li><a href='javascript:adminAirlineList("+data.paging.totalPage+")'>끝</a></li>";
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
            	html += "<table class='table table-striped table-bordered table-hover'><thead><tr><th>노선번호</th><th>노선이름</th><th>출발지</th><th>도착지</th><th>가격</th><th>승인</th><th>거절</th></tr></thead>";
            	html += "<td colspan='7'>노선 등록 신청 기록이 존재하지 않습니다.</td>"
            	html +="</table>";
            }
            $("#airlineListPage").html(page);
            $("#airlineList").html(html);
            
        },
        error:function(request,status,error){
            alert("노선 등록 신청 리스트를 불러오는 중 에러 발생");
       }
        
    });
	
}
function inquiryList(page){
    $.ajax({
        type:'GET',
        url : "selectInquire",
        data : "page=" + page + "&otherid="+"${id}",
        dataType : "json",
        async : false,
        success : function(data){
            var html = "<h2>문의내역 보기</h2>";
            var length=data.list.length;
            if(length > 0){
                	html += "<table class='table table-striped table-bordered table-hover'><thead><tr><th>문의번호</th><th>문의글제목</th><th>작성자</th><th>작성시간</th><th>답변유무</th></tr></thead>";
                	for(var i=0; i<length; i++){
						html+="<tr onclick='selectInquirePost("+data.list[i].ino+")'><td>"+data.list[i].ino+"</td><td>"+data.list[i].title+"</td><td>"+data.list[i].id+"</td><td>"+data.list[i].writedate+"</td>";
						if(data.list[i].answer=="N"){
							html+="<td>아직 답변 하지 않음</td>";
						}
						else{
							html+="<td>답변 완료</td>";
						}
						html+="</tr>";
                        	var page="<div class='text-center'>";
                    		 page+="<ul class='pagination pagination-sm'>";
                    		var prevPage=data.paging.beginPage-1;
                    		var nextPage=data.paging.endPage+1;
                    		if(data.paging.page!=1){
                    			page += "<li><a href='javascript:inquiryList("+1+")'>처음</a></li>";
                    		}
                    		else{
                    			page += "<li class='disable'><a>처음</a></li>";
                    		}
                    		if(data.paging.beginPage!=1){
                    			page +="<li><a href='javascript:inquiryList("+prevPage+")'>이전</a></li>";
                    		}
                    		else{
                    			page +="<li class='disable'><a>이전</a></li>";
                    			
                    		}
                    		for(var j=data.paging.beginPage; j<=data.paging.endPage; j++){
                    			if(j==data.paging.page){
                    				page+="<li><a style='color:red'><span>"+j+"</span></a></li>";
                    			}
                    			else{
                    				page+="<li><a href='javascript:inquiryList("+j+")'>"+j+"</a></li>";
                    				
                    			}
                    		}
                    		
                    		if(data.paging.endPage!=data.paging.totalPage){
                    			page += "<li><a href='javascript:inquiryList("+nextPage+")'>다음</a></li>";
                    		}
                    		else{
                    			page += "<li class='disable'><a>다음</a></li>";
                    		}
                    		if(data.paging.page<data.paging.totalPage){
                    			page +="<li><a href='javascript:inquiryList("+data.paging.totalPage+")'>끝</a></li>";
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
            	html += "<table class='table table-striped table-bordered table-hover'><thead><tr><th>문의번호</th><th>문의글제목</th><th>작성자</th><th>작성시간</th></tr></thead>";
            	html += "<td colspan='4'>문의내역이 존재하지 않습니다.</td>"
            	html +="</table>";
            }
            $("#inquiryListPage").html(page);
            $("#inquiryList").html(html);
            
        },
        error:function(request,status,error){
            alert("문의 내역 리스트를 불러오는 중 에러 발생");
       }
        
    });
	
}
function selectInquirePost(ino){
	var popUrl = "selectInquirePost?ino="+ino;	

	var popOption = "width=450, height=650, resizable=no, scrollbars=no, status=no;";    //팝업창 옵션(optoin)

		window.open(popUrl,"",popOption);

	}
</script>
<body>
<jsp:include page="../Header.jsp"></jsp:include><br>
<jsp:include page="../Nav.jsp"></jsp:include><br>
<div class="row">
	<div class="col-xs-3">
	<jsp:include page="SideNav.jsp"></jsp:include>
	</div>
	<div class="col-xs-9">
		<div id="roomBookingList">
		
		</div>
				<div id="roomBookingListPage">
				</div>
		<div id="airlineBookingList">
		
		</div>
				<div id="airlineBookingListPage">
				</div>
				<div id="roomList">
				
				</div>
				<div id="roomListPage">
				</div>
				<div id="airlineList">
				
				</div>
				<div id="airlineListPage">
				</div>
				<div id="inquiryList">
				
				</div>
				<div id="inquiryListPage">
				</div>
	</div>
</div>
</body>
</html>