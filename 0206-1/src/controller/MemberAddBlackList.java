package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberAddBlackListService;

@WebServlet("/BlackList")
public class MemberAddBlackList extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MemberAddBlackList() {
        super();
    }
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id=request.getParameter("id");
		if(id.equals("qwerty123258")) {
			response.sendRedirect("AdminBlackListFail.jsp");
		}
		else {
			MemberAddBlackListService service = new MemberAddBlackListService();
			boolean result=service.addBlackList(id);
			if(result) {
				response.sendRedirect("BlackListSuccess.jsp");
			}
			else {
				response.sendRedirect("BlackListFail.jsp");
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
