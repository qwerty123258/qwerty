package test1;

public class Bank {
	private  int accountNumber;
	private  int customerNumber;
	private String name;
	private int money;
	
	public Bank(String name, int accountNumber, int customerNumber, int money) {
		this.accountNumber = accountNumber;
		this.customerNumber = customerNumber;
		this.name = name;
		this.money = money;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}

	public int getCustomerNumber() {
		return customerNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money += money;
	}
	public void setminusMoney(int money) {
		this.money -= money;
	}
}
