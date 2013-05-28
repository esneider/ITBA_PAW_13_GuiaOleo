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
	 * Returns a list of all the restaurants
	 */
	public List<Restaurant> getAll();

	/**
	 * Returns a single Restaurant by its id
	 */
	public Restaurant get(int restaurantId);

	/**
	 * Returns a list of the {@code cant} best rated Restaurants
	 */
	public List<Restaurant> getBestRatedRestaurants(int cant);

	/**
	 * Returns a list of all the Restaurants matching a given query
	 */
	public List<Restaurant> getRestaurantsByQuery(String query);

	/**
	 * Saves Restaurant
	 */
	public void save(Restaurant r);

	/**
	 * Returns a list of all pending Restaurants
	 */
	public List<Restaurant> getPendingRestaurants();

	/**
	 * Returns a rating by its id
	 */
	public Rating getRating(int id);

	/**
	 * TODO: WTF is this
	 *
	 * Gets recommended restaurants from users who like
	 * the given restaurant and are not the user sent by
	 * parameter.
	 */
	public Set<Restaurant> getRecommendedRestaurants(Restaurant r, User u);
}

