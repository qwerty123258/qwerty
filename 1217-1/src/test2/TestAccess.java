package test2;

import test1.A;

public class TestAccess extends A {
	public void test() {
		A a = new A();
		System.out.println(a.field1); //public
		System.out.println(field2); // protected
//		System.out.println(a.field3); //default
//		System.out.println(a.field4); // private
		a.hello(); //public
		hello1(); // protected
//		a.hello2(); //default
//		a.hello3(); //private
	}
}
