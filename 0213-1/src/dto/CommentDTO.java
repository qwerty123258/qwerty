package dto;

public class CommentDTO {
	private int cnum;
	private int bnum;
	private String ccontent;
	private String writer;
	@Override
	public String toString() {
		return "CommentDTO [cnum=" + cnum + ", bnum=" + bnum + ", ccontent=" + ccontent + ", writer=" + writer
				+ ", writedate=" + writedate + "]";
	}
	public int getCnum() {
		return cnum;
	}
	public void setCnum(int cnum) {
		this.cnum = cnum;
	}
	public int getBnum() {
		return bnum;
	}
	public void setBnum(int bnum) {
		this.bnum = bnum;
	}
	public String getCcontent() {
		return ccontent;
	}
	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getWritedate() {
		return writedate;
	}
	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
	private String writedate;
}
