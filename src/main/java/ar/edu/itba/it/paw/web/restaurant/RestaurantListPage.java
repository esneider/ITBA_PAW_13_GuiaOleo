package ar.edu.itba.it.paw.web.restaurant;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.domain.restaurant.RestaurantRepo;
import ar.edu.itba.it.paw.web.base.SideBarPage;

@SuppressWarnings("serial")
public class RestaurantListPage extends SideBarPage {

	@SpringBean
	private RestaurantRepo restRepo;

	public RestaurantListPage() {
		super(null);
		IModel<List<Restaurant>> restaurantsModel = new LoadableDetachableModel<List<Restaurant>>() {
			@Override
			protected List<Restaurant> load() {
				return restRepo.getAll();
			}
		};
		add(new PropertyListView<Restaurant>("restaurant", restaurantsModel) {
			@Override
			protected void populateItem(ListItem<Restaurant> item) {
				item.add(new Link<Restaurant>("name", item.getModel()) {
					@Override
					public void onClick() {
						setResponsePage(null, null);
					}
				}.add(new Label("restName", item.getModel())));
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
