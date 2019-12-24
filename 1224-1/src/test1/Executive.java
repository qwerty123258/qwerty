package test1;

public class Executive extends Manager {
	
	double bonus;
	
	public Executive(String name, double salary, double managersal, double bonus) {
		super(name, salary, managersal);
		this.bonus = bonus;
	}
	
	@Override
	public double increasesal() {
		salary*=1.3;
		return salary;
	}

	@Override
	public String toString() {
		return "Executive [name=" + name + ", salary=" + salary
				+ ", managersal=" + managersal+ ", bonus=" + bonus +"]";
	}
	
}
