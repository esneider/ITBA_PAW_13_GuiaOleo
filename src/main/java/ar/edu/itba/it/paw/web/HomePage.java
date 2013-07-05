package ar.edu.itba.it.paw.web;

import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.EntityModel;
import ar.edu.itba.it.paw.domain.foodtype.FoodType;
import ar.edu.itba.it.paw.domain.foodtype.FoodTypeRepo;

public class HomePage extends WebPage {

	@SpringBean
	private FoodTypeRepo ftRepo;
	
	public HomePage() {

		IModel<List<FoodType>> foodTypesModel = new LoadableDetachableModel<List<FoodType>>() {
			@Override
			protected List<FoodType> load() {
				List<FoodType> lstFt = ftRepo.getAll();
				return lstFt; 
			}
		};
		add(new PropertyListView<FoodType>("foodtype", foodTypesModel) {
			@Override
			protected void populateItem(ListItem<FoodType> item) {
				item.add(new Label("name"));
//				item.add(new Label("ammount"));
			}
		});
		

	}
	
	
//	public class BasePage extends WebPage {
//
//		@SpringBean
//		private FoodTypeRepo foodtypes;
//		
//		public BasePage() {
//			WicketSession session = WicketSession.get();
//			if (session.getAttribute("userId") == null) {
//				add(new LoginPanel("headerPanel"));
//			} else {
//				add(new LogoutPanel("headerPanel", session.getAttribute("userId").toString()));
//			}
//			add(new RefreshingView<FoodType>("foodtype_li") {
//				@Override
//				protected Iterator<IModel<FoodType>> getItemModels() {
//					List<IModel<FoodType>> result = new ArrayList<IModel<FoodType>>();
//					for (FoodType food : foodtypes.getAll()) {
//						result.add(new EntityModel<FoodType>(FoodType.class, food));
//					}
//					return result.iterator();
//				}
//
//				@Override
//				protected void populateItem(Item<FoodType> item) {
//					BookmarkablePageLink<Void> link = new BookmarkablePageLink<Void>("foodtype_page", LoginPage.class);
//					link.add(new Label("foodtype", new PropertyModel<String>(item.getModel(), "type")));
//					String restaurant_per_food = "(" + item.getModelObject().getRestaurants().size() + ")";
//					link.add(new Label("restaurant_per_food", restaurant_per_food));
//					item.add(link);
//				}
//			});
//		}
//	}
	
}
