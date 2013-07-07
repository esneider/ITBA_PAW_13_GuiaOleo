package ar.edu.itba.it.paw.web.user;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.domain.user.UserRepo;
import ar.edu.itba.it.paw.web.base.NoSideBarPage;
import ar.edu.itba.it.paw.web.restaurant.RestaurantListPage;

public class RegisteredUsersPage extends NoSideBarPage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -651147582500585478L;
	@SpringBean
	private UserRepo userRepo;

	public RegisteredUsersPage() {
		super();
		if (!getRestaurantWicketSession().isSignedIn()
				|| !getRestaurantWicketSession().getUser(userRepo).isAdmin())
			setResponsePage(RestaurantListPage.class);
		@SuppressWarnings("serial")
		IModel<List<User>> listModel = new LoadableDetachableModel<List<User>>() {
			@Override
			protected List<User> load() {
				return userRepo.getAll();
			}
		};
		populatePage(listModel);

	}

	private void populatePage(IModel<List<User>> listModel) {
		add(new PropertyListView<User>("users", listModel) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(final ListItem<User> item) {
				item.add(new Link<User>("link", item.getModel()) {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick() {
						setResponsePage(new UserProfilePage(item.getModel()));
					}
				}.add(new Label("userName", item.getModel())));
				
			
				item.add(new Label("type"));
				item.add(new Label("email"));
				item.add(new Label("registerDate"));
			}
		});
	}
}
