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
@media all and (max-width:1600px){
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
 <div class="container">
 <div class="col-xs-12">
 <div class="col-xs-9">
 
       <div class="group">      
      <input type="text" onkeyup="enter()" id="keyword" class="searchbar" required>
      <span class="highlight"></span>
      <span class="bar"></span>
      <label><i class="fas fa-search" style="font-size:25px;" ></i>국가/지역</label>     
    </div>
 </div>
 <div class="col-xs-3">
 <button onclick="search()" class="btn btn-success" style="margin-left:50px; background-color: #555555; border: none; padding:10px;"> <i class="fas fa-search" style="font-size:25px;" ></i>검색</button>
 
 </div>
 
  </div>
 </div>
</div>
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
