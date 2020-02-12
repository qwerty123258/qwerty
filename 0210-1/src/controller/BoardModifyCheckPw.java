package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDTO;
import service.DetailService;


@WebServlet("/BoardModifyCheckPw")
public class BoardModifyCheckPw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardModifyCheckPw() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String bnum=request.getParameter("bnum");
		BoardDTO board=new BoardDTO();
		DetailService service=new DetailService();
		service.detail(bnum,board);
		request.setAttribute("bnum", bnum);
		request.setAttribute("bpassword", board.getBpassword());
		RequestDispatcher dispatcher = request.getRequestDispatcher("BoardModifyCheckPw.jsp");
		dispatcher.forward(request, response);

		}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}