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
import dto.CommentDTO;
import page.Paging;
import service.CommentPageService;
import service.CommentService;
import service.DetailService;

@WebServlet("/BoardDetailOrder")
public class BoardDetailOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardDetailOrder() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String bnum=request.getParameter("bnum");
		String page=request.getParameter("page");
		List<CommentDTO> commentList = new ArrayList<CommentDTO>();
		CommentService commentService= new CommentService();
        Paging paging = new Paging();
    	CommentPageService commentPageService = new CommentPageService();
    	int count=commentPageService.CommentCount(bnum);
    	int commentpage = 1;
        if(request.getParameter("commentpage")!=null){
        	commentpage = Integer.parseInt(request.getParameter("commentpage"));
        }
        paging.setPage(commentpage);
        paging.setTotalCount(count);
		BoardDTO board=new BoardDTO();
		DetailService service=new DetailService();
		service.detail(bnum,board);
		commentList=commentService.selectComment(bnum,paging);
			request.setAttribute("bnum", board.getBnum());
			request.setAttribute("title", board.getTitle());
			request.setAttribute("bcontent", board.getBcontent());
			request.setAttribute("bview", board.getBview());
			request.setAttribute("writer", board.getWriter());
			request.setAttribute("writedate", board.getWritedate());
			request.setAttribute("bpassword", board.getBpassword());
			request.setAttribute("bimgfile", board.getBimgfile());
			ArrayList<String> fileList=new ArrayList<String>();
			if(board.getBfile()==null) {
				request.setAttribute("bfile", board.getBfile());
			}
			else {
				String filear[]=board.getBfile().split("&fileName");
				for(int i=1; i<filear.length; i++) {
					fileList.add(filear[i]);
				}
				request.setAttribute("bfile", fileList);
			}
			request.setAttribute("paging", paging);
			request.setAttribute("commentpage", commentpage);
			request.setAttribute("commentList", commentList);
			request.setAttribute("page", page);
			RequestDispatcher dispatcher = request.getRequestDispatcher("BoardDetailOrder.jsp");
			dispatcher.forward(request, response);
		}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
