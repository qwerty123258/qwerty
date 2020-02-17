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
import service.PageService;


@WebServlet("/ViewMyBoard")
public class ViewMyBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ViewMyBoard() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id=request.getParameter("id");
        Paging paging = new Paging();
    	PageService service = new PageService();
    	int count=service.MyBoardCount(id);
    	int page = 1;
        if(request.getParameter("page")!=null){
            page = Integer.parseInt(request.getParameter("page"));
        }
        paging.setPage(page);
        paging.setTotalCount(count);
    	List<BoardDTO> boardList=service.MyBoardList(id,paging);
        request.setAttribute("board", boardList);
        request.setAttribute("paging", paging);
        RequestDispatcher dispatcher = request.getRequestDispatcher("MyBoardList.jsp");
        dispatcher.forward(request, response);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
