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
import service.MemberSelectService;

@WebServlet("/MemberSelect")
public class MemberSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberSelect() {
        super();
    }
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		MemberSelectService service = new MemberSelectService();
		List<MemberDTO> memberList = new ArrayList<MemberDTO>();
		memberList=service.selectMember();
		request.setAttribute("member", memberList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Member/SelectMember.jsp");
		dispatcher.forward(request, response);

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
