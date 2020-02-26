package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.RequestDTO;
import service.RequestService;

@WebServlet("/RequestDetail")
public class RequestDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public RequestDetail() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String rno=request.getParameter("rno");
		RequestService service= new RequestService();
		RequestDTO req=new RequestDTO();
		service.requestDetail(rno,req);
		request.setAttribute("rno", req.getRno());
		request.setAttribute("rtitle", req.getRtitle());
		request.setAttribute("id", req.getId());
		request.setAttribute("writedate", req.getWritedate());
		request.setAttribute("rcontent", req.getRcontent());
        RequestDispatcher dispatcher = request.getRequestDispatcher("RequestDetail.jsp");
        dispatcher.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
