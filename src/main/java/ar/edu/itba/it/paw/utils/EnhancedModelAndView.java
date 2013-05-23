package ar.edu.itba.it.paw.utils;

import org.springframework.web.servlet.ModelAndView;

public class EnhancedModelAndView extends ModelAndView {
	
	public EnhancedModelAndView(String title) {

		super();
		this.addObject("documentTitle", title);
	}
	
}
