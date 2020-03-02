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
import page.Paging;
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
		String category=request.getParameter("category");
		BoardDTO board = new BoardDTO();
		BoardService service = new BoardService();
		service.bviewIncrease(bno);
		service.boardDetail(bno,board);
		service.checkLike(bno,board);
		service.checkReport(bno,board);
		request.setAttribute("bno", bno);
		request.setAttribute("title", board.getTitle());
		request.setAttribute("id", board.getId());
		request.setAttribute("bview", board.getBview());
		request.setAttribute("writedate", board.getWritedate());
		request.setAttribute("bimgfile", board.getBimgfile());
		request.setAttribute("content", board.getContent());
		request.setAttribute("likeuser", board.getLikeuser());
		request.setAttribute("reportuser", board.getReportuser());
		if(category.equals("movie")) {
			Paging paging = new Paging();
			int count=service.countMovieBoard();
	    	int page = 1;
	        if(request.getParameter("page")!=null){
	            page = Integer.parseInt(request.getParameter("page"));
	        }
	        paging.setPage(page);
	        paging.setTotalCount(count);
	        List<BoardDTO> boardList=new ArrayList<BoardDTO>();
	        boardList=service.movieList(paging);
	        request.setAttribute("boardList", boardList);
	        request.setAttribute("paging", paging);
	        request.setAttribute("category", category);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("BoardDetail.jsp");
	        dispatcher.forward(request, response);
		}
		if(category.equals("drama")) {
			Paging paging = new Paging();
			int count=service.countDramaBoard();
	    	int page = 1;
	        if(request.getParameter("page")!=null){
	            page = Integer.parseInt(request.getParameter("page"));
	        }
	        paging.setPage(page);
	        paging.setTotalCount(count);
	        List<BoardDTO> boardList=new ArrayList<BoardDTO>();
	        boardList=service.dramaList(paging);
	        request.setAttribute("boardList", boardList);
	        request.setAttribute("paging", paging);
	        request.setAttribute("category", category);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("BoardDetail.jsp");
	        dispatcher.forward(request, response);
		}
		if(category.equals("util")) {
			Paging paging = new Paging();
			int count=service.countUtilBoard();
	    	int page = 1;
	        if(request.getParameter("page")!=null){
	            page = Integer.parseInt(request.getParameter("page"));
	        }
	        paging.setPage(page);
	        paging.setTotalCount(count);
	        List<BoardDTO> boardList=new ArrayList<BoardDTO>();
	        boardList=service.utilList(paging);
	        request.setAttribute("boardList", boardList);
	        request.setAttribute("paging", paging);
	        request.setAttribute("category", category);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("BoardDetail.jsp");
	        dispatcher.forward(request, response);
		}
		if(category.equals("other")) {
			Paging paging = new Paging();
			int count=service.countOtherBoard();
	    	int page = 1;
	        if(request.getParameter("page")!=null){
	            page = Integer.parseInt(request.getParameter("page"));
	        }
	        paging.setPage(page);
	        paging.setTotalCount(count);
	        List<BoardDTO> boardList=new ArrayList<BoardDTO>();
	        boardList=service.otherList(paging);
	        request.setAttribute("boardList", boardList);
	        request.setAttribute("paging", paging);
	        request.setAttribute("category", category);
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
