package ar.edu.itba.it.paw.web.base;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;

import ar.edu.itba.it.paw.web.RestaurantWicketSession;
import ar.edu.itba.it.paw.web.restaurant.RestaurantListPage;


@SuppressWarnings("serial")
public class BasePage extends WebPage {
	
	private transient String query;

	public BasePage() {

		// Header

		RestaurantWicketSession session = getRestaurantWicketSession();

		if (session.isSignedIn()) {
			add(new LoggedHeaderPanel("header"));
		} else {
			add(new UnLoggedHeaderPanel("header"));
		}

		// Busqueda

		Form<BasePage> form = new Form<BasePage>("searchForm") {
			@Override
			protected void onSubmit() {
				setResponsePage(new RestaurantListPage(query));
			}
		};
		form.add(new TextField<String>("query", new PropertyModel<String>(this,
				"query")));
		form.add(new Button("search", new ResourceModel("search")));
		add(form);

		// Logo

		add(new Link<Void>("link-logo") {

			@Override
			public void onClick() {
				setResponsePage(RestaurantListPage.class);
			}
		});
	}

	protected RestaurantWicketSession getRestaurantWicketSession() {
		return (RestaurantWicketSession) getSession();
	}
}
