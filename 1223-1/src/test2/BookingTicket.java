package test2;

public class BookingTicket extends Ticket {

	
	public BookingTicket(int ticketnum, boolean d30, boolean d10, boolean d5) {
		this.d30 = d30;
		this.d10 = d10;
		this.d5 = d5;
	}
	
	public double d30() {
		if(this.d30) {
			setPrice(this.price*0.5);
		}
		return price;
	}
	
	public double d10() {
		if(this.d10) {
			setPrice(this.price*0.8);
			
		}
		return price;
	}
	
	public double d5() {
		if(this.d5) {
			setPrice(this.price*0.9);
			
		}
		return price;
	}
	
	boolean d30=false;
	boolean d10=false;
	boolean d5=false;
}
