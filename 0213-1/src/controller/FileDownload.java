package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
 
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/FileDownload")
public class FileDownload extends HttpServlet  {
	private static final long serialVersionUID = 1L;
	
	public FileDownload() {
		super();
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // reads input file from an absolute path
	String filename=request.getParameter("bfile");	
    String relativePath = getServletContext().getRealPath("fileUpload/"+filename);
    File downloadFile = new File(relativePath);
    FileInputStream inStream = new FileInputStream(downloadFile); 
    // obtains ServletContext
    ServletContext context = getServletContext();
     
    // gets MIME type of the file
    String mimeType = context.getMimeType(relativePath);
    if (mimeType == null) {        
        // set to binary type if MIME mapping not found
        mimeType = "application/octet-stream";
    }
     
    // modifies response
    response.setContentType(mimeType);
    response.setContentLength((int) downloadFile.length());
     
    // forces download
    String headerKey = "Content-Disposition";
    String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
    response.setHeader(headerKey, headerValue);
     
    // obtains response's output stream
    OutputStream outStream = response.getOutputStream();
     
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
