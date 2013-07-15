package ar.edu.itba.it.paw.web.restaurant;

import java.util.Date;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.ResourceModel;

import ar.edu.itba.it.paw.domain.restaurant.Rating;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.utils.Utils;
import ar.edu.itba.it.paw.web.RestaurantWicketSession;
import ar.edu.itba.it.paw.web.auth.LoginRegisterPage;

public class CommentRestaurantPanel extends Panel {

	private static final long serialVersionUID = 6304294251193681168L;

	private transient String text;

	private transient Integer group;

	public CommentRestaurantPanel(String id,
			final IModel<Restaurant> restaurantModel) {

		super(id);

		Form<CommentRestaurantPanel> form = new Form<CommentRestaurantPanel>(
				"commentForm",
				new CompoundPropertyModel<CommentRestaurantPanel>(this)) {

			private static final long serialVersionUID = -9039667871817899106L;

			@Override
			protected void onSubmit() {

				RestaurantWicketSession session = RestaurantWicketSession.get();
				User user = session.getUser();
				Restaurant rest = restaurantModel.getObject();
				Rating r = new Rating(group, Utils.normalizeString(text), user,
						rest, new Date());
				rest.addRating(r);
			}
		};

		form.add(new TextArea<String>("text"));
		form.add(new Button("send", new ResourceModel("Send")));

		RadioGroup<Integer> group = new RadioGroup<Integer>("group");
		form.add(group);

		group.add(new Radio<Integer>("0", new Model<Integer>(0)));
		group.add(new Radio<Integer>("1", new Model<Integer>(1)));
		group.add(new Radio<Integer>("2", new Model<Integer>(2)));
		group.add(new Radio<Integer>("3", new Model<Integer>(3)));
		group.add(new Radio<Integer>("4", new Model<Integer>(4)));
		group.add(new Radio<Integer>("5", new Model<Integer>(5)));

		RestaurantWicketSession session = RestaurantWicketSession.get();

		boolean signed = session.isSignedIn();
		boolean rated = false;

		if (signed) {
			User user = session.getUser();
			Rating rate = restaurantModel.getObject().getUserRating(user);
			rated = rate != null;
		}

		WebMarkupContainer comment = new WebMarkupContainer("commentField");
		comment.add(form);
		comment.setVisible(signed && !rated);
		add(comment);

		WebMarkupContainer alreadyCommented = new WebMarkupContainer(
				"alreadyCommentedField");
		alreadyCommented.setVisible(rated);
		add(alreadyCommented);

		WebMarkupContainer login = new WebMarkupContainer("loginField");
		login.add(new Link<Void>("login") {

			private static final long serialVersionUID = -7894998777410479084L;

			@Override
			public void onClick() {
				setResponsePage(LoginRegisterPage.class);
			}
		});
		login.setVisible(!signed);
		add(login);
	}
}
