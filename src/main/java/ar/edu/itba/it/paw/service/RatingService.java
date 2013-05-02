package ar.edu.itba.it.paw.service;

import java.util.Date;
import java.util.List;

import ar.edu.itba.it.paw.dao.JDBCRatingsDAO;
import ar.edu.itba.it.paw.dao.interfaces.RatingsDAO;
import ar.edu.itba.it.paw.model.Rating;
import ar.edu.itba.it.paw.model.Restaurant;
import ar.edu.itba.it.paw.model.User;

public class RatingService {

	private static RatingService self;
	private RatingsDAO DAO;

	public synchronized static RatingService getInstance() {
		if (self == null)
			self = new RatingService(new JDBCRatingsDAO());
		return self;
	}

	private RatingService(RatingsDAO ftDAO) {
		this.DAO = ftDAO;
	}

	public void insertRating(int value, String comment, User user,
			int restId) {
		if (getSingleRating(user, restId) == null) {
			Rating rate = new Rating(value, comment, user,
					RestaurantService.getInstance().getSingleRestaurant(restId), new Date());
			
			DAO.insertSingleRating(rate);
	
			RestaurantService.getInstance().updateRestaurantRatings(rate); 
		}
	}
	
	public List<Rating> getRatingsByRestaurant (int id) {
		Restaurant r = RestaurantService.getInstance().getSingleRestaurant(id);
		if (r != null)
			return DAO.getRatingsByRestaurant(r);
		return null;
	}
	
	public Rating getSingleRating (User user, int restaurantId) {
		
		Restaurant r = RestaurantService.getInstance().getSingleRestaurant(restaurantId);
		return DAO.getSingleRating(user, r);
	}

}
