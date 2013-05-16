package ar.edu.itba.it.paw.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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
	public ModelAndView list(@RequestParam(value="query", required=false) String query, @RequestParam(value="id", required=false) Integer id, @RequestParam(value="num", required=false) Integer num) {
		
		if (query == null) {
			//render(req, resp, "error.jsp", "404 NOT FOUND", false);
			error();
			return null;
		}

		ModelAndView mav = new ModelAndView();
		
		try {
			if (query.equals("all")) {
				mav.addObject("restaurantList", restService.getAll());
			} else if (query.equals("foodtypes")) {
				mav.addObject("restaurantList", restService
						.getRestaurantsByFoodType(ftService.getSingleFoodType(id)));
			} else if (query.equals("bestrated")) {
				mav.addObject("restaurantList",
						restService.getBestRatedRestaurants(num));
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
