package ar.edu.itba.it.paw.web;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.domain.restaurant.Rating;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.utils.EnhancedModelAndView;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController extends BaseController {
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView view(@RequestParam("id") Restaurant rest, HttpSession session) {
		
		EnhancedModelAndView mav = generateContext("Simple Restaurant", true);
		if (rest != null) {
			mav.addObject("restaurant", rest);
		    mav.addObject("commentList", rest.getRatings());
		    
		    if (isLoggedIn(session)) {
				Rating rate = rest.getUserRating(getLoggedInUser(session));
			    if (rate != null)
					mav.addObject("userComment", rate);
		    }
		    return mav;
		} else {
			return indexContext();
		}
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView view(@RequestParam("id") Restaurant rest, 
			@RequestParam(value = "restaurant_rating") Integer rating,
			@RequestParam(value = "comment") String comment,
			HttpSession session) {
	
		if (rest != null) {
			User user = getLoggedInUser(session);
			try {
				Rating r = new Rating(rating, comment, user, rest, new Date());
				rest.addRating(r);
				return view(rest, session);
			} catch (Exception e) {
				return view(rest, session);
			}
		} else {
			return null;
		}
	}
}
