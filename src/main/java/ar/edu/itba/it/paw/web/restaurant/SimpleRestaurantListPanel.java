package ar.edu.itba.it.paw.web.restaurant;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.EntityModel;
import ar.edu.itba.it.paw.domain.restaurant.DailyReportRepo;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.web.common.HighlightedRestaurantLink;

public class SimpleRestaurantListPanel extends Panel {

	private static final long serialVersionUID = -3687249904130826179L;

	@SpringBean
	private DailyReportRepo reportRepo;
	
	public SimpleRestaurantListPanel(String id,
			final IModel<List<Restaurant>> restaurantListModel, final boolean reportOnClick) {
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
				item.add(new HighlightedRestaurantLink<Restaurant>("link", item
						.getModel()) {

					private static final long serialVersionUID = 3059997643546595055L;

					@Override
					public void onClick() {
						if (reportOnClick) 
							item.getModelObject().click(reportRepo);
						
						setResponsePage(new RestaurantViewPage(item.getModel()));
					}
				});
				item.add(new Label("name", item.getModel()));
			}
		});

	}
}
