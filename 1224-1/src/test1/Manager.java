package test1;

public class Manager extends Employee {
	
	
	public Manager(String name, double salary,double managersal) {
		super(name, salary);
		this.managersal=managersal;
	}



	double managersal;
	
	
	@Override
	public double increasesal() {
		salary*=1.2;
		return salary;
	}


	@Override
	public String toString() {
		return "Manager [name=" + name + ", salary=" + salary + ", managersal=" + managersal+"]";
	}
}
