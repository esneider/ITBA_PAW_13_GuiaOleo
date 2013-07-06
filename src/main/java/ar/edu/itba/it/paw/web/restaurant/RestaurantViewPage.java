package ar.edu.itba.it.paw.web.restaurant;

import java.util.List;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;

import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.web.base.SideBarPage;

public class RestaurantViewPage extends SideBarPage {

	private static final long serialVersionUID = 1094753744913503034L;
	

	public RestaurantViewPage(IModel<Restaurant> restaurantModel) {
		super(null);

		setDefaultModel(new CompoundPropertyModel<Restaurant>(restaurantModel));
		add(new Label("name"));
		add(new Label("address"));
		add(new Label("area"));
		add(new Label("telephone"));
		add(new Label("price"));
		add(new Label("timerange"));
		add(new Label("website"));
		add(new Label("registerUser", new Model<String>(restaurantModel
				.getObject().getRegisterUser().getName()
				+ " - "
				+ restaurantModel.getObject().getRegisterUser().getUsername())));
		add(
				new WebMarkupContainer("googlemap").add(new AttributeAppender(
						"data-address", new Model<String>(restaurantModel
								.getObject().getAddress())))).add(
				new AttributeAppender("data-description", new Model<String>(
						restaurantModel.getObject().getName())));
		
		IModel<List<Restaurant>> listModel = new LoadableDetachableModel<List<Restaurant>>() {
			@Override
			protected List<Restaurant> load() {
				return null;
			}
		};
		User u = getRestaurantWicketSession().getUser();
		
	}
}
