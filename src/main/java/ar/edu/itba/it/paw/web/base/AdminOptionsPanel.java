package ar.edu.itba.it.paw.web.base;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

import ar.edu.itba.it.paw.web.user.PendingRestaurantsPage;
import ar.edu.itba.it.paw.web.user.RegisteredUsersPage;

public class AdminOptionsPanel extends Panel {

	private static final long serialVersionUID = 1989955639109223939L;

	public AdminOptionsPanel(String id) {
		super(id);
		
		add(new Link<Void>("pending") {

			private static final long serialVersionUID = 581719778846402033L;

			@Override
			public void onClick() {
				setResponsePage(PendingRestaurantsPage.class);
			}
		});
		
		add(new Link<Void>("registeredUsers") {

			private static final long serialVersionUID = -5904485654593507904L;

			@Override
			public void onClick() {
				setResponsePage(RegisteredUsersPage.class);
			}
		});
	}

}
