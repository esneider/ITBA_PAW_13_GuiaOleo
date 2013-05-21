package ar.edu.itba.it.paw.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.dao.interfaces.RestaurantDAO;
import ar.edu.itba.it.paw.domain.FoodType;
import ar.edu.itba.it.paw.domain.restaurant.Restaurant;
import ar.edu.itba.it.paw.exceptions.SQLNoConnectionException;

@Repository
public class JDBCRestaurantDAO extends AbstractDAO implements RestaurantDAO {

	private static JDBCRestaurantDAO self;
	private static Logger logger = Logger.getLogger(JDBCRestaurantDAO.class);

	public synchronized static RestaurantDAO getInstance() {
		if (self == null)
			self = new JDBCRestaurantDAO();
		return self;
	}

	public List<Restaurant> getBestRatedRestaurants(int cant) {

		List<Restaurant> ls = new ArrayList<Restaurant>();
		ResultSet rs = executeQuery(
				"SELECT restaurants.*, "
						+ "foodtypes.id AS fid, foodtypes.name AS fname, foodtypes.ammount AS fammount "
						+ "FROM restaurants JOIN foodtypes "
						+ "ON restaurants.foodTypeId = foodtypes.id "
						+ "ORDER BY avgscore desc " + "LIMIT ?", cant);

		try {
			while (rs.next())
				ls.add(getRestaurant(rs));
			rs.close();
			return ls;
		} catch (SQLException e) {
			logger.error("SQL Error");
			throw new SQLNoConnectionException();

		}

	}

	public Restaurant getSingleRestaurant(int id) {
		ResultSet rs = executeQuery(
				"SELECT restaurants.*, "
						+ "foodtypes.id AS fid, foodtypes.name AS fname, foodtypes.ammount AS fammount "
						+ "FROM restaurants JOIN foodtypes "
						+ "ON restaurants.foodTypeId = foodtypes.id "
						+ "WHERE restaurants.id = ?", id);
		try {
			Restaurant r = null;
			if (rs.next())
				r = getRestaurant(rs);
			rs.close();
			return r;
		} catch (SQLException e) {
			logger.error("SQL Error Getting Single Restaurant");

			throw new SQLNoConnectionException();
		}
	}

	public void save(Restaurant r) {
		/*executeUpdate("UPDATE restaurants SET "
				+ "name = ?,  address = ?, area = ?, telephone = ?, "
				+ "website = , timerange = ?, avgprice = ?, avgscore = ?, "
				+ "ratings = ?, foodtype = ?" + "WHERE id = ?;", r.getName(),
				r.getAddress(), r.getArea(), r.getTelephone(), r.getWebsite(),
				r.getTimerange(), r.getAvgprice(), r.getAvgScore(),
				r.getRatings(), r.getFoodtype(), r.getId());*/
	}

	public List<Restaurant> getAll() {

		ResultSet rs = executeQuery("SELECT restaurants.*, "
				+ "foodtypes.id AS fid, foodtypes.name AS fname, foodtypes.ammount AS fammount "
				+ "FROM restaurants JOIN foodtypes "
				+ "ON restaurants.foodTypeId = foodtypes.id");
		List<Restaurant> ls = new ArrayList<Restaurant>();
		try {
			while (rs.next())
				ls.add(getRestaurant(rs));
			rs.close();
			return ls;
		} catch (SQLException e) {
			logger.error("SQL Error");

			throw new SQLNoConnectionException();
		}
	}

	@Override
	public List<Restaurant> getRestaurantsByFoodtype(FoodType ft) {

		ResultSet rs = executeQuery(
				"SELECT restaurants.*, "
						+ "foodtypes.id AS fid, foodtypes.name AS fname, foodtypes.ammount AS fammount "
						+ "FROM restaurants JOIN foodtypes "
						+ "ON restaurants.foodTypeId = foodtypes.id "
						+ "WHERE foodtypes.id = ?", ft.getId());

		List<Restaurant> ls = new ArrayList<Restaurant>();
		try {
			while (rs.next())
				ls.add(getRestaurant(rs));
			rs.close();
			return ls;
		} catch (SQLException e) {
			logger.error("SQL Error");

			throw new SQLNoConnectionException();
		}
	}

