package ar.edu.itba.it.paw.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.dao.interfaces.RatingsDAO;
import ar.edu.itba.it.paw.domain.FoodType;
import ar.edu.itba.it.paw.domain.Rating;
import ar.edu.itba.it.paw.domain.User;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;

@Repository
public class JDBCRatingsDAO extends AbstractDAO implements RatingsDAO {

	private static JDBCRatingsDAO self;
	private static Logger logger = Logger.getLogger(JDBCRatingsDAO.class);

	public synchronized static RatingsDAO getInstance() {
		if (self == null)
			self = new JDBCRatingsDAO();
		return self;
	}

	public float getRestaurantAvgRating(Restaurant r) {
		ResultSet rs = executeQuery(
				"SELECT COALESCE(AVG(score), 0) AS score FROM ratings "
						+ "WHERE restaurantId = ?;", r.getId());
		try {
			if (rs.next()) {
				return rs.getFloat("score");
			} else {
				return 0;
			}
		} catch (SQLException e) {
			logger.error("SQL Error");
		}
		return 0;
	}

	public int getRestaurantRatingAmmount(Restaurant r) {
		ResultSet rs = executeQuery("SELECT COUNT(*) FROM ratings "
				+ "WHERE restaurantId = ?;", r.getId());
		try {
			if (rs.next()) {
				return rs.getInt("ammount");
			} else {
				return 0;
			}
		} catch (SQLException e) {
			logger.error("SQL Error");
		}
		return 0;
	}

	@Override
	public void insertSingleRating(Rating r) {

		PreparedStatement ps = execute(
				"INSERT INTO ratings (score, comment, userId, restaurantId, ratingDate) "
						+ "VALUES (?, ?, ?, ?, ?)", r.getScore(),
				r.getComment(), r.getUser().getId(), r.getRestaurant().getId(),
				r.getSQLDate());

		try {

			ResultSet rs = ps.getGeneratedKeys();

			if (rs.next())
				r.setId(rs.getInt("id"));

			rs.close();

		} catch (SQLException e) {
			logger.error("SQL Error");
		}
	}

	@Override
	public List<Rating> getRatingsByRestaurant(Restaurant r) {
		ResultSet rs = executeQuery(
				"SELECT ratings.*, "
						+ "users.id AS uid, users.name AS uname, users.surname, users.mail, users.username, users.password "
						+ "FROM ratings JOIN users ON ratings.userId = users.id "
						+ "WHERE ratings.restaurantId = ? "
						+ "ORDER BY ratings.ratingDate desc", r.getId());
		List<Rating> ls = new ArrayList<Rating>();
		try {
			while (rs.next())
				ls.add(getRating(rs, r));
			rs.close();
			return ls;
		} catch (SQLException e) {
			logger.error("SQL Error");
		}
		return null;
	}

	public Rating getSingleRating(User u, Restaurant r) {
		ResultSet rs = executeQuery("SELECT * FROM ratings "
				+ "WHERE ratings.restaurantId = ? AND ratings.userId = ?",
				r.getId(), u.getId());
		try {
			Rating rate = null;
			if (rs.next())
				rate = getRating(rs, r, u);
			rs.close();
			return rate;
		} catch (SQLException e) {
			logger.error("SQL Error");
		}
		return null;
	}

	@SuppressWarnings("unused")
	private Rating getRating(ResultSet rs) {
//		try {
////			FoodType ft = new FoodType(rs.getString("foodtypename"),
////					rs.getInt("ammount"));
////			ft.setId(rs.getInt("foodTypeId"));
//			/*Restaurant r = new Restaurant(rs.getString("name"),
//					rs.getString("address"), rs.getString("area"),
//					rs.getString("telephone"), rs.getString("website"),
//					rs.getString("timerange"), rs.getFloat("avgprice"),
//					rs.getFloat("avgscore"), rs.getInt("Ratings"), ft);
//			return getRating(rs, r);*/
//		} catch (SQLException e) {
//			logger.error("SQL Error");
//		}
		return null;
	}

	private Rating getRating(ResultSet rs, Restaurant r) {
		try {
			return getRating(rs, r,
					JDBCUserDAO.getInstance().getSingleUser(rs.getInt("uid")));
		} catch (SQLException e) {
			logger.error("SQL Error");
		}
		return null;
	}

	private Rating getRating(ResultSet rs, Restaurant r, User u) {
		try {
			Rating rate = new Rating(rs.getInt("score"),
					rs.getString("comment"), u, r, rs.getDate("ratingDate"));
			rate.setId(rs.getInt("id"));
			return rate;
		} catch (SQLException e) {
			logger.error("SQL Error");
		}
		return null;
	}

}
