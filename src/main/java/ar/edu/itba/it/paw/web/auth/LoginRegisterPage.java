package ar.edu.itba.it.paw.web.auth;

import ar.edu.itba.it.paw.web.RestaurantWicketSession;
import ar.edu.itba.it.paw.web.base.NoSideBarPage;

public class LoginRegisterPage extends NoSideBarPage {

	private static final long serialVersionUID = -1895220774057276855L;

	public LoginRegisterPage() {
		super(false);
		if (RestaurantWicketSession.get().isSignedIn()) {
			setResponsePage(getApplication().getHomePage());
		}
		add(new LoginPanel("login"));
		add(new RegisterPanel("register"));
	}
	
}