	public List<Restaurant> getRestaurantsByName(String query) {

		query = "%" + query + "%";
		ResultSet rs = executeQuery(
				"SELECT restaurants.*, "
						+ "foodtypes.id AS fid, foodtypes.name AS fname, foodtypes.ammount AS fammount "
						+ "FROM restaurants JOIN foodtypes "
						+ "ON restaurants.foodTypeId = foodtypes.id "
						+ "WHERE restaurants.name ILIKE ?", query);
		List<Restaurant> ls = new ArrayList<Restaurant>();

		try {
			while (rs.next())
				ls.add(getRestaurant(rs));
			rs.close();
			return ls;
		} catch (SQLException e) {
			logger.error("SQL Error");

			throw new SQLNoConnectionException();
		}
	}

	public List<Restaurant> getRestaurantsByFoodType(String query) {
		query = "%" + query + "%";
		ResultSet rs = executeQuery(
				"SELECT restaurants.*, "
						+ "foodtypes.id AS fid, foodtypes.name AS fname, foodtypes.ammount AS fammount "
						+ "FROM restaurants JOIN foodtypes "
						+ "ON restaurants.foodTypeId = foodtypes.id "
						+ "WHERE foodtypes.name ILIKE ?", query);
		List<Restaurant> ls = new ArrayList<Restaurant>();
		try {
			while (rs.next())
				ls.add(getRestaurant(rs));
			rs.close();
			return ls;
		} catch (SQLException e) {
			logger.error("SQL Error");

			throw new SQLNoConnectionException();
		}
	}

	private List<Restaurant> getRestaurantsByFoodtype(String query) {
		query = "%" + query + "%";
		ResultSet rs = executeQuery(
				"SELECT restaurants.*, "
						+ "foodtypes.id AS fid, foodtypes.name AS fname, foodtypes.ammount AS fammount "
						+ "FROM restaurants JOIN foodtypes "
						+ "ON restaurants.foodTypeId = foodtypes.id "
						+ "WHERE foodtypes.name ILIKE ?", query);
		List<Restaurant> ls = new ArrayList<Restaurant>();
		try {
			while (rs.next())
				ls.add(getRestaurant(rs));
			rs.close();
			return ls;
		} catch (SQLException e) {
			logger.error("SQL Error");

			throw new SQLNoConnectionException();
		}
	}

	public List<Restaurant> getRestaurantsByArea(String query) {
		query = "%" + query + "%";
		ResultSet rs = executeQuery(
				"SELECT restaurants.*, "
						+ "foodtypes.id AS fid, foodtypes.name AS fname, foodtypes.ammount AS fammount "
						+ "FROM restaurants JOIN foodtypes "
						+ "ON restaurants.foodTypeId = foodtypes.id "
						+ "WHERE restaurants.area ILIKE ?", query);
		List<Restaurant> ls = new ArrayList<Restaurant>();

		try {
			while (rs.next())
				ls.add(getRestaurant(rs));
			rs.close();
			return ls;
		} catch (SQLException e) {
			logger.error("SQL Error");

			throw new SQLNoConnectionException();
		}

	}

	private Restaurant getRestaurant(ResultSet rs) {
//		try {
//			FoodType ft = new FoodType(rs.getString("fname"),
//					rs.getInt("fammount"));
//			ft.setId(rs.getInt("fid"));
//			/*Restaurant r = new Restaurant(rs.getString("name"),
//					rs.getString("address"), rs.getString("area"),
//					rs.getString("telephone"), rs.getString("website"),
//					rs.getString("timerange"), roundToDigits(
//							rs.getFloat("avgprice"), 2), roundToDigits(
//							rs.getFloat("avgscore"), 2),
//					rs.getInt("cantratings"), ft);*/
//			//r.setId(rs.getInt("id"));
//			//return r;
//			return null;
//		} catch (SQLException e) {
//			logger.error("SQL Error");
//
//			throw new SQLNoConnectionException();
//		}
		return null;
	}

	private float roundToDigits(float number, int cant) {
		double decimals = Math.pow(10, cant);
		return (float) (Math.round(number * decimals) / decimals);
	}

}
