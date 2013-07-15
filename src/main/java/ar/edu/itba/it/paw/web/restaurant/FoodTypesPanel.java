package ar.edu.itba.it.paw.web.restaurant;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;

import ar.edu.itba.it.paw.domain.foodtype.FoodType;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;

public class FoodTypesPanel extends Panel{

	private static final long serialVersionUID = 2655489110845048370L;

	public FoodTypesPanel(String id, final IModel<Restaurant> restaurantModel) {
		super(id);
		IModel<List<FoodType>> foodTypesModel = new LoadableDetachableModel<List<FoodType>>() {

			private static final long serialVersionUID = 1735848154421008619L;

			@Override
			protected List<FoodType> load() {
				return new ArrayList<FoodType>(restaurantModel.getObject()
						.getFoodtypes());
			}
		};

		add(new PropertyListView<FoodType>("foodtype", foodTypesModel) {

			private static final long serialVersionUID = 1034176867036916220L;

			@Override
			protected void populateItem(ListItem<FoodType> item) {
				item.add(new Label("name"));
			}
		});

	}
	
}
