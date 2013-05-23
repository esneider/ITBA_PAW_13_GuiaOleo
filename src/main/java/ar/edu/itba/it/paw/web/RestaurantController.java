package ar.edu.itba.it.paw.web;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import ar.edu.itba.it.paw.domain.foodtype.FoodTypeRepo;
import ar.edu.itba.it.paw.domain.restaurant.Rating;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.domain.restaurant.RestaurantRepo;
import ar.edu.itba.it.paw.domain.user.User;
import ar.edu.itba.it.paw.utils.EnhancedModelAndView;
import ar.edu.itba.it.paw.web.command.RestaurantForm;
import ar.edu.itba.it.paw.web.command.validator.RestaurantFormValidator;

@Controller
@RequestMapping("/restaurant")
public class RestaurantController extends BaseController {

	private FoodTypeRepo ftRepo;
	private RestaurantRepo restRepo;
	private RestaurantFormValidator rValidator;

	@Autowired
	public RestaurantController(FoodTypeRepo ftRepo, RestaurantRepo restRepo,
			RestaurantFormValidator rValidator) {
		this.ftRepo = ftRepo;
		this.restRepo = restRepo;
		this.rValidator = rValidator;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView view(@RequestParam("id") Restaurant rest,
			HttpSession session) {

		EnhancedModelAndView mav = generateContext("Simple Restaurant", true,
				true);
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
			@RequestParam(value = "comment") String comment, HttpSession session) {

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

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView add(HttpSession session) {
		if (!isLoggedIn(session))
			return indexContext();
		EnhancedModelAndView mav = generateContext("Add Restaurant", true, true);
		mav.addObject(new RestaurantForm(ftRepo));
		return mav;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView add(RestaurantForm restaurantForm, Errors errors,
			HttpSession session) {
		if (!isLoggedIn(session))
			return indexContext();
		rValidator.validate(restaurantForm, errors);
		if (errors.hasErrors())
			return add(session);
		Restaurant r = restaurantForm
				.build(getLoggedInUser(session), "Pending");
		restRepo.save(r);
		return indexContext();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView publish(@RequestParam("id") Restaurant rest,
			HttpSession session) {
		if (!isLoggedIn(session) && !getLoggedInUser(session).isAdmin())
			return indexContext();
		if (rest != null) {
			EnhancedModelAndView mav = generateContext("Publish Restaurant",
					true, true);
			mav.addObject("restaurant", rest);
			return mav;
		}

		return indexContext();

	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView publish(@RequestParam("action") String action,
			@RequestParam("id") Restaurant r, HttpSession session) {
		if (action.equals("accept")) {
			r.setState("Accepted");
			System.out.println("Aceptado");
		} else if (action.equals("decline")) {
			System.out.println("Declinado");
			r.setState("Rejected");
		}
		restRepo.save(r);
		
		return indexContext();
	}
}
