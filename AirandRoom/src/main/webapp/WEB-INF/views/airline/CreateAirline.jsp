<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>노선 등록</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script>
function departure() {
	var value = $("#pac-input").val();
    $("#departure").val(value);
    $("#pac-input").val("");
}
function arrive() {
	var value = $("#pac-input").val();
    $("#arrive").val(value);
    $("#pac-input").val("");
}
function alterValue() {
	var value1 = $("#departure").val();
	var value2 = $("#arrive").val();
	var alter = 0;
		alter = value1;
		console.log(alter);
		$("#departure").val(value2);
		$("#arrive").val(alter);
}
function checkHour() {
	var hour = $("#hour").val();
	var val = /^[0-9]+$/;
	if(!hour.match(val)){
		$('#checkTakenTime').css("color", "red");
		$('#checkTakenTime').html("숫자만 입력 가능합니다.");
	}
	else{
		$('#checkTakenTime').html("");
	}
	}
function checkMinute() {
	var minute = $("#minute").val();
	var val = /^[0-9]+$/;
	if(!minute.match(val)){
		$('#checkTakenTime').css("color", "red");
		$('#checkTakenTime').html("숫자만 입력 가능합니다.");
	}
	else{
		$('#checkTakenTime').html("");
	}
	}
function checkSrow() {
	var srow = $("#srow").val();
	var val = /^[0-9]+$/;
	if(!srow.match(val)){
		$('#checkSrow').css("color", "red");
		$('#checkSrow').html("숫자만 입력 가능합니다.");
	}
	else{
		$('#checkSrow').html("");
	}
	}
function checkScolumn() {
	var scolumn = $("#scolumn").val();
	var val = /^[0-9]+$/;
	if(!scolumn.match(val)){
		$('#checkScolumn').css("color", "red");
		$('#checkScolumn').html("숫자만 입력 가능합니다.");
	}
	else{
		$('#checkScolumn').html("");
	}
	}
function checkAprice() {
	var aPrice = $("#aPrice").val();
	var val = /^[0-9]+$/;
	if(!aPrice.match(val)){
		$('#checkAprice').css("color", "red");
		$('#checkAprice').html("숫자만 입력 가능합니다.");
	}
	else{
		$('#checkAprice').html("");
		$('#checkApriceResult').val("승인");
	}
}
function createAirline() {
	var aName = $("#aName").val();
	var airportType = $("#airportType").val();
	var departure = $("#departure").val();
	var arrive = $("#arrive").val();
	var aPrice = $("#aPrice").val();
	var value= /^[0-9]+$/;
	if($("#airportType").val() == ""){
		alert("항공사를 설정해주세요.");
		$("#airportType").focus();
	}
	else if(!aPrice.match(value)){
		alert("가격을 설정해주세요.");
		$("#aPrice").focus();

	}
	else if(departure==""){
		alert("출발지를 설정해주세요.");
		$("departure").focus();
	}
	else if(arrive==""){
		alert("도착지를 설정해주세요.");
		$("arrive").focus();
	}
	else if($("#srow").val()<0){
		alert("잘못된 입력입니다.");
		$("#srow").focus();
	}
	else if($("#scolumn").val()<0){
		alert("잘못된 입력입니다.");
		$("#scolumn").focus();
	}
	else {
    	var id = "${id}";
		$.ajax({
    		type : "POST",
    		url : "registerAirline",
    		dataType : "json", //json
    		data : "id=" + id + 
    		       "&aname=" + $("#aName").val() +
    		       "&airporttype=" + airportType +
    		       "&minute=" + $("#minute").val() +
    		       "&hour=" + $("#hour").val() +
    		       "&srow=" + $("#srow").val() +
    		       "&scolumn=" + $("#scolumn").val() +
    		       "&aprice=" + $("#aPrice").val() +
    		       "&startpoint=" + $("#departure").val() +
    		       "&endpoint=" + $("#arrive").val() +
    		       "&approval=" + "N",
    			success : function(createResult) {
    			if(createResult>0){
        			alert("노선 등록이 완료되었습니다.");
        			location.href="airlineManagement";
    			}
    			else{
    				alert("노선 등록 실패");
    			}

    		},
    		error : function() {
    			console.log("노선 등록 중 에러 발생");
    		}    	    		    		
    	});
	}		
}
</script>
<style>
      /* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
      #map {
        height: 400px;
        width: 900px;
      }
      /* Optional: Makes the sample page fill the window. */
      html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
      #description {
        font-family: Roboto;
        font-size: 15px;
        font-weight: 300;
      }

      #infowindow-content .title {
        font-weight: bold;
      }

      #infowindow-content {
        display: none;
      }

      #map #infowindow-content {
        display: inline;
      }

      .pac-card {
        margin: 10px 10px 0 0;
        border-radius: 2px 0 0 2px;
        box-sizing: border-box;
        -moz-box-sizing: border-box;
        outline: none;
        box-shadow: 0 2px 6px rgba(0, 0, 0, 0.3);
        background-color: #fff;
        font-family: Roboto;
      }

      #pac-container {
        padding-bottom: 12px;
        margin-right: 12px;
      }

      .pac-controls {
        display: inline-block;
        padding: 5px 11px;
      }

      .pac-controls label {
        font-family: Roboto;
        font-size: 13px;
        font-weight: 300;
      }

      #pac-input {
        background-color: #fff;
        font-family: Roboto;
        font-size: 15px;
        font-weight: 300;
        margin-left: 12px;
        padding: 0 11px 0 13px;
        text-overflow: ellipsis;
        width: 400px;
      }

      #pac-input:focus {
        border-color: #4d90fe;
      }

      #title {
        color: #fff;
        background-color: #4d90fe;
        font-size: 25px;
        font-weight: 500;
        padding: 6px 12px;
      }
      
      #mandu {
        background-color: #4d90fe;
        font-family: Roboto;
        font-size: 15px;
        font-weight: 300;
        margin-left: 12px;
        padding: 0 11px 0 13px;
        text-overflow: ellipsis;   
      }
