package ar.edu.itba.it.paw.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.manager.AvatarManager;

public class ImageServlet extends BaseServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String imageId = req.getParameter("imageId");
		byte[] imageData = AvatarManager.getInstance().getAvatarById(Integer.valueOf(imageId)).getImage();
		resp.setContentType("image/jpeg");
		resp.getOutputStream().write(imageData);
		resp.getOutputStream().flush();
		resp.getOutputStream().close();
	}
	
}
