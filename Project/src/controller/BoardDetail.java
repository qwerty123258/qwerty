package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.BoardDTO;
import service.BoardService;


@WebServlet("/BoardDetail")
public class BoardDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BoardDetail() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String bno=request.getParameter("bno");
		BoardDTO board = new BoardDTO();
		BoardService service = new BoardService();
		service.bviewIncrease(bno);
		service.boardDetail(bno,board);
		request.setAttribute("bno", bno);
		request.setAttribute("title", board.getTitle());
		request.setAttribute("id", board.getId());
		request.setAttribute("bview", board.getBview());
		request.setAttribute("writedate", board.getWritedate());
		request.setAttribute("bimgfile", board.getBimgfile());
		request.setAttribute("content", board.getContent());
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
