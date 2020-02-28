<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
            <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script>
function add(){
	location.href="CreateUser.jsp";
}
function searchUserID(){
	location.href="SearchUserID.jsp";
}
function searchUserPw(){
	location.href="SearchUserPw.jsp";
}
function boardList(){
	location.href="BoardList";
}
</script>
<script>
$(document).ready(function() {
	
	getBoardList();
	
	function getBoardList(){
	    $.ajax({
	        type:'GET',
	        url : "<c:url value='BoardList'/>",
	        dataType : "json",
	        contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
	        success : function(data){
	            var html = "";
	            var cCnt = data.board.length;
	            html += "<div>";
	            html += "<div><table class='table table-striped table-bordered table-hover'>";
	            html += "<thead><tr><th>카테고리</th><th>글 제목</th><th>작성자</th><th>조회수</th><th> 작성날짜</th></tr></thead>";
	            if(data.board.length > 0){
	                for(i=0; i<data.board.length; i++){
	                    html += "<tbody><tr><td class='listtd'>"+data.board[i].category+"</td>";
	                    html += "<td class='listtd'><a href=BoardDetail?bno="+data.board[i].bno+">"+data.board[i].title+"</td><td class='listtd'>";
	                    html += "<div class='dropdown'>";
	                    html += "<button class='btn btn-default dropdown-toggle' type='button' id='menu1' data-toggle='dropdown'>"+data.board[i].id+"</button>";
	                    html += "<ul class='dropdown-menu' role='menu' aria-labelledby='menu1'>";
	                    html += "<li role='presentation'>";
	                    html += "<a role='menuitem' tabindex='-1' href='#' onclick=window.open('SelectDetailUser?id="+data.board[i].id+"','상세보기','width=430,height=500,location=no,status=no,scrollbars=yes');>상세보기</a></li>";
	                    html += "<c:if test="${sessionScope.id  eq 'qwerty123258'}">";
	                    html += "<li role='presentation'><a role='menuitem' tabindex='-1' href='#' onclick=deleteUser('";
	                    html += data.board[i].id+"')>탈퇴시키기</a></li></c:if></ul></div>";
	                    html += "</td><td class='listtd' >"+data.board[i].bview+"</td><td class='listtd'>"+data.board[i].writedate+"</td></tr>";
	                }
                    html += "</tbody></table></div>";
                    html += "</div>";
	            } else {
	                html += "<tbody><h6><strong>등록된 게시글이 없습니다.</strong></h6>";
	                html += "</tbody></table></div>";
	                html += "</div>";
	            }
	            $("#cCnt").html(cCnt);
	            $("#boardList").html(html);
	            page();
	            
	        },
	        error:function(request,status,error){
	            
	       }
	        
	    });
	}
	  function page(){ 
		  var reSortColors = function($table) {
		    $('tbody tr:odd td', $table).removeClass('even').removeClass('listtd').addClass('odd');
		    $('tbody tr:even td', $table).removeClass('odd').removeClass('listtd').addClass('even');
		   };
		   $('table.paginated').each(function() {
			   var pagesu = 5; 
			   var currentPage = 0;
			   var numPerPage = 10; 
			   var $table = $(this);    
			   var numRows = $table.find('tbody tr').length;
			   var numPages = Math.ceil(numRows / numPerPage);
			   if (numPages==0) return;
			   var $pager = $('<td align="center" id="remo" colspan="10"><div class="pager"></div></td>');
			   var nowp = currentPage;
			   var endp = nowp+10;
			   $table.bind('repaginate', function() {
			    $table.find('tbody tr').hide().slice(currentPage * numPerPage, (currentPage + 1) * numPerPage).show();
			    $("#remo").html("");
			    if (numPages > 1) {
			     if (currentPage < 5 && numPages-currentPage >= 5) {
			      nowp = 0;
			      endp = pagesu;
			     }else{
			      nowp = currentPage -5;
			      endp = nowp+pagesu;
			      pi = 1;
			     }
			     if (numPages < endp) {
			      endp = numPages;
			      nowp = numPages-pagesu;
			     }
			     if (nowp < 1) {
			      nowp = 0;
			     }
			    }else{
			     nowp = 0;
			     endp = numPages;
			    }
			    $('<br /><span class="page-number" cursor: "pointer">[처음]</span>').bind('click', {newPage: page},function(event) {
			           currentPage = 0;   
			           $table.trigger('repaginate');  
			           $($(".page-number")[2]).addClass('active').siblings().removeClass('active');
			       }).appendTo($pager).addClass('clickable');
			       $('<span class="page-number" cursor: "pointer">&nbsp;&nbsp;&nbsp;[이전]&nbsp;</span>').bind('click', {newPage: page},function(event) {
			           if(currentPage == 0) return; 
			           currentPage = currentPage-1;
			     $table.trigger('repaginate'); 
			     $($(".page-number")[(currentPage-nowp)+2]).addClass('active').siblings().removeClass('active');
			    }).appendTo($pager).addClass('clickable');
			    for (var page = nowp ; page < endp; page++) {
			     $('<span class="page-number" cursor: "pointer" style="margin-left: 8px;"></span>').text(page + 1).bind('click', {newPage: page}, function(event) {
			      currentPage = event.data['newPage'];
			      $table.trigger('repaginate');
			      $($(".page-number")[(currentPage-nowp)+2]).addClass('active').siblings().removeClass('active');
			      }).appendTo($pager).addClass('clickable');
			    } 
			       $('<span class="page-number" cursor: "pointer">&nbsp;&nbsp;&nbsp;[다음]&nbsp;</span>').bind('click', {newPage: page},function(event) {
			     if(currentPage == numPages-1) return;
			         currentPage = currentPage+1;
			     $table.trigger('repaginate'); 
			      $($(".page-number")[(currentPage-nowp)+2]).addClass('active').siblings().removeClass('active');
			    }).appendTo($pager).addClass('clickable');
			    $('<span class="page-number" cursor: "pointer">&nbsp;[끝]</span>').bind('click', {newPage: page},function(event) {
			            currentPage = numPages-1;
			            $table.trigger('repaginate');
			            $($(".page-number")[endp-nowp+1]).addClass('active').siblings().removeClass('active');
			    }).appendTo($pager).addClass('clickable');
			      
			      $($(".page-number")[2]).addClass('active');
			 reSortColors($table);
			   });
			    $pager.insertAfter($table).find('span.page-number:first').next().next().addClass('active');   
			    $pager.appendTo($table);
			    $table.trigger('repaginate');
			  });
			 }
	  
	$('#loginbtn').click(function() {
        var id = $('#id_input').val();
        var password = $('#pw_input').val();
      $.ajax({
           type : "POST",
             url : "Login",
             data : "id=" + id + "&pw=" + password,
             dataType : "text",
           success : function(data, textStatus, xhr) {
                if (data == 'blackList') {
                     alert('당신은 블랙리스트입니다. 로그인이 불가능합니다.');
              } else if(data=='certify') {
            	  alert('이메일인증이 아직 완료되지 않으셨습니다. 로그인이 불가능합니다.');
          	}
              else if(data=='loginFail') {
            	  alert('아이디 또는 비밀번호가 맞지 않습니다.');
          	}
              else if(data=='login') {
                   location.href = 'Main.jsp';
	}
           },
error : function(request, status, error) {
alert("code:" + request.status + "\n" + "error:" + error);
}
})
});
	$('#logout').click(function() {
        $.ajax({
            type : "POST",
              url : "Logout",
            success : function(data, textStatus, xhr) {
            alert('로그아웃 되셨습니다.');
            location.href='Main.jsp';

            },
error : function(request, status, error) {
alert("code:" + request.status + "\n" + "error:" + error);
}
 })
	});
	$('#alluser').click(function() {
		location.href="SelectUser";
	});
	$('#modify').click(function() {
            location.href="CheckPw.jsp";
	});
	$('#delete').click(function() {
        location.href="CheckDelPw.jsp";
});
	$('#write').click(function() {
        $.ajax({
            type : "POST",
              url : "WriteAccessCheck",
              dataType : "text",
            success : function(data, textStatus, xhr) {
                 if (data == 'no') {
                      alert('글 작성권한이 없습니다. 먼저 판매자 자격 요청을 하세요.');
                      location.href='RequestWrite.jsp';
                 }
               else if(data=='yes') {
                   location.href='BoardWrite.jsp';
 	}
            },
error : function(request, status, error) {
alert("code:" + request.status + "\n" + "error:" + error);
}
 })
});
	$('#requestWrite').click(function() {
        location.href="RequestWrite.jsp";
});
	$('#request').click(function() {
        location.href="RequestList";
});
})
</script>
<script>
function deleteUser(id){
	var id=id;
	  $.ajax({
	       type : "POST",
	         url : "AdminDeleteUser",
	         data : "id=" + id,
	         dataType : "text",
	       success : function(data, textStatus, xhr) {
	            if (data == 'deleteFail') {
	                 alert('회원삭제 실패 \n 또는 가입된 회원이 아닙니다.');
	          	}
	            else if(data=='adminDelFail'){
		        	  alert('관리자는 삭제 하실 수 없습니다.');
	            }
	            else if(data=='deleteSuccess') {
	        	  alert('삭제성공');
	}
	       },
	error : function(request, status, error) {
	alert("code:" + request.status + "\n" + "error:" + error);
	}
	});
}
</script>
<script>
    function enterkey(){
    if (window.event.keyCode == 13) { //로그인 버튼 말고 엔터키로 로그인 하는 경우
    	document.getElementById("loginbtn").click();
   }
}
   </script>
   <style>
   .sidenav{
   height:100%;
   }
   
       footer {
      background-color: #555;
      color: white;
      padding: 15px;
    }
   </style>
</head>
<body>
<div class="header">
<div class="container">
  <ul class="nav nav-pills">
    <li class="active"><a data-toggle="pill" href="#home">Home</a></li>
    <li><a data-toggle="pill" href="#menu1">Menu 1</a></li>
    <li><a data-toggle="pill" href="#menu2">글 보기</a></li>
    <li><a data-toggle="pill" href="#menu3">Menu 3</a></li>
    <li><a data-toggle="pill" href="#menu4">Menu 4</a></li>
  </ul>
  <div class="tab-content">
    <div id="home" class="tab-pane fade in active">
      <h3>HOME</h3>
      <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
    </div>
    <div id="menu1" class="tab-pane fade">
      <h3>Menu 1</h3>
      <p>Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
    </div>
    <div id="menu2" class="tab-pane fade">
<div id="boardList">

</div>


<table class="tbl paginated" id="tbl">

</table>
    </div>
    <div id="menu3" class="tab-pane fade">
      <h3>Menu 3</h3>
      <p>Eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.</p>
    </div>
    <div id="menu4" class="tab-pane fade">
      <h3>Menu 3</h3>
      <p>Eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo.</p>
    </div>
  </div>
</div>

</div>
<div class="sidenav">
<c:if test="${sessionScope.id eq NULL}">
<div class="loginar" style=display:flex>
<div>
<p>
아이디
</p>
<input type="text" style=width:150px; id="id_input">
<p>비밀번호</p>
<input type="password" onkeyup="enterkey()" style=width:150px; id="pw_input">
</div>
<button id="loginbtn" style=padding:10px;>로그인 하기</button>
</div>
<p><a href="#" onclick="boardList()">글보기</a></p>
<p><a href="#" onclick="add()">회원 가입하기</a></p>
<p><a href="#" onclick="searchUserID()">아이디 찾기</a></p>
<p><a href="#" onclick="searchUserPw()">비밀번호 찾기</a></p>
</c:if>
<c:if test="${sessionScope.id  ne NULL}">
<div class="loginar" style=display:flex>
${sessionScope.id}님 환영합니다.
</div>
<c:if test="${sessionScope.id  eq 'qwerty123258'}">
<p><a href="#" id="alluser">회원 전체조회</a></p>
<p><a href="#" id="request">작성권한 요청확인</a></p>
</c:if>
<p><a href="#" id="write">글쓰기</a></p>
<c:if test="${sessionScope.id  ne 'qwerty123258'}">
<p><a href="#" id="requestWrite">작성 권한 요청 글쓰기</a></p>
</c:if>
<p><a href="#" onclick="boardList()">글보기</a></p>
<p><a href="#" id="modify">정보수정</a></p>
<p><a href="#" id="delete">회원탈퇴</a></p>
<p><a href="#" id="logout">로그아웃</a></p>
</c:if>
</div>
<footer class="container-fluid text-center">
  <p>Footer Text</p>
 </footer>
</body>
</html>