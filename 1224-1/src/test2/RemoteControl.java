package test2;

public interface RemoteControl {
//	1. 메인코드와 객체간 접점
//	2. 객체의 다형성 구현
//	3. 구성요소
//		1.상수필드만선언가능 - 그냥 컴파일해도 static final이 자동으로 붙음
//		2.추상메소드를 가짐
//		3.디폴트 메소드 가짐(기본 실행내용까지).
//		4.정적 메소드 가짐.(인터페이스 객체화하지 않고도 호출 가능)
	int MAX_VOL=10;
	int MIN_VOL=0;
	public void turnOn();
	public void turnOff();
	public void setVol(int vol);
	default void setMute(boolean mute) {
		if(mute) {
		System.out.println("음소거");
		}
		else {
		System.out.println("음소거 해제");
	}
	}
	 public static void changeBat() {
		System.out.println("건전지 교환해주세요.");
	}
	
}