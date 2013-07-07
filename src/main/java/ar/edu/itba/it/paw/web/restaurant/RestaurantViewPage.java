package ar.edu.itba.it.paw.web.restaurant;

import java.util.List;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.EntityModel;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.domain.restaurant.RestaurantRepo;
import ar.edu.itba.it.paw.domain.restaurant.RestaurantState;
import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.utils.Utils;
import ar.edu.itba.it.paw.web.base.SideBarPage;

public class RestaurantViewPage extends SideBarPage {

	private static final long serialVersionUID = 1094753744913503034L;
	@SpringBean
	private RestaurantRepo restRepo;

	@SuppressWarnings("serial")
	public RestaurantViewPage(final IModel<Restaurant> restaurantModel) {
		super(null);
		setDefaultModel(new CompoundPropertyModel<Restaurant>(restaurantModel));
		add(new Label("name"));
		add(new Label("address"));
		add(new Label("area"));
		add(new Label("telephone"));
		add(new Label("avgprice", new Model<String>("$"
				+ Utils.function((double) restaurantModel.getObject()
						.getAvgprice()))));
		add(new Label("timerange"));
		add(new Label("website"));
		add(new Label("avgScore"));

		add(new Label("ratingsAmmount", String.valueOf(restaurantModel
				.getObject().getRatingsAmmount())));
		add(new FoodTypesPanel("foodtypesPanel", restaurantModel));

		if (restaurantModel.getObject().getRegisterUser() != null) {
			add(new Label("registerUser", new Model<String>(restaurantModel
					.getObject().getRegisterUser().getName()
					+ " - "
					+ restaurantModel.getObject().getRegisterUser()
							.getUsername())));
		} else {
			add(new Label("registerUser", " - ")); // TODO REFACTOR
		}
		add(
				new WebMarkupContainer("googlemap").add(new AttributeAppender(
						"data-address", new Model<String>(restaurantModel
								.getObject().getAddress())))).add(
				new AttributeAppender("data-description", new Model<String>(
						restaurantModel.getObject().getName())));
		IModel<List<Restaurant>> listModel = new LoadableDetachableModel<List<Restaurant>>() {
			@Override
			protected List<Restaurant> load() {
				if (getRestaurantWicketSession().isSignedIn()) {
					return restRepo.getRecommendedRestaurants(
							restaurantModel.getObject(),
							getRestaurantWicketSession().getUser());
				} else {
					return restRepo.getRecommendedRestaurants(restaurantModel
							.getObject());
				}
			}
		};
		add(new PropertyListView<Restaurant>("recommended", listModel) {
			@Override
			protected void populateItem(final ListItem<Restaurant> item) {
				item.add(new Link<Restaurant>("link", item.getModel()) {
					@Override
					public void onClick() {
						System.out.println(item.getModel().getObject()
								.getName());
						setResponsePage(new RestaurantViewPage(
								new EntityModel<Restaurant>(Restaurant.class,
										item.getModelObject())));
					}
				});
				item.add(new Label("name", item.getModel()));

			}
		});
		add(new AdminPendingRequestsPanel("adminPendingResquests",
				restaurantModel) {
			@Override
			public boolean isVisible() {
				if (!getRestaurantWicketSession().isSignedIn())
					return false;
				User currentUser = getRestaurantWicketSession().getUser();
				return currentUser.isAdmin()
						&& restaurantModel.getObject().getState()
								.equals(RestaurantState.Pending);
			}
		});

	}
}
