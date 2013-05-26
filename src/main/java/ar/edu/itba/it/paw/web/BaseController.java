package ar.edu.itba.it.paw.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import ar.edu.itba.it.paw.domain.foodtype.FoodTypeRepo;
import ar.edu.itba.it.paw.domain.picture.PictureRepo;
import ar.edu.itba.it.paw.domain.restaurant.RestaurantRepo;
import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.utils.EnhancedModelAndView;

@Controller
public abstract class BaseController {

	@Autowired
	private PictureRepo pictureRepo;
	
	@Autowired
	private FoodTypeRepo ftRepo;
	
	@Autowired
	private RestaurantRepo restRepo;

	public boolean isLoggedIn(HttpSession session) {

		return session.getAttribute("user") != null;
	}

	public User getLoggedInUser(HttpSession session) {
		
		if (!isLoggedIn(session)) {
			return null;
		}
		
		return (User)session.getAttribute("user");
	}
	
	public void setLoggedInUser(HttpSession session, User user) {

		if (user != null) {
			if (user.getAvatar() != null)
				user.setAvatar(pictureRepo.getPictureById(user.getAvatar().getId()));
		}
		session.setAttribute("user", user);
	}
	
	public void logoutUser(HttpSession session) {

		session.invalidate();
	}

	public EnhancedModelAndView generateContext(String title, boolean sidebar, boolean setFoodTypes) {

		EnhancedModelAndView mav = new EnhancedModelAndView(title);

		mav.addObject("sidebar", sidebar);
		mav.addObject("numberOfRestaurants", restRepo.getAll().size());
		
		if (setFoodTypes) {
			mav.addObject("foodTypesList", ftRepo.getAll());
		}

		return mav;
	}
	
	public EnhancedModelAndView generateContext(String title, boolean sidebar, boolean setFoodTypes, String viewName) {

		EnhancedModelAndView mav = generateContext(title, sidebar, setFoodTypes);
		mav.setViewName(viewName);
		return mav;
	}
	
	public EnhancedModelAndView indexContext() {

		EnhancedModelAndView mav = new EnhancedModelAndView("Guia Oleo Facha");
		mav.setViewName("redirect:/bin/index/list");
		return mav;
	}
}
