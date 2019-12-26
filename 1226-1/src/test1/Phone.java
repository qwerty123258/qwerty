package test1;

public interface Phone {
	default void callbutton(String num) {
		System.out.println(num+"에게"+" 전화를 건다");
	}
	default void callreceivebutton(String num) {
		System.out.println(num+"의"+" 전화를 받는다.");
	}
}
