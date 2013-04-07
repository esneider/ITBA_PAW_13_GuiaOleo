package ar.edu.itba.it.paw.controller;

import java.io.IOException;
//import java.util.Calendar;
//import java.util.GregorianCalendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ar.edu.itba.it.paw.model.User;


@SuppressWarnings("serial")
public abstract class BaseServlet extends HttpServlet {

	//UserDao userDao;

	public BaseServlet() {
		super();
	}

	protected void render(HttpServletRequest req, HttpServletResponse resp, String file, String title)
			throws ServletException, IOException {

		req.setAttribute("documentTitle", title);
		req.setAttribute("documentBodyFile", file);
		req.setAttribute("basePath", req.getContextPath());

		req.getRequestDispatcher("layout.jsp").forward(req, resp);
	}

	protected void setLoggedInUser(HttpServletRequest req, User user) {
		req.getSession().setAttribute("userId", user.getId());
	}

	protected boolean isLoggedIn(HttpServletRequest req) {
		return req.getSession().getAttribute("userId") != null;
	}

	protected User getLoggedInUser(HttpServletRequest req) {
		return (User) req.getAttribute("user");
	}

	protected void logout(HttpServletRequest req, HttpServletResponse resp) {
		req.getSession().invalidate();
	}

	protected boolean checkParameter(HttpServletRequest req, String param, int min, int max) {

		return checkParameter(req, param, min, max, false);
	}

	protected boolean checkParameter(HttpServletRequest req, String param, int min, int max, boolean optional) {

		String value = req.getParameter(param);

		if ((value == null || value.length() == 0) && !optional) {
			req.setAttribute(param + "Empty", true);
			return false;
		}

		if (value.length() < min || value.length() > max) {
			req.setAttribute(param + "BadLength", true);
			return false;
		}

		return true;
	}

	protected boolean checkIntegerParameter(HttpServletRequest req, String param) {

		String value = req.getParameter(param);

		if (value == null || value.length() == 0) {
			req.setAttribute(param + "Empty", true);
			return false;
		}

		try {
			Integer.parseInt(value);
		} catch (Exception e) {
			req.setAttribute(param + "InvalidFormat", true);
			return false;
		}

		return true;
	}

	protected boolean checkIntegerParameter(HttpServletRequest req, String param, int min, int max) {


		if (!checkIntegerParameter(req, param)) {
			return false;
		}

		Integer num = Integer.parseInt(req.getParameter(param));

		if (num < min || num > max) {
			req.setAttribute(param + "OutOfRange", true);
			return false;
		}

		return true;
	}

	protected boolean checkDoubleParameter(HttpServletRequest req, String param, int min, double max) {

		String value = req.getParameter(param);
		Double num;

		if (value == null || value.length() == 0) {
			req.setAttribute(param + "Empty", true);
			return false;
		}

		try {
			num = Double.parseDouble(value);
		} catch (Exception e) {
			req.setAttribute(param + "InvalidFormat", true);
			return false;
		}

		if (num <= min || num >= max) {
			req.setAttribute(param + "OutOfRange", true);
			return false;
		}

		return true;
	}

//	protected boolean checkEmail(HttpServletRequest req, String param, int min,	int max) {
//
//		if (!checkParameter(req, param, min, max)) {
//			return false;
//		}
//
//		if (!ValidationUtils.isEmail(req.getParameter(param))) {
//			req.setAttribute(param + "InvalidFormat", true);
//			return false;
//		}
//
//		return true;
//	}

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
//	protected boolean checkParamsEqual(HttpServletRequest req, String param1, String param2) {
//
//		String value1 = req.getParameter(param1);
//		String value2 = req.getParameter(param2);
//
//		if (value1 == null || value2 == null) {
//			return false;
//		}
//		
//		if (!value1.equals(value2)) {
//			req.setAttribute(param2 + "DoesntMatch", true);
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