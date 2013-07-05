package ar.edu.itba.it.paw.web;

import org.apache.wicket.spring.injection.annot.SpringBean;

import ar.edu.itba.it.paw.domain.foodtype.FoodTypeRepo;
import ar.edu.itba.it.paw.web.base.SideBarPage;

public class HomePage extends SideBarPage {

	@SpringBean
	private FoodTypeRepo ftRepo;
	
	public HomePage() {
		
	}	
}
