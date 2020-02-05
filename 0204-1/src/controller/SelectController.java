package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.InputService;

@WebServlet("/selectDB")
public class SelectController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SelectController() {
        super();
    }

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		InputService service = new InputService();
		List<String> dataList= new ArrayList<String>();
		dataList=service.selectDB();
		System.out.println(dataList.toString());
		request.setAttribute("reqData1", dataList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Select.jsp");
		dispatcher.forward(request, response);
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
