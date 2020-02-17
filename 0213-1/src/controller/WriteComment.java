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
import service.BoardUpdateService;
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
    		commentList=service.selectComment(bnum);
        	request.setAttribute("page", page);
        	request.setAttribute("bnum", bnum);
        	request.setAttribute("commentList", commentList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("BoardDetail.jsp");
			dispatcher.forward(request, response);
    	}
    	else {
        	request.setAttribute("page", page);
        	request.setAttribute("bnum", bnum);
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
