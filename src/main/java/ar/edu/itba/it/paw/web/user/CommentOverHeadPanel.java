package ar.edu.itba.it.paw.web.user;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.restaurant.Rating;
import ar.edu.itba.it.paw.domain.user.UserRepo;
import ar.edu.itba.it.paw.web.RestaurantWicketSession;

public class CommentOverHeadPanel extends Panel {

	@SpringBean
	private UserRepo userRepo;

	public CommentOverHeadPanel(String id, final IModel<Rating> ratingModel) {
		super(id);

		Link<Void> likeLink = new Link<Void>("like") {
			@Override
			public void onClick() {
				RestaurantWicketSession.get().getUser(userRepo)
						.like(ratingModel.getObject());
				setResponsePage(getPage());
			}
		};
		likeLink.add(new Label("ammount", String.valueOf(ratingModel
				.getObject().getLikeAmmount())));

		Link<Void> unLikeLink = new Link<Void>("unlike") {
			@Override
			public void onClick() {
				RestaurantWicketSession.get().getUser(userRepo)
						.unlike(ratingModel.getObject());
				setResponsePage(getPage());
			}
		};
		unLikeLink.add(new Label("ammount", String.valueOf(ratingModel
				.getObject().getUnlikeAmmount())));

		add(likeLink);
		add(unLikeLink);

	}

}
