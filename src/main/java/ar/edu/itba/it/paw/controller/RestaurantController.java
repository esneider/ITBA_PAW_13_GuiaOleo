package ar.edu.itba.it.paw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.model.Restaurant;
import ar.edu.itba.it.paw.service.RatingService;
import ar.edu.itba.it.paw.service.RestaurantService;
import ar.edu.itba.it.paw.utils.EnhancedModelAndView;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController {
	
	private RestaurantService restService;
	
	@Autowired
	public RestaurantController(RestaurantService restService) {
		this.restService = restService;
	}
	
	@RequestMapping
	public ModelAndView view(@RequestParam(value = "id") Integer id) {
	
		Restaurant rest = restService.getSingleRestaurant(id);

		EnhancedModelAndView mav = new EnhancedModelAndView("Simple Restaurant");
		if (rest != null) {
			mav.addObject("restaurant", rest);
		
		    mav.addObject("commentList", RatingService.getInstance()
					.getRatingsByRestaurant(restService.getSingleRestaurant(id)));

			/*if (isLoggedIn(req)) {
				Rating r = RatingService.getInstance().getSingleRating(
						getLoggedInUser(req), RestaurantService.getInstance().getSingleRestaurant(id));
				if (r != null)
					req.setAttribute("userComment", r);
			}*/
		}
		return mav;
	}
}
