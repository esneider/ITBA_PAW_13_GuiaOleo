package ar.edu.itba.it.paw.manager;

import java.util.List;

import ar.edu.itba.it.paw.dao.JDBCRestaurantDAO;
import ar.edu.itba.it.paw.dao.interfaces.RestaurantDAO;
import ar.edu.itba.it.paw.model.FoodType;
import ar.edu.itba.it.paw.model.Restaurant;


public class RestaurantManager {
	
	private static RestaurantManager self;
	private RestaurantDAO DAO;
	
	public synchronized static RestaurantManager getInstance() {
		if (self == null) 
			self = new RestaurantManager(new JDBCRestaurantDAO());
		return self;
	}
	
	private RestaurantManager (RestaurantDAO rsDAO) {
		this.DAO = rsDAO;
	}
	
	public List<Restaurant> getBestRatedRestaurants(int cant) {
		return DAO.getBestRatedRestaurants(cant);
	}
	
	public List<Restaurant> getAll() {
		return DAO.getAll();
	}
	
	public List<Restaurant> getRestaurantsByFoodType(int foodTypeId) {
		FoodType ft = FoodTypeManager.getInstance().getSingleFoodType(foodTypeId);
		if (ft != null)
			return DAO.getRestaurantsByFoodtype(ft);
		return null;
	}
	
	public Restaurant getSingleRestaurant(int id) {
		return DAO.getSingleRestaurant(id);
	}

}
