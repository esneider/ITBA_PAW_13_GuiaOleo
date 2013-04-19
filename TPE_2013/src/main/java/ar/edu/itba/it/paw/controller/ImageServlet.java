package ar.edu.itba.it.paw.controller;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.model.Picture;
import ar.edu.itba.it.paw.service.PictureService;

@SuppressWarnings("serial")
public class ImageServlet extends BaseServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String imageId = req.getParameter("imageId");
		Picture pic = PictureService.getInstance().getPictureById(Integer.valueOf(imageId));
		resp.setContentType("image/jpeg");

		OutputStream os = resp.getOutputStream();  
		
		os.write(pic.getBytes());
		os.flush();
 
		os.close();
	}
}
