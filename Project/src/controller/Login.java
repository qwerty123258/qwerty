package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.UserService;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Login() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id= request.getParameter("id");
		String pw=request.getParameter("pw");
		UserService userservice= new UserService();
		boolean loginresult=userservice.login(id,pw);
		if(loginresult) {
			boolean blackresult=userservice.checkBlackList(id);
			if(blackresult) {
				 response.getWriter().write("blackList");
			}
			else {
				boolean certifyresult=userservice.checkCertify(id);
				if(certifyresult) {
					HttpSession session =request.getSession();
					session.setAttribute("id", id);
					response.getWriter().write("login");
				}
				else {
					response.getWriter().write("certify");
				}
			}
		}
		else {
			response.getWriter().write("loginFail");
		}

		}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
