package ar.edu.itba.it.paw.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;

import ar.edu.itba.it.paw.domain.foodtype.FoodTypeRepo;
import ar.edu.itba.it.paw.domain.user.UserRepo;

public class Utils {

	public Utils() {
	}

	public static boolean isEmail(String str) {

		return str != null
				&& str.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
	}

	public static float round(float num) {

		return ((float) Math.round(num * 100)) / 100;
	}

	public static String normalizeString(String s) {

		if (s == null) {

			return "";
		}

		return s.trim();
	}

	public static String function(Double doubleValue) {

		boolean isWholeNumber = (doubleValue == Math.round(doubleValue));
		DecimalFormatSymbols formatSymbols = new DecimalFormatSymbols(
				Locale.ENGLISH);
		formatSymbols.setDecimalSeparator('.');

		String pattern = isWholeNumber ? "#.##" : "#.00";
		DecimalFormat df = new DecimalFormat(pattern, formatSymbols);
		return (df.format(doubleValue));
	}
}
