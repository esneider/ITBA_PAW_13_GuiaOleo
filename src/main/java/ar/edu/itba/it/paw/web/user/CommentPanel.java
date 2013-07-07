package ar.edu.itba.it.paw.web.user;

import java.util.ArrayList;
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

public class CommentPanel extends Panel {

	public CommentPanel(String id, final IModel<User> userModel,
			boolean showOverHead) {
		super(id);

		IModel<List<Rating>> listModel = new LoadableDetachableModel<List<Rating>>() {
			@Override
			protected List<Rating> load() {
				return new ArrayList<Rating>(userModel.getObject()
						.getComments());
			}

		};
		populatePage(listModel, showOverHead);
	}
	public CommentPanel(String id, final IModel<Restaurant> restaurantModel) {
		super(id);
		IModel<List<Rating>> listModel = new LoadableDetachableModel<List<Rating>>() {
			@Override
			protected List<Rating> load() {
				return restaurantModel.getObject().getRatings();
			}

		};
		populatePage(listModel, true);
	}

	private void populatePage(IModel<List<Rating>> listModel,
			final boolean showOverHead) {
		add(new PropertyListView<Rating>("comments", listModel) {

			@Override
			protected void populateItem(final ListItem<Rating> item) {
				item.setDefaultModel(new CompoundPropertyModel<Rating>(
						new EntityModel(Rating.class, item.getModelObject())));
				item.add(new Label("score"));
				item.add(new Label("comment"));
				item.add(new Link<User>("username", new EntityModel<User>(
						User.class, item.getModelObject().getUser())) {
					@Override
					public void onClick() {
						setResponsePage(new UserProfilePage(
								new EntityModel<User>(User.class, item
										.getModelObject().getUser())));
					}
				});
				item.add(new Label("date"));
				item.add(new Label("restaurantName", item.getModelObject()
						.getRestaurant().getName()));
				item.add(ImageProvider.getImage("img", item.getModelObject()
						.getUser()));
				item.add(new CommentOverHeadPanel("overhead",
						new EntityModel<Rating>(Rating.class, item
								.getModelObject()))
						.setVisible(RestaurantWicketSession.get().isSignedIn()
								&& showOverHead));
			}

		});

	}

}
