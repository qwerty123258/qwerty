/**
 * 
 */
   function imgFileCheck(){
	    var imgfile=document.getElementById("imgfile").value;
		if (imgfile != "") {
		    var ext = imgfile.slice(imgfile.lastIndexOf(".") + 1).toLowerCase();
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