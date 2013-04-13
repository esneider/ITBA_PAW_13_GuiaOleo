package ar.edu.itba.it.paw.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.it.paw.dao.interfaces.RatingsDAO;
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
	public boolean insertSingleRating(Rating r) {
		return execute(
				"INSERT INTO ratings (score, comment, userId, restaurantId) "
						+ "VALUES (?, ?, ?, ?)", r.getScore(), r.getComment(),
				r.getUser().getId(), r.getRestaurant().getId());
	}

	@Override
	public List<Rating> getRatingsByRestaurant(Restaurant r) {
		ResultSet rs = executeQuery(
				"SELECT ratings.*, "
						+ "users.id AS uid, users.name AS uname, users.surname, users.mail, users.username, users.password "
						+ "FROM ratings JOIN users ON ratings.userId = users.id "
						+ "WHERE ratings.restaurantId = ?", r.getId());
		List<Rating> ls = new ArrayList<Rating>();
		try {
			while (rs.next()) {
				ls.add(getRating(rs, r));
			}
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
			if (rs.next())
				return getRating(rs, r, u);
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Rating getRating(ResultSet rs) {
		try {
			return new Rating(rs.getInt("rid"), rs.getInt("score"),
					rs.getString("comment"), new User(rs.getInt("uid"),
							rs.getString("uname"), rs.getString("surname"),
							rs.getString("mail"), rs.getString("username"),
							rs.getString("password")), new Restaurant(
							rs.getInt("id"), rs.getString("name"),
							rs.getString("address"), rs.getString("area"),
							rs.getString("telephone"), rs.getString("website"),
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
			return new Rating(rs.getInt("id"), rs.getInt("score"),
					rs.getString("comment"), new User(rs.getInt("uid"),
							rs.getString("uname"), rs.getString("surname"),
							rs.getString("mail"), rs.getString("username"),
							rs.getString("password")), r);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Rating getRating(ResultSet rs, Restaurant r, User u) {
		try {
			return new Rating(rs.getInt("id"), rs.getInt("score"),
					rs.getString("comment"), u, r);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
