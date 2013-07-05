package ar.edu.itba.it.paw.web.base;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import ar.edu.itba.it.paw.web.auth.LoginRegisterPage;

public class UnLoggedHeaderPanel extends Panel {

	private static final long serialVersionUID = 2231596185563965901L;

	public UnLoggedHeaderPanel(String id) {
		super(id);
		
		add(new Link<Void>("login"){

			private static final long serialVersionUID = -6116478108555751666L;

			@Override
			public void onClick() {
				setResponsePage(LoginRegisterPage.class);
			}
			
		});
	}
	
}
