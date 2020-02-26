package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dto.BoardDTO;
import service.WriteService;

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
		MultipartRequest multiRequest = new MultipartRequest(
				request,savePath,size,"UTF-8",new DefaultFileRenamePolicy()
				);
		String writer=multiRequest.getParameter("writer");
		String bpassword=multiRequest.getParameter("bpassword");
		String title=multiRequest.getParameter("title");
		String bcontent=multiRequest.getParameter("bcontent");
		String bimgfile=multiRequest.getFilesystemName("bimgfile");
        String fileName = "";
        File file = null;
        Enumeration efiles = multiRequest.getFileNames();
            while(efiles.hasMoreElements()){
                String name = (String)efiles.nextElement(); 
                file = multiRequest.getFile(name);
                if(file==null) {
                	
                }
                else {
                    String str = file.getName();
                    fileName += "&fileName"+str;
                    System.out.println(fileName);
                } 
        } 
		BoardDTO board=new BoardDTO();
		board.setWriter(writer);
		board.setBpassword(bpassword);
		board.setTitle(title);
		board.setBcontent(bcontent);
		board.setBimgfile(bimgfile);
		board.setBfile(fileName);
		WriteService service=new WriteService();
		boolean result=service.writeBoard(board);
		if(result) {
			response.sendRedirect("MemberBoardMain.jsp");
		}
		else {
			response.sendRedirect("BoardWrite.jsp");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
