package ar.edu.itba.it.paw.dao;

import ar.edu.itba.it.paw.dao.interfaces.RatingsDAO;
import ar.edu.itba.it.paw.model.Rating;

public class JDBCRatingsDAO extends AbstractDAO implements RatingsDAO {

	private static JDBCRatingsDAO self;

	public synchronized static RatingsDAO getInstance() {
		if (self == null)
			self = new JDBCRatingsDAO();
		return self;
	}

	
	@Override
	public boolean insertSingleRating(Rating r) {
		return execute("INSERT INTO ratings (score, comment, userId, restaurantId) "
				+ "VALUES (?, ?, ?, ?)", r.getScore(), r.getComment(), r
				.getUser().getId(), r.getRestaurant().getId());
	}
}
