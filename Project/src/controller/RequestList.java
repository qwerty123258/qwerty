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

import dto.RequestDTO;
import page.Paging;
import service.RequestService;

@WebServlet("/RequestList")
public class RequestList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RequestList() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		RequestService service=new RequestService();
		int count=service.countRequest();
		Paging paging = new Paging();
    	int page = 1;
        if(request.getParameter("page")!=null){
            page = Integer.parseInt(request.getParameter("page"));
        }
		paging.setPage(page);
		paging.setTotalCount(count);
		List<RequestDTO> requestList =new ArrayList<RequestDTO>();
		requestList=service.requestList(paging);
		request.setAttribute("paging", paging);
		request.setAttribute("requestList", requestList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("RequestList.jsp");
        dispatcher.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
