package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberDeleteService;


@WebServlet("/Delete")
public class MemberDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberDelete() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id=request.getParameter("id");
		MemberDeleteService service = new MemberDeleteService();
		boolean result=service.deleteMember(id);
		if(result) {
			response.sendRedirect("DeleteSuccess.jsp");
		}
		else {
			response.sendRedirect("DeleteFail.jsp");
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
