package ar.edu.itba.it.paw.web.restaurant;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.foodtype.FoodType;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.domain.restaurant.RestaurantRepo;
import ar.edu.itba.it.paw.web.base.SideBarPage;

@SuppressWarnings("serial")
public class RestaurantListPage extends SideBarPage {

	@SpringBean
	private RestaurantRepo restRepo;

	public RestaurantListPage() {
		super(null);
		IModel<List<Restaurant>> listModel = new LoadableDetachableModel<List<Restaurant>>() {
			@Override
			protected List<Restaurant> load() {
				return restRepo.getBestRatedRestaurants(10);
			}
		};
		populatePage(listModel);
	}

	public RestaurantListPage(final IModel<FoodType> ft) {
		super(null);
		IModel<List<Restaurant>> listModel = new LoadableDetachableModel<List<Restaurant>>() {
			@Override
			protected List<Restaurant> load() {
				return new ArrayList<Restaurant>(ft.getObject().getRestaurants());
			}

		};
		populatePage(listModel);
	}

	public RestaurantListPage(final String query) {
		super(null);
		IModel<List<Restaurant>> listModel = new LoadableDetachableModel<List<Restaurant>>() {
			@Override
			protected List<Restaurant> load() {
				return restRepo.getRestaurantsByQuery(query);
			}

		};
		populatePage(listModel);
	}

	public void populatePage(IModel<List<Restaurant>> restaurantsModel) {
		add(new PropertyListView<Restaurant>("restaurant", restaurantsModel) {
			@Override
			protected void populateItem(final ListItem<Restaurant> item) {
				item.add(new Link<Restaurant>("name", item.getModel()) {
					@Override
					public void onClick() {
						setResponsePage(null, null);
					}
				}.add(new Label("restName", item.getModel())));

				IModel<List<FoodType>> foodTypesModel = new LoadableDetachableModel<List<FoodType>>() {
					@Override
					protected List<FoodType> load() {
						return new ArrayList<FoodType>(item.getModelObject().getFoodtypes());
					}

				};
				
				item.add(new PropertyListView<FoodType>("foodtypes", foodTypesModel) {

					@Override
					protected void populateItem(ListItem<FoodType> item) {
						item.add(new Label("name"));
					}
				});
				item.add(new Label("address"));
				item.add(new Label("area"));
				item.add(new Label("avgScore"));
				item.add(new Label("ratingsAmmount"));
			}
		});
	}

	// UNA CON FOODTYPE
	// OTRA CON QUERY .
	// ALL
	public RestaurantListPage(Restaurant restaurant) {
		super(null);
		/*
		 * super(null); setDefaultModel(new
		 * EntityModel<Restaurant>(Restaurant.class, restaurant)); add(new
		 * RestaurantListPanel("restaurants", new
		 * PropertyModel<Collection<Restaurant>>(getDefaultModel(),
		 * "restaurants"), new
		 * LoadableDetachableModel<RestaurantPanelProvider>() {
		 * 
		 * @Override protected RestaurantPanelProvider load() { return new
		 * SimpleRestaurantPanelProvider(); } }));
		 */
	}

}
