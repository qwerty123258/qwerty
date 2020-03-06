/**
 * 
 */
    function deleteBoard(bno,category){
    	var bno=bno;
    	var category=category;
    	if(confirm("삭제하시겠습니까?")){
    		location.href="DeleteBoard?bno="+bno+"&category="+category;
    	}
    }
    function fileView(bno){
    	var bno=bno;
        $.ajax({
            type:'GET',
            url : "FileList",
            data : "bno=" + bno,
            dataType : "json",
            contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
            success : function(data){
                var html = "";
                var file = data.file.length;
                if(data.file.length > 0){
                    for(i=0; i<data.file.length; i++){
                        html += "<div>";
                        html += "<div><table class='table'><tr><td><h4><strong><a href=DownloadFile?price="+data.file[i].price+"&bfile="+data.file[i].bfile+"&bno="+bno+"&bfno="+data.file[i].bfno+"&boriginfile="+data.file[i].boriginfile+">파일 이름 : "+data.file[i].boriginfile+"</a></strong></h4></td>";
                        html += "<td>"+data.file[i].price+" 포인트 필요</td></tr>";
                        html += "</table></div>";
                        html += "</div>";
                    }
                } else {
                    html += "<div>";
                    html += "<div><table class='table'><h6><strong>등록된 파일이 없습니다.</strong></h6>";
                    html += "</table></div>";
                    html += "</div>";
                }
                $("#file").html(file);
                $("#fileList").html(html);
            	getLikeCount(bno);
                
            },
            error:function(request,status,error){
                
           }
            
        });
    }
    function getLikeCount(bno){
    	var bno=bno;
  	  $.ajax({
  	       type : "POST",
  	         url : "LikeCount",
  	         data: "bno=" + bno,
  	         dataType : "text",
  	       success : function(data, textStatus, xhr) {
					$("#likeCount").html("좋아요 수 : "+ data);
					getReportCount(bno);
  	       },
  	error : function(request, status, error) {
  	alert("code:" + request.status + "\n" + "error:" + error);
  	}
  	})
    }
    function getReportCount(bno){
    	var bno=bno;
    	  $.ajax({
     	       type : "POST",
     	         url : "ReportCount",
     	         data : "bno="+bno,
     	         dataType : "text",
     	       success : function(data, textStatus, xhr) {
   					$("#reportCount").html("신고 수 : "+ data);
   				    getCommentList(1);
     	       },
     	error : function(request, status, error) {
     	alert("code:" + request.status + "\n" + "error:" + error);
     	}
     	})
    	
    }
    function deleteComment(cno){
    	var cno=cno;
    	if(confirm("삭제하시겠습니까?")){
    	  	  $.ajax({
    		       type : "POST",
    		         url : "DeleteComment",
    		         data : "cno=" + cno,
    		         dataType : "text",
    		       success : function(data, textStatus, xhr) {
    								if(data=='Success'){
    									if($("#cCnt").text()%5<=1){
    										getCommentList(1);
    									}
    									else{
    										getCommentList(Math.ceil($("#cCnt").text()/5));
    									}
    								}
    								else if(data=='Fail'){
    									alert('삭제 실패');
    								}
    		       },
    		error : function(request, status, error) {
    		alert("code:" + request.status + "\n" + "error:" + error);
    		}
    		})
    	}
    	else{
    		
    	}
	
    }
    function modifyComment(content,cno){
    	var content=content;
    	var cno=cno;
    	var html="<textarea style=width:100%; rows='3' cols='30' id='updateText'>"+content+"</textarea>";
    	html += "<a style='float:right' href='javascript:updateComment(\""+content+"\","+cno+")'>완료</a>";
    	html += "<a style='float:right' href='javascript:cancle(\""+content+"\","+cno+")'>취소</a>";
    	$("#modify"+cno).html(html); 
    }
    function updateComment(content,cno){
    	var cno=cno;
    	var content=$('#updateText').val();
    	  $.ajax({
   	       type : "POST",
   	         url : "UpdateComment",
   	         data : "cno=" + cno + "&content=" + content,
   	         dataType : "text",
   	       success : function(data, textStatus, xhr) {
   							if(data=='Success'){
   									getCommentList($("#page").find("span").text());
   							}
   							else if(data=='Fail'){
   								alert('수정 실패');
   							}
   	       },
   	error : function(request, status, error) {
   	alert("code:" + request.status + "\n" + "error:" + error);
   	}
   	})
    	
    }
    function cancle(content,cno){
    	var cno=cno;
    	var content=content;
    	var a="<a href='javascript:modifyComment(\""+content+"\","+cno+")'>수정</a>";
    	var b="<a href='javascript:deleteComment("+cno+")'>삭제</a>";
    	var html="";
        html +=  content;
        html +=  "<div style='float:right'>"+a+b+"</div>"
    	$("#modify"+cno).html(html); 
    }
    function addBlack(id){
    	var id=id;
    	  $.ajax({
    	       type : "POST",
    	         url : "AddBlackList",
    	         data : "id=" + id,
    	         dataType : "text",
    	       success : function(data, textStatus, xhr) {
    	            if (data == 'addFail') {
    	                 alert('블랙리스트 추가 실패');
    	          	}
    	            else if(data=='adminAddFail'){
    		        	  alert('관리자는 블랙리스트에 추가 하실 수 없습니다.');
    	            }
    	            else if(data=='addSuccess') {
    	        	  alert('블랙리스트에 추가하였습니다.');
    	        	  location.reload();
    	}
    	       },
    	error : function(request, status, error) {
    	alert("code:" + request.status + "\n" + "error:" + error);
    	}
    	})
    }
    function removeBlack(id){
    	var id=id;
    	  $.ajax({
    	       type : "POST",
    	         url : "RemoveBlackList",
    	         data : "id=" + id,
    	         dataType : "text",
    	       success : function(data, textStatus, xhr) {
    	            if (data == 'removeFail') {
    	                 alert('블랙리스트 해제 실패');
    	          	}
    	            else if(data=='adminRemoveFail'){
    		        	  alert('관리자는 해제 하실 수 없습니다.');
    	            }
    	            else if(data=='removeSuccess') {
    	        	  alert('블랙리스트를 해제 하였습니다.');
    	        	  location.reload();
    	}
    	       },
    	error : function(request, status, error) {
    	alert("code:" + request.status + "\n" + "error:" + error);
    	}
    	})
    }
    function Like(bno){
    	var bno=bno;
  	  $.ajax({
	       type : "POST",
	         url : "Like",
	         data : "bno="+bno,
	         dataType : "text",
	       success : function(data, textStatus, xhr) {
	            if (data == 'Fail') {
	                 alert('좋아요 실패');
	          	}
	            else if(data=='likeSuccess') {
	        	  alert('해당 게시글을 좋아요하셨습니다.');
	        	  $("#like").children().remove();
	   	        	getLikeCount(bno);
	        	  var html="";
	        	  html+="<a href='javascript:LikeCancel("+bno+")'><i class='fas fa-thumbs-up fa-5x'></i></a>";
	        	  $("#like").html(html);
	}
	       },
	error : function(request, status, error) {
	alert("code:" + request.status + "\n" + "error:" + error);
	}
	})
    }
    function LikeCancel(bno){
    	var bno=bno;
    	  $.ajax({
   	       type : "POST",
   	         url : "LikeCancel",
   	         data : "bno=" + bno,
   	         dataType : "text",
   	       success : function(data, textStatus, xhr) {
   	            if (data == 'Fail') {
   	                 alert('좋아요 취소 실패');
   	          	}
   	            else if(data=='likeCancelSuccess') {
   	        	  alert('해당 게시글에 대해서 좋아요를 취소하셨습니다.');
   	        	  $("#like").children().remove();
     	        	getLikeCount(bno);
   	        	  var html="";
   	        	  html+="<a href='javascript:Like("+bno+")'><i class='far fa-thumbs-up fa-5x'></i></a>";
   	        	  $("#like").html(html);
   	}
   	       },
   	error : function(request, status, error) {
   	alert("code:" + request.status + "\n" + "error:" + error);
   	}
   	})
    }
    function Report(bno){
    	var bno=bno;
    	  $.ajax({
  	       type : "POST",
  	         url : "Report",
  	         data : "bno=" + bno,
  	         dataType : "text",
  	       success : function(data, textStatus, xhr) {
  	            if (data == 'Fail') {
  	                 alert('신고 실패');
  	          	}
  	            else if(data=='reportSuccess') {
  	        	  alert('해당 게시글을 신고하셨습니다.');
  	        	  $("#report").children().remove();
  	   	        	getReportCount(bno);
  	        	  var html="";
  	        	  html+="<a href='javascript:ReportCancel("+bno+")'><i class='fas fa-thumbs-down fa-5x'></i></a>";
  	        	  $("#report").html(html);
  	}
  	       },
  	error : function(request, status, error) {
  	alert("code:" + request.status + "\n" + "error:" + error);
  	}
  	})
      }
      function ReportCancel(bno){
    	  var bno=bno;
      	  $.ajax({
     	       type : "POST",
     	         url : "ReportCancel",
     	         data : "bno=" + bno,
     	         dataType : "text",
     	       success : function(data, textStatus, xhr) {
     	            if (data == 'Fail') {
     	                 alert('신고 취소 실패');
     	          	}
     	            else if(data=='reportCancelSuccess') {
     	        	  alert('해당 게시글에 대해서 신고를 취소하셨습니다.');
     	        	  $("#report").children().remove();
       	        	getReportCount(bno);
     	        	  var html="";
     	        	  html+="<p>불량한 게시물은 신고하기를 눌러주세요!</p>";
     	        	  html+="<a href='javascript:Report("+bno+")'><i class='far fa-thumbs-down fa-5x'></i></a>";
     	        	  $("#report").html(html);
     	}
     	       },
     	error : function(request, status, error) {
     	alert("code:" + request.status + "\n" + "error:" + error);
     	}
     	})
      }
      function  grade(){
    	    $.ajax({
    	        type : "POST",
    	          url : "GradeUp",
    	          dataType : "text",
    	        success : function(data, textStatus, xhr) {
    							if(data=='SILVER'){
    								alert("실버 등급으로 승격하셨습니다.");
    								location.reload();
    							}
    							if(data=="GOLD"){
    								alert("골드 등급으로 승격하셨습니다.");
    								location.reload();
    							}
    							if(data=="DIAMOND"){
    								alert("다이아 등급으로 승격하셨습니다.");
    								location.reload();
    							}
    	        },
    	error : function(request, status, error) {
    	alert("code:" + request.status + "\n" + "error:" + error);
    	}
    	})
    	}
    	function randomPoint(){
    			    $.ajax({
    			        type : "POST",
    			          url : "RandomPoint",
    			          dataType : "text",
    			        success : function(data, textStatus, xhr) {
    						 alert("오늘의 첫 댓글 작성으로 "+data+" 포인트를 적립하셨습니다.");
    						 location.reload();
    			        },
    			error : function(request, status, error) {
    			alert("code:" + request.status + "\n" + "error:" + error);
    			}
    			})
    			}
    	function commentWrite(bno){
    		var bno=bno;
    	    $.ajax({
    	        type:'POST',
    	        url : "AddComments?bno="+bno,
    	        data:$("#commentForm").serialize(),
    	        success : function(data){
    	            if(data=="Success") {
    	                grade();
    	            	if(Math.ceil($("#cCnt").text()/5)<=$("#cCnt").text()/5){
    		                getCommentList(Math.ceil($("#cCnt").text()/5)+1,bno);
    	            	}
    	            	else{
    		                getCommentList(Math.ceil($("#cCnt").text()/5),bno);
    	            	}
    	                $("#comment").val("");
    	            }
    	            else if(data=="Fail"){
    	            	alert('댓글 등록 실패');
    	            }
    	        },
    	        error:function(request,status,error){
    	        	
    	       }
    	        
    	    });
    	}