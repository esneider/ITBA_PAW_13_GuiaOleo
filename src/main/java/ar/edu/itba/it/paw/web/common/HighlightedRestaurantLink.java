package ar.edu.itba.it.paw.web.common;

import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.IModel;

import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.web.application.RestaurantApplication;

@SuppressWarnings("serial")
public abstract class HighlightedRestaurantLink<T> extends Link<T> {

	public HighlightedRestaurantLink(String id, IModel<T> model) {
		super(id, model);
		boolean isRestaurant = model.getObject() != null
				&& model.getObject().getClass() == Restaurant.class;
		add(new Image("imgHighlighted", RestaurantApplication.HIGHLIGHTED_ICON)
				.setVisible(isRestaurant
						&& ((Restaurant) model.getObject()).isHighlighted()));
	}
}
