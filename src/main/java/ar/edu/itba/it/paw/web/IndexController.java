package ar.edu.itba.it.paw.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ar.edu.itba.it.paw.domain.foodtype.FoodType;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.domain.restaurant.RestaurantRepo;
import ar.edu.itba.it.paw.utils.EnhancedModelAndView;

@Controller
public class IndexController extends BaseController {

	private RestaurantRepo restRepo;

	@Autowired
	public IndexController(RestaurantRepo restRepo) {
		this.restRepo = restRepo;
	}

	@RequestMapping(value = { "/pending", "/index/pending" })
	public EnhancedModelAndView pending(HttpSession session) {

		if (!isLoggedIn(session) || !getLoggedInUser(session).isAdmin())
			return indexContext();

		EnhancedModelAndView mav = generateContext("List", true, true);
		mav.addObject("restaurantList", restRepo.getPendingRestaurants());
		return mav;
	}

	@RequestMapping(value = { "/search", "/index/search" })
	public EnhancedModelAndView search(
			@RequestParam(value = "query", required = true) String query) {

		EnhancedModelAndView mav = generateContext("List", true, true);
		List<Restaurant> r = restRepo.getRestaurantsByQuery(query);
		mav.addObject("restaurantList", r);
		mav.setViewName("index/list");
		mav.addObject("squery", query);
		return mav;
	}

	@RequestMapping(value = { "/", "/index", "/index/list" })
	public EnhancedModelAndView list(
			@RequestParam(value = "query", required = false) String query,
			@RequestParam(value = "id", required = false) FoodType ft,
			@RequestParam(value = "num", required = false) Integer num) {

		EnhancedModelAndView mav = generateContext("Guia Oleo Facha", true,
				true);

		if (query == null) {
			query = "bestrated";
			num = 10;
		}

		if (query.equals("foodtypes")) {

			mav.addObject("restaurantList", ft.getRestaurants());
			mav.addObject("ftid", ft.getId());

		} else if (query.equals("bestrated")) {
			mav.addObject("restaurantList",
					restRepo.getBestRatedRestaurants(num));

		} else {

			mav.addObject("restaurantList", restRepo.getAll());
			mav.addObject("tab_all", true);
		}

		mav.setViewName("index/list");
		return mav;
	}
}
