package controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import service.InputService;

@WebServlet("/inputServer")
public class InputController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public InputController() {
        super();
    }
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("do Process 메소드");
		String data1;
		data1=request.getParameter("data1");
		InputService service = new InputService();
		boolean inputResult;
		inputResult=service.inputDB(data1);
		if(inputResult) {
			response.sendRedirect("InsertSuccess.jsp");
		}
		else {
			response.sendRedirect("InsertFail.jsp");
		}
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("do Get 메소드");
		doProcess(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("do Post 메소드");
		doProcess(request,response);
	}
}
