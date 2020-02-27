package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.BoardService;
import service.DownloadService;
import service.UserService;

@WebServlet("/DownloadFile")
public class DownloadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DownloadFile() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String bfile=request.getParameter("bfile");
		int price= Integer.parseInt(request.getParameter("price"));
		String bno=request.getParameter("bno");
		HttpSession session =request.getSession();
		String id=(String) session.getAttribute("id");
		String bfno=request.getParameter("bfno");
		UserService userservice = new UserService();
		BoardService boardservice = new BoardService();
		int point=userservice.checkPonit(id);
		String uploder=boardservice.checkUploder(bno);
			DownloadService down = new DownloadService();
			boolean result=down.checkHistory(id,bfno);
			if(uploder.equals(id)) {
		        RequestDispatcher dispatcher = request.getRequestDispatcher("Download?bfile="+bfile);
		        dispatcher.forward(request, response);
			}
			else {
				if(result) {
			        RequestDispatcher dispatcher = request.getRequestDispatcher("Download?bfile="+bfile);
			        dispatcher.forward(request, response);
				}
				else {
					if(point>=price) {
						userservice.payPoint(id,price);
						userservice.receivePoint(uploder,price);
						down.addHistory(id,bfno,bfile,bno);
				        RequestDispatcher dispatcher = request.getRequestDispatcher("Download?bfile="+bfile);
				        dispatcher.forward(request, response);
					}
					else {
						request.setAttribute("bno", bno);
				        RequestDispatcher dispatcher = request.getRequestDispatcher("DownFail.jsp");
				        dispatcher.forward(request, response);
					}
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
