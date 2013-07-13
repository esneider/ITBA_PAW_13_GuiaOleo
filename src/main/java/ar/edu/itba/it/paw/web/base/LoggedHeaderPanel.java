package ar.edu.itba.it.paw.web.base;

import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

import ar.edu.itba.it.paw.domain.EntityModel;
import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.web.RestaurantWicketSession;
import ar.edu.itba.it.paw.web.provider.ImageProvider;
import ar.edu.itba.it.paw.web.restaurant.RegisterRestaurantPage;
import ar.edu.itba.it.paw.web.user.ModifyPage;
import ar.edu.itba.it.paw.web.user.UserProfilePage;

public class LoggedHeaderPanel extends Panel {

	private static final long serialVersionUID = 719015207600371947L;

	public LoggedHeaderPanel(String id) {
		super(id);

		add(new Label("username", new Model<String>(getUser().getUsername())));
		add(ImageProvider.getImage("avatar", getUser()));

		add(new Link<Void>("modify") {

			private static final long serialVersionUID = 5315750304894078540L;

			@Override
			public void onClick() {
				setResponsePage(ModifyPage.class);
			}
		});

		add(new Link<Void>("profile") {

			private static final long serialVersionUID = -7597430459206052099L;

			@Override
			public void onClick() {
				setResponsePage(new UserProfilePage(new EntityModel<User>(
						User.class, getUser())));
			}
		});

		add(new Link<Void>("registerRestaurant") {

			private static final long serialVersionUID = 58416051968233625L;

			@Override
			public void onClick() {
				setResponsePage(RegisterRestaurantPage.class);
			}
		});

		add(new Link<Void>("logout") {

			private static final long serialVersionUID = 8250987587459407328L;

			@Override
			public void onClick() {
				((RestaurantWicketSession) getSession()).signOut();
				throw new RestartResponseAtInterceptPageException(
						(getApplication().getHomePage()));
			}
		});

		add(new AdminOptionsPanel("adminOptions") {

			private static final long serialVersionUID = -8250652161807158533L;

			@Override
			public boolean isVisible() {
				return getUser().isAdmin();
			}
		});

	}

	private User getUser() {
		return RestaurantWicketSession.get().getUser();
	}

}
