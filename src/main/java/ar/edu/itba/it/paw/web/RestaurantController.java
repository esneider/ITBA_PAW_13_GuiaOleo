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

	private RestaurantRepo restRepo;
	private RestaurantFormValidator rValidator;

	@Autowired
	public RestaurantController(RestaurantRepo restRepo,
			RestaurantFormValidator rValidator) {
		this.restRepo = restRepo;
		this.rValidator = rValidator;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView view(@RequestParam("id") Restaurant rest,
			HttpSession session) {
		if (rest == null)
			return indexContext();
		if (rest.getState().equals("Pending")
				&& (!isLoggedIn(session) || !getLoggedInUser(session).getType()
						.equals("Admin")))
			return indexContext();
		EnhancedModelAndView mav = generateContext("Simple Restaurant", true,
				true, "restaurant/view");
		mav.addObject("restaurant", rest);

		if (isLoggedIn(session)) {
			Rating rate = rest.getUserRating(getLoggedInUser(session));
			if (rate != null)
				mav.addObject("userComment", rate);
		}
		return mav;
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
		mav.addObject(new RestaurantForm());
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
		User actualUser = getLoggedInUser(session);
		Restaurant r = restaurantForm.build(actualUser, "Pending");

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
					true, true, "restaurant/view");
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

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView deleteComment(@RequestParam("ratingId") Rating rating,
			@RequestParam("restId") Restaurant rest, HttpSession session) {
		if (rest == null || rating == null)
			return indexContext();
		rest.removeRating(rating);
		restRepo.save(rest);
		return view(rest, session);
	}
}
