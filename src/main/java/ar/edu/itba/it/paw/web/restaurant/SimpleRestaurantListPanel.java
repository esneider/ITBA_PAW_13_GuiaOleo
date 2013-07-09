package ar.edu.itba.it.paw.web.restaurant;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import ar.edu.itba.it.paw.domain.EntityModel;
import ar.edu.itba.it.paw.domain.foodtype.FoodType;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;

public class SimpleRestaurantListPanel extends Panel {

	public SimpleRestaurantListPanel(String id,
			final IModel<List<Restaurant>> restaurantListModel) {
		super(id);

		add(new RefreshingView<Restaurant>("restaurants") {

			private static final long serialVersionUID = 7702722087881335104L;

			@Override
			protected Iterator<IModel<Restaurant>> getItemModels() {
				List<IModel<Restaurant>> result = new ArrayList<IModel<Restaurant>>();
				for (Restaurant r : restaurantListModel.getObject()) {
					result.add(new EntityModel<Restaurant>(Restaurant.class, r));
				}
				return result.iterator();
			}

			@Override
			protected void populateItem(final Item<Restaurant> item) {
				item.add(new Link<Restaurant>("link") {
					@Override
					public void onClick() {
						setResponsePage(new RestaurantViewPage(item.getModel()));
					}
				});
				item.add(new Label("name", item.getModel()));
			}
		});

	}
}
