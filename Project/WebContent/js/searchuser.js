/**
 * 
 */

function Pwdomainselect(){
    var email=document.getElementById("Pwdomainvalue").value;
    document.getElementById("Pwdomain_input").value=email;
}
function IDpnreg(){
    var reg =  /^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))-[1-4][0-9]{6}$/;
    var personno=$('#IDpersonno_input').val();
    var personno2=$('#IDpersonno_input2').val();
    var personno3=personno+"-"+personno2;
    if(personno3.match(reg)){
        document.getElementById("IDpntext").style.color="green";
        document.getElementById("IDpntext").innerHTML="사용가능"
    }
    if(!personno3.match(reg)){
        document.getElementById("IDpntext").style.color="red";
        document.getElementById("IDpntext").innerHTML="유효하지 않습니다."
    }
}