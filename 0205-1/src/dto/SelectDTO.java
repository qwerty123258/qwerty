package dto;

public class SelectDTO {
	private String data1;
	
	public SelectDTO() {
		super();
	}
	
	@Override
	public String toString() {
		return data1;
	}

	public String getData1() {
		return data1;
	}
	public void setData1(String data1) {
		this.data1 = data1;
	}
}