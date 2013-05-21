package ar.edu.itba.it.paw.domain.restaurant;

import java.util.List;

import ar.edu.itba.it.paw.domain.FoodType;
import ar.edu.itba.it.paw.exceptions.SQLNoConnectionException;

/**
 * Restaurant repository.
 * 
 */
public interface RestaurantRepo {

	/**
	 * Obtains a list from all the restaurants
	 */
	public List<Restaurant> getAll();

	/**
	 * Obtains a single Restaurant by its id
	 */
	public Restaurant get(int restaurantId);

	/**
	 * Obtains a list from all the best rated Restaurants
	 */

	public List<Restaurant> getBestRatedRestaurants(int cant)
			throws SQLNoConnectionException;

	/**
	 * Obtains a list from all the Restaurants by FoodType
	 */
	public List<Restaurant> getRestaurantsByFoodtype(FoodType ft);

	/**
	 * Obtains a list from all the Restaurants by Name
	 */
	public List<Restaurant> getRestaurantsByName(String query);

	/**
	 * Obtains a list from all the Restaurants by Area
	 */
	public List<Restaurant> getRestaurantsByArea(String query);

	/**
	 * Obtains a list from all the Restaurants by FoodType TODO: ESTA NO VA MAS
	 * !!!
	 */
	public List<Restaurant> getRestaurantsByFoodType(String query);
	
	/**
	 * Obtains a list from all the Restaurants by Query
	 */
	public List<Restaurant> getRestaurantsByQuery(String query);

}
