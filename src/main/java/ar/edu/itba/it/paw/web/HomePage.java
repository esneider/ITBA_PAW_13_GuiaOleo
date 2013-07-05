package ar.edu.itba.it.paw.web;

import ar.edu.itba.it.paw.domain.foodtype.FoodType;
import ar.edu.itba.it.paw.web.base.SideBarPage;

public class HomePage extends SideBarPage {
	
	private static final long serialVersionUID = -8108587764148338460L;

	public HomePage() {
		super(null);
	}
	
	public HomePage(final FoodType selected) {
		super(selected);
	}
}