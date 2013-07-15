package ar.edu.itba.it.paw.web.restaurant;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

import ar.edu.itba.it.paw.domain.EntityModel;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.web.common.HighlightedRestaurantLink;

public class RefreshingRestaurantListView extends RefreshingView<Restaurant> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3598656638378637587L;
	private transient List<Restaurant> restaurantList;

	public RefreshingRestaurantListView(String id,
			List<Restaurant> restaurantList) {
		super(id);
		this.restaurantList = restaurantList;
	}

	@Override
	protected Iterator<IModel<Restaurant>> getItemModels() {
		List<IModel<Restaurant>> restaurantModelList = new ArrayList<IModel<Restaurant>>();
		for (Restaurant r : restaurantList) {
			restaurantModelList.add(new EntityModel<Restaurant>(
					Restaurant.class, r));
		}
		return restaurantModelList.iterator();
	}

	@Override
	protected void populateItem(final Item<Restaurant> item) {
		item.setDefaultModel(new CompoundPropertyModel<Restaurant>(item
				.getModel()));
		item.add(new HighlightedRestaurantLink<Restaurant>("name", item
				.getModel()) {
			/**
					 * 
					 */
					private static final long serialVersionUID = 7185882732507990579L;

			@Override
			public void onClick() {
				setResponsePage(new RestaurantViewPage(item.getModel(), false));
			}
		}.add(new Label("restName", item.getModel())));

		item.add(new FoodTypesPanel("foodtypes", item.getModel()));
		item.add(new Label("address"));
		item.add(new Label("area"));
		item.add(new Label("avgScore"));
		item.add(new Label("scoredBy", new StringResourceModel("scoredBy",
				new Model<Serializable>(item.getModelObject()
						.getRatingsAmmount()))));
		item.add(new HighlightActionsPanel("highlightpanel", item.getModel()));
	}

}
