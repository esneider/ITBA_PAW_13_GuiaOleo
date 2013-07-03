package ar.edu.itba.it.paw.web;

import java.util.List;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.PropertyListView;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.foodtype.FoodType;
import ar.edu.itba.it.paw.domain.foodtype.FoodTypeRepo;

public class HomePage extends WebPage {

	@SpringBean
	private FoodTypeRepo ftRepo;
	
	@SuppressWarnings("serial")
	public HomePage() {
		System.out.println(ftRepo);
		add(new PropertyListView<FoodType>("foodtypes", new LoadableDetachableModel<List<FoodType>>(){
			@Override
			protected List<FoodType> load() {
				return ftRepo.getAll();
			}		
		}) {

			@Override
			protected void populateItem(ListItem<FoodType> lift) {
				add(new Label("foodtype", lift.getModelObject().getName()));
			}
			
		});
		//<a href="" wicket:id="foodtype"></a>
		// add(new Label("message", "HOLA MUNDO"));
	}
	
}
