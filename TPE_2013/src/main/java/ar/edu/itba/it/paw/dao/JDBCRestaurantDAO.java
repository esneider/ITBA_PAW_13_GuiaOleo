package ar.edu.itba.it.paw.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.edu.itba.it.paw.dao.interfaces.RestaurantDAO;
import ar.edu.itba.it.paw.model.FoodType;
import ar.edu.itba.it.paw.model.Rating;
import ar.edu.itba.it.paw.model.Restaurant;

public class JDBCRestaurantDAO extends AbstractDAO implements RestaurantDAO {

	private static JDBCRestaurantDAO self;

	public synchronized static RestaurantDAO getInstance() {
		if (self == null)
			self = new JDBCRestaurantDAO();
		return self;
	}

	public List<Restaurant> getBestRatedRestaurants(int cant) {
		ResultSet rs = executeQuery(
				"SELECT restaurants.*, "
						+ "foodtypes.id AS fid, foodtypes.name AS fname, foodtypes.ammount AS fammount "
						+ "FROM restaurants JOIN foodtypes "
						+ "ON restaurants.foodTypeId = foodtypes.id "
						+ "ORDER BY avgscore desc " + "LIMIT ?", cant);
		List<Restaurant> ls = new ArrayList<Restaurant>();
		try {
			while (rs.next()) {
				ls.add(getRestaurant(rs));
			}
			rs.close();
			return ls;
		} catch (SQLException e) {
			System.out.println("Holis");
			e.printStackTrace();
		}
		return null;
	}

	public Restaurant getSingleRestaurant(int id) {
		ResultSet rs = executeQuery(
				"SELECT restaurants.*, "
						+ "foodtypes.id AS fid, foodtypes.name AS fname, foodtypes.ammount AS fammount "
						+ "FROM restaurants JOIN foodtypes "
						+ "ON restaurants.foodTypeId = foodtypes.id "
						+ "WHERE restaurants.id = ?", id);
		try {
			rs.next();
			Restaurant r = getRestaurant(rs);
			rs.close();
			return r;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void updateRestaurantRatings(Rating rate) {
		executeUpdate("UPDATE restaurants SET avgScore = "
				+ "(SELECT COALESCE(AVG(score), 0) FROM ratings "
				+ "WHERE ratings.restaurantId = restaurants.id) "
				+ "WHERE id = ?", rate.getRestaurant().getId());
		executeUpdate("UPDATE restaurants SET cantRatings = "
				+ "(SELECT COUNT(*) FROM ratings "
				+ "WHERE ratings.restaurantId = restaurants.id) "
				+ "WHERE id = ?", rate.getRestaurant().getId());

	}

	public List<Restaurant> getAll() {

		ResultSet rs = executeQuery("SELECT restaurants.*, "
				+ "foodtypes.id AS fid, foodtypes.name AS fname, foodtypes.ammount AS fammount "
				+ "FROM restaurants JOIN foodtypes "
				+ "ON restaurants.foodTypeId = foodtypes.id");
		List<Restaurant> ls = new ArrayList<Restaurant>();
		try {
			while (rs.next()) {
				ls.add(getRestaurant(rs));
			}
			rs.close();
			return ls;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
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
			while (rs.next()) {
				ls.add(getRestaurant(rs));
			}
			rs.close();
			return ls;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected List<Restaurant> getRestaurantsByName(String query) {
		query = "%" + query + "%"; 
        ResultSet rs = executeQuery("SELECT restaurants.*, "
				+ "foodtypes.id AS fid, foodtypes.name AS fname, foodtypes.ammount AS fammount "
				+ "FROM restaurants JOIN foodtypes "
				+ "ON restaurants.foodTypeId = foodtypes.id "
				+ "WHERE restaurants.name ILIKE ?", query);
        List<Restaurant> ls = new ArrayList<Restaurant>();
       
		try {
			while (rs.next()) {

				ls.add(getRestaurant(rs));
			}
			return ls;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public List<Restaurant> getRestaurantsByQuery(String query) {

		query = "%" + query + "%";
		ResultSet rs = executeQuery(
				"SELECT restaurants.*, "
						+ "foodtypes.id AS fid, foodtypes.name AS fname, foodtypes.ammount AS fammount "
						+ "FROM restaurants JOIN foodtypes "
						+ "ON restaurants.foodTypeId = foodtypes.id "
						+ "WHERE restaurants.name ILIKE ?", query);
		List<Restaurant> ls = new ArrayList<Restaurant>();

		
		Set<Restaurant> auxSet = new HashSet<Restaurant>();
        List<Restaurant> result = getRestaurantsByName(query);
        for (Restaurant r : result) {
			auxSet.add(r);
		}
        List<Restaurant> restByArea = getRestaurantsByArea(query);
        for (Restaurant r : restByArea)
        if (auxSet.add(r))
        	result.add(r);
        List<Restaurant> restByFoodtype = getRestaurantsByFoodtype(query);
        for (Restaurant r : restByFoodtype)
            if (auxSet.add(r))
            	result.add(r);
        return result;

	}

	private List<Restaurant> getRestaurantsByFoodtype(String query) {
		query = "%" + query + "%"; 
        ResultSet rs = executeQuery("SELECT restaurants.*, "
				+ "foodtypes.id AS fid, foodtypes.name AS fname, foodtypes.ammount AS fammount "
				+ "FROM restaurants JOIN foodtypes "
				+ "ON restaurants.foodTypeId = foodtypes.id "
				+ "WHERE foodtypes.name ILIKE ?", query);
        List<Restaurant> ls = new ArrayList<Restaurant>();
       
		try {
			while (rs.next()) {

				ls.add(getRestaurant(rs));
			}
			return ls;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private List<Restaurant> getRestaurantsByArea(String query) {
		query = "%" + query + "%"; 
        ResultSet rs = executeQuery("SELECT restaurants.*, "
				+ "foodtypes.id AS fid, foodtypes.name AS fname, foodtypes.ammount AS fammount "
				+ "FROM restaurants JOIN foodtypes "
				+ "ON restaurants.foodTypeId = foodtypes.id "
				+ "WHERE restaurants.area ILIKE ?", query);
        List<Restaurant> ls = new ArrayList<Restaurant>();
       

		try {
			while (rs.next()) {
				ls.add(getRestaurant(rs));
			}
			rs.close();
			return ls;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private Restaurant getRestaurant(ResultSet rs) {
		try {			
			return new Restaurant(rs.getInt("id"), rs.getString("name"),
					rs.getString("address"), rs.getString("area"),
					rs.getString("telephone"), rs.getString("website"),
					rs.getString("timerange"), roundToDigits(rs.getFloat("avgprice"),2),
					roundToDigits(rs.getFloat("avgscore"),2), rs.getInt("cantratings"),
					new FoodType(rs.getInt("fid"), rs.getString("fname"), rs
							.getInt("fammount")));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private float roundToDigits(float number, int cant) {
		double decimals = Math.pow(10, cant);
		return (float) (Math.round(number * decimals) / decimals);
	}
	
}

