package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDTO;
import service.WriteService;

@WebServlet("/BoardWrite")
public class BoardWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardWrite() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String writer=request.getParameter("writer");
		String bpassword=request.getParameter("bpassword");
		String title=request.getParameter("title");
		String bcontent=request.getParameter("bcontent");
		BoardDTO board=new BoardDTO();
		board.setWriter(writer);
		board.setBpassword(bpassword);
		board.setTitle(title);
		board.setBcontent(bcontent);
		WriteService service=new WriteService();
		boolean result=service.writeBoard(board);
		if(result) {
			response.sendRedirect("BoardMain.jsp");
		}
		else {
			response.sendRedirect("BoardWrite.jsp");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
