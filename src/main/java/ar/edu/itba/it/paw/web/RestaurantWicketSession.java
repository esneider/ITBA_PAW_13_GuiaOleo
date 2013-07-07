package ar.edu.itba.it.paw.web;

import org.apache.wicket.Session;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

import ar.edu.itba.it.paw.domain.EntityModel;
import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.domain.user.UserRepo;

public class RestaurantWicketSession extends WebSession {

	private static final long serialVersionUID = -6016629258146518387L;
	
	private String username = null;
	
	public static RestaurantWicketSession get() {
		return (RestaurantWicketSession) Session.get();
	}

	public RestaurantWicketSession(Request request) {
		super(request);
	}

	public String getUsername() {
		return username;
	}
	
	public User getUser(UserRepo userRepo) {
		return userRepo.get(username);
	}

	public boolean signIn(String username, String password, UserRepo users) {
		User user = users.get(username);
		if (user != null && user.checkPassword(password)) {
			this.username = username;
			return true;
		}
		return false;
	}

	public boolean isSignedIn() {
		return username != null;
	}

	public void signOut() {
        invalidate();
        clear();
	}
}
