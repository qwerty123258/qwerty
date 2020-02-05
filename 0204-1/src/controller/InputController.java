package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.InputService;

@WebServlet("/inputServer")
public class InputController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public InputController() {
        super();
    }
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("do Process 메소드");
		String data1,data2;
		data1=request.getParameter("data1");
		data2=request.getParameter("data2");
		System.out.println("data1 : "+data1);
		System.out.println("data2 : "+data2);
//		DB연동
//		1.ojdbc6.jar>>톰캣(현재 사용하고 있는것이 아피치 톰캣이므로) 설치폴더 lib
//		2.DB 계정 생성
//		3.META-INF 폴더에 context.xml 파일 생성후 DB 계정 관련 내용 작성
//		4.db 패지키 생성 후 JdbcUtil 클래스 생성
//		5.service 패키지,dao 패키지 생성후 각각 InputService 클래스,DAO 클래스 생성
//		-Service 역할 : controller 에서 넘겨받는 내용을 처리하고 db 연동을 위한 DAO 클래스 호출
//		-DAO 역할 : DB와 CRUD 작업 수행
		InputService service = new InputService();
		boolean inputResult;
		inputResult=service.inputDB(data1,data2);
		if(inputResult) {
			response.sendRedirect("InsertSuccess.jsp");
		}
		else {
			response.sendRedirect("InsertFail.jsp");
		}
		
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("do Get 메소드");
		doProcess(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("do Post 메소드");
		doProcess(request,response);
	}
}
