package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.UserService;

@WebServlet("/WriteAccessCheck")
public class WriteAccessCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public WriteAccessCheck() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 request.setCharacterEncoding("UTF-8");
			HttpSession session =request.getSession();
			String id=(String) session.getAttribute("id");
			UserService service=new UserService();
			boolean result=service.writeAccessCheck(id);
			if(result) {
				response.getWriter().write("yes");
			}
			else {
				response.getWriter().write("no");
			}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
