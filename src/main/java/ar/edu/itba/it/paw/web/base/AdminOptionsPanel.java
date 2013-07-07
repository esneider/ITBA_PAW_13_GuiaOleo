package ar.edu.itba.it.paw.web.base;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import ar.edu.itba.it.paw.web.user.PendingRestaurantsPage;
import ar.edu.itba.it.paw.web.user.RegisteredUsersPage;

public class AdminOptionsPanel extends Panel {

	public AdminOptionsPanel(String id) {
		super(id);
		
		add(new Link<Void>("pending") {
			@Override
			public void onClick() {
				setResponsePage(PendingRestaurantsPage.class);
			}
		});
		
		add(new Link<Void>("registeredUsers") {
			@Override
			public void onClick() {
				setResponsePage(RegisteredUsersPage.class);
			}
		});
	}

}
