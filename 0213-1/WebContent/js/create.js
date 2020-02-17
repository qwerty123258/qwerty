
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
    function transfer(){
        var idreg = /^(?=.*[a-z])(?=.*\d)[A-Za-z\d]{6,20}$/;
        var id=document.getElementById("idinput").value;
        var passreg = /^(?=.*[a-z])(?=.*\d)[a-zA-Z\d$@$!%*#?&]{8,16}$/;
        var phonereg=/^\d{3}-\d{4}-\d{4}$/;
        var pass=document.getElementById("passinput").value;
        var pass2=document.getElementById("pass_checkinput").value;
        var phone=document.getElementById("inputphone").value;
        var idcheckresult=document.getElementById("idresult").value;
        if(pass.match(passreg) && id.match(idreg) && pass==pass2 && phone.match(phonereg)){
        	if(imgFileCheck()){
        		if(idcheckresult=="true"){
                	document.getElementById("formTrans").submit();
        		}
        		else if(idcheckresult==""){
        			alert("아이디 중복확인을 하셔야합니다.");
        		}
        	}
        }
        else if(pass!=pass2){
        	alert("비밀번호가 일치하지 않습니다.");
        }
        else{
        	alert("입력된 값이 올바르지 않습니다.");
        }
    }
    function checkID(){
    	if(addForm.id.value == "") {
    		alert("id를 입력하시오.");
    		addForm.id.focus();
    	} else {
    		{
    			url = "CheckID.jsp?id=" + addForm.id.value;
    			window.open(
    					url,
    					"아이디 중복확인", "toolbar=no, width=350, height=150, top=150, left=150");
    		}
    }
    }
    function imgFileCheck(){
        var imgfile=document.getElementById("picture").value;
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
    		return false;
    	}
    }
    function idcheck(){
        var reg = /^(?=.*[a-z])[A-Za-z\d]{6,20}$/;
        var id=document.getElementById("idinput").value;
        if(id.match(reg)){
            document.getElementById("idtext").style.color="green";
            document.getElementById("idtext").innerHTML="사용가능"
        }
        if(!id.match(reg)){
            document.getElementById("idtext").style.color="red";
            document.getElementById("idtext").innerHTML="유효하지 않습니다."
        }
    }
    function passcheck(){
        var reg = /^(?=.*[a-z])(?=.*\d)[a-zA-Z\d$@$!%*#?&]{8,16}$/;
        var pass=document.getElementById("passinput").value;
        if(pass.match(reg)){
            document.getElementById("passtext").style.color="green";
            document.getElementById("passtext").innerHTML="사용가능"
        }
        if(!pass.match(reg)){
            document.getElementById("passtext").style.color="red";
            document.getElementById("passtext").innerHTML="유효하지 않습니다."
        }
    }
    function eminput(){
        var email=document.getElementById("emailvalue").value;
        document.getElementById("emailinput").value=email;
    }
    function passconfirm(){
        var pass=document.getElementById("passinput").value;
        var pass2=document.getElementById("pass_checkinput").value;
        if(pass==pass2){
            document.getElementById("passtext").style.color="green";
            document.getElementById("passtext").innerHTML="비밀번호가 일치합니다."
        }
        if(pass!=pass2){
            document.getElementById("passtext").style.color="red";
            document.getElementById("passtext").innerHTML="비밀번호가 일치하지 않습니다."
        }
    }
    function phonecheck(){
    	var phone=document.getElementById("inputphone").value;
    	var phonereg=/^\d{3}-\d{4}-\d{4}$/;
        if(phone.match(phonereg)){
            document.getElementById("phonetext").innerHTML="사용가능";
            document.getElementById("phonetext").style.color="green";
        }
        else if(!phone.match(phonereg)){
            document.getElementById("phonetext").innerHTML="유효하지 않습니다.";
            document.getElementById("phonetext").style.color="red";
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