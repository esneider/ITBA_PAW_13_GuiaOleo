package ar.edu.itba.it.paw.web.user;

import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.panel.Panel;

public class TwoPasswordPanel extends Panel {

	public TwoPasswordPanel(String id) {
		super(id);
		add(new PasswordTextField("password").setRequired(false));
		add(new PasswordTextField("repassword").setRequired(false));
	}

}
