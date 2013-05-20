package ar.edu.itba.it.paw.dao.interfaces;

import java.util.List;

import ar.edu.itba.it.paw.domain.Rating;
import ar.edu.itba.it.paw.domain.Restaurant;
import ar.edu.itba.it.paw.domain.User;

public interface RatingsDAO {

	public void insertSingleRating(Rating r);
	
	public List<Rating> getRatingsByRestaurant (Restaurant r);
	
	public Rating getSingleRating (User u, Restaurant r);
	
	public float getRestaurantAvgRating(Restaurant r);
	
	public int getRestaurantRatingAmmount(Restaurant r);
}
