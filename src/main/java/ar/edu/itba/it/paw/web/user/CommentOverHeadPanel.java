package ar.edu.itba.it.paw.web.user;

import java.io.Serializable;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.StringResourceModel;

import ar.edu.itba.it.paw.domain.restaurant.Rating;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.web.RestaurantWicketSession;

public class CommentOverHeadPanel extends Panel {

	private static final long serialVersionUID = 5900994724722752325L;

	public CommentOverHeadPanel(String id, final IModel<Rating> ratingModel,
			final IModel<Restaurant> restaurantModel) {
		super(id);

		setDefaultModel(ratingModel);

		Link<Void> likeLink = new Link<Void>("like") {

			private static final long serialVersionUID = 3417638833309559759L;

			@Override
			public void onClick() {
				RestaurantWicketSession.get().getUser()
						.like(ratingModel.getObject());
			}
		};
		likeLink.add(new Label("useful", new StringResourceModel("useful",
				new Model<Serializable>(ratingModel.getObject()
						.getLikeAmmount()))));

		Link<Void> unLikeLink = new Link<Void>("unlike") {

			private static final long serialVersionUID = -2700162788168005521L;

			@Override
			public void onClick() {
				RestaurantWicketSession.get().getUser()
						.unlike(ratingModel.getObject());
			}
		};
		unLikeLink.add(new Label("notUseful", new StringResourceModel(
				"notUseful", new Model<Serializable>(ratingModel.getObject()
						.getUnlikeAmmount()))));

		add(likeLink);
		add(unLikeLink);

		add(new Form<CommentOverHeadPanel>("deleteForm") {

			private static final long serialVersionUID = -7731089894402937799L;

			@Override
			protected void onSubmit() {
				restaurantModel.getObject().removeRating(
						ratingModel.getObject());
			}
		}.setVisible(restaurantModel != null
				&& RestaurantWicketSession.get().isSignedIn()
				&& RestaurantWicketSession.get().getUser().isAdmin()));

	}

}
