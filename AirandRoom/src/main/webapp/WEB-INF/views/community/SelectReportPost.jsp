<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개별 문의글 보는 페이지입니다.</title>
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
			} else {
				$("#mandu1").remove();
				$("#stylized").append($("#mandu2"));
				$("#mandu2").show();
			}
		});
	});

	function deleteReport() {
		var kind = '${kind}'
		var read = '${selectReportPost.read}';
		var repno = '${selectReportPost.repno}';
		var id = '${selectReportPost.id}';
		var acceptReport = '${selectReportPost.acceptreport}';
		var result = confirm("정말로 삭제하시겠습니까?");
		if (result && acceptReport == 'Y' && kind == 'normal') {
			alert("신고 접수 된 글은 삭제할 수 없습니다.");
		} else if (result == true && read == 'N' && kind == 'normal') {
			location.href = "deleteReport?repno=" + repno + "&id=" + id
					+ "&kind=" + kind;
			self.close();
			opener.location.reload();
		} else if (result == true && read == 'N' || read == 'Y' && kind == 'admin') {
			location.href = "deleteReport?repno=" + repno + "&id=" + id
					+ "&kind=" + kind;
			self.close();
			opener.location.reload();
		} else if (result == false){
			return false;
		}
	}

	function modifyReportForm() {

		var title = $("#title").val();
		var contents = $("#contents").val();
		var ifile = $("#ifile").val();
		var read = '${selectReportPost.read}';

		console.log(read);
		if (title == "") {
			alert("제목을 입력해주세요.");
			$("#title").focus();
		} else if (contents == "") {
			alert("내용을 입력해주세요.");
			$("#contents").focus();
		} else if (read == 'Y') {
			alert("신고 접수된 글은 수정이 불가능합니다.")
		} else {
			var result = confirm("등록하시겠습니까?");

			if (result) {
				var formData = new FormData(document.getElementById("form"));
				formData.append("read", read);
				$.ajax({
					type : "POST",
					url : "modifyReportForm",
					data : formData,
					processData : false,
					contentType : false,
					dataType : "text",
					success : function(result) {
						alert("성공");
						self.close();
						opener.location.reload();
					}
				});
			}
		}
	}

	function acceptReport() {
		var acceptReport = '${selectReportPost.acceptreport}';
		var otherid = '${selectReportPost.otherid}';
		var repno = ${selectReportPost.repno};
		var desicion = confirm("신고글 접수 하시겠습니깡?");
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
	font-size: 11px;
	font-weight: normal;
	text-align: right;
	width: 140px;
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
		<h1>${selectReportPost.id}님의신고문의 글</h1>
		<c:if test="${kind eq 'admin'}">
		신고대상 : ${selectReportPost.otherid}
		</c:if>
		<br>

		<div id="mandu1">

			<label class="small">제목 :</label> <input type="text" value="${selectReportPost.title}" readonly />

			<p>
				<textarea cols="50" rows="30" readonly>${selectReportPost.contents}</textarea>
			</p>

			<div class="spacer"></div>

			<c:choose>
				<c:when test="${kind eq 'admin' && selectReportPost.acceptreport == 'N'}">
					<button onclick="javascript:check();">닫기</button>
					<button onclick="javascript:deleteReport();">삭제하기</button>
					<button onclick="javascropt:acceptReport();">신고글 접수</button>
				</c:when>


				<c:otherwise>
					<button id="btn1">수정하기</button>
					<button onclick="javascript:deleteReport();">삭제하기</button>
					<button onclick="javascript:check();">닫기</button>
				</c:otherwise>
			</c:choose>
		</div>


	</div>


	<div id="mandu2">

		<form id="form" method="POST" enctype="multipart/form-data">


			<label class="small">제목 :</label> <input type="text" name="title"
				id="title" value="${selectReportPost.title}" />

			<p>
				<textarea cols="50" rows="30" name="contents" id="contents">${selectReportPost.contents}</textarea>
			</p>

			<label class="small">첨부파일 :</label> <input type="file" name="rfile"
				id="rfile">


			<div class="spacer"></div>

			<input type="hidden" value="${selectReportPost.repno}" name="repno"	id="repno"> 
			<input type="hidden" value="${selectReportPost.id}" name="id" id="id"> 
			<input type="hidden" value="${selectReportPost.otherid}" name="otherid" id="otherid"> 
			<input type="hidden" value="N" name="read" id="read">
		</form>

		<button onclick="modifyReportForm()">수정하기</button>


	</div>

</body>
</html>