package ar.edu.itba.it.paw.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.struts.upload.MultipartRequestWrapper;

import ar.edu.itba.it.paw.manager.FoodTypeManager;
import ar.edu.itba.it.paw.manager.PictureManager;
import ar.edu.itba.it.paw.manager.UserManager;
import ar.edu.itba.it.paw.model.FoodType;
import ar.edu.itba.it.paw.model.Picture;
import ar.edu.itba.it.paw.model.User;


@SuppressWarnings("serial")
public abstract class BaseServlet extends HttpServlet {

	public BaseServlet() {
		super();
	}

	protected void render(HttpServletRequest req, HttpServletResponse resp, String file, String title, boolean sidebar)
			throws ServletException, IOException {

		req.setAttribute("documentTitle", title);
		req.setAttribute("documentBodyFile", "/WEB-INF/jsp/" + file);
		req.setAttribute("basePath", req.getContextPath());

		if (isLoggedIn(req)) {

			req.setAttribute("user", getLoggedInUser(req));
			req.setAttribute("loginAction",    "successful".equals(req.getParameter("loginAction")));
			req.setAttribute("registerAction", "successful".equals(req.getParameter("registerAction")));
			req.setAttribute("modifyAction",   "successful".equals(req.getParameter("modifyAction")));
		}

		if (sidebar) {

			List<FoodType> all = FoodTypeManager.getInstance().getAll();
			int total = 0;

			for (FoodType foodType : all) {
				total += foodType.getAmmount();
			}

			Collections.sort(all);

			req.setAttribute("sidebar", true);
			req.setAttribute("foodTypesList", all);
			req.setAttribute("numberOfRestaurants", total);

			if ("foodtypes".equals(req.getParameter("query"))) {

				req.setAttribute("tab_active", req.getParameter("id"));

			} else if ("all".equals(req.getParameter("query"))) {

				req.setAttribute("tab_all", "active");
			}
		}

		req.getRequestDispatcher("/WEB-INF/jsp/layout.jsp").forward(req, resp);
	}

	protected void setLoggedInUser(HttpServletRequest req, User user) {

		req.getSession(true).setAttribute("userId", user.getId());
	}

	protected boolean isLoggedIn(HttpServletRequest req) {

		return req.getSession(true).getAttribute("userId") != null;
	}

	protected User getLoggedInUser(HttpServletRequest req) {

		return UserManager.getInstance().getSingleUser((Integer)req.getSession(true).getAttribute("userId"));
	}

	protected void logout(HttpServletRequest req) {

		req.getSession(true).invalidate();
	}

	protected String getDestination(HttpServletRequest req) throws UnsupportedEncodingException {

		String destination = req.getParameter("from");

		if (destination == null || destination.isEmpty()) {

			destination = req.getContextPath();

		} else if (!req.getMethod().toUpperCase().equals("GET")) {

			destination = URLDecoder.decode(destination, "UTF-8");
		}

		req.setAttribute("from", URLEncoder.encode(destination, "UTF-8"));

		System.out.println("Get destination: " + destination);

		return destination;
	}

	protected String setDestination(HttpServletRequest req) throws UnsupportedEncodingException {

		String from = req.getRequestURI();

		if (req.getQueryString() != null) {
			from += "?" + req.getQueryString();
		}

		System.out.println("Set destination: " + URLEncoder.encode(from, "UTF-8"));

		return "from=" + URLEncoder.encode(from, "UTF-8");
	}

	
	protected class MultipartPictures {
		List<Picture> pictures = new LinkedList<Picture>();
		HttpServletRequest req;
	};

	protected MultipartPictures multipartPictures(HttpServletRequest req) {

		MultipartPictures multipart = new MultipartPictures();

		MultipartRequestWrapper mpReq = new MultipartRequestWrapper(req);

		try {

			ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
			FileItemIterator iter = upload.getItemIterator(req);

			while (iter.hasNext()) {
				
			    FileItemStream item = iter.next();
			    String name = item.getFieldName();
			    InputStream stream = item.openStream();

			    if (item.isFormField()) {
			    	mpReq.setParameter(name, Streams.asString(stream));
			    } else {
			    	mpReq.setParameter(name, "true");
			    	multipart.pictures.add(PictureManager.getInstance().insert(stream, item.getContentType()));
			    }
			}

		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();			
		}

        multipart.req = mpReq;
 
        return multipart;
	}
}