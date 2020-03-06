/**
 * 
 */
function domainselect(){
    var email=document.getElementById("domainvalue").value;
    document.getElementById("domain_input").value=email;
}
function pwreg(){
    var reg = /^(?=.*[a-z])(?=.*\d)[a-zA-Z\d$@$!%*#?&]{8,16}$/;
    var pw=document.getElementById("pw_input").value;
    if(pw.match(reg)){
        document.getElementById("pwtext").style.color="green";
        document.getElementById("pwtext").innerHTML="사용가능"
    }
    if(!pw.match(reg)){
        document.getElementById("pwtext").style.color="red";
        document.getElementById("pwtext").innerHTML="유효하지 않습니다."
    }
}