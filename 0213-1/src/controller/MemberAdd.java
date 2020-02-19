package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dto.MemberDTO;
import service.MemberCheckOverlapService;
import service.MemberService;

@WebServlet("/MemberAdd")
public class MemberAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public MemberAdd() {
        super();
    }
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
	    String savePath = getServletContext().getRealPath("fileUpload");
		int size=10*1024*1024;
		MultipartRequest multiRequest = new MultipartRequest(
				request,savePath,size,"UTF-8",new DefaultFileRenamePolicy()
				);
		String id,password,name,birth,gender,emailID,emailDomain,email,address,phone,mempicture;
		id=multiRequest.getParameter("id");
		password=multiRequest.getParameter("password");
		name=multiRequest.getParameter("name");
		birth=multiRequest.getParameter("birth");
		gender=multiRequest.getParameter("gender");
		emailID=multiRequest.getParameter("emailID");
		emailDomain=multiRequest.getParameter("emailDomain");
		email=emailID+"@"+emailDomain;
		address=multiRequest.getParameter("address");
		phone=multiRequest.getParameter("phone");
		mempicture=multiRequest.getFilesystemName("mempicture");
		MemberDTO member = new MemberDTO();
		member.setId(id);
		member.setPassword(password);
		member.setName(name);
		member.setBirth(birth);
		member.setGender(gender);
		member.setEmail(email);	
		member.setAddress(address);
		member.setPhone(phone);
		member.setMempicture(mempicture);
		MemberService service = new MemberService();
		MemberCheckOverlapService checkservice = new MemberCheckOverlapService();
		boolean emailresult=checkservice.checkOverlapEmail(email);
			if(emailresult) {
				response.sendRedirect("Member/OverlapEmail.jsp");
			}
			else {
				boolean inputResult;
				inputResult=service.memberDB(member);
				if(inputResult) {
					response.sendRedirect("Member/InsertSuccess.jsp");
				}
				else {
					response.sendRedirect("Member/InsertFail.jsp");
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
