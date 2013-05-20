package ar.edu.itba.it.paw.dao.interfaces;

import java.util.List;

import ar.edu.itba.it.paw.domain.FoodType;
import ar.edu.itba.it.paw.domain.Restaurant;
import ar.edu.itba.it.paw.exceptions.SQLNoConnectionException;

public interface RestaurantDAO {

	public List<Restaurant> getBestRatedRestaurants(int cant) throws SQLNoConnectionException;
	
	public List<Restaurant> getAll();
	
	public Restaurant getSingleRestaurant(int id);

	public List<Restaurant> getRestaurantsByFoodtype(FoodType ft);

	public List<Restaurant> getRestaurantsByName(String query);
	
	public List<Restaurant> getRestaurantsByArea(String query);
	
	public List<Restaurant> getRestaurantsByFoodType(String query);
	
	public void save(Restaurant r);
	
}