</style>
</head>
<body>
<jsp:include page="../Header.jsp"></jsp:include><br>
	<jsp:include page="../Nav.jsp"></jsp:include>
<div class="row">
	<div class="col-xs-3">
	<jsp:include page="AirlineSideNav.jsp"></jsp:include>
	</div>
	<div class="col-xs-9">
	<div class="row">
	    <h1>노선 등록 페이지입니다.</h1>
        노선 이름 : <input type="text" id="aName" name ="aName"><br>
        항공사 종류 : 
        <input type="text" id="airportType" name = "airportType"><br>
        소요 시간 : <input type="number" id="hour" name="hour" onkeyup="checkHour()"> 시간
        <input type="number" id="minute" name="minute" onkeyup="checkMinute()"> 분<br>
                    <span id="checkTakenTime"></span>
        <br>
        좌석의 행 입력 : <input type="number" id="srow" name="srow" onkeyup="checkSrow()">
                    <span id="checkSrow"></span>
                    <br>
      좌석의 열 입력 : <input type="number" id="scolumn" name="scolumn" onkeyup="checkScolumn()">
      <span id="checkScolumn"></span>
        <br>
        노선 가격 : <input type="text" id="aPrice" name="aPrice" onkeyup="checkAprice()">
                    <span id="checkAprice"></span>
        <br>
    <div class="pac-card" id="pac-card">
      <div>
        <div id="title">
                        장소 검색하기
        </div>
        <div id="type-selector" class="pac-controls">
          <input type="radio" name="type" id="changetype-all" checked="checked">
          <label for="changetype-all">All</label>

          <input type="radio" name="type" id="changetype-establishment">
          <label for="changetype-establishment">Establishments</label>

          <input type="radio" name="type" id="changetype-address">
          <label for="changetype-address">Addresses</label>

          <input type="radio" name="type" id="changetype-geocode">
          <label for="changetype-geocode">Geocodes</label>
        </div>
        <div id="strict-bounds-selector" class="pac-controls">
          <input type="checkbox" id="use-strict-bounds" value="">
          <label for="use-strict-bounds">Strict Bounds</label>
        </div>
      </div>
      <div id="pac-container">
        <input id="pac-input" type="text"
            placeholder="Enter a location">
        <button onclick="departure()" id="mandu"><font color=white>출발지로</font></button>
        <button onclick="arrive()" id="mandu"><font color=white>도착지로</font></button>            
      </div>
    </div>
    <div id="map" ></div>
    <div id="infowindow-content">
      <img src="" width="16" height="16" id="place-icon">
      <span id="place-name"  class="title"></span><br>
      <span id="place-address"></span>
    </div>
        <input id="departure" name="departure" type="text"
            placeholder="출발지" readonly>
         <button onclick="alterValue()">출발지,도착지 바꾸기</button>   
        <input id="arrive" name="arrive" type="text"
            placeholder="도착지" readonly><br>
        <button onclick="createAirline()">노선 등록하기</button> 
	</div>
	</div>
