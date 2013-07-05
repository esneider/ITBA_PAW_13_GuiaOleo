package ar.edu.itba.it.paw.web.base;

import java.util.List;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.foodtype.FoodType;
import ar.edu.itba.it.paw.domain.foodtype.FoodTypeRepo;

public class SideBarPage extends BasePage {
	
	@SpringBean
	private FoodTypeRepo ftRepo;
	
	public SideBarPage() {
	
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
				item.add(new Label("ammount"));
			}
		});
	
	}
	

	
}
