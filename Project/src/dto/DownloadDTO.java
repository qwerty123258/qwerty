package dto;

public class DownloadDTO {
	private String bno;
	private String bfno;
	private String id;
	private String bfile;
	private String price;
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
	public String getBfno() {
		return bfno;
	}
	public void setBfno(String bfno) {
		this.bfno = bfno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBfile() {
		return bfile;
	}
	public void setBfile(String bfile) {
		this.bfile = bfile;
	}
	public String getWritedate() {
		return writedate;
	}
	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "DownloadDTO [bno=" + bno + ", bfno=" + bfno + ", id=" + id + ", bfile=" + bfile + ", price=" + price
				+ ", writedate=" + writedate + ", title=" + title + "]";
	}
	private String writedate;
	private String title;
}
