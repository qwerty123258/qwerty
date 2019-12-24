package test2;

public class Tvmain {

	public static void main(String[] args) {
		Tv tv= new Tv();
		tv.turnOn();
		tv.setVol(5);
		tv.setMute(true); //상속처럼 메서드 정의되어있지 않아도 호출은 가능.
		tv.setMute(false); //Tv쪽에서 메서드 재정의
		tv.turnOff();
		
//----------------------------------------------------------//
//      다형성을 활용해 리모트하기위해선 인터페이스 타입자체를 변수화.
		RemoteControl rc= new Tv();
		rc.turnOn();
		rc.setVol(5);
		rc.turnOff();
		rc = new Audio();
		rc.turnOn();
		rc.setVol(8);
		System.out.println(RemoteControl.MAX_VOL);
		rc.setVol(11); //소리를 더 큰 값을 입력했기때문에 인터페이스 최대치인 10으로 강제?적용.
		rc.setMute(true); //재정의한 Tv와 하지 않은 Audio 출력 차이..
		rc.turnOff();
		RemoteControl.changeBat();
		rc= new SmartTv();
		rc.turnOn();
		rc.setVol(7);
		InternetSearch is = new SmartTv();
		is.search("구글"); //인터페이스 2개를 사용d시에...특정 인터페이스에만 있는 메서드는 따로 변수화 해서 사용해야함.

	}

}
