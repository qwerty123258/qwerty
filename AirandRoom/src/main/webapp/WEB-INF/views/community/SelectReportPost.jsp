<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개별 신고글 보는 페이지입니다.</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"
	integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
	crossorigin="anonymous">
	
</script>

<script>
	function check() {
		self.close();
	}

	$(document).ready(function() {
		$("#mandu2").hide();
		$("#btn1").click(function() {
			var test = '${selectReportPost.read}';
			if (test == 'Y') {
				alert("신고 접수된 글은 수정할 수 없습니다.");
			} 
			else {
				$("#mandu1").remove();
				$("#stylized").append($("#mandu2"));
				$("#mandu2").show();
			}
		});
	});
	function deleteReport() {
		var read = '${selectReportPost.read}';
		var repno = '${selectReportPost.repno}';
		var id = '${selectReportPost.id}';
		var acceptReport = '${selectReportPost.acceptreport}';
		var result = confirm("정말로 삭제하시겠습니까?");
		if (result) {
			if (result && acceptReport == 'Y') {
				alert("신고 접수 된 글은 삭제할 수 없습니다.");
			}
			else if(result && acceptReport == 'N'){
				$.ajax({
					url:"deleteReport",
					data : "repno="+repno,
					dataType:"text",
					type:"GET",
					success:function(result){
						if(result=="Success"){
							self.close();
							opener.location.reload();
						}
						else if(result=="Fail"){
							alert("신고글 삭제 실패");
						}
					},
					error:function(){
						alert("신고글 삭제중 에러 발생");
					}
					});
			}
		} 
	}

	function acceptReport() {
		var acceptReport = '${selectReportPost.acceptreport}';
		var otherid = '${selectReportPost.otherid}';
		var repno = ${selectReportPost.repno};
		var desicion = confirm("신고글 접수 하시겠습니까??");
		if (desicion == true && acceptReport == 'N') {
			$.ajax({
				type : "POST",
				url : "acceptReport",
				data : "otherid=" + otherid + "&repno=" + repno,
				dataType : "text",
				success : function(result) {
					alert("성공");
					self.close();
					opener.location.reload();
				}
			});
		} else if(desicion == true && acceptReport == 'Y'){
			alert("이미 접수된 신고문의글입니다.");
		}
	}
</script>

<style>
p, h1, form, button {
	border: 0;
	margin: 0;
	padding: 0;
}

.spacer {
	clear: both;
	height: 1px;
}

.myform {
	margin: 10px;
	width: 400px;
	height: 600px;
	padding: 10px;
}

#stylized {
	border: solid 2px #b7ddf2;
	background: #ebf4fb;
}

#stylized h1 {
	font-size: 16px;
	font-weight: bold;
	margin-bottom: 8px;
	font-family: nanumgothic, dotum;
}

#stylized p {
	font-size: 11px;
	color: #666666;
	margin-bottom: 20px;
	border-bottom: solid 1px #b7ddf2;
	padding-bottom: 10px;
	font-family: dotum;
}

#stylized label {
	display: block;
	font-weight: bold;
	text-align: right;
	width: 20px;
	float: left;
	font-family: tahoma;
}

#stylized .small {
	color: #666666;
	display: block;
	font-size: 18px;
	font-weight: normal;
	text-align: right;
	width: 100px;
	font-family: dotum;
	letter-spacing: -1px;
}

#stylized input {
	float: left;
	font-size: 12px;
	padding: 4px 2px;
	border: solid 1px #aacfe4;
	width: 200px;
	margin: 2px 0 20px 10px;
}

#stylized button {
	clear: both;
	margin-left: 150px;
	width: 125px;
	height: 31px;
	text-align: center;
	line-height: 31px;
	background-color: #000;
	color: #FFFFFF;
	font-size: 11px;
	font-weight: bold;
	font-family: tahoma;
}
</style>
</head>
<body>


	<div id="stylized" class="myform">
		<h1>${selectReportPost.id}님의 신고글</h1>
		신고대상 : ${selectReportPost.otherid}<br>
		첨부파일 : <a href="filedownload?filename=${selectReportPost.rfilename}">${selectReportPost.rfileoriname}</a>
		<br><br>
		<div id="mandu1">
			<label class="small">제목 :</label> <input type="text" value="${selectReportPost.title}" readonly />
			<br>
			<p>
				<textarea cols="50" rows="20" readonly>${selectReportPost.contents}</textarea>
			</p>
			<div class="spacer"></div>
					<button onclick="javascript:check();">닫기</button>
					<button onclick="javascript:deleteReport();">삭제하기</button>
					<button onclick="javascropt:acceptReport();">신고글 접수</button>
		</div>
	</div>
</body>
</html>