package ar.edu.itba.it.paw.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.domain.Rating;
import ar.edu.itba.it.paw.domain.Restaurant;
import ar.edu.itba.it.paw.domain.User;
import ar.edu.itba.it.paw.service.interfaces.RestaurantService;
import ar.edu.itba.it.paw.service.interfaces.UserService;
import ar.edu.itba.it.paw.utils.EnhancedModelAndView;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController extends BaseController {
	
	private RestaurantService restService;
	
	@Autowired
	public RestaurantController(RestaurantService restService, UserService userService) {
		this.restService = restService;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView view(@RequestParam("id") Restaurant rest, HttpSession session) {
		
		EnhancedModelAndView mav = generateContext("Simple Restaurant", true);
		if (rest != null) {
			mav.addObject("restaurant", rest);
		    mav.addObject("commentList", restService.getRatingsByRestaurant(rest));
		    
		    if (isLoggedIn(session)) {
				Rating rate = restService.getSingleRating(getLoggedInUser(session), rest);
			    if (rate != null)
					mav.addObject("userComment", rate);
		    }
		}
		
		
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView view(@RequestParam("id") Restaurant rest, 
			@RequestParam(value = "restaurant_rating") Integer rating,
			@RequestParam(value = "comment") String comment,
			HttpSession session) {
	
		if (rest != null) {
			User user = getLoggedInUser(session);
			try {
				if (restService.getSingleRating(user, rest) == null)
					restService.insertRating(rating, comment, user, rest);
				return view(rest, session);
			} catch (Exception e) {
				return view(rest, session);
			}
		} else {
			return null;
		}
	}
}
