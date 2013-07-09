package ar.edu.itba.it.paw.web.restaurant;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.foodtype.FoodType;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.domain.restaurant.RestaurantRepo;
import ar.edu.itba.it.paw.web.base.SideBarPage;
import ar.edu.itba.it.paw.web.common.HighlightedRestaurantLink;

@SuppressWarnings("serial")
public class RestaurantListPage extends SideBarPage {

	@SpringBean
	private RestaurantRepo restRepo;

	public RestaurantListPage() {
		super(null, false);
		IModel<List<Restaurant>> listModel = new LoadableDetachableModel<List<Restaurant>>() {
			@Override
			protected List<Restaurant> load() {
				return restRepo.getBestRatedRestaurants(10);
			}
		};
		populatePage(listModel);
	}

	public RestaurantListPage(final IModel<FoodType> ft) {
		super(ft, false);
		IModel<List<Restaurant>> listModel = new LoadableDetachableModel<List<Restaurant>>() {
			@Override
			protected List<Restaurant> load() {
				return new ArrayList<Restaurant>(ft.getObject()
						.getRestaurants());
			}

		};
		populatePage(listModel);
	}

	public RestaurantListPage(final String query) {
		super(null, false);
		IModel<List<Restaurant>> listModel = new LoadableDetachableModel<List<Restaurant>>() {
			@Override
			protected List<Restaurant> load() {
				return restRepo.getRestaurantsByQuery(query);
			}

		};
		populatePage(listModel);
	}

	public void populatePage(IModel<List<Restaurant>> restaurantsModel) {
		add(new AdvertisePanel("advPanel"));
		add(new PropertyListView<Restaurant>("restaurant", restaurantsModel) {
			@Override
			protected void populateItem(final ListItem<Restaurant> item) {
				item.setDefaultModel(item.getModel());
				item.add(new HighlightedRestaurantLink<Restaurant>("name", item
						.getModel()) {
					@Override
					public void onClick() {
						setResponsePage(new RestaurantViewPage(item.getModel()));
					}
				}.add(new Label("restName", item.getModel())));

				item.add(new FoodTypesPanel("foodtypes", item.getModel()));
				item.add(new Label("address"));
				item.add(new Label("area"));
				item.add(new Label("avgScore"));
				item.add(new Label("ratingsAmmount"));
				item.add(new HighlightActionsPanel("highlightpanel", item
						.getModel()));
			}
		});

	}

}
