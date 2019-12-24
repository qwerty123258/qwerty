package test2;

public class BookingTicket extends Ticket {

	public BookingTicket(int ticketnum, double price, boolean d30, boolean d10, boolean d5) {
		super(ticketnum, price);
		this.d30 = d30;
		this.d10 = d10;
		this.d5 = d5;
	}
	
	@Override
	public double price() {
		if(this.d30) {
			this.price*=0.5;
			return price;
		}
		else if(this.d10) {
			this.price*=0.8;
			return price;
		}
		else if(this.d5) {
			this.price*=0.9;
			return price;
		}
		return price;
	}
	
	boolean d30=false;
	boolean d10=false;
	boolean d5=false;
}
