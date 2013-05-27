package ar.edu.itba.it.paw.domain.restaurant;

import java.util.List;
import java.util.Set;

import ar.edu.itba.it.paw.domain.user.User;

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

	public List<Restaurant> getBestRatedRestaurants(int cant);

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
	 * Obtains a List from all the Restaurants Searched by Query
	 */
	public List<Restaurant> getRestaurantsByQuery(String query);

	/**
	 * Saves user
	 */
	public void save(Restaurant r);

	/**
	 * Obtains a list from all Pending Restaurants
	 */
	public List<Restaurant> getPendingRestaurants();
	
	/**
	 * Obtains a rating by its id
	 */
	public Rating getRating(int id);
	
	/**
	 * Gets recommended restaurants from users who like 
	 * the given restaurant and are not the user sent by
	 * parameter.
	 */
	public Set<Restaurant> getRecommendedRestaurants(Restaurant r, User u);
}
