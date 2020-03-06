/**
 * 
 */
function domainselect(){
    var email=document.getElementById("domainvalue").value;
    document.getElementById("domain_input").value=email;
}
function idreg(){
    var reg = /^(?=.*[a-z])[A-Za-z\d]{6,20}$/;
    var id=document.getElementById("createid_input").value;
    if(id.match(reg)){
        document.getElementById("idtext").style.color="green";
        document.getElementById("idtext").innerHTML="사용가능"
    }
    if(!id.match(reg)){
        document.getElementById("idtext").style.color="red";
        document.getElementById("idtext").innerHTML="유효하지 않습니다."
    }
}
function pwreg(){
    var reg = /^(?=.*[a-z])(?=.*\d)[a-zA-Z\d$@$!%*#?&]{8,16}$/;
    var pw=document.getElementById("createpw_input").value;
    if(pw.match(reg)){
        document.getElementById("pwtext").style.color="green";
        document.getElementById("pwtext").innerHTML="사용가능"
    }
    if(!pw.match(reg)){
        document.getElementById("pwtext").style.color="red";
        document.getElementById("pwtext").innerHTML="유효하지 않습니다."
    }
}
function pnreg(){
    var reg =  /^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))-[1-4][0-9]{6}$/;
    var personno=$('#personno_input').val();
    var personno2=$('#personno_input2').val();
    var personno3=personno+"-"+personno2;
    if(personno3.match(reg)){
        document.getElementById("pntext").style.color="green";
        document.getElementById("pntext").innerHTML="사용가능"
    }
    if(!personno3.match(reg)){
        document.getElementById("pntext").style.color="red";
        document.getElementById("pntext").innerHTML="유효하지 않습니다."
    }
}