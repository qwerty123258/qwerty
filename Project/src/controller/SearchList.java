package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDTO;
import page.Paging;
import service.BoardService;

@WebServlet("/SearchList")
public class SearchList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchList() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String keyword=request.getParameter("keyword");
		BoardService board = new BoardService();
		int count=board.searchCount(keyword);
		Paging paging=new Paging();
    	int page = 1;
        if(request.getParameter("page")!=null){
            page = Integer.parseInt(request.getParameter("page"));
        }
        paging.setPage(page);
        paging.setTotalCount(count);
        List<BoardDTO> boardList=new ArrayList<BoardDTO>();
        boardList=board.searchList(keyword,paging);
        request.setAttribute("boardList", boardList);
        request.setAttribute("keyword", keyword);
        request.setAttribute("paging", paging);
        RequestDispatcher dispatcher = request.getRequestDispatcher("SearchList.jsp");
        dispatcher.forward(request, response);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
