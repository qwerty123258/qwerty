package com.icia.myproject.page;
 
public class Paging {
    @Override
	public String toString() {
		return "Paging [page=" + page + ", totalCount=" + totalCount + ", beginPage=" + beginPage + ", endPage="
				+ endPage + ", displayRow=" + displayRow + ", displayPage=" + displayPage + ", startNum=" + startNum
				+ ", endNum=" + endNum + ", totalPage=" + totalPage + "]";
	}
	private int page =1;
    private int totalCount;
    private int beginPage;
    private int endPage;
    private int displayRow =5;
    private int displayPage =5;
    private int startNum; 
    private int endNum; 
    private int totalPage;
        
    public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getStartNum() {
    	startNum = (page-1)*displayRow+1;
		return startNum;
	}
	public int getEndNum() {
		endNum = page*displayRow;
		return endNum;
	}
	public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public int getTotalCount() {
        return totalCount;
    }
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        paging();
    }
    public int getDisplayRow() {
        return displayRow;
    }
    public void setDisplayRow(int displayRow) {
        this.displayRow = displayRow;
    }
    public int getDisplayPage() {
        return displayPage;
    }
    public void setDisplayPage(int displayPage) {
        this.displayPage = displayPage;
    }
    public int getBeginPage() {
        return beginPage;
    }
    public int getEndPage() {
        return endPage;
    }
    private void paging(){
        endPage = ((int)Math.ceil(page/(double)displayPage))*displayPage;
        beginPage = endPage - (displayPage - 1);
        totalPage = (int)Math.ceil(totalCount/(double)displayRow);
        if(totalPage<endPage){
            endPage = totalPage;
        }
        
    }
}