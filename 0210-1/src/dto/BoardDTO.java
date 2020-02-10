package dto;

public class BoardDTO {
	private int bnum;
	private String writer;
	private String password;
	@Override
	public String toString() {
		return "BoardDTO [bnum=" + bnum + ", writer=" + writer + ", password=" + password + ", title=" + title
				+ ", bcontent=" + bcontent + ", writedate=" + writedate + ", bview=" + bview + "]";
	}
	public int getBnum() {
		return bnum;
	}
	public void setBnum(int bnum) {
		this.bnum = bnum;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBcontent() {
		return bcontent;
	}
	public void setBcontent(String bcontent) {
		this.bcontent = bcontent;
	}
	public String getWritedate() {
		return writedate;
	}
	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
	private String title;
	public int getBview() {
		return bview;
	}
	public void setBview(int bview) {
		this.bview = bview;
	}
	private String bcontent;
	private String writedate;
	private int bview;
}