</div>
</body>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD7ZjWhUw8nBnn5lNMyZiaCRu3tMZXKXCY&libraries=places&callback=initMap"
        async defer></script>
    <script>
      // This example requires the Places library. Include the libraries=places
      // parameter when you first load the API. For example:
      // <script src="https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&libraries=places">
      function initMap() {
        var map = new google.maps.Map(document.getElementById('map'), {
          center: {lat: -33.8688, lng: 151.2195},
          zoom: 13
        });
        var card = document.getElementById('pac-card');
        var input = document.getElementById('pac-input');
        var types = document.getElementById('type-selector');
        var strictBounds = document.getElementById('strict-bounds-selector');
        map.controls[google.maps.ControlPosition.TOP_RIGHT].push(card);
        var autocomplete = new google.maps.places.Autocomplete(input);
        // Bind the map's bounds (viewport) property to the autocomplete object,
        // so that the autocomplete requests use the current map bounds for the
        // bounds option in the request.
        autocomplete.bindTo('bounds', map);
        // Set the data fields to return when the user selects a place.
        autocomplete.setFields(
            ['address_components', 'geometry', 'icon', 'name']);
        var infowindow = new google.maps.InfoWindow();
        var infowindowContent = document.getElementById('infowindow-content');
        infowindow.setContent(infowindowContent);
        var marker = new google.maps.Marker({
          map: map,
          anchorPoint: new google.maps.Point(0, -29)
        });
        autocomplete.addListener('place_changed', function() {
          infowindow.close();
          marker.setVisible(false);
          var place = autocomplete.getPlace();
          if (!place.geometry) {
            // User entered the name of a Place that was not suggested and
            // pressed the Enter key, or the Place Details request failed.
            window.alert("No details available for input: '" + place.name + "'");
            return;
          }
          // If the place has a geometry, then present it on a map.
          if (place.geometry.viewport) {
            map.fitBounds(place.geometry.viewport);
          } else {
            map.setCenter(place.geometry.location);
            map.setZoom(17);  // Why 17? Because it looks good.
          }
          marker.setPosition(place.geometry.location);
          marker.setVisible(true);
          var address = '';
          if (place.address_components) {
            address = [
              (place.address_components[0] && place.address_components[0].short_name || ''),
              (place.address_components[1] && place.address_components[1].short_name || ''),
              (place.address_components[2] && place.address_components[2].short_name || '')
            ].join(' ');
          }
          $("#pac-input").val(place.name);
          infowindowContent.children['place-icon'].src = place.icon;
          infowindowContent.children['place-name'].textContent = place.name;
          infowindowContent.children['place-address'].textContent = address;
          infowindow.open(map, marker);
        });
        // Sets a listener on a radio button to change the filter type on Places
        // Autocomplete.
        function setupClickListener(id, types) {
          var radioButton = document.getElementById(id);
          radioButton.addEventListener('click', function() {
            autocomplete.setTypes(types);
          });
        }
        setupClickListener('changetype-all', []);
        setupClickListener('changetype-address', ['address']);
        setupClickListener('changetype-establishment', ['establishment']);
        setupClickListener('changetype-geocode', ['geocode']);

        document.getElementById('use-strict-bounds')
            .addEventListener('click', function() {
              console.log('Checkbox clicked! New state=' + this.checked);
              autocomplete.setOptions({strictBounds: this.checked});
            });
      }
    </script>
</html>