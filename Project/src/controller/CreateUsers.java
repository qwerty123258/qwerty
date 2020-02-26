package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.UserService;

@WebServlet("/CreateUsers")
public class CreateUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CreateUsers() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String id=request.getParameter("id");
		String pw=request.getParameter("pw");
		String name=request.getParameter("name");
		String personno=request.getParameter("personno");
		String personno2=request.getParameter("personno2");
		String personno3=personno+"-"+personno2;
		String email=request.getParameter("email");
		String domain=request.getParameter("domain");
		String email2=email+"@"+domain;
		UserService userservice= new UserService();
		boolean checkresult=userservice.checkPersonno(personno3);
		if(checkresult) {
			 response.getWriter().write("personnoOverlap");
		}
		else {
			if(id.equals("qwerty123258")) {
				boolean addresult=userservice.adminCreate(id,pw,name,personno3,email2);
				if(addresult) {
					 response.getWriter().write("addSuccess");
				}
				else {
					 response.getWriter().write("addFail");
				}
			}
			else {
				boolean addresult=userservice.create(id,pw,name,personno3,email2);
				if(addresult) {
					String host="http://localhost:8090/Project/";
					String subject = "파일천국 가입 인증용 메일입니다.";
					String content = "가입을 환영합니다.<br>"
							+ "인증링크 발송용 메일 입니다.<br>"
							+ "인증을 하지 않으시면 로그인이 불가능 하오니 참고바랍니다.<br>"
							+ "다음 링크에 접속하여 이메일 확인을 진행하세요.<br><br>" +
					"<a href='" + host + "Certify?id=" + id + "'>이메일 인증하기</a>";
					RequestDispatcher dispatcher = request.getRequestDispatcher("MailSend?id="+id+"&receiver="+email2+"&sender=qwerty123258@naver.com&subject="+subject+"&content="+content);
					dispatcher.forward(request, response);
				}
				else {
					 response.getWriter().write("addFail");
				}
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
