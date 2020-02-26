package dto;

public class RequestDTO {
	private String rno;
	private String rtitle;
	private String id;
	private String rcontent;
	private String writedate;
	@Override
	public String toString() {
		return "RequestDTO [rno=" + rno + ", rtitle=" + rtitle + ", id=" + id + ", rcontent=" + rcontent
				+ ", writedate=" + writedate + "]";
	}
	public String getRno() {
		return rno;
	}
	public void setRno(String rno) {
		this.rno = rno;
	}
	public String getRtitle() {
		return rtitle;
	}
	public void setRtitle(String rtitle) {
		this.rtitle = rtitle;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRcontent() {
		return rcontent;
	}
	public void setRcontent(String rcontent) {
		this.rcontent = rcontent;
	}
	public String getWritedate() {
		return writedate;
	}
	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
}
