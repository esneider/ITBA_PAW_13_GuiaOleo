package ar.edu.itba.it.paw.utils;

import java.net.URISyntaxException;

import org.apache.http.client.utils.URIBuilder;

public class Utils {

	public static boolean isEmail(String str) {

		return str != null && str.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
	}
	
	public static String addParameterToURI(String URI, String param, String value) {
		
		URIBuilder builder;

		try {

			builder = new URIBuilder(URI);

		} catch (URISyntaxException e) {

			e.printStackTrace();
			return "";
		}
		
		builder.addParameter(param, value);
		
		return builder.toString();
	}

	public static float round(float num) {

		return ((float) Math.round(num * 100)) / 100;
	}
}
