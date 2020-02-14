package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDTO;
import page.Paging;
import service.KeywordSearchService;

@WebServlet("/BoardSearch")
public class BoardSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public BoardSearch() {
        super();
    }
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Paging paging = new Paging();
		String searchOpt=request.getParameter("searchOpt");
		String keyword=request.getParameter("keyword");
		if(searchOpt.equals("제목")) {
			KeywordSearchService service = new KeywordSearchService();
	    	int count=service.titleCount(keyword);
	    	int page = 1;
	        if(request.getParameter("page")!=null){
	            page = Integer.parseInt(request.getParameter("page"));
	        }
	        paging.setPage(page);
	        paging.setTotalCount(count);
			List<BoardDTO> boardList=service.titleSearch(keyword,paging);
	        request.setAttribute("board", boardList);
	        request.setAttribute("paging", paging);
	        request.setAttribute("searchOpt", searchOpt);
	        request.setAttribute("keyword", keyword);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("SearchList.jsp");
	        dispatcher.forward(request, response);
			System.out.println("제목");
		}
		else if(searchOpt.equals("작성자")) {
			KeywordSearchService service = new KeywordSearchService();
	    	int count=service.writerCount(keyword);
	    	int page = 1;
	        if(request.getParameter("page")!=null){
	            page = Integer.parseInt(request.getParameter("page"));
	        }
	        paging.setPage(page);
	        paging.setTotalCount(count);
			List<BoardDTO> boardList=service.writerSearch(keyword,paging);
	        request.setAttribute("board", boardList);
	        request.setAttribute("paging", paging);
	        request.setAttribute("searchOpt", searchOpt);
	        request.setAttribute("keyword", keyword);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("SearchList.jsp");
	        dispatcher.forward(request, response);
			System.out.println("작성자");
		}
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
