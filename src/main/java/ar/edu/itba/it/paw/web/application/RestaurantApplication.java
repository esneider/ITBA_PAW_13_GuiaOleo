package ar.edu.itba.it.paw.web.application;

import org.apache.wicket.ConverterLocator;
import org.apache.wicket.IConverterLocator;
import org.apache.wicket.Page;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.request.resource.ResourceReference;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ar.edu.itba.it.paw.domain.foodtype.FoodType;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.domain.user.UserRepo;
import ar.edu.itba.it.paw.web.RestaurantWicketSession;
import ar.edu.itba.it.paw.web.common.ErrorRequestCycleListener;
import ar.edu.itba.it.paw.web.common.HibernateRequestCycleListener;
import ar.edu.itba.it.paw.web.converter.FoodTypeConverter;
import ar.edu.itba.it.paw.web.converter.RestaurantConverter;
import ar.edu.itba.it.paw.web.converter.UserConverter;
import ar.edu.itba.it.paw.web.restaurant.RestaurantListPage;

@Component
public class RestaurantApplication extends WebApplication {

	public static final ResourceReference HIGHLIGHTED_ICON = new PackageResourceReference(
			RestaurantApplication.class, "high.jpg");

	public static final String SESSION_COOKIE = "sessionCookie";
	public static final int COOKIE_TTL = 2592000;

	private final SessionFactory sessionFactory;

	private UserRepo userRepo;

	@Autowired
	public RestaurantApplication(SessionFactory sessionFactory,
			UserRepo userRepo) {
		this.userRepo = userRepo;
		this.sessionFactory = sessionFactory;
	}

	@Override
	protected void init() {
		super.init();
		getComponentInstantiationListeners().add(
				new SpringComponentInjector(this));
		getRequestCycleListeners().add(
				new HibernateRequestCycleListener(sessionFactory));
		getRequestCycleListeners().add(new ErrorRequestCycleListener());

	}

	@Override
	public Class<? extends Page> getHomePage() {
		return RestaurantListPage.class;
	}

	@Override
	public Session newSession(Request request, Response response) {
		return new RestaurantWicketSession(request);
	}

	@Override
	protected IConverterLocator newConverterLocator() {
		ConverterLocator converterLocator = new ConverterLocator();
		converterLocator.set(FoodType.class, new FoodTypeConverter());
		converterLocator.set(Restaurant.class, new RestaurantConverter());
		converterLocator.set(User.class, new UserConverter(userRepo));
		return converterLocator;
	}

}
