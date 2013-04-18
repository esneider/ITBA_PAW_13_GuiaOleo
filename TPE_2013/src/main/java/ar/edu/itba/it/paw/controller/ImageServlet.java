package ar.edu.itba.it.paw.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.manager.PictureManager;

@SuppressWarnings("serial")
public class ImageServlet extends BaseServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String imageId = req.getParameter("imageId");
		InputStream is = PictureManager.getInstance().getPictureById(Integer.valueOf(imageId)).getInputStream();
		resp.setContentType("image/jpeg");

		OutputStream os = resp.getOutputStream();  
		/*byte[] buffer = new byte[4096];  
		int bytesRead;  
		while ((bytesRead = is.read(buffer)) != -1) { 
		  os.write(buffer, 0, bytesRead);  
		}*/
		int a;
		while((a = is.read()) > -1) {
			os.write(a);
		}
		os.flush();

		is.close();  
		os.close();
	}
}
