package dto;

public class FileDTO {
	private String bfno;
	private String bfile;
	private String boriginfile;
	private String price;
	private String bno;
	
	@Override
	public String toString() {
		return "FileDTO [bfno=" + bfno + ", bfile=" + bfile + ", boriginfile=" + boriginfile + ", price=" + price
				+ ", bno=" + bno + "]";
	}
	
	public String getBfno() {
		return bfno;
	}
	public void setBfno(String bfno) {
		this.bfno = bfno;
	}
	public String getBfile() {
		return bfile;
	}
	public void setBfile(String bfile) {
		this.bfile = bfile;
	}
	public String getBoriginfile() {
		return boriginfile;
	}
	public void setBoriginfile(String boriginfile) {
		this.boriginfile = boriginfile;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getBno() {
		return bno;
	}
	public void setBno(String bno) {
		this.bno = bno;
	}
}
