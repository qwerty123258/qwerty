<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<head>
	<title>메인 페이지</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<script>
function getCookie(name) {
    var Found = false
    var start, end
    var i = 0
    while(i <= document.cookie.length) {
      start = i
      end = start + name.length  
      if(document.cookie.substring(start, end) == name) {
        Found = true
        break
      }
      i++
    }  
    if(Found == true) {
      start = end + 1
      end = document.cookie.indexOf(";", start)
        if(end < start)
          end = document.cookie.length
      return document.cookie.substring(start, end)
    }
    return ""
}



$(function() {
	most10();
    var noticeCookie=getCookie("popup");
    if (noticeCookie != "no"){
        window.open('goPopup','popTest','width=450,height=650');
    }else{
    	
    }
});
function most10(){
    $.ajax({
        type:'GET',
        url : "mostRoomList",
        dataType : "json",
        async : false,
        success : function(data){
        	var html="";
        	html+="<div class='container'>";
        	html+="<h2>"+data.month+"월 동안의 인기 숙소</h2>";
        	if(data.list.length>0){
				for(var i=0; i<data.list.length; i++){
					html+="<div style='width:10%; float:left;' onclick='roomView("+data.list[i].rno+")'>";
					html+="<div style='width:100px;'><img style='width:100px;height:100px;' src='${pageContext.request.contextPath}/resources/fileUpload/"+data.list[i].rimgname+"' /></div>";
					html+="<p style='text-overflow: ellipsis;'>"+data.list[i].rname+"</p>";
					html+="<p>가격 :"+data.list[i].price+"</p>";
					html+="</div>";
				}	
        	}
        	else if(data.list.length>9){
				for(var i=0; i<9; i++){
					html+="<div style='width:10%' onclick='roomView("+data.list[i].rno+")'>";
					html+="<div style='width:100px;'><img style='width:100px;height:100px;' src='${pageContext.request.contextPath}/resources/fileUpload/"+data.list[i].rimgname+"' /></div>";
					html+="<p>"+data.list[i].rname+"</p>";
					html+="<p>가격 :"+data.list[i].price+"</p>";
					html+="</div>";
				}
        	}
			html+="</div>";
        	$("#most10").html(html);
        },
        error:function(){
        	
        }
        });
}
function roomView(rno){
	   location.href="roomView?page=1&rno="+rno;
}
</script>
<style>
div p{
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  width: 100px;
  height: 20px;
}

</style>
<body>
<jsp:include page="Header.jsp"></jsp:include><br>
<jsp:include page="Nav.jsp"></jsp:include><br>
<div id="most10">

</div>
<div class="col-xs-12" style="height:50px;">
</div>
<div class="col-xs-12">

<div class="col-xs-4">
<div class="row" style="height:20%">


</div>
</div>
<div class="col-xs-4">

</div>
<div class="col-xs-4">
<jsp:include page="Time.jsp"></jsp:include>

</div>
</div>
</body>
</html>
