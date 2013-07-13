package ar.edu.itba.it.paw.web.base;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.model.ResourceModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.foodtype.FoodType;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.domain.restaurant.RestaurantRepo;
import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.domain.user.UserRepo;
import ar.edu.itba.it.paw.utils.CookieManager;
import ar.edu.itba.it.paw.web.RestaurantWicketSession;
import ar.edu.itba.it.paw.web.application.RestaurantApplication;
import ar.edu.itba.it.paw.web.auth.LoginRegisterPage;
import ar.edu.itba.it.paw.web.restaurant.RestaurantListPage;

@SuppressWarnings("serial")
public class BasePage extends WebPage {

	@SpringBean
	private RestaurantRepo restRepo;

	@SpringBean
	private UserRepo userRepo;

	private transient String query;

	public BasePage(boolean secured) {

		// Header

		RestaurantWicketSession session = getRestaurantWicketSession();

		if (!session.isSignedIn()) {
			CookieManager cookieManager = new CookieManager();
			Cookie sessionCookie = cookieManager.retrieveCookie(getRequest(),
					RestaurantApplication.SESSION_COOKIE);
			if (sessionCookie != null && sessionCookie.getValue() != null) {
				User user = userRepo.get(sessionCookie.getValue());
				if (session.signIn(user.getUsername(), user.getPassword(),
						userRepo)) {
				}
			}
		}

		if (secured) {
			if (!session.isSignedIn()) {
				redirectToInterceptPage(new LoginRegisterPage());
			}
		}

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

		form.add(new TypeAhead<String>("query", new PropertyModel<String>(this,
				"query")) {

			@Override
			protected Iterator<String> getChoices() {
				Set<String> stringSet = new HashSet<String>();
				List<Restaurant> restList = restRepo.getAll();
				String s = getInput().toLowerCase();
				for (Restaurant r : restList) {
					if (r.getName().toLowerCase().contains(s))
						stringSet.add(r.getName());
					if (r.getArea().toLowerCase().contains(s))
						stringSet.add(r.getArea());
					for (FoodType f : r.getFoodtypes()) {
						if (f.getName().toLowerCase().contains(s))
							stringSet.add(f.getName());
					}
				}
				return stringSet.iterator();
			}

			@Override
			protected void onSelect(AjaxRequestTarget target) {
				setResponsePage(new RestaurantListPage(getInput()));
			}
		});

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
