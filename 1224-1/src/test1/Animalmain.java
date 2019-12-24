package test1;

public class Animalmain {

	public static void main(String[] args) {
//		Animal ani = new Animal(); 객체화 불가.
		Dog dog = new Dog();
		Cat cat = new Cat();
		dog.breath();
		dog.sound();
		dog.walking();
		cat.breath();
		cat.sound();
		cat.walking();
		
		//다형성(Polymorphism)
		Animal ani = new Dog(); //부모자신을 객체화하진 못하지만 자식 객체를 부모에 넣기 가능.
		ani.sound();
		ani=new Cat();
		ani.sound();
//		cat=Dog(); 부모는 자식보다 범위가 넓지만 자식끼리는 서로 동등해서 안됨

	}

}
