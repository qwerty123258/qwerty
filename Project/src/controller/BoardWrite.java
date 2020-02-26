package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import service.BoardService;
import service.FileService;

@WebServlet("/BoardWrite")
public class BoardWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardWrite() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	    String savePath = getServletContext().getRealPath("fileUpload");
	    int size=10*1024*1024;
		MultipartRequest multi = new MultipartRequest(
				request,savePath,size,"UTF-8",new DefaultFileRenamePolicy()
				);
		HttpSession session =request.getSession();
		String id=(String) session.getAttribute("id");
		String title=multi.getParameter("title");
		String category=multi.getParameter("category");
		String content=multi.getParameter("content");
		String bimgfile=multi.getFilesystemName("bimgfile");
		String price[]=multi.getParameterValues("price");
		BoardService boardservice = new BoardService();
		boardservice.writeBoard(title,category,content,bimgfile,id);
		FileService fileservice= new FileService();
		for(int i=0; i<price.length; i++) {
			String file=multi.getFilesystemName("bfile"+"["+i+"]");
			String fileoriname=multi.getOriginalFileName("bfile"+"["+i+"]");
			fileservice.addfile(file,fileoriname,price[i]);
		}
		response.sendRedirect("Main.jsp");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
