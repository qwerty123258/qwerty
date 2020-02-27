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
import dto.FileDTO;
import service.BoardService;
import service.FileService;

@WebServlet("/ModifyBoard")
public class ModifyBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ModifyBoard() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String bno=request.getParameter("bno");
		BoardService boardservice= new BoardService();
		BoardDTO board=new BoardDTO();
		boardservice.boardDetail(bno, board);
		FileService fileservice = new FileService();
		List<FileDTO> fileList =new ArrayList<FileDTO>();
		fileList=fileservice.fileDetail(bno);
		request.setAttribute("fileList", fileList);
		request.setAttribute("bno", bno);
		request.setAttribute("title", board.getTitle());
		request.setAttribute("category", board.getCategory());
		request.setAttribute("content", board.getContent());
		request.setAttribute("bimgfile", board.getBimgfile());
        RequestDispatcher dispatcher = request.getRequestDispatcher("ModifyBoard.jsp");
        dispatcher.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
