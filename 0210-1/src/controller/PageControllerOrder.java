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

@SuppressWarnings("serial")
@WebServlet("/PageListOrder")
public class PageControllerOrder extends HttpServlet{
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
        Paging paging = new Paging();
    	PageService service = new PageService();
    	int count=service.BoardCount();
    	List<BoardDTO> boardList=service.BoardListOrder(paging);
    	int page = 1;
        if(request.getParameter("page")!=null){
            page = Integer.parseInt(request.getParameter("page"));
        }
        paging.setTotalCount(count);
        paging.setPage(page);
        request.setAttribute("board", boardList);
        request.setAttribute("paging", paging);
        RequestDispatcher dispatcher = request.getRequestDispatcher("BoardListOrder.jsp");
        dispatcher.forward(request, response);
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
}