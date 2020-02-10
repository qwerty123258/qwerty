package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDTO;
import service.WriteService;

@WebServlet("/write")
public class Write extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Write() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String writer=request.getParameter("writer");
		String password=request.getParameter("password");
		String title=request.getParameter("title");
		String bcontent=request.getParameter("bcontent");
		BoardDTO board=new BoardDTO();
		board.setWriter(writer);
		board.setPassword(password);
		board.setTitle(title);
		board.setBcontent(bcontent);
		WriteService service=new WriteService();
		boolean result=service.writeBoard(board);
		if(result) {
			response.sendRedirect("Main.jsp");
		}
		else {
			response.sendRedirect("Write.jsp");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
