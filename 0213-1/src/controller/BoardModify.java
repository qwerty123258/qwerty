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

@WebServlet("/BoardModify")
public class BoardModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardModify() {
        super();
    }
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	String bnum=request.getParameter("bnum");
    	String page=request.getParameter("page");
    	System.out.println(bnum);
    	String bpassword=request.getParameter("bpassword");
    	BoardDTO board=new BoardDTO();
    	DetailService service=new DetailService();
    	service.detail(bnum,board);
    	if(bpassword.equals(board.getBpassword())) {
    		request.setAttribute("bnum", board.getBnum());
    		request.setAttribute("title", board.getTitle());
    		request.setAttribute("bcontent", board.getBcontent());
    		request.setAttribute("page", page);
    		request.setAttribute("bimgfile",board.getBimgfile());
    		request.setAttribute("bfile",board.getBfile());
    		RequestDispatcher dispatcher = request.getRequestDispatcher("BoardModify.jsp");
    		dispatcher.forward(request, response);
    	}
    	else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("BoardDetail.jsp");
			dispatcher.forward(request, response);
    	}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
