package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CommentService;
import service.HistoryService;

@WebServlet("/AddComments")
public class AddComments extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddComments() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session =request.getSession();
		String id=(String) session.getAttribute("id");
		String bno=request.getParameter("bno");
		String content=request.getParameter("comment");
		HistoryService history=new HistoryService();
		String action="댓글작성";
		boolean check=history.checkHistory(id, action);
		if(check) {
			CommentService service = new CommentService();
			boolean result=service.addComments(id,bno,content);
			if(result) {
				 response.getWriter().write("Success");
			}
			else {
				 response.getWriter().write("Fail");
			}
		}
		else {
			boolean hisresult=history.addHistory(id,action);
			CommentService service = new CommentService();
			boolean result=service.addComments(id,bno,content);
			if(result) {
				 response.getWriter().write("Success");
			}
			else {
				 response.getWriter().write("Fail");
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
