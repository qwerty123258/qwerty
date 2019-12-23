package test2;

public class NormalTicket extends Ticket {
	
	public NormalTicket(int ticketnum,boolean creditcard) {
		this.ticketnum = ticketnum;
		this.creditcard = creditcard;
	}
	
	boolean creditcard=false;
	
	public double creditcard() {
		if(this.creditcard) {
			setPrice(this.price*1.05);
		}
		return price;
	}
	
}
