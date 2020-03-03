package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import dto.BoardDTO;
import service.BoardService;

@WebServlet("/ViewTopList")
public class ViewTopList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ViewTopList() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		List<BoardDTO> viewList=new ArrayList<BoardDTO>();
		BoardService board =new BoardService();
		viewList=board.viewTopList();
		JSONObject jso=new JSONObject();
		jso.put("view", viewList);
		response.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
		PrintWriter out=response.getWriter();
		out.print(jso); 
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
