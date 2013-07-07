package ar.edu.itba.it.paw.web.user;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;

import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.web.base.NoSideBarPage;
import ar.edu.itba.it.paw.web.provider.ImageProvider;

public class UserProfilePage extends NoSideBarPage {

	public UserProfilePage(IModel<User> userModel) {
		setDefaultModel(new CompoundPropertyModel<User>(userModel));

		add(ImageProvider.getImage("profilePic", userModel.getObject()));

		add(new Label("username"));
		add(new Label("name"));
		add(new Label("surname"));
		add(new Label("registerDate"));
		add(new Label("email"));

		User currentUser = getRestaurantWicketSession().getUser();
		
		add(new AdminUserProfilePanel("adminPanel", userModel).setVisible(currentUser.isAdmin()
				&& !currentUser.equals(userModel.getObject())));

		add(new CommentPanel("commentPanel", userModel, true));
	}

}
