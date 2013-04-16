package ar.edu.itba.it.paw.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
//import java.util.Calendar;
//import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.manager.UserManager;
import ar.edu.itba.it.paw.model.User;
import ar.edu.itba.it.paw.utils.Validation;


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

	protected boolean checkParameter(HttpServletRequest req, String param, int min, int max) {

		return checkParameter(req, param, min, max, false);
	}

	protected boolean checkParameter(HttpServletRequest req, String param, int min, int max, boolean optional) {

		String value = req.getParameter(param);

		if ((value == null || value.length() == 0) && !optional) {
			req.setAttribute(param + "Empty", true);
			req.setAttribute(param + "Error", true);
			return false;
		}

		if (value.length() < min || value.length() > max) {
			req.setAttribute(param + "BadLength", true);
			req.setAttribute(param + "Error", true);
			return false;
		}

		return true;
	}

	protected boolean checkIntegerParameter(HttpServletRequest req, String param) {

		String value = req.getParameter(param);

		if (value == null || value.length() == 0) {
			req.setAttribute(param + "Empty", true);
			req.setAttribute(param + "Error", true);
			return false;
		}

		try {
			Integer.parseInt(value);
		} catch (Exception e) {
			req.setAttribute(param + "InvalidFormat", true);
			req.setAttribute(param + "Error", true);
			return false;
		}

		return true;
	}

	protected boolean checkIntegerParameter(HttpServletRequest req, String param, int min, int max) {


		if (!checkIntegerParameter(req, param)) {
			req.setAttribute(param + "Error", true);
			return false;
		}

		Integer num = Integer.parseInt(req.getParameter(param));

		if (num < min || num > max) {
			req.setAttribute(param + "OutOfRange", true);
			req.setAttribute(param + "Error", true);
			return false;
		}

		return true;
	}

	protected boolean checkDoubleParameter(HttpServletRequest req, String param, int min, double max) {

		String value = req.getParameter(param);
		Double num;

		if (value == null || value.length() == 0) {
			req.setAttribute(param + "Empty", true);
			req.setAttribute(param + "Error", true);
			return false;
		}

		try {
			num = Double.parseDouble(value);
		} catch (Exception e) {
			req.setAttribute(param + "InvalidFormat", true);
			req.setAttribute(param + "Error", true);
			return false;
		}

		if (num <= min || num >= max) {
			req.setAttribute(param + "OutOfRange", true);
			req.setAttribute(param + "Error", true);
			return false;
		}

		return true;
	}

	protected boolean checkEmail(HttpServletRequest req, String param, int min,	int max) {

		if (!checkParameter(req, param, min, max)) {
			req.setAttribute(param + "Error", true);
			return false;
		}

		if (!Validation.isEmail(req.getParameter(param))) {
			req.setAttribute(param + "InvalidFormat", true);
			req.setAttribute(param + "Error", true);
			return false;
		}

		return true;
	}

	protected boolean checkParamsEqual(HttpServletRequest req, String param1, String param2) {

		String value1 = req.getParameter(param1);
		String value2 = req.getParameter(param2);

		if (value1 == null || value2 == null) {
			return false;
		}
		
		if (!value1.equals(value2)) {
			req.setAttribute(param2 + "DoesntMatch", true);
			req.setAttribute(param2 + "Error", true);
			return false;
		}

		return true;
	}

//	protected boolean checkUsername(HttpServletRequest req, String param, int min, int max) {
//
//		if (!checkParameter(req, param, min, max)) {
//			return false;
//		}
//
//		String value = req.getParameter(param);
//		
//		if (!ValidationUtils.isIdentifier(value)) {
//			req.setAttribute(param + "InvalidFormat", true);
//			return false;
//		}
//
//		if (userDao == null) {
//			userDao = ApplicationContainer.get(UserDao.class);
//		}
//
//		if (userDao.exists(value)) {
//			req.setAttribute(param + "NotAvailable", true);
//			return false;
//		}
//
//		return true;
//	}
//
//	protected boolean checkDate(HttpServletRequest req, String param1, String param2, String param3) {
//
//		if (!checkIntegerParameter(req, param1) || !checkIntegerParameter(req, param2) ||
//				!checkIntegerParameter(req, param3)) {
//			return false;
//		}
//
//		Integer value1 = Integer.parseInt(req.getParameter(param1));
//		Integer value2 = Integer.parseInt(req.getParameter(param2));
//		Integer value3 = Integer.parseInt(req.getParameter(param3));
//
//		Calendar date = new GregorianCalendar(value3, value1, value2);
//
//		date.setLenient(false);
//
//		try {
//			date.get(0);
//		} catch (Exception e) {
//			req.setAttribute("dateInvalidFormat", true);
//			return false;
//		}
//
//		return true;
//	}
//
//	protected boolean checkHasParameter(HttpServletRequest req, String param) {
//
//		String value = req.getParameter(param);
//
//		if (value == null || value.length() == 0) {
//			req.setAttribute(param + "Empty", true);
//			return false;
//		}
//
//		return true;
//	}

}