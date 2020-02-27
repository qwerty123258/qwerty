package dto;

public class CommentDTO {
	private String cno;
	private String bno;
	private String id;
	private String ccontent;
	private String writedate;
	public String getCno() {
		return cno;
	}
	public void setCno(String cno) {
		this.cno = cno;
	}
	public String getBno() {
		return bno;
	}
	public void setBno(String bno) {
		this.bno = bno;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCcontent() {
		return ccontent;
	}
	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}
	public String getWritedate() {
		return writedate;
	}
	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
	@Override
	public String toString() {
		return "CommentDTO [cno=" + cno + ", bno=" + bno + ", id=" + id + ", ccontent=" + ccontent + ", writedate="
				+ writedate + "]";
	}
}
