package ar.edu.itba.it.paw.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.it.paw.dao.interfaces.RatingsDAO;
import ar.edu.itba.it.paw.manager.RestaurantManager;
import ar.edu.itba.it.paw.manager.UserManager;
import ar.edu.itba.it.paw.model.FoodType;
import ar.edu.itba.it.paw.model.Rating;
import ar.edu.itba.it.paw.model.Restaurant;
import ar.edu.itba.it.paw.model.User;

public class JDBCRatingsDAO extends AbstractDAO implements RatingsDAO {

	private static JDBCRatingsDAO self;

	public synchronized static RatingsDAO getInstance() {
		if (self == null)
			self = new JDBCRatingsDAO();
		return self;
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

			if (rs.next()) {
				r.setId(rs.getInt("id"));
			}
			rs.close();

		} catch (SQLException e) {
			e.printStackTrace();
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
			e.printStackTrace();
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
			e.printStackTrace();
		}
		return null;
	}

	private Rating getRating(ResultSet rs) {
		try {
			return getRating(
					rs,
					new Restaurant(rs.getInt("id"), rs.getString("name"), rs
							.getString("address"), rs.getString("area"), rs
							.getString("telephone"), rs.getString("website"),
							rs.getString("timerange"), rs.getFloat("avgprice"),
							rs.getFloat("avgscore"), rs.getInt("Ratings"),
							new FoodType(rs.getInt("foodTypeId"), rs
									.getString("foodtypename"), rs
									.getInt("ammount"))));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Rating getRating(ResultSet rs, Restaurant r) {
		try {
			return getRating(
					rs,
					r,
					UserManager.getInstance().getSingleUser(rs.getInt("uid")));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private Rating getRating(ResultSet rs, Restaurant r, User u) {
		try {
			return new Rating(rs.getInt("id"), rs.getInt("score"),
					rs.getString("comment"), u, r, rs.getDate("ratingDate"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
