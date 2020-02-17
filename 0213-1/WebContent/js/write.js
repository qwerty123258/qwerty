function writeboard(){
    var writer=document.getElementById("boardwriter").value;
    var password=document.getElementById("boardpassword").value;
    var title=document.getElementById("boardtitle").value;
	if(writer == "") {
		alert("작성자가 비어있습니다.");
		document.getElementById("boardwriter").focus();
	} 
	else if(password=="") {
		alert("비밀번호가 비어있습니다.");
		document.getElementById("boardpassword").focus();
}
	else if(title==""){
		alert("제목이 비어있습니다.");
		document.getElementById("boardtitle").focus();
	}
	else{
		if(bimgFileCheck()){
			document.getElementById("boardwrite").submit();
		}
	}
}
function bimgFileCheck(){
    var bimgfile=document.getElementById("bimgfile").value;
	if (bimgfile != "") {
	    var ext = bimgfile.slice(bimgfile.lastIndexOf(".") + 1).toLowerCase();
	    if (!(ext == "gif" || ext == "jpg" || ext == "png" || ext == "bmp")) {
	        alert("이미지파일 (.jpg, .png, .gif,.bmp ) 만 업로드 가능합니다.");
	        return false;
	}
	    else{
	    	return true;
	    }
}
	else{
		return true;
	}
}