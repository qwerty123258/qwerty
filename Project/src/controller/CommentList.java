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
import dto.CommentDTO;
import page.Paging;
import service.CommentService;

@WebServlet("/CommentList")
public class CommentList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CommentList() {
        super();
    }
    
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String bno=request.getParameter("bno");
		CommentService service = new CommentService();
		Paging paging = new Paging();
		int count=service.countComment(bno);
    	int page = 1;
        if(request.getParameter("page")!=null){
            page = Integer.parseInt(request.getParameter("page"));
        }
        paging.setPage(page);
        paging.setTotalCount(count);
		List<CommentDTO> commentList = new ArrayList<CommentDTO>();
		commentList=service.commentList(bno,paging);
		JSONObject jso=new JSONObject();
		List<Paging> pageList = new ArrayList<Paging>();
		pageList.add(paging);
		jso.put("comment", commentList);
		jso.put("paging", pageList);
		jso.put("count", count);
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
