package ar.edu.itba.it.paw.web.restaurant;

import java.util.List;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.image.Image;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.resource.PackageResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.EntityModel;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.domain.restaurant.RestaurantRepo;
import ar.edu.itba.it.paw.domain.restaurant.RestaurantState;
import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.utils.Utils;
import ar.edu.itba.it.paw.web.application.RestaurantApplication;
import ar.edu.itba.it.paw.web.base.SideBarPage;
import ar.edu.itba.it.paw.web.common.HighlightedRestaurantLink;
import ar.edu.itba.it.paw.web.user.CommentPanel;

public class RestaurantViewPage extends SideBarPage {

	private static final long serialVersionUID = 1094753744913503034L;
	@SpringBean
	private RestaurantRepo restRepo;

	@SuppressWarnings("serial")
	public RestaurantViewPage(final IModel<Restaurant> restaurantModel) {
		super(null, false);
		setDefaultModel(new CompoundPropertyModel<Restaurant>(restaurantModel));
		restaurantModel.detach();
		restaurantModel.getObject().setNewAccess();

		/*
		 * Basic fields
		 */
		add(new Label("name"));
		add(new Image("imghigh", RestaurantApplication.HIGHLIGHTED_ICON)
				.setVisible(restaurantModel.getObject().isHighlighted()));
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

		add(new CommentPanel("commentPanel", restaurantModel));

		/*
		 * Adding Register user
		 */
		if (restaurantModel.getObject().getRegisterUser() != null) {
			add(new Label("registerUser", new Model<String>(restaurantModel
					.getObject().getRegisterUser().getName()
					+ " - "
					+ restaurantModel.getObject().getRegisterUser()
							.getUsername())));
		} else {
			add(new Label("registerUser", " - ")); // TODO REFACTOR
		}

		/*
		 * Google Maps
		 */
		add(new WebMarkupContainer("googlemap").add(
				new AttributeAppender("data-address", new Model<String>(
						restaurantModel.getObject().getAddress()))).add(
				new AttributeAppender("data-description", new Model<String>(
						restaurantModel.getObject().getName()))));

		/*
		 * Recommended restaurants
		 */
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

		add(new SimpleRestaurantListPanel("recommended", listModel));

		/*
		 * Access Restaurant ViewPage Count
		 */
		add(new Label("accessCount"));

		/*
		 * Pending requests
		 */
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

	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		response.renderJavaScriptReference("https://maps.google.com/maps/api/js?sensor=false");
		response.renderJavaScriptReference(new PackageResourceReference(
				RestaurantApplication.class, "maps.js"));
	}
}
