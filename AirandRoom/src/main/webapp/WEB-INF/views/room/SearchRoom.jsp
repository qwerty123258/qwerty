<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>숙소검색</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="//code.jquery.com/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>
<style>
.pic{
width:80%;
height:150px;
}
td,tr,th{
width:150px;
}
#map{
width:400px;
height:500px;
}
@media all and (max-width:1250px){
#mapar{
display:none;
}
#listar{
width:100%;
}
}
</style>
<script>
var list=[];
$( function() {
	$("#keyword").autocomplete({
        source : function( request, response ) {
        	list=[];
             $.ajax({
                    type: "GET",
                    url: "selectKeyword",
                    dataType: "json",
                    data: "keyword=" + $("#keyword").val(),
                    success: function(data) {
                    	for(var i=0; i<data.length; i++){
                    		if(i==0 && data[0].searchoutput=="nation"){
                    			list.push(data[i].nation);
                    		}
                    		else if(i==0 && data[0].searchoutput=="region"){
                    			list.push(data[i].region);
                    		}
                    		else{
                    			list.push(data[i].nation+" "+data[i].region);
                    		}
                    	}

                    	response(
                            $.map(list, function(item) {
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
  } );
</script>
<script>
function search(){
	var keyword=$("#keyword").val();
	if(keyword==""){
		alert("검색할 키워드가 비어있습니다.");
	}
	else{
		$.ajax({
            type: "GET",
            url: "searchRoom",
            dataType: "json",
            data: "keyword=" + keyword +"&page=1",
            success: function(data) {
            	var html="";
        		html+="<h3>"+data.output+"에 대한 숙소 검색결과</h1>";
        		html+="<div class='col-xs-4'>";
        		html+="<div id='mapar'>";
        		html+="<div id='map'></div>";
        		html+="<div id='infowindow-content'>";
        		html+="<span id='place-name' class='title'></span>";
        		html+="<br> <span id='place-address'></span>";
        		html+="</div>";
        		html+="</div>";
        		html+="</div>";
        		html+="<div id='listar' class='col-xs-8'>";
        		html+="<table class='table table-striped table-bordered table-hover'>";
        		html+="<thead>";
        		html+="<tr>";
        		html+="<th>구분</th>";
        		html+="<th>숙소 이름</th>";
        		html+="<th>상세 위치</th>";
        		html+="<th>1박시 가격</th>";
        		html+="</tr>";
        		html+="</thead>";
            	for(var i=0; i<data.roomList.length; i++){
					html+="<tr onclick='roomDetail("+data.roomList[i].rno+")'>";
                    html+="<td><img class='pic' src='${pageContext.request.contextPath}/resources/fileUpload/"+data.roomList[i].rimgname+"'></td>";
                    html+="<td>"+data.roomList[i].rname+"</td>";
                    html+="<td>"+data.roomList[i].address+"</td>";
                    html+="<td>"+data.roomList[i].price+"</td>";
                    html+="</tr>";
                    latitude.push(data.roomList[i].latitude);
                    longitude.push(data.roomList[i].longitude);
                    roomname.push(data.roomList[i].rname);
                    address.push(data.roomList[i].address);
            	}
            	html+="</table>";
            	$("#searchoutput").html(html);
            	initMap();
            },
			error :function(){
				alert("검색 도중 에러 발생");
			}
		});
	}
}
function enter(){
    if (window.event.keyCode == 13) {
    	search();
   }
}
function roomDetail(rno){
	   var rno=rno;
	   window.open("roomView?page=1&rno="+rno);
}
var latitude=[];
var longitude=[];
var roomname=[];
var address=[];
</script>

</head>
<body>
	<jsp:include page="../Header.jsp"></jsp:include><br>
	<jsp:include page="../Nav.jsp"></jsp:include><br>
	<div class="col-xs-3">
	</div>
 <div class="col-xs-6">
  <input id="keyword" onkeyup="enter()"  placeholder="숙소검색" style="border-radius:20px;  font-size:28px; height:60px; width:100%; outline:none;">
</div>
<div class="col-xs-3" style='margin-top:6px;'>
<i onclick="search()" class="fas fa-search" style="font-size:56px;" ></i>
</div><br>
<div class="col-xs-12">
<div class="col-xs-10">
<div id="searchoutput">
<div id="map" style="display:none"></div>

</div>
</div>
<div class="col-xs-2">
		<jsp:include page="../Time.jsp" ></jsp:include>
</div>
</div>
</body>
   <script
      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD7ZjWhUw8nBnn5lNMyZiaCRu3tMZXKXCY&libraries=places&callback=initMap"
      async defer></script>
      <script>
      var coordinates=[];
      function initMap() {
         var mapOptions = {
               streetViewControl: false,
               mapTypeControl: false,
               mapTypeId: google.maps.MapTypeId.ROADMAP
               }
               map = new google.maps.Map(document.getElementById('map'), mapOptions);
         var bounds = new google.maps.LatLngBounds();
         for (var i=0; i < latitude.length; i++) {
            var latlng = new google.maps.LatLng(latitude[i], longitude[i]);
            coordinates.push(latlng);  
               bounds.extend(coordinates[i]);
         }
         map.fitBounds(bounds);
           for(var i=0; i<latitude.length; i++){
               var infowindow = new google.maps.InfoWindow();
               var marker = new google.maps.Marker({
                  map : map,
                   id:i,
                   position: new google.maps.LatLng(latitude[i], longitude[i])
               });
                 google.maps.event.addListener(marker, 'click', (function(marker, i) {
                     return function() {
                       infowindow.setContent(roomname[i]+"<br>"+address[i]);
                       infowindow.open(map, marker);
                     }

                   })(marker, i));

           }
           
        }
      </script>
</html>
