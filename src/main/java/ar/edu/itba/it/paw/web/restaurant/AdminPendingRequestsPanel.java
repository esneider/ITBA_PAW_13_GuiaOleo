package ar.edu.itba.it.paw.web.restaurant;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;

import ar.edu.itba.it.paw.domain.restaurant.Restaurant;

public class AdminPendingRequestsPanel extends Panel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2655489110845048370L;

	@SuppressWarnings("serial")
	public AdminPendingRequestsPanel(String id,
			final IModel<Restaurant> restaurantModel) {
		super(id);
		Form<Void> acceptForm = new Form<Void>("acceptForm") {
			@Override
			protected void onSubmit() {
				setResponsePage(new RestaurantListPage());
			}
		};

		acceptForm.add(new Button("accept", new ResourceModel("accept")));
		add(acceptForm);
		Form<Void> declineForm = new Form<Void>("declineForm") {
			@Override
			protected void onSubmit() {
				setResponsePage(new RestaurantListPage());
			}
		};
		declineForm.add(new Button("decline", new ResourceModel("decline")));
		add(declineForm);

	}

}
