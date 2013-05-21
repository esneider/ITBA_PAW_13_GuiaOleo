package ar.edu.itba.it.paw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.edu.itba.it.paw.domain.foodtype.FoodType;
import ar.edu.itba.it.paw.domain.restaurant.RestaurantRepo;
import ar.edu.itba.it.paw.utils.EnhancedModelAndView;

@Controller
public class IndexController extends BaseController {

	private RestaurantRepo restRepo;
	
	@Autowired
	public IndexController(RestaurantRepo restRepo) {
		this.restRepo = restRepo;
	}

	@RequestMapping
	public EnhancedModelAndView search(
			@RequestParam(value = "query", required = false) String query) {
		
		EnhancedModelAndView mav = generateContext("List", true);
		mav.addObject("restaurantList",
				restRepo.getRestaurantsByQuery(query));
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
					mav.addObject("restaurantList", restRepo.getAll());
				} else if (query.equals("foodtypes")) {
					mav.addObject("restaurantList", ft.getRestaurants());
					mav.addObject("ftid", ft.getId());
				} else if (query.equals("bestrated")) {
					mav.addObject("restaurantList",
							restRepo.getBestRatedRestaurants(num));
				} else {
					mav.addObject("restaurantList", restRepo.getAll());
				}
			} else {
				mav.addObject("restaurantList",
						restRepo.getBestRatedRestaurants(10));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return mav;
	}

}
