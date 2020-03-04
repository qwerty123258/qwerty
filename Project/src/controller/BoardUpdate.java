package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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

@WebServlet("/BoardUpdate")
public class BoardUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public BoardUpdate() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	    String savePath = getServletContext().getRealPath("fileUpload");
	    int size=10*1024*1024;
		MultipartRequest multi = new MultipartRequest(
				request,savePath,size,"UTF-8",new DefaultFileRenamePolicy()
				);
		String title=multi.getParameter("title");
		String category=multi.getParameter("category");
		String content=multi.getParameter("ir1");
		String bimgfile=multi.getFilesystemName("bimgfile");
		String beforeimg=multi.getParameter("beforeimg");
		String bno=multi.getParameter("bno");
		if(bimgfile==null) {
			bimgfile=beforeimg;
		}
		BoardService boardservice = new BoardService();
		boardservice.updateBoard(title,category,content,bimgfile,bno);
		FileService fileservice= new FileService();
		String price[]=multi.getParameterValues("price");
		String bfile[]=multi.getParameterValues("bfile");
		String bfno[]=multi.getParameterValues("bfno");
		String boriginfile[]=multi.getParameterValues("boriginfile");
		for(int i=0; i<price.length; i++) {
			String file=multi.getFilesystemName("bfile"+"["+i+"]");
			String fileoriname=multi.getOriginalFileName("bfile"+"["+i+"]");
			if(file==null) {
				file=bfile[i];
				fileoriname=boriginfile[i];
			}
			fileservice.updateFile(file,fileoriname,price[i],bfno[i]);
		}
        RequestDispatcher dispatcher = request.getRequestDispatcher("BoardDetail?bno="+bno+"&category="+category);
        dispatcher.forward(request, response);
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
