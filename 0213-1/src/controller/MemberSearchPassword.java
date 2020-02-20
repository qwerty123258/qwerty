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


@WebServlet("/SearchPassword")
public class MemberSearchPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public MemberSearchPassword() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id=request.getParameter("id");
		String email=request.getParameter("email");
		MemberSearchService service = new MemberSearchService();
		MemberDTO member = new MemberDTO();
		boolean result=service.memberSearchPassword(id,email,member);
		if(result) {
			String sender="qwerty123258@naver.com";
			String title="비밀번호 찾기 결과";
			String content="<a href='http://localhost:8090/0213-1/MemberBoardMain.jsp'>로그인 하러가기</a><br><br>"
					+ "찾으신 비밀번호는"+ member.getPassword()+"입니다.";
			request.setAttribute("sender", sender);
			request.setAttribute("receiver", email);
			request.setAttribute("title", title);
			request.setAttribute("content",content);
			RequestDispatcher dispatcher = request.getRequestDispatcher("PasswordSend.jsp");
			dispatcher.forward(request, response);
		}
		else {
			response.sendRedirect("Member/SearchFail.jsp");
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}