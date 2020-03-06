/**
 * 
 */
function enterkey(){
    if (window.event.keyCode == 13) { //로그인 버튼 말고 엔터키로 로그인 하는 경우
    	document.getElementById("loginbtn").click();
   }
}
function getPoint(){
    $.ajax({
        type : "POST",
          url : "CheckPoint",
          dataType : "text",
        success : function(data, textStatus, xhr) {
					var html="";
					html+="<p> 포인트 : "+data+"</p>";
					$("#savePoint").html(html);
					getGrade();
        },
error : function(request, status, error) {
alert("code:" + request.status + "\n" + "error:" + error);
}
})
}
function  getGrade(){
    $.ajax({
        type : "POST",
          url : "CheckGrade",
          dataType : "text",
        success : function(data, textStatus, xhr) {
					var html="";
					html+="<p> 등급 : "+data+"</p>";
					$("#grade").html(html);
        },
error : function(request, status, error) {
alert("code:" + request.status + "\n" + "error:" + error);
}
})
}
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