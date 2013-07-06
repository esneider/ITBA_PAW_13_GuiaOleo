package ar.edu.itba.it.paw.web.user;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import ar.edu.itba.it.paw.domain.restaurant.Rating;
import ar.edu.itba.it.paw.domain.user.User;

public class CommentPanel extends Panel {

	public CommentPanel(String id, final IModel<User> userModel) {
		super(id);
		IModel<List<Rating>> listModel = new LoadableDetachableModel<List<Rating>>() {
			@Override
			protected List<Rating> load() {
				return new ArrayList<Rating>(userModel.getObject()
						.getComments());
			}
		};
		populatePanel(listModel);
	}

	private void populatePanel(IModel<List<Rating>> listModel) {
		add(new PropertyListView<Rating>("comments", listModel) {

			@Override
			protected void populateItem(ListItem<Rating> item) {
				item.setDefaultModel(new CompoundPropertyModel<Rating>(item
						.getModelObject()));
				item.add(new Label("score"));
				item.add(new Label("comment"));
				item.add(new Label("username", item.getModelObject().getUser()
						.getUsername()));
				item.add(new Label("date"));
				item.add(new Label("restaurantName", item.getModelObject()
						.getRestaurant().getName()));
			}

		});
	}

}
