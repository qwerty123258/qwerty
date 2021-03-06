package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.UserService;

@WebServlet("/Point")
public class Point extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Point() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String point = request.getParameter("point");
		UserService user = new UserService();
		HttpSession session =request.getSession();
		String id=(String) session.getAttribute("id");
		boolean result=user.addPoint(id,point);
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
