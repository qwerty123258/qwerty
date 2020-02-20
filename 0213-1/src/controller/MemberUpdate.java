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

import service.MemberUpdateService;


@WebServlet("/Update")
public class MemberUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberUpdate() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	    String savePath = getServletContext().getRealPath("fileUpload");
		int size=10*1024*1024;
		MultipartRequest multiRequest = new MultipartRequest(
				request,savePath,size,"UTF-8",new DefaultFileRenamePolicy()
				);
		HttpSession session =request.getSession();
		String id=(String) session.getAttribute("id");
		String name=multiRequest.getParameter("name");
		String email=multiRequest.getParameter("email");
		String address=multiRequest.getParameter("address");
		String phone=multiRequest.getParameter("phone");
		String mempicture=multiRequest.getFilesystemName("mempicture");
		MemberUpdateService service = new MemberUpdateService();
		boolean result=service.updateMember(id,name,email,address,phone,mempicture);
			if(result) {
				session.setAttribute("id", id);
				session.setAttribute("mempicture",mempicture);
				response.sendRedirect("Member/UpdateSuccess.jsp");
				}
			else if(!result) {
				response.sendRedirect("Member/UpdateFail.jsp");
			}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}