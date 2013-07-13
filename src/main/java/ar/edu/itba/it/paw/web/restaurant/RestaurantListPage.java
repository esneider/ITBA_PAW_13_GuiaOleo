package ar.edu.itba.it.paw.web.restaurant;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.foodtype.FoodType;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.domain.restaurant.RestaurantRepo;
import ar.edu.itba.it.paw.utils.Utils;
import ar.edu.itba.it.paw.web.base.SideBarPage;

@SuppressWarnings("serial")
public class RestaurantListPage extends SideBarPage {

	@SpringBean
	private RestaurantRepo restRepo;

	public RestaurantListPage() {
		super(null, false);
		populatePage(restRepo.getBestRatedRestaurants(10));
	}

	public RestaurantListPage(final IModel<FoodType> ft) {
		super(ft, false);
		populatePage(new ArrayList<Restaurant>(ft.getObject()
				.getRestaurants()));
	}

	public RestaurantListPage(final String query) {
		super(null, false);
		populatePage(restRepo.getRestaurantsByQuery(Utils.normalizeString(query)));
	}

	public void populatePage(List<Restaurant> restaurantsModel) {
		add(new AdvertisePanel("advPanel"));
		add(new RefreshingRestaurantListView("restaurant", restaurantsModel));
	}

}
