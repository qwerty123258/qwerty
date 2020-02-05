package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/inputTest") //주소값이 호출되면 이 클래스가 실행됨.
public class TestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public TestController() {
        super();
    }

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("do Process 메소드");
		String value1,value2;
		value1=request.getParameter("val1");
		value2=request.getParameter("val2");
		int value3;
		value3=Integer.parseInt(request.getParameter("num1"));
		System.out.println("1번째 입력값 "+value1);
		System.out.println("2번째 입력값 "+value2);
		System.out.println("숫자 입력값 "+value3);
		
		response.sendRedirect("RedirectPage.jsp"); 
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("do get 메소드");
		doProcess(request,response);
		// http://localhost:8090/0203-1/inputTest?val1=sdffsdf&val2=sdfsdf
		// get 방식의 데이터 전송,입력한 값들이 주소창에 그대로 노출됨
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("do post 메소드");
		doProcess(request,response);
		// http://localhost:8090/0203-1/inputTest
		// post 방식의 데이터 전송
		
	}

}
