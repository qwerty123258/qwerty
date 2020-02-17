$(function() {
            $("#picture").on('change', function(){
                readURL(this);
            });
        });

        function readURL(input) {
            if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                    $('#profileimg').attr('src', e.target.result);
                }

              reader.readAsDataURL(input.files[0]);
            }
        }
function update(){
	var phone=document.getElementById("phoneinput").value;
	var phonereg=/^\d{3}-\d{4}-\d{4}$/;
    if(phone.match(phonereg)){
    	document.getElementById("update").submit();
    }
    else{
    	alert("휴대폰 번호가 유효하지 않은 형식입니다.");
    }
}
function imgFileCheck(){
    var imgfile=document.getElementById("picture").value;
	if (imgfile != "") {
	    var ext = imgfile.slice(imgfile.lastIndexOf("@") + 1).toLowerCase();
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
function sample5_execDaumPostcode10() {
	new daum.Postcode({
	    oncomplete: function(data) {
	        var addr = data.address; // 최종 주소 변수
	        document.getElementById("sample5_address").value = addr;
	    }
	}).open();
	}