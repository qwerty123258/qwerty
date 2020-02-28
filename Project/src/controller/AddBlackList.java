package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;


@WebServlet("/AddBlackList")
public class AddBlackList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddBlackList() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		UserService service =new UserService();
		if(id.equals("qwerty123258")) {
			response.getWriter().write("adminAddFail");
		}
		else {
			boolean result = service.addBlackList(id);
			if(result) {
				response.getWriter().write("addSuccess");
			}
			else {
				response.getWriter().write("addFail");
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