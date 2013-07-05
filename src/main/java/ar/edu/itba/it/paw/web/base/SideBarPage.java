package ar.edu.itba.it.paw.web.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.RefreshingView;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.EntityModel;
import ar.edu.itba.it.paw.domain.foodtype.FoodType;
import ar.edu.itba.it.paw.domain.foodtype.FoodTypeRepo;
import ar.edu.itba.it.paw.domain.restaurant.RestaurantRepo;
import ar.edu.itba.it.paw.web.HomePage;

@SuppressWarnings("serial")
public class SideBarPage extends BasePage {

	@SpringBean
	private FoodTypeRepo ftRepo;

	@SpringBean
	private RestaurantRepo restRepo;

	public SideBarPage(final FoodType selected) {

		int totalCant = restRepo.getAll().size();

		Link<Void> generalLink = new Link<Void>("linkAll") {
			@Override
			public void onClick() {
				setResponsePage(HomePage.class);
			}
		};
		generalLink.add(new Label("all", String.valueOf(totalCant)));
		generalLink.add(new Label("pluralizeAll", pluralizeItem("Restaurant",
				totalCant)));
		add(generalLink);

		if (selected == null) {
			generalLink.add(new AttributeAppender("class", new Model<String>(
					"active")));
		}

		add(new RefreshingView<FoodType>("foodtypes") {
			@Override
			protected Iterator<IModel<FoodType>> getItemModels() {
				List<IModel<FoodType>> result = new ArrayList<IModel<FoodType>>();
				for (FoodType ft : ftRepo.getAll()) {
					if (ft.getAmmount() > 0)
						result.add(new EntityModel<FoodType>(FoodType.class, ft));
				}
				return result.iterator();
			}

			@Override
			protected void populateItem(final Item<FoodType> item) {
				final FoodType ft = item.getModelObject();
				Link<FoodType> link = new Link<FoodType>("foodtype",
						item.getModel()) {
					@Override
					public void onClick() {
						setResponsePage(new HomePage(item.getModelObject()));
					}
				};
				link.add(new Label("name", item.getModel()));
				link.add(new Label("ammount",
						new AbstractReadOnlyModel<Integer>() {
							@Override
							public Integer getObject() {
								return ft.getAmmount();
							}
						}));
				link.add(new Label("pluralizeRestaurants", pluralizeItem(
						"Restaurant", ft.getAmmount())));
				item.add(link);
				if (item.getModelObject().equals(selected)) {
					item.add(new AttributeAppender("class", new Model<String>(
							"active")));
				}
			}
		});

	}
	private String pluralizeItem(String item, int ammount) {
		if (ammount > 1)
			return item + "s";
		else
			return item;
	}

}
