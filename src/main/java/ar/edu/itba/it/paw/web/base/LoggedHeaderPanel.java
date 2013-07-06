package ar.edu.itba.it.paw.web.base;

import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.image.NonCachingImage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.PackageResourceReference;

import ar.edu.itba.it.paw.domain.EntityModel;
import ar.edu.itba.it.paw.domain.picture.Picture;
import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.web.HomePage;
import ar.edu.itba.it.paw.web.RestaurantWicketSession;
import ar.edu.itba.it.paw.web.application.RestaurantApplication;
import ar.edu.itba.it.paw.web.picture.PictureImageResource;

public class LoggedHeaderPanel extends Panel {

	private static final long serialVersionUID = 719015207600371947L;

	public LoggedHeaderPanel(String id) {
		super(id);

		add(new Label("username", new Model<String>(getUser().getUsername())));
		if (getUser().getAvatar() != null) {
			add(new NonCachingImage("avatar",
					new PictureImageResource(new EntityModel<Picture>(Picture.class,
							getUser().getAvatar()))));
		} else {
			add(new Image("avatar",new PackageResourceReference(RestaurantApplication.class, "default.jpg")));
		}
		
		add(new Link<Void>("modify") {
			@Override
			public void onClick() {
				setResponsePage(HomePage.class);
			}
		});
		
		add(new Link<Void>("profile") {
			@Override
			public void onClick() {
				setResponsePage(HomePage.class);
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
				throw new RestartResponseAtInterceptPageException((getApplication().getHomePage()));
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
		return RestaurantWicketSession.get().getUser();
	}

}
