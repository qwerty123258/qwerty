package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.MemberDTO;
import service.MemberSearchService;


@WebServlet("/Search")
public class MemberSearch extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberSearch() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		MemberSearchService service = new MemberSearchService();
		MemberDTO member = new MemberDTO();
		boolean result=service.memberSearch(name,email,member);
		if(result) {
			request.setAttribute("id", member.getId());
			RequestDispatcher dispatcher = request.getRequestDispatcher("IDOutput.jsp");
			dispatcher.forward(request, response);
		}
		else {
			response.sendRedirect("SearchFail.jsp");
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}