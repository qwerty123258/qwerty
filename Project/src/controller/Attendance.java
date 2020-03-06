package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.HistoryService;
import service.UserService;


@WebServlet("/Attendance")
public class Attendance extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Attendance() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session =request.getSession();
		String id=(String) session.getAttribute("id");
		HistoryService history=new HistoryService();
		String action="출석체크";
		boolean result=history.addHistory(id, action);
		if(result) {
			UserService user = new UserService();
			int point=30+(int)((Math.random() * 10) +1);
			user.randomPoint(id, point);
			response.getWriter().print(point);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
