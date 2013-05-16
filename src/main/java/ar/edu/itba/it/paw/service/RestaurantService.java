package ar.edu.itba.it.paw.service;

import java.util.List;

import ar.edu.itba.it.paw.model.FoodType;
import ar.edu.itba.it.paw.model.Restaurant;


public interface RestaurantService {
	
	public List<Restaurant> getBestRatedRestaurants(int cant);
	
	public List<Restaurant> getAll();
	
	public List<Restaurant> getRestaurantsByFoodType(FoodType ft);
	
	public Restaurant getSingleRestaurant(int id);
	
	public void save(Restaurant r);
	
	public List<Restaurant> getRestaurantsByQuery(String query);
	
}
