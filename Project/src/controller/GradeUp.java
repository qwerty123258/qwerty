package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.BoardService;
import service.CommentService;
import service.UserService;

@WebServlet("/GradeUp")
public class GradeUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GradeUp() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session =request.getSession();
		String id=(String) session.getAttribute("id");
		BoardService board = new BoardService();
		CommentService comment = new CommentService();
		UserService user = new UserService();
		int commentCount=comment.countUserComment(id);
		int boardCount=board.countUserBoard(id);
		String grade=user.getGrade(id);
		if(!id.equals("qwerty123258")) {
			if(grade.equals("BRONZE")) {
				if(commentCount>4) {
					grade="SILVER";
					user.setGrade(id,grade);
					response.getWriter().write("SILVER");
				}
		     }
			if(grade.equals("SILVER")) {
				if(commentCount>4 && boardCount>4) {
					grade="GOLD";
					user.setGrade(id,grade);
					response.getWriter().write("GOLD");
				}
		     }
			if(grade.equals("GOLD")) {
				if(commentCount>99 && boardCount>29) {
					grade="DIAMOND";
					user.setGrade(id,grade);
					response.getWriter().write("DIAMOND");
				}
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
