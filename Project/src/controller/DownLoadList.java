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
import javax.servlet.http.HttpSession;

import dto.DownloadDTO;
import service.DownloadService;

@WebServlet("/DownLoadList")
public class DownLoadList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DownLoadList() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session =request.getSession();
		String id=(String) session.getAttribute("id");
		DownloadService down = new DownloadService();
		List<DownloadDTO>  downList = new ArrayList<DownloadDTO>();
		downList=down.downloadList(id);
		request.setAttribute("down", downList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("DownLoadList.jsp");
        dispatcher.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
