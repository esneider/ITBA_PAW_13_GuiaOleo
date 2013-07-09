package ar.edu.itba.it.paw.web.restaurant;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.domain.restaurant.RestaurantRepo;
import ar.edu.itba.it.paw.web.RestaurantWicketSession;

public class AdvertisePanel extends Panel {

	/**
	 * 
	 */
	@SpringBean
	private RestaurantRepo restRepo;

	private static final long serialVersionUID = 2655489110845048370L;

	@SuppressWarnings("serial")
	public AdvertisePanel(String id) {
		super(id);
		IModel<List<Restaurant>> advRestModel = new LoadableDetachableModel<List<Restaurant>>() {
			@Override
			protected List<Restaurant> load() {
				if (RestaurantWicketSession.get().isSignedIn()) {
					return restRepo
							.getAdvertisedRestaurants(RestaurantWicketSession
									.get().getUser());
				} else
					return restRepo.getAdvertisedRestaurants();
			}
		};

		add(new SimpleRestaurantListPanel("restaurant", advRestModel));

	}
}
