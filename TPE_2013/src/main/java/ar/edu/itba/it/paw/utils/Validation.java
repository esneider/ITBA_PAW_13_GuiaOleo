package ar.edu.itba.it.paw.utils;

public class Validation {

	public static boolean isEmail(String str) {
		return str != null && str.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
	}
}
