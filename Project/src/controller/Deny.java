package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.RequestService;

@WebServlet("/Deny")
public class Deny extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Deny() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String rno=request.getParameter("rno");
		RequestService service = new RequestService();
		boolean result=service.deletereq(rno);
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
