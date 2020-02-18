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
import javax.servlet.http.HttpSession;

import dto.MemberDTO;
import service.MemberArticlecountService;
import service.MemberBlackListCheck;
import service.MemberDetailPopUpService;
import service.MemberLoginService;

@WebServlet("/DetailPopUp")
public class MemberDetailPopUp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberDetailPopUp() {
        super();
    }
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id=request.getParameter("id");
		MemberDetailPopUpService service= new MemberDetailPopUpService();
		MemberArticlecountService countservice = new MemberArticlecountService();
		int count=countservice.ArticleCount(id);
		int commentcount=countservice.MyCommentCount(id);
		List<MemberDTO> memberList = new ArrayList<MemberDTO>();
		memberList=service.DetailPopUp(id);
		request.setAttribute("member", memberList);
		request.setAttribute("count", count);
		request.setAttribute("commentcount", commentcount);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Member/MemberDetailPopUp.jsp");
		dispatcher.forward(request, response);

	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
