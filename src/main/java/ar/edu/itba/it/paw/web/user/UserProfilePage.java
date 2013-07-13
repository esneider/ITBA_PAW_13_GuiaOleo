package ar.edu.itba.it.paw.web.user;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.web.base.NoSideBarPage;
import ar.edu.itba.it.paw.web.provider.ImageProvider;
import ar.edu.itba.it.paw.web.restaurant.SimpleRestaurantListPanel;

public class UserProfilePage extends NoSideBarPage {

	private static final long serialVersionUID = -5254610654248907904L;

	public UserProfilePage(final IModel<User> userModel) {
		super(false);
		setDefaultModel(new CompoundPropertyModel<User>(userModel));

		add(ImageProvider.getImage("profilePic", userModel.getObject()));

		add(new Label("username"));
		add(new Label("name"));
		add(new Label("surname"));
		add(new Label("registerDate"));
		add(new Label("email"));

		User currentUser = getRestaurantWicketSession().getUser();

		add(new AdminUserProfilePanel("adminPanel", userModel)
				.setVisible(getRestaurantWicketSession().isSignedIn()
						&& currentUser.isAdmin()
						&& !currentUser.equals(userModel.getObject())));

		add(new CommentPanel("commentPanel", userModel, false));

		IModel<List<Restaurant>> listModel = new LoadableDetachableModel<List<Restaurant>>() {

			private static final long serialVersionUID = -5575909931092985382L;

			@Override
			protected List<Restaurant> load() {
				List<Restaurant> registered = new ArrayList<Restaurant>();
				for (Restaurant r : userModel.getObject()
						.getRegisteredRestaurants()) {
					if (r.isAccepted())
						registered.add(r);
				}
				return registered;
			}

		};

		add(new SimpleRestaurantListPanel("registeredRestaurants", listModel));
	}

}
