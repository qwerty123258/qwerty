package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;


@WebServlet("/RemoveBlackList")
public class RemoveBlackList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RemoveBlackList() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		UserService service =new UserService();
		if(id.equals("qwerty123258")) {
			
			response.getWriter().write("adminRemoveFail");
		}
		else {
			boolean result = service.removeBlackList(id);
			if(result) {
				response.getWriter().write("removeSuccess");
			}
			else {
				response.getWriter().write("removeFail");
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
