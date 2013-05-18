package ar.edu.itba.it.paw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.service.FoodTypeService;
import ar.edu.itba.it.paw.service.RestaurantService;
import ar.edu.itba.it.paw.utils.EnhancedModelAndView;

@Controller
public class IndexController extends BaseController {

	FoodTypeService ftService;
	RestaurantService restService;

	@Autowired
	public IndexController(FoodTypeService ftService,
			RestaurantService restService) {
		this.ftService = ftService;
		this.restService = restService;
	}

	@RequestMapping
	public EnhancedModelAndView search(
			@RequestParam(value = "query", required = false) String query) {
		
		EnhancedModelAndView mav = new EnhancedModelAndView("list");
		mav.addObject("restaurantList",
				restService.getRestaurantsByQuery(query));
		mav.setViewName("index/list");
		addContextVariables(mav, true);
		mav.addObject("squery", query);
		return mav;

	}

	@RequestMapping
	public EnhancedModelAndView list(
			@RequestParam(value = "query", required = false) String query,
			@RequestParam(value = "id", required = false) Integer id,
			@RequestParam(value = "num", required = false) Integer num) {
		
		EnhancedModelAndView mav = new EnhancedModelAndView("Guia Oleo Facha");
		
		addContextVariables(mav, true);
		
		try {
			if (query != null) {
				if (query.equals("all")) {
					mav.addObject("restaurantList", restService.getAll());
				} else if (query.equals("foodtypes")) {
					mav.addObject("restaurantList", restService
							.getRestaurantsByFoodType(ftService
									.getSingleFoodType(id)));
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
