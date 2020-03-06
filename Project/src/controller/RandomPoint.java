package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.CommentService;
import service.UserService;

@WebServlet("/RandomPoint")
public class RandomPoint extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RandomPoint() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session =request.getSession();
		String id=(String) session.getAttribute("id");
		UserService service =  new UserService();
			int point=20+(int)((Math.random() * 10) +1);
			boolean result=service.randomPoint(id,point);
			if(result) {
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
