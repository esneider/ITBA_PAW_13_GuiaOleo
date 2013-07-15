package ar.edu.itba.it.paw.web.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import ar.edu.itba.it.paw.domain.EntityModel;
import ar.edu.itba.it.paw.domain.restaurant.Rating;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.web.RestaurantWicketSession;
import ar.edu.itba.it.paw.web.provider.ImageProvider;
import ar.edu.itba.it.paw.web.restaurant.RestaurantViewPage;

public class CommentPanel extends Panel {

	private static final long serialVersionUID = -5151933145269591740L;

	public CommentPanel(String id, final IModel<User> userModel,
			boolean showOverHead) {
		super(id);

		IModel<List<Rating>> listModel = new LoadableDetachableModel<List<Rating>>() {

			private static final long serialVersionUID = 2751168865421199459L;

			@Override
			protected List<Rating> load() {
				return new ArrayList<Rating>(userModel.getObject()
						.getComments());
			}

		};
		populatePage(listModel, null, showOverHead);
	}

	public CommentPanel(String id, final IModel<Restaurant> restaurantModel) {
		super(id);
		setDefaultModel(restaurantModel);
		IModel<List<Rating>> listModel = new LoadableDetachableModel<List<Rating>>() {

			private static final long serialVersionUID = -1233934859283357630L;

			@Override
			protected List<Rating> load() {
				List<Rating> l = restaurantModel.getObject().getRatings();
				Collections.sort(l, new Comparator<Rating>() {
					@Override
					public int compare(Rating r1, Rating r2) {
						return r1.getLikedScore() - r2.getLikedScore();
					}
				});
				return restaurantModel.getObject().getRatings();
			}

		};
		populatePage(listModel, restaurantModel, true);
	}

	private void populatePage(IModel<List<Rating>> listModel,
			final IModel<Restaurant> restaurantModel, final boolean showOverHead) {
		add(new PropertyListView<Rating>("comments", listModel) {

			private static final long serialVersionUID = 4388837109742369283L;

			@Override
			protected void populateItem(final ListItem<Rating> item) {
				item.setDefaultModel(new CompoundPropertyModel<Rating>(
						new EntityModel<Rating>(Rating.class, item
								.getModelObject())));
				item.add(new Label("score"));
				item.add(new Label("comment"));

				final IModel<Restaurant> rModel = new EntityModel<Restaurant>(
						Restaurant.class, item.getModelObject().getRestaurant());
				item.add(new Link<User>("link2") {

					private static final long serialVersionUID = 4983575985141500243L;

					@Override
					public void onClick() {
						User u = item.getModelObject().getUser();
						setResponsePage(new UserProfilePage(
								new EntityModel<User>(User.class, u)));
					}
				}.add(new Label("user.username")));
				item.add(new Label("date"));

				item.add(new Link<User>("rlink") {

					private static final long serialVersionUID = 4983575985141500243L;

					@Override
					public void onClick() {
						Restaurant r = item.getModelObject().getRestaurant();
						setResponsePage(new RestaurantViewPage(
								new EntityModel<Restaurant>(Restaurant.class, r),
								false));
					}
				}.add(new Label("restaurant.name", item.getModelObject()
						.getRestaurant().getName())));

				// item.add(new Label("restaurantName", item.getModelObject()
				// .getRestaurant().getName()));
				item.add(ImageProvider.getImage("img", item.getModelObject()
						.getUser()));
				item.add(new CommentOverHeadPanel("overhead",
						new EntityModel<Rating>(Rating.class, item
								.getModelObject()), restaurantModel)
						.setVisible(RestaurantWicketSession.get().isSignedIn()
								&& showOverHead));
			}

		});

	}
}
