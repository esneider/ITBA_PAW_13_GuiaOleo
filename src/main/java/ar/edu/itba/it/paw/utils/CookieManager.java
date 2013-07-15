package ar.edu.itba.it.paw.utils;

import java.util.List;

import javax.servlet.http.Cookie;

import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.http.WebRequest;
import org.apache.wicket.request.http.WebResponse;

import ar.edu.itba.it.paw.web.application.RestaurantApplication;

public class CookieManager {

	public void clearCookie(Request req, Response resp, String cookieName) {
		Cookie c = retrieveCookie(req, cookieName);
		if (c != null) {
			c.setMaxAge(-1000);
			c.setValue("no-user");
			((WebResponse)resp).addCookie(c);
		}
	}
	
	public Cookie retrieveCookie(Request req, String cookieName) {
		List<Cookie> previousCookies = ((WebRequest)req).getCookies();
		for (Cookie c : previousCookies) {
			if (c.getName().equals(cookieName))
				return c;
		}
		return null;
	}
	
	public void setCookie(Response resp, String cookieName, String value) {
		Cookie cookie = new Cookie(cookieName, value);
		cookie.setMaxAge(RestaurantApplication.COOKIE_TTL);
		cookie.setPath("/paw/bin");
		((WebResponse) resp).addCookie(cookie);
	}
	
}
