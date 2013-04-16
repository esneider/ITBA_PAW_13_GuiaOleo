package ar.edu.itba.it.paw.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.manager.UserManager;
import ar.edu.itba.it.paw.model.User;


@SuppressWarnings("serial")
public abstract class BaseServlet extends HttpServlet {

	public BaseServlet() {
		super();
	}

	protected void render(HttpServletRequest req, HttpServletResponse resp, String file, String title)
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

}