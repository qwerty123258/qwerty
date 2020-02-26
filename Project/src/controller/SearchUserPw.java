package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.UsersDTO;
import service.UserService;

@WebServlet("/SearchUserPw")
public class SearchUserPw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SearchUserPw() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id=request.getParameter("id");
		String email=request.getParameter("email");
		UserService service= new UserService();
		UsersDTO user = new UsersDTO();
		boolean result=service.searchUserPw(id,email,user);
		if(result) {
			String host="http://localhost:8090/Project/";
			String subject = "파일천국 비밀번호 찾기 결과 발송.";
			String content = "찾으신 비밀번호는<br><br><br>"
					+ user.getPw() + "입니다."
					+ "<a href='" + host + "Main.jsp'>로그인하러 가기</a>";
			RequestDispatcher dispatcher = request.getRequestDispatcher("MailSend?id="+id+"&receiver="+email+"&sender=qwerty123258@naver.com&subject="+subject+"&content="+content);
			dispatcher.forward(request, response);
		}
		else {
			response.getWriter().write("searchFail");
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
