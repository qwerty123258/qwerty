package dto;

public class BoardDTO {
	public String getBlacklist() {
		return blacklist;
	}
	public void setBlacklist(String blacklist) {
		this.blacklist = blacklist;
	}
	private String bno;
	private String title;
	private String id;
	private String content;
	private String writedate;
	private String bview;
	private String bimgfile;
	private String category;
	private String blacklist;
	private String likes;
	private String report;
	
	public String getLikes() {
		return likes;
	}
	public void setLikes(String likes) {
		this.likes = likes;
	}
	public String getReport() {
		return report;
	}
	public void setReport(String report) {
		this.report = report;
	}
	private String likeuser;
	private String reportuser;
	
	public String getReportuser() {
		return reportuser;
	}
	public void setReportuser(String reportuser) {
		this.reportuser = reportuser;
	}
	public String getLikeuser() {
		return likeuser;
	}
	public void setLikeuser(String likeuser) {
		this.likeuser = likeuser;
	}
	@Override
	public String toString() {
		return "BoardDTO [bno=" + bno + ", title=" + title + ", id=" + id + ", content=" + content + ", writedate="
				+ writedate + ", bview=" + bview + ", bimgfile=" + bimgfile + ", category=" + category + ", blacklist="
				+ blacklist + ", likes=" + likes + ", report=" + report + ", likeuser=" + likeuser + ", reportuser="
				+ reportuser + "]";
	}
	public String getBno() {
		return bno;
	}
	public void setBno(String bno) {
		this.bno = bno;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWritedate() {
		return writedate;
	}
	public void setWritedate(String writedate) {
		this.writedate = writedate;
	}
	public String getBview() {
		return bview;
	}
	public void setBview(String bview) {
		this.bview = bview;
	}
	public String getBimgfile() {
		return bimgfile;
	}
	public void setBimgfile(String bimgfile) {
		this.bimgfile = bimgfile;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
}
