package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import service.BoardUpdateService;

@WebServlet("/BoardUpdate")
public class BoardUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardUpdate() {
        super();
    }
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.setCharacterEncoding("UTF-8");
		String savePath="C:/Users/5/git/qwerty/0210-1/WebContent/fileUpload";
		int size=10*1024*1024;
		MultipartRequest multiRequest = new MultipartRequest(
				request,savePath,size,"UTF-8",new DefaultFileRenamePolicy()
				);
    	String bnum=multiRequest.getParameter("bnum");
    	String title=multiRequest.getParameter("title");
    	String bcontent=multiRequest.getParameter("bcontent");
    	String page=multiRequest.getParameter("page");
    	String bimgfile=multiRequest.getFilesystemName("bimgfile");
    	String bfile=multiRequest.getFilesystemName("bfile");
    	BoardUpdateService service = new BoardUpdateService();
    	boolean result=service.updateBoard(bnum,title,bcontent,bimgfile,bfile);
    	if(result) {
    		request.setAttribute("bnum", bnum);
    		request.setAttribute("page", page);
            RequestDispatcher dispatcher = request.getRequestDispatcher("BoardModifySuccess.jsp");
            dispatcher.forward(request, response);
    	}
    	else {
    		request.setAttribute("bnum", bnum);
    		request.setAttribute("page", page);
            RequestDispatcher dispatcher = request.getRequestDispatcher("BoardModifyFail.jsp");
            dispatcher.forward(request, response);
    	}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
