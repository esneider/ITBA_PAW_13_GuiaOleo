package ar.edu.itba.it.paw.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.itba.it.paw.dao.interfaces.RestaurantDAO;
import ar.edu.itba.it.paw.model.Restaurant;

public class JDBCRestaurantDAO extends AbstractDAO implements RestaurantDAO {

	private static JDBCRestaurantDAO self;

	public synchronized static RestaurantDAO getInstance() {
		if (self == null)
			self = new JDBCRestaurantDAO();
		return self;
	}

	public List<Restaurant> getBestRatedRestaurants(int cant) {
		ResultSet rs = executeQuery("SELECT * FROM restaurants "
				+ "ORDER BY avgscore dec " + "LIMIT ?", cant);
		List<Restaurant> ls = new ArrayList<Restaurant>();
		try {
			while (rs.next()) {
				ls.add(new Restaurant(rs.getInt("id"), rs.getString("name"), rs
						.getString("address"), rs.getString("area"), rs
						.getString("telephone"), rs.getString("website"), rs
						.getString("timerange"), rs.getFloat("avgprice"), rs
						.getFloat("avgscore"), rs.getInt("ratings")));
			}
			return ls;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Restaurant> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
