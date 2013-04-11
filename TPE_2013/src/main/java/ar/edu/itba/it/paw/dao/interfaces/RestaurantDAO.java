package ar.edu.itba.it.paw.dao.interfaces;

import java.util.List;

import ar.edu.itba.it.paw.model.Restaurant;

public interface RestaurantDAO {

	public List<Restaurant> getBestRatedRestaurants(int cant);
	
	public List<Restaurant> getAll();
	
	public Restaurant getSingleRestaurant(int id);

	public List<Restaurant> getRestaurantsByFoodtype(int id);
	
}
