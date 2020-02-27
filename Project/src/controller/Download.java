package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Download")
public class Download extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	
	public Download() {
		super();
	}
	public String getBrowser(HttpServletRequest request) {
        String header = request.getHeader("User-Agent");
   if (header.indexOf("MSIE") > -1) {
       return "MSIE";
   } else if (header.indexOf("Trident") > -1) {   // IE11 문자열 깨짐 방지
       return "Trident";
   } else if (header.indexOf("Chrome") > -1) {
       return "Chrome";
   } else if (header.indexOf("Opera") > -1) {
       return "Opera";
   } else if (header.indexOf("Safari") > -1) {
       return "Safari";
   }
   return "Firefox";
  }
	
    private void setDisposition(String filename, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String browser = getBrowser(request); //현재 브라우저 리턴 받기
        String dispositionPrefix = "attachment; filename="; // 값 초기화
        String encodedFilename = null; //인코딩된 파일 이름
        if (browser.equals("MSIE")) {
               encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
        } else if (browser.equals("Trident")) {       // IE11 문자열 깨짐 방지
               encodedFilename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+", "%20");
        } else if (browser.equals("Firefox")) {
               encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
               encodedFilename = URLDecoder.decode(encodedFilename);
        } else if (browser.equals("Opera")) {
               encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1") + "\"";
        } else if (browser.equals("Chrome")) {
               StringBuffer sb = new StringBuffer();
               for (int i = 0; i < filename.length(); i++) {
                      char c = filename.charAt(i);
                      if (c > '~') {
                            sb.append(URLEncoder.encode("" + c, "UTF-8"));
                      } else {
                            sb.append(c);
                      }
               }
               encodedFilename = sb.toString();
        } else if (browser.equals("Safari")){
               encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1")+ "\"";
               encodedFilename = URLDecoder.decode(encodedFilename);
        }
        else {
               encodedFilename = "\"" + new String(filename.getBytes("UTF-8"), "8859_1")+ "\"";

        }
        response.setHeader("Content-Disposition", dispositionPrefix + encodedFilename);
        if ("Opera".equals(browser)){
               response.setContentType("application/octet-stream;charset=UTF-8");
        }
}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8"); //파라미터 이름 인코딩
	String filename=request.getParameter("bfile");  //파라미터 수신
    String relativePath = getServletContext().getRealPath("fileUpload/"+filename); //파일이 있는 경로 설정
    File downloadFile = new File(relativePath); //그 경로 맞는 파일 객체 생성
    FileInputStream inStream = new FileInputStream(downloadFile);  // 객체를 읽어들임
    try {
		setDisposition(filename,request,response);// 파일 다운로드시 한글 처리
	} catch (Exception e) {
		e.printStackTrace();
	}
    OutputStream outStream = response.getOutputStream(); //객체를 쓰기함
     
    byte[] buffer = new byte[4096]; 
    int bytesRead = -1;
     
    while ((bytesRead = inStream.read(buffer)) != -1) {
        outStream.write(buffer, 0, bytesRead);
    }
    inStream.close();
    outStream.close();     
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
}
