package ar.edu.itba.it.paw.web.base;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import ar.edu.itba.it.paw.web.auth.LoginRegisterPage;

public class UnLoggedHeaderPanel extends Panel {

	public UnLoggedHeaderPanel(String id) {
		super(id);
		
		add(new Link<Void>("login"){

			@Override
			public void onClick() {
				setResponsePage(LoginRegisterPage.class);
			}
			
		});
	}
	
}
