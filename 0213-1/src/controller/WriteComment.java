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

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dto.CommentDTO;
import page.Paging;
import service.BoardUpdateService;
import service.CommentPageService;
import service.CommentService;

@WebServlet("/WriteComment")
public class WriteComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WriteComment() {
        super();
    }
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	List<CommentDTO> commentList = new ArrayList<CommentDTO>();
    	String page=request.getParameter("page");
    	String bnum=request.getParameter("bnum");
    	String writer=request.getParameter("writer");
    	String ccontent=request.getParameter("ccontent");
    	CommentService service= new CommentService();
    	boolean result=service.writeComment(bnum,writer,ccontent);
    	if(result) {
            Paging paging = new Paging();
        	CommentPageService commentPageService = new CommentPageService();
        	int count=commentPageService.CommentCount(bnum);
        	int commentpage = 1;
            if(request.getParameter("commentpage")!=null){
            	commentpage = Integer.parseInt(request.getParameter("commentpage"));
            }
            paging.setPage(commentpage);
            paging.setTotalCount(count);
    		commentList=service.selectComment(bnum,paging);
        	request.setAttribute("page", page);
        	request.setAttribute("board", bnum);
        	request.setAttribute("paging", paging);
        	request.setAttribute("commentList", commentList);
        	request.setAttribute("commentpage", commentpage);
			RequestDispatcher dispatcher = request.getRequestDispatcher("BoardDetail");
			dispatcher.forward(request, response);
    	}
    	else {
        	request.setAttribute("page", page);
        	request.setAttribute("board", bnum);
			RequestDispatcher dispatcher = request.getRequestDispatcher("BoardDetail");
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
