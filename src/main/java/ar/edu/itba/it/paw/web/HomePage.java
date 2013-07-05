package ar.edu.itba.it.paw.web;

import org.apache.wicket.model.IModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.foodtype.FoodType;
import ar.edu.itba.it.paw.domain.foodtype.FoodTypeRepo;
import ar.edu.itba.it.paw.web.base.SideBarPage;

public class HomePage extends SideBarPage {

	@SpringBean
	private FoodTypeRepo ftRepo;
	
	public HomePage() {
		super(null);
	}
	
	public HomePage(final FoodType selected) {
		super(selected);
	}	
}
