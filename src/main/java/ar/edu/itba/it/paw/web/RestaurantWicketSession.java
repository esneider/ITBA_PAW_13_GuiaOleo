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
	
	private IModel<User> sessionUserModel = new EntityModel<User>(User.class);
	
	public static RestaurantWicketSession get() {
		return (RestaurantWicketSession) Session.get();
	}

	public RestaurantWicketSession(Request request) {
		super(request);
	}

	public String getUsername() {
		return sessionUserModel.getObject().getUsername();
	}
	
	public User getUser() {
		return sessionUserModel.getObject();
	}

	public boolean signIn(String username, String password, UserRepo users) {
		User user = users.get(username);
		if (user != null && user.checkPassword(password)) {
			this.sessionUserModel.setObject(user);
			return true;
		}
		return false;
	}

	public boolean isSignedIn() {
		return sessionUserModel.getObject() != null;
	}

	public void signOut() {
        invalidate();
        clear();
	}
}
