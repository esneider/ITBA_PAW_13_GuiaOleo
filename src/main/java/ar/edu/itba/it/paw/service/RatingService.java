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
			Restaurant rest) {
		if (getSingleRating(user, rest) == null) {
			Rating rate = new Rating(value, comment, user, rest, new Date());

			DAO.insertSingleRating(rate);

			rest.setAvgScore(DAO.getRestaurantAvgRating(rest));
			rest.setRatings(DAO.getRestaurantRatingAmmount(rest));
		//	RestaurantService.getInstance().save(rest);
		}
	}

	public List<Rating> getRatingsByRestaurant(Restaurant rest) {
		return DAO.getRatingsByRestaurant(rest);
	}

	public Rating getSingleRating(User user, Restaurant rest) {
		return DAO.getSingleRating(user, rest);
	}

}
