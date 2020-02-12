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

@WebServlet("/BoardDetail")
public class BoardDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardDetail() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String bnum=request.getParameter("board");
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
			request.setAttribute("bfile", board.getBfile());
			RequestDispatcher dispatcher = request.getRequestDispatcher("BoardDetail.jsp");
			dispatcher.forward(request, response);
		}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
