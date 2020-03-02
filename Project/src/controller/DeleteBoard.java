package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.BoardService;

@WebServlet("/DeleteBoard")
public class DeleteBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteBoard() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String bno=request.getParameter("bno");
		String category=request.getParameter("category");
		BoardService service = new BoardService();
		service.deleteBoard(bno);
		request.setAttribute("category", category);
		if(category.equals("movie")) {
	        RequestDispatcher dispatcher = request.getRequestDispatcher("MovieList.jsp");
	        dispatcher.forward(request, response);
		}
		if(category.equals("drama")) {
	        RequestDispatcher dispatcher = request.getRequestDispatcher("DramaList.jsp");
	        dispatcher.forward(request, response);
		}
		if(category.equals("util")) {
	        RequestDispatcher dispatcher = request.getRequestDispatcher("UtilList.jsp");
	        dispatcher.forward(request, response);
		}
		if(category.equals("other")) {
	        RequestDispatcher dispatcher = request.getRequestDispatcher("OtherList.jsp");
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
