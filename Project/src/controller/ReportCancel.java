package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.ReportService;


@WebServlet("/ReportCancel")
public class ReportCancel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReportCancel() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String bno=request.getParameter("bno");
		HttpSession session =request.getSession();
		String id=(String) session.getAttribute("id");
		ReportService report= new ReportService();
		boolean result=report.reportCancel(bno,id);
		if(result) {
			response.getWriter().write("reportCancelSuccess");
		}
		else {
			response.getWriter().write("Fail");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
