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


@WebServlet("/DramaList")
public class DramaList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DramaList() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Paging paging = new Paging();
		BoardService boardservice = new BoardService();
		int count=boardservice.countDramaBoard();
    	int page = 1;
        if(request.getParameter("page")!=null){
            page = Integer.parseInt(request.getParameter("page"));
        }
        paging.setPage(page);
        paging.setTotalCount(count);
        List<BoardDTO> boardList=new ArrayList<BoardDTO>();
        boardList=boardservice.dramaList(paging);
        request.setAttribute("boardList", boardList);
        request.setAttribute("paging", paging);
        RequestDispatcher dispatcher = request.getRequestDispatcher("DramaList.jsp");
        dispatcher.forward(request, response);
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
