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
function idinputfocus(){
    document.getElementById("idinput").style.backgroundColor="black";
    document.getElementById("idinput").style.color="white";
}
function idinputblur(){
    document.getElementById("idinput").style.backgroundColor="";
    document.getElementById("idinput").style.color="black";
    var id=document.getElementById("idinput").value;
    if(id.length==0){
        document.getElementById("idtext").style.color="red";
        document.getElementById("idtext").innerHTML="ID는 필수 입력입니다."
    }
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
function passinputfocus(){
    document.getElementById("passinput").style.backgroundColor="black";
    document.getElementById("passinput").style.color="white";
}
function passinputblur(){
    document.getElementById("passinput").style.backgroundColor="";
    document.getElementById("passinput").style.color="black";
}
function passcheckinputfocus(){
    document.getElementById("pass_checkinput").style.backgroundColor="black";
    document.getElementById("pass_checkinput").style.color="white";
}
function passcheckinputblur(){
    document.getElementById("pass_checkinput").style.backgroundColor="";
    document.getElementById("pass_checkinput").style.color="black";
}
function nameinputfocus(){
    document.getElementById("nameinput").style.backgroundColor="black";
    document.getElementById("nameinput").style.color="white";
}
function nameinputblur(){
    document.getElementById("nameinput").style.backgroundColor="";
    document.getElementById("nameinput").style.color="black";
}
function emailinputfocus(){
    document.getElementById("emailnameinput").style.backgroundColor="black";
    document.getElementById("emailnameinput").style.color="white";
}
function emailinputblur(){
    document.getElementById("emailnameinput").style.backgroundColor="";
    document.getElementById("emailnameinput").style.color="black";
}