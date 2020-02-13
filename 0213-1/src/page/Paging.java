package page;
 
public class Paging {
    private int page =1; //현재 페이지 (get)
    private int totalCount; //전체 게시글의 수
    private int beginPage;  //페이지 네비게이션의 시작 페이지 번호
    private int endPage;    //페이지 네비게이션의 끝 페이지 번호
    private int displayRow =5;  //한 페이지에 몇 개의 글을 보여줄것인지?
    private int displayPage =5;  //한 페이지에 몇 개의 페이지 번호를 보여줄것인지(네비게이션 링크 숫자)
    private int startNum; 
    private int endNum; 
    private int totalPage; //가장 마지막 페이지 번호
        
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
        endPage = ((int)Math.ceil(page/(double)displayPage))*displayPage; //현재 페이지가 7일때 몫은 1이고 나머지는 버려지지만
        // 올림 함수로 2가 나오고 곱하기 5를 하면 페이지 네비게이션의 마지막 페이지는 10이 된다.
        beginPage = endPage - (displayPage - 1);
        totalPage = (int)Math.ceil(totalCount/(double)displayRow); //올림용 함수 총 30개 게시글이면 토탈 페이지는 6
        if(totalPage<endPage){ //토탈 페이지보다 마지막 페이지가 크면
            endPage = totalPage; //마지막 페이지를 토탈페이지로 바꿔버림.(혹시 모를 예외 방지)
        }
        
    }
}