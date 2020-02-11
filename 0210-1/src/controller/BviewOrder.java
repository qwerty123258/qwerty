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
import service.BoardListService;


@WebServlet("/bviewOrder")
public class BviewOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BviewOrder() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		List<BoardDTO> boardList= new ArrayList<BoardDTO>();
		BoardListService service= new BoardListService();
		boardList=service.boardListBviewOrder();
		request.setAttribute("board", boardList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("BoardListBviewOrder.jsp");
		dispatcher.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
