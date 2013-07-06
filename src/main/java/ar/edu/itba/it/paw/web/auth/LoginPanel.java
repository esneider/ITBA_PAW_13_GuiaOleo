package ar.edu.itba.it.paw.web.auth;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.user.UserRepo;
import ar.edu.itba.it.paw.web.RestaurantWicketSession;

public class LoginPanel extends Panel {

	@SpringBean
	private UserRepo userRepo;
	
	private transient String username;
	private transient String password;
	
	public LoginPanel(String id) {
		super(id);
	
		add(new FeedbackPanel("feedback"));
		Form<LoginPanel> form = new Form<LoginPanel>("loginForm", new CompoundPropertyModel<LoginPanel>(this)) {
			@Override
			protected void onSubmit() {
				RestaurantWicketSession session = RestaurantWicketSession.get();

				if (session.signIn(username, password, userRepo)) {
					if (!continueToOriginalDestination()) {
						setResponsePage(getApplication().getHomePage());
					}
				} else {
					error(getString("invalidCredentials"));
				}
			}
		};
		
		form.add(new TextField<String>("username").setRequired(true));
		form.add(new PasswordTextField("password"));
		form.add(new Button("login", new ResourceModel("login")));
		add(form);

	}

}
