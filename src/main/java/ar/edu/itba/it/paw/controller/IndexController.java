package ar.edu.itba.it.paw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.domain.FoodType;
import ar.edu.itba.it.paw.service.interfaces.RestaurantService;
import ar.edu.itba.it.paw.utils.EnhancedModelAndView;

@Controller
public class IndexController extends BaseController {

	private RestaurantService restService;

	@Autowired
	public IndexController(RestaurantService restService) {
		this.restService = restService;
	}

	@RequestMapping
	public EnhancedModelAndView search(
			@RequestParam(value = "query", required = false) String query) {
		
		EnhancedModelAndView mav = generateContext("List", true);
		mav.addObject("restaurantList",
				restService.getRestaurantsByQuery(query));
		mav.setViewName("index/list");
		mav.addObject("squery", query);
		return mav;

	}

	@RequestMapping
	public EnhancedModelAndView list(
			@RequestParam(value = "query", required = false) String query,
			@RequestParam(value = "id", required = false) FoodType ft,
			@RequestParam(value = "num", required = false) Integer num) {
		
		EnhancedModelAndView mav = generateContext("Guia Oleo Facha", true);
		
		try {
			if (query != null) {
				if (query.equals("all")) {
					mav.addObject("restaurantList", restService.getAll());
				} else if (query.equals("foodtypes")) {
					mav.addObject("restaurantList", restService
							.getRestaurantsByFoodType(ft));
				} else if (query.equals("bestrated")) {
					mav.addObject("restaurantList",
							restService.getBestRatedRestaurants(num));
				} else {
					mav.addObject("restaurantList", restService.getAll());
				}
			} else {
				mav.addObject("restaurantList",
						restService.getBestRatedRestaurants(10));
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
