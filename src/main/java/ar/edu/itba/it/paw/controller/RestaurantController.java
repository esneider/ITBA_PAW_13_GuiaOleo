package ar.edu.itba.it.paw.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.model.Rating;
import ar.edu.itba.it.paw.model.Restaurant;
import ar.edu.itba.it.paw.model.User;
import ar.edu.itba.it.paw.service.RestaurantService;
import ar.edu.itba.it.paw.service.UserService;
import ar.edu.itba.it.paw.utils.EnhancedModelAndView;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController extends BaseController {
	
	private RestaurantService restService;
	private UserService userService;
	
	@Autowired
	public RestaurantController(RestaurantService restService, UserService userService) {
		this.restService = restService;
		this.userService = userService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView view(@RequestParam(value = "id") Integer id, HttpSession session) {
		
		Restaurant rest = restService.getSingleRestaurant(id);

		EnhancedModelAndView mav = new EnhancedModelAndView("Simple Restaurant");
		if (rest != null) {
			mav.addObject("restaurant", rest);
		
		    mav.addObject("commentList", restService
					.getRatingsByRestaurant(restService.getSingleRestaurant(id)));
		    
		    addContextVariables(mav, true, "../index/");
		    
		    if (isLoggedIn(session)) {
				Rating rate = restService.getSingleRating(getLoggedInUser(session), rest);
			    if (rate != null)
					mav.addObject("userComment", rate);
		    }
		}
		
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView view(@RequestParam(value = "id") Integer id, 
			@RequestParam(value = "restaurant_rating") Integer rating,
			@RequestParam(value = "comment") String comment,
			HttpSession session) {
	
		if (id != null) {
			try {
				User user = getLoggedInUser(session);
				Restaurant rest = restService.getSingleRestaurant(id);
				if (restService.getSingleRating(user, rest) == null)
					restService.insertRating(rating, comment, user, rest);
				return view(id, session);
			} catch (Exception e) {
				return view(id, session);
			}
		} else {
			return view(id, session);
		}
	}
}
