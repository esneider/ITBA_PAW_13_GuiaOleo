package ar.edu.itba.it.paw.web.auth;

import org.apache.wicket.feedback.ContainerFeedbackMessageFilter;
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

	private static final long serialVersionUID = -4788537571243090492L;

	@SpringBean
	private UserRepo userRepo;
	
	private transient String username;
	private transient String password;
	
	public LoginPanel(String id) {
		super(id);

		FeedbackPanel panel = new FeedbackPanel("loginFeedback");
		panel.setFilter(new ContainerFeedbackMessageFilter(this));

		add(panel);

		Form<LoginPanel> form = new Form<LoginPanel>("loginForm", new CompoundPropertyModel<LoginPanel>(this)) {

			private static final long serialVersionUID = 4524463630059496333L;

			@Override
			protected void onSubmit() {
				RestaurantWicketSession session = RestaurantWicketSession.get();

				if (session.signIn(username, password, userRepo)) {
					continueToOriginalDestination();
					setResponsePage(getApplication().getHomePage());
				} else {
					error(getString("invalidCredentials"));
				}
			}
		};
		
		form.add(new TextField<String>("username").setRequired(true));
		form.add(new PasswordTextField("password").setRequired(true));
		form.add(new Button("login", new ResourceModel("login")));
		add(form);

	}

}
