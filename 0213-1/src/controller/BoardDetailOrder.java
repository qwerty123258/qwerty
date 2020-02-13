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
import service.DetailService;

@WebServlet("/BoardDetailOrder")
public class BoardDetailOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardDetailOrder() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String bnum=request.getParameter("board");
		String page=request.getParameter("page");
		BoardDTO board=new BoardDTO();
		DetailService service=new DetailService();
		service.bViewIncrease(bnum);
		service.detail(bnum,board);
			request.setAttribute("bnum", board.getBnum());
			request.setAttribute("title", board.getTitle());
			request.setAttribute("bcontent", board.getBcontent());
			request.setAttribute("bview", board.getBview());
			request.setAttribute("writer", board.getWriter());
			request.setAttribute("writedate", board.getWritedate());
			request.setAttribute("bpassword", board.getBpassword());
			request.setAttribute("bimgfile", board.getBimgfile());
			request.setAttribute("bfile", board.getBfile());
			request.setAttribute("page", page);
			RequestDispatcher dispatcher = request.getRequestDispatcher("BoardDetailOrder.jsp");
			dispatcher.forward(request, response);
		}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
