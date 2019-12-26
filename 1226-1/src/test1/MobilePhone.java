package test1;

public interface MobilePhone extends Phone {
	default void messagebutton(String num) {
		System.out.println(num+"에게"+" 문자를 발신한다.");
	}
	default void messagereceivebutton(String num) {
		System.out.println(num+"의"+" 문자를 수신한다.");
	}
}
