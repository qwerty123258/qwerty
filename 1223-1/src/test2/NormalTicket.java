package test2;

public class NormalTicket extends Ticket {
	
	public NormalTicket(int ticketnum,double price, boolean creditcard) {
		super(ticketnum,price);
		this.creditcard = creditcard;
	}
	
	boolean creditcard=false;
	
	@Override
	public double price() {
		if(this.creditcard) {
			this.price*=1.05;
			return price;
		}
		return price;
	}
	
}
