package ar.edu.itba.it.paw.web.restaurant;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.ResourceModel;

import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.web.RestaurantWicketSession;

@SuppressWarnings("serial")
public class HighlightActionsPanel extends Panel {
	public HighlightActionsPanel(String id,
			final IModel<Restaurant> restaurantModel) {
		super(id);
		setVisible(RestaurantWicketSession.get().isSignedIn()
				&& RestaurantWicketSession.get().getUser().isAdmin());
		boolean state = restaurantModel.getObject().isHighlighted();

		Form<Void> highlightForm = new Form<Void>("highlightForm") {
			@Override
			protected void onSubmit() {
				Restaurant r = restaurantModel.getObject();
				r.highlight();
				setResponsePage(new RestaurantListPage());

			}
		};
		highlightForm.add(new Button("highlight",
				new ResourceModel("highlight")));
		add(highlightForm);

		Form<Void> unhighlightForm = new Form<Void>("unhighlightForm") {
			@Override
			protected void onSubmit() {
				Restaurant r = restaurantModel.getObject();
				r.unhighlight();
				setResponsePage(new RestaurantListPage());

			}
		};
		unhighlightForm.add(new Button("unhighlight", new ResourceModel(
				"unhighlight")));
		add(unhighlightForm);
		unhighlightForm.setVisible(state);
		highlightForm.setVisible(!state);

	}
}
