package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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
    private static final int LIMIT_SIZE_BYTES = 10 * 1024 * 1024;

       
    public BoardWrite() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	    String savePath = getServletContext().getRealPath("fileUpload");
//	    File attachesDir = new File(savePath);
//        DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
//        fileItemFactory.setRepository(savePath);
//        fileItemFactory.setSizeThreshold(LIMIT_SIZE_BYTES);
//        ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
//        try {
//            List<FileItem> items = fileUpload.parseRequest(request);
//            for (FileItem item : items) {
//                if (item.isFormField()) {
//                    System.out.printf("파라미터 명 : %s, 파라미터 값 :  %s \n", item.getFieldName(), item.getString("UTF-8"));
//                } else {
//                    System.out.printf("파라미터 명 : %s, 파일 명 : %s,  파일 크기 : %s bytes \n", item.getFieldName(),
//                            item.getName(), item.getSize());
//                    if (item.getSize() > 0) {
//                        String separator = File.separator;
//                        int index =  item.getName().lastIndexOf(separator);
//                        String fileName = item.getName().substring(index  + 1);
//                        File uploadFile = new File(savePath +  separator + fileName);
//                        item.write(uploadFile);
//                    }
//                }
//            }
//        }
//        catch(Exception e) {
//        	
//        }
		MultipartRequest multiRequest = new MultipartRequest(
				request,savePath,LIMIT_SIZE_BYTES,"UTF-8",new DefaultFileRenamePolicy()
				);
		String writer=multiRequest.getParameter("writer");
		String bpassword=multiRequest.getParameter("bpassword");
		String title=multiRequest.getParameter("title");
		String bcontent=multiRequest.getParameter("bcontent");
		String bimgfile=multiRequest.getFilesystemName("bimgfile");
		String bfile=multiRequest.getFilesystemName("bfile");
		BoardDTO board=new BoardDTO();
		board.setWriter(writer);
		board.setBpassword(bpassword);
		board.setTitle(title);
		board.setBcontent(bcontent);
		board.setBimgfile(bimgfile);
		board.setBfile(bfile);
		WriteService service=new WriteService();
		boolean result=service.writeBoard(board);
		if(result) {
			response.sendRedirect("MemberBoardMain.jsp");
		}
		else {
			response.sendRedirect("Member/BoardWrite.jsp");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
