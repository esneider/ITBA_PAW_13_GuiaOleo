package ar.edu.itba.it.paw.service;

import java.util.List;

import ar.edu.itba.it.paw.model.FoodType;
import ar.edu.itba.it.paw.model.Rating;
import ar.edu.itba.it.paw.model.Restaurant;
import ar.edu.itba.it.paw.model.User;


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
