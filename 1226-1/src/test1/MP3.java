package test1;

public interface MP3 {
	default void playbutton() {
		System.out.println("재생 시작");
	}
	default void stopbutton() {
		System.out.println("재생 중지");
	}
}
