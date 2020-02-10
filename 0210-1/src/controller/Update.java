package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.BoardUpdateService;

@WebServlet("/Update")
public class Update extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Update() {
        super();
    }
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
    	String bnum=request.getParameter("bnum");
    	String title=request.getParameter("title");
    	String bcontent=request.getParameter("bcontent");
    	BoardUpdateService service = new BoardUpdateService();
    	boolean result=service.updateBoard(bnum,title,bcontent);
    	if(result) {
    		response.sendRedirect("ModifySuccess.jsp");
    	}
    	else {
    		response.sendRedirect("ModifyFail.jsp");
    	}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
