package test1;

public class SmartPhonemain {

	public static void main(String[] args) {
		SmartPhone sp= new SmartPhone();
		MobilePhone ph= sp;
		ph.callbutton("010-4567-7896");
		ph.callreceivebutton("010-4567-7896"); //모바일 폰에는 없는 기능이지만 상속을 받아서 사용가능.
		ph.messagebutton("010-1234-5678");
		ph.messagereceivebutton("010-1234-5678");
		MP3 mp3= sp;
		mp3.playbutton();
		mp3.stopbutton();
		sp.caculation(15, 224); //객체는 여러 인터페이스를 참조할수 있으면서 상속도 받을수 있음.

	}

}