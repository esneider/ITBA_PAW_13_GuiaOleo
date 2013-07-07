package ar.edu.itba.it.paw.web.base;

import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.EntityModel;
import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.domain.user.UserRepo;
import ar.edu.itba.it.paw.web.HomePage;
import ar.edu.itba.it.paw.web.RestaurantWicketSession;
import ar.edu.itba.it.paw.web.provider.ImageProvider;
import ar.edu.itba.it.paw.web.user.UserProfilePage;

public class LoggedHeaderPanel extends Panel {

	private static final long serialVersionUID = 719015207600371947L;

	@SpringBean
	private UserRepo userRepo;
	
	public LoggedHeaderPanel(String id) {
		super(id);

		add(new Label("username", new Model<String>(getUser().getUsername())));
		add(ImageProvider.getImage("avatar", getUser()));

		add(new Link<Void>("modify") {
			@Override
			public void onClick() {
				setResponsePage(HomePage.class);
			}
		});

		add(new Link<Void>("profile") {
			@Override
			public void onClick() {
				setResponsePage(new UserProfilePage(new EntityModel<User>(
						User.class, getUser())));
			}
		});

		add(new Link<Void>("registerRestaurant") {
			@Override
			public void onClick() {
				setResponsePage(HomePage.class);
			}
		});

		add(new Link<Void>("logout") {
			@Override
			public void onClick() {
				((RestaurantWicketSession) getSession()).signOut();
				throw new RestartResponseAtInterceptPageException(
						(getApplication().getHomePage()));
			}
		});

		add(new AdminOptionsPanel("adminOptions") {
			@Override
			public boolean isVisible() {
				return getUser().isAdmin();
			}
		});

	}

	private User getUser() {
		return RestaurantWicketSession.get().getUser(userRepo);
	}

}
