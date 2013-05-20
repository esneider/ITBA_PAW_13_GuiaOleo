package ar.edu.itba.it.paw.utils;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

import ar.edu.itba.it.paw.service.interfaces.UserService;

public class ValidationHelpers {

	public static boolean checkHasParameter(HttpServletRequest req, String param) {

		String value = req.getParameter(param);

		if (value == null || value.length() == 0) {
			req.setAttribute(param + "Empty", true);
			req.setAttribute(param + "Error", true);
			return false;
		}

		return true;
	}
	
	public static boolean checkIsNotNull(HttpServletRequest req, Object o, String param) {
		if (o == null) {
			req.setAttribute(param + "Empty", true);
			req.setAttribute(param + "Error", true);
			return false;
		}
		return true;
	}

	public static boolean checkParameter(HttpServletRequest req, String param, int min, int max) {

		return checkParameter(req, param, min, max, false);
	}

	public static boolean checkParameter(HttpServletRequest req, String param, int min, int max, boolean optional) {

		if (!optional && !checkHasParameter(req, param)) {
			return false;			
		}

		String value = req.getParameter(param);

		if (value.length() < min || value.length() > max) {
			req.setAttribute(param + "BadLength", true);
			req.setAttribute(param + "Error", true);
			return false;
		}

		return true;
	}

	public static boolean checkIntegerParameter(HttpServletRequest req, String param) {

		if (!checkHasParameter(req, param)) {
			return false;
		}

		try {
			Integer.parseInt(req.getParameter(param));
		} catch (Exception e) {
			req.setAttribute(param + "InvalidFormat", true);
			req.setAttribute(param + "Error", true);
			return false;
		}

		return true;
	}

	public static boolean checkIntegerParameter(HttpServletRequest req, String param, int min, int max) {


		if (!checkIntegerParameter(req, param)) {
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

	public static boolean checkDoubleParameter(HttpServletRequest req, String param, int min, double max) {

		if (checkHasParameter(req, param)) {
			return false;
		}
		
		Double num;

		try {
			num = Double.parseDouble(req.getParameter(param));
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

	public static boolean checkEmail(HttpServletRequest req, String param, int min,	int max, boolean canRepeat, int id) {

		if (!checkParameter(req, param, min, max)) {
			return false;
		}

		String value = req.getParameter(param);

		if (!Utils.isEmail(value)) {
			req.setAttribute(param + "InvalidFormat", true);
			req.setAttribute(param + "Error", true);
			return false;
		}
/*
		if (UserService.getInstance().emailExists(value, canRepeat, id)) {
			req.setAttribute(param + "NotAvailable", true);
			req.setAttribute(param + "Error", true);
			return false;
		}
*/
		return true;
	}

	public static boolean checkParamsEqual(HttpServletRequest req, String param1, String param2) {

		String value1 = req.getParameter(param1);
		String value2 = req.getParameter(param2);

		if (value1 == null || value2 == null) {
			return value1 == value2;
		}
		
		if (!value1.equals(value2)) {
			req.setAttribute(param2 + "DoesntMatch", true);
			req.setAttribute(param2 + "Error", true);
			return false;
		}

		return true;
	}

	public static boolean checkUsername(HttpServletRequest req, String param, int min, int max) {

		if (!checkParameter(req, param, min, max)) {
			return false;
		}
/*
		if (UserService.getInstance().usernameExists(req.getParameter(param))) {
			req.setAttribute(param + "NotAvailable", true);
			req.setAttribute(param + "Error", true);
			return false;
		}
*/
		return true;
	}

	protected boolean checkDate(HttpServletRequest req, String param1, String param2, String param3) {

		if (!checkIntegerParameter(req, param1) || !checkIntegerParameter(req, param2) ||
				!checkIntegerParameter(req, param3)) {
			return false;
		}

		Integer value1 = Integer.parseInt(req.getParameter(param1));
		Integer value2 = Integer.parseInt(req.getParameter(param2));
		Integer value3 = Integer.parseInt(req.getParameter(param3));

		Calendar date = new GregorianCalendar(value3, value1, value2);

		date.setLenient(false);

		try {
			date.get(0);
		} catch (Exception e) {
			req.setAttribute("dateInvalidFormat", true);
			req.setAttribute("dateError", true);
			return false;
		}

		return true;
	}
}
