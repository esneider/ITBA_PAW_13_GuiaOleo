package ar.edu.itba.it.paw.web.restaurant;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;

import ar.edu.itba.it.paw.domain.EntityModel;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;

public class SimpleRestaurantListPanel extends Panel {

	public SimpleRestaurantListPanel(String id, IModel<List<Restaurant>> restaurantListModel) {
		super(id);
		add(new PropertyListView<Restaurant>("restaurant", restaurantListModel) {
			@Override
			protected void populateItem(final ListItem<Restaurant> item) {
				item.setDefaultModel(item.getModel());
				item.add(new Link<Restaurant>("link", item.getModel()) {
					@Override
					public void onClick() {
						setResponsePage(new RestaurantViewPage(
								new EntityModel<Restaurant>(Restaurant.class,
										item.getModelObject())));
					}
				});
				item.add(new Label("name", item.getModel()));

			}
		});
	}

}
