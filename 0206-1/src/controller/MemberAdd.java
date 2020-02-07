package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		System.out.println("do Process 메소드");
		String id,password,name,birth,gender,emailID,emailDomain,email;
		id=request.getParameter("id");
		password=request.getParameter("password");
		name=request.getParameter("name");
		birth=request.getParameter("birth");
		gender=request.getParameter("gender");
		emailID=request.getParameter("emailID");
		emailDomain=request.getParameter("emailDomain");
		email=emailID+"@"+emailDomain;
		MemberDTO member = new MemberDTO();
		member.setId(id);
		member.setPassword(password);
		member.setName(name);
		member.setBirth(birth);
		member.setGender(gender);
		member.setEmail(email);	
		MemberService service = new MemberService();
		MemberCheckOverlapService checkservice = new MemberCheckOverlapService();
		boolean result=checkservice.checkOverlap(id);
		if(result) {
			response.sendRedirect("Overlap.jsp");
		}
		else {
			boolean inputResult;
			inputResult=service.memberDB(member);
			if(inputResult) {
				response.sendRedirect("InsertSuccess.jsp");
			}
			else {
				response.sendRedirect("InsertFail.jsp");
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
