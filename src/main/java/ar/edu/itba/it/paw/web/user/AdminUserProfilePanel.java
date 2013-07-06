package ar.edu.itba.it.paw.web.user;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.domain.user.UserType;

public class AdminUserProfilePanel extends Panel {

	public AdminUserProfilePanel(String id, final IModel<User> currentUserModel) {
		super(id);
		final boolean isAdmin = currentUserModel.getObject().isAdmin();
		Form<Void> form = new Form<Void>("setAdminForm") {
			@Override
			protected void onSubmit() {
				if (isAdmin) {
					currentUserModel.getObject().setType(UserType.Normal);
				} else {
					currentUserModel.getObject().setType(UserType.Admin);
				}
				setResponsePage(new UserProfilePage(currentUserModel));
			}
		};
		form.add(new Button("submit", isAdmin ? new Model<String>(
				"Unset as admin") : new Model<String>("Set as admin")));
		add(form);
	}

}
