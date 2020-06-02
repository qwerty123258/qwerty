<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<style>

#clouds{
    padding: 50px 0;
    background: #c9dbe9;
    background: -webkit-linear-gradient(top, #c9dbe9 0%, #fff 100%);
    background: -linear-gradient(top, #c9dbe9 0%, #fff 100%);
    background: -moz-linear-gradient(top, #c9dbe9 0%, #fff 100%);
}

/*Time to finalise the cloud shape*/
.cloud {
    width: 200px; height: 60px;
    background: #fff;
    
    border-radius: 200px;
    -moz-border-radius: 200px;
    -webkit-border-radius: 200px;
    
    position: relative; 
}

.cloud:before, .cloud:after {
    content: '';
    position: absolute; 
    background: #fff;
    width: 100px; height: 80px;
    position: absolute; top: -15px; left: 10px;
    
    border-radius: 100px;
    -moz-border-radius: 100px;
    -webkit-border-radius: 100px;
    
    -webkit-transform: rotate(30deg);
    transform: rotate(30deg);
    -moz-transform: rotate(30deg);
}

.cloud:after {
    width: 120px; height: 120px;
    top: -55px; left: auto; right: 15px;
}

/*Time to animate*/
.x1 {
    -webkit-animation: moveclouds 15s linear infinite;
    -moz-animation: moveclouds 15s linear infinite;
    -o-animation: moveclouds 15s linear infinite;
}

/*variable speed, opacity, and position of clouds for realistic effect*/
.x2 {
    left: 200px;
    
    -webkit-transform: scale(0.6);
    -moz-transform: scale(0.6);
    transform: scale(0.6);
    opacity: 0.6; /*opacity proportional to the size*/
    
    /*Speed will also be proportional to the size and opacity*/
    /*More the speed. Less the time in 's' = seconds*/
    -webkit-animation: moveclouds 25s linear infinite;
    -moz-animation: moveclouds 25s linear infinite;
    -o-animation: moveclouds 25s linear infinite;
}

.x3 {
    left: -250px; top: -20px;
    
    -webkit-transform: scale(0.8);
    -moz-transform: scale(0.8);
    transform: scale(0.8);
    opacity: 0.8; /*opacity proportional to the size*/
    
    -webkit-animation: moveclouds 20s linear infinite;
    -moz-animation: moveclouds 20s linear infinite;
    -o-animation: moveclouds 20s linear infinite;
}

.x4 {
    left: 470px; top: -250px;
    
    -webkit-transform: scale(0.75);
    -moz-transform: scale(0.75);
    transform: scale(0.75);
    opacity: 0.75; /*opacity proportional to the size*/
    
    -webkit-animation: moveclouds 18s linear infinite;
    -moz-animation: moveclouds 18s linear infinite;
    -o-animation: moveclouds 18s linear infinite;
}

.x5 {
    left: -150px; top: -150px;
    
    -webkit-transform: scale(0.8);
    -moz-transform: scale(0.8);
    transform: scale(0.8);
    opacity: 0.8; /*opacity proportional to the size*/
    
    -webkit-animation: moveclouds 20s linear infinite;
    -moz-animation: moveclouds 20s linear infinite;
    -o-animation: moveclouds 20s linear infinite;
}

@-webkit-keyframes moveclouds {
    0% {margin-left: 1000px;}
    100% {margin-left: -1000px;}
}
@-moz-keyframes moveclouds {
    0% {margin-left: 1000px;}
    100% {margin-left: -1000px;}
}
@-o-keyframes moveclouds {
    0% {margin-left: 1000px;}
    100% {margin-left: -1000px;}
}
</style>
</head>
<body>
<div id="clouds">
    <div class="cloud x1"></div>
    <!-- Time for multiple clouds to dance around -->
    <div class="cotainer"><div class="col-xs-12"><div class="col-xs-5"></div><div class="col-xs-2"><a href="main">
    <img style="width:200px; margin:auto;
text-align:center; " src="${pageContext.request.contextPath}/resources/img/Air & Room-Logo.png"></a></div><div class="col-xs-5"></div></div>
    </div><div class="cloud x2"></div>
    <div class="cloud x3"></div>


</div>

</body>
</html>