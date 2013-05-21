package ar.edu.itba.it.paw.service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.itba.it.paw.dao.interfaces.RatingsDAO;
import ar.edu.itba.it.paw.dao.interfaces.RestaurantDAO;
import ar.edu.itba.it.paw.domain.FoodType;
import ar.edu.itba.it.paw.domain.Rating;
import ar.edu.itba.it.paw.domain.User;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.service.interfaces.RestaurantService;

@Service
public class RestaurantServiceImpl implements RestaurantService {
	
	private RestaurantDAO DAO;
	private RatingsDAO rtDAO;

	@Autowired
	public RestaurantServiceImpl(RestaurantDAO DAO, RatingsDAO rtDAO) {
		this.DAO = DAO;
		this.rtDAO = rtDAO;
	}
	
	public List<Restaurant> getBestRatedRestaurants(int cant) {

		return DAO.getBestRatedRestaurants(cant);
	}
	
	public List<Restaurant> getAll() {
		return DAO.getAll();
	}
	
	public List<Restaurant> getRestaurantsByFoodType(FoodType ft) {
		return DAO.getRestaurantsByFoodtype(ft);
	}
	
	public Restaurant getSingleRestaurant(int id) {
		return DAO.getSingleRestaurant(id);
	}
	
	public void save(Restaurant r) {
		DAO.save(r);
	}
	
	public List<Restaurant> getRestaurantsByQuery(String query) {
		Set<Restaurant> auxSet = new HashSet<Restaurant>();
        List<Restaurant> result = DAO.getRestaurantsByName(query);
        for (Restaurant r : result) {
			auxSet.add(r);
		}
        List<Restaurant> restByArea = DAO.getRestaurantsByArea(query);
        for (Restaurant r : restByArea)
        if (auxSet.add(r))
        	result.add(r);
        List<Restaurant> restByFoodtype = DAO.getRestaurantsByFoodType(query);
        for (Restaurant r : restByFoodtype)
            if (auxSet.add(r))
            	result.add(r);
        return result;
	}
	
	public void insertRating(int value, String comment, User user,
			Restaurant rest) {
		if (getSingleRating(user, rest) == null) {
			Rating rate = new Rating(value, comment, user, rest, new Date());

			rtDAO.insertSingleRating(rate);

			rest.setAvgScore(rtDAO.getRestaurantAvgRating(rest));
			//rest.setRatings(rtDAO.getRestaurantRatingAmmount(rest));
			save(rest);
		}
	}
	
	public List<Rating> getRatingsByRestaurant(Restaurant rest) {
		return rtDAO.getRatingsByRestaurant(rest);
	}

	public Rating getSingleRating(User user, Restaurant rest) {
		return rtDAO.getSingleRating(user, rest);
	}

}
