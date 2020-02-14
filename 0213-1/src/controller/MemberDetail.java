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

import dto.MemberDTO;
import service.MemberDetailService;

@WebServlet("/Detail")
public class MemberDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberDetail() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id=request.getParameter("id");
		List<MemberDTO> memberList = new ArrayList<MemberDTO>();
		MemberDetailService service=new MemberDetailService();
		memberList=service.detailMember(id);
		request.setAttribute("member", memberList);
		memberList.toString();
		RequestDispatcher dispatcher = request.getRequestDispatcher("Member/DetailMember.jsp");
		dispatcher.forward(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
