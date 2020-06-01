<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
		<style>
		table a:link {
	color: #666;
	font-weight: bold;
	text-decoration:none;
}
table a:visited {
	color: #999999;
	font-weight:bold;
	text-decoration:none;
}
table a:active,
table a:hover {
	color: #bd5a35;
	text-decoration:underline;
}
table {
	font-family:Arial, Helvetica, sans-serif;
	color:#666;
	font-size:12px;
	text-shadow: 1px 1px 0px #fff;
	background:#eaebec;
	margin:20px;
	border:#ccc 1px solid;

	-moz-border-radius:3px;
	-webkit-border-radius:3px;
	border-radius:3px;

	-moz-box-shadow: 0 1px 2px #d1d1d1;
	-webkit-box-shadow: 0 1px 2px #d1d1d1;
	box-shadow: 0 1px 2px #d1d1d1;
}
table th {
	padding:21px 25px 22px 25px;
	border-top:1px solid #fafafa;
	border-bottom:1px solid #e0e0e0;

	background: #ededed;
	background: -webkit-gradient(linear, left top, left bottom, from(#ededed), to(#ebebeb));
	background: -moz-linear-gradient(top,  #ededed,  #ebebeb);
}
table th:first-child {
	text-align: left;
	padding-left:20px;
}
table tr:first-child th:first-child {
	-moz-border-radius-topleft:3px;
	-webkit-border-top-left-radius:3px;
	border-top-left-radius:3px;
}
table tr:first-child th:last-child {
	-moz-border-radius-topright:3px;
	-webkit-border-top-right-radius:3px;
	border-top-right-radius:3px;
}
table tr {
	
	padding-left:20px;
}
table td:first-child {
	text-align: left;
	padding-left:20px;
	border-left: 0;
}
table td {
	padding:18px;
	border-top: 1px solid #ffffff;
	border-bottom:1px solid #e0e0e0;
	border-left: 1px solid #e0e0e0;

	background: #fafafa;
	background: -webkit-gradient(linear, left top, left bottom, from(#fbfbfb), to(#fafafa));
	background: -moz-linear-gradient(top,  #fbfbfb,  #fafafa);
}
table tr.even td {
	background: #f6f6f6;
	background: -webkit-gradient(linear, left top, left bottom, from(#f8f8f8), to(#f6f6f6));
	background: -moz-linear-gradient(top,  #f8f8f8,  #f6f6f6);
}
table tr:last-child td {
	border-bottom:0;
}
table tr:last-child td:first-child {
	-moz-border-radius-bottomleft:3px;
	-webkit-border-bottom-left-radius:3px;
	border-bottom-left-radius:3px;
}
table tr:last-child td:last-child {
	-moz-border-radius-bottomright:3px;
	-webkit-border-bottom-right-radius:3px;
	border-bottom-right-radius:3px;
}
table tr:hover td {
	background: #f2f2f2;
	background: -webkit-gradient(linear, left top, left bottom, from(#f2f2f2), to(#f0f0f0));
	background: -moz-linear-gradient(top,  #f2f2f2,  #f0f0f0);	
}
		
		
     #star_grade a{
        text-decoration: none;
        color: gray;
    }
    #star_grade a.on{
        color: red;
    }
</style>
</head>
<body>
<table>

<tr><td>평가</td><td>
		<form id="GrdaeCheck">
						<input type="hidden" name="rbno" readonly  value="${rbno}" > 					
						<input type="hidden" name="id" readonly  value="${id}" type="hidden"> 					
						
						<input id="grade" name="grade" readonly  type="hidden"> 
						<p id="star_grade">
							<a href="#" onclick="stars('1')">★</a> 
							<a href="#"
								onclick="stars('2')">★</a> 
								<a href="#" onclick="stars('3')">★</a>
							<a href="#" onclick="stars('4')">★</a> <a href="#"
								onclick="stars('5')">★</a>
						</p>
			</td>
			</tr>
			<tr><td>		
						평가남기기  
					</td>
					<td>	
						  <select id="contents" name="contents">
            <option value="깔끔">깔끔</option>
            <option value="매너">매너</option>
           
            <option value="비매너">비매너</option>
            <option value="청결불량">청결불량</option>
            <option value="금품갈취">금품갈취</option>
        </select>
        </td>
        </tr>
        <tr><td colspan="2" style="text-align:center;">
						<button class="btn btn-success" type="button" onclick="goCommentCheck()">평가 완료</button>
					</form>
			</td></tr>		
					</table>
	<script>
	var contentsCheck=document.getElementById("contents").value;
		var save = 0;
		
		function CommentCheck(){
			var formData = new FormData(document.getElementById("GrdaeCheck"));
		$.ajax({
			type:"post",
			url:"guestEvaluation",
			processData : false, // 필수 
			contentType : false, // 필수 		
			data:formData,
			dataType:"text",
			success:function(result){
				if(result=="yes"){
				alert('평가성공');
				window.close();				
				}else{			
				alert('평가실패');				
			}
			},error:function(){
				
			}
		});	 		
		}
		function stars(s) {
			save = s;
			document.getElementById("grade").value = save;
		}

		$('#star_grade a').click(function() {

			$(this).parent().children("a").removeClass("on"); /* 별점의 on 클래스 전부 제거 */
			$(this).addClass("on").prevAll("a").addClass("on"); /* 클릭한 별과, 그 앞 까지 별점에 on 클래스 추가 */
			return false;
		});

		
		function goCommentCheck(){
			if(save==0){
				alert('평가점수를 반영하여주세요');
			}else{
				 CommentCheck();
			}
		}
		
		
		
		</script>
	

</body>
</html>