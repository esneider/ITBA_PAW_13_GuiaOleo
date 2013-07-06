package ar.edu.itba.it.paw.web.base;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.image.NonCachingImage;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.request.resource.PackageResourceReference;

import ar.edu.itba.it.paw.domain.EntityModel;
import ar.edu.itba.it.paw.domain.picture.Picture;
import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.web.HomePage;
import ar.edu.itba.it.paw.web.RestaurantWicketSession;
import ar.edu.itba.it.paw.web.application.RestaurantApplication;
import ar.edu.itba.it.paw.web.picture.PictureImageResource;

@SuppressWarnings("serial")
public class BasePage extends WebPage {

	public BasePage() {

		// Header

		RestaurantWicketSession session = getRestaurantWicketSession();

		if (session.isSignedIn()) {
			add(new LoggedHeaderPanel("header"));
		} else {
			add(new UnLoggedHeaderPanel("header"));
		}

		// Logo

		add(new Link<Void>("link-logo") {

			@Override
			public void onClick() {
				setResponsePage(HomePage.class);
			}
		});
	}

	private RestaurantWicketSession getRestaurantWicketSession() {

		return (RestaurantWicketSession) getSession();
	}
}
