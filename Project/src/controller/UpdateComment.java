package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CommentService;

@WebServlet("/UpdateComment")
public class UpdateComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UpdateComment() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String cno=request.getParameter("cno");
		String content=request.getParameter("content");
		System.out.println(cno);
		System.out.println(content);
		CommentService service = new CommentService();
		boolean result=service.updateComment(cno,content);
		if(result) {
			response.getWriter().write("Success");
		}
		else {
			response.getWriter().write("Fail");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
