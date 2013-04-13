package ar.edu.itba.it.paw.dao.interfaces;

import java.util.List;

import ar.edu.itba.it.paw.model.Rating;
import ar.edu.itba.it.paw.model.Restaurant;
import ar.edu.itba.it.paw.model.User;

public interface RatingsDAO {

	public boolean insertSingleRating(Rating r);
	
	public List<Rating> getRatingsByRestaurant (Restaurant r);
	
	public Rating getSingleRating (User u, Restaurant r);
	
}
