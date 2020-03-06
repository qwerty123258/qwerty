/**
 * 
 */

function deleteUser(id,page){
	var id=id;
	var page=page;
	if(confirm("탈퇴시키겠습니까?")){
		  $.ajax({
		       type : "POST",
		         url : "AdminDeleteUser",
		         data : "id=" + id +"&page="+page,
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
	              location.href = 'BoardList';
		}
		       },
		error : function(request, status, error) {
		alert("code:" + request.status + "\n" + "error:" + error);
		}
		})
	}
	else{
		alert("탈퇴 취소");
	}
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