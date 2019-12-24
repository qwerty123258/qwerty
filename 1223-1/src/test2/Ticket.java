package test2;

public class Ticket {	
	int ticketnum;
	double price;
	
	public Ticket(int ticketnum, double price) {
		super();
		this.ticketnum = ticketnum;
		this.price = price;
	}
	
	public double price() {
		return price;
	}
}
