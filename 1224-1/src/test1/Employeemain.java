package test1;

public class Employeemain {

	public static void main(String[] args) {
		Employee em = new Employee("1번",1000);
		System.out.println("기본급 :"+em.toString());
		em.increasesal();
		System.out.println("인상 후 :"+ em.toString());
		em.increasesal();
		System.out.println("재 인상 후 :"+ em.toString());
		Manager ma = new Manager("2번",1000,200);
		System.out.println("기본급 :"+ma.toString());
		ma.increasesal();
		System.out.println("인상 후 :"+ma.toString());
		ma.increasesal();
		System.out.println("재 인상 후 :"+ma.toString());
		Executive ex = new Executive("3번",1000,200,500);
		System.out.println("기본급 :"+ex.toString());
		ex.increasesal();
		System.out.println("인상 후 :"+ex.toString());
		ex.increasesal();
		System.out.println("재 인상 후 :"+ex.toString());

	}

}
