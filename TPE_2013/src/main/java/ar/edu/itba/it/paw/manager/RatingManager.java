package ar.edu.itba.it.paw.manager;

import java.util.List;

import ar.edu.itba.it.paw.dao.JDBCRatingsDAO;
import ar.edu.itba.it.paw.dao.interfaces.RatingsDAO;
import ar.edu.itba.it.paw.model.Rating;
import ar.edu.itba.it.paw.model.Restaurant;
import ar.edu.itba.it.paw.model.User;

public class RatingManager {

	private static RatingManager self;
	private RatingsDAO DAO;

	public synchronized static RatingManager getInstance() {
		if (self == null)
			self = new RatingManager(new JDBCRatingsDAO());
		return self;
	}

	private RatingManager(RatingsDAO ftDAO) {
		this.DAO = ftDAO;
	}

	public boolean insertRating(int value, String comment, User user,
			int restId) {
	
		Rating rate = new Rating(value, comment, user,
				RestaurantManager.getInstance().getSingleRestaurant(restId));
		
		boolean ans = DAO.insertSingleRating(rate);
		RestaurantManager.getInstance().updateRestaurantRatings(rate);
		return ans;
	}
	
	public List<Rating> getRatingsByRestaurant (int id) {
		Restaurant r = RestaurantManager.getInstance().getSingleRestaurant(id);
		if (r != null)
			return DAO.getRatingsByRestaurant(r);
		return null;
	}
	
	public Rating getSingleRating (User user, int restaurantId) {
		
		Restaurant r = RestaurantManager.getInstance().getSingleRestaurant(restaurantId);
		return DAO.getSingleRating(user, r);
	}

}
