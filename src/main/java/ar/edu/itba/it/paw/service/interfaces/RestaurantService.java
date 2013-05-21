package ar.edu.itba.it.paw.service.interfaces;

import java.util.List;

import ar.edu.itba.it.paw.domain.FoodType;
import ar.edu.itba.it.paw.domain.Rating;
import ar.edu.itba.it.paw.domain.User;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;


public interface RestaurantService {
	
	public List<Restaurant> getBestRatedRestaurants(int cant);
	
	public List<Restaurant> getAll();
	
	public List<Restaurant> getRestaurantsByFoodType(FoodType ft);
	
	public Restaurant getSingleRestaurant(int id);
	
	public void save(Restaurant r);
	
	public List<Restaurant> getRestaurantsByQuery(String query);
	
	public void insertRating(int value, String comment, User user,
			Restaurant rest);
	
	public List<Rating> getRatingsByRestaurant(Restaurant rest);

	public Rating getSingleRating(User user, Restaurant rest);

	
}
