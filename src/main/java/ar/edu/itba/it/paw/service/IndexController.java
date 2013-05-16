package ar.edu.itba.it.paw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.utils.EnhancedModelAndView;

@Controller
public class IndexController {

	FoodTypeService ftService;
	RestaurantService restService;
	
	@Autowired
	public IndexController(FoodTypeService ftService, RestaurantService restService) {
		this.ftService = ftService;
		this.restService = restService;
	}
	
	@RequestMapping
	public EnhancedModelAndView list(@RequestParam(value="query", required=false) String query, @RequestParam(value="id", required=false) Integer id, @RequestParam(value="num", required=false) Integer num) {

		EnhancedModelAndView mav = new EnhancedModelAndView("Guia Oleo Facha");
		
		try {
			if (query != null) {
				if (query.equals("all")) {
					mav.addObject("restaurantList", restService.getAll());
				} else if (query.equals("foodtypes")) {
					mav.addObject("restaurantList", restService
							.getRestaurantsByFoodType(ftService.getSingleFoodType(id)));
				} else if (query.equals("bestrated")) {
					mav.addObject("restaurantList",
							restService.getBestRatedRestaurants(num));
				} else {
					mav.addObject("restaurantList", restService.getAll());
				}
			} else {
				mav.addObject("restaurantList", restService.getAll());
			}
		} catch (Exception e) {
			error();
			return null;
		}
		return mav;
	}
	
	@RequestMapping
	public ModelAndView error() {
		return null;
	}
	
}
