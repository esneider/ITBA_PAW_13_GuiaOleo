package ar.edu.itba.it.paw.dao;

import java.util.List;

import ar.edu.itba.it.paw.dao.interfaces.RestaurantDAO;
import ar.edu.itba.it.paw.model.Restaurant;

public class JDBCRestaurantDAO extends AbstractDAO implements RestaurantDAO {
	
	private static JDBCRestaurantDAO self;

	public synchronized static JDBCRestaurantDAO getInstance() {
		if (self == null)
			self = new JDBCRestaurantDAO();
		return self;
	}
	
	public List<Restaurant> getBestRatedRestaurants(int cant) {
		ResultSet rs = executeQuery("SELECT * FROM restaurants WHERE (SELECT )", cant);
		return null;
	}

	
	
}
