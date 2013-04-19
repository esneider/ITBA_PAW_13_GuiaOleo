package ar.edu.itba.it.paw.service;

import java.util.List;

import ar.edu.itba.it.paw.dao.JDBCRestaurantDAO;
import ar.edu.itba.it.paw.dao.interfaces.RestaurantDAO;
import ar.edu.itba.it.paw.model.FoodType;
import ar.edu.itba.it.paw.model.Rating;
import ar.edu.itba.it.paw.model.Restaurant;


public class RestaurantService {
	
	private static RestaurantService self;
	private RestaurantDAO DAO;
	
	public synchronized static RestaurantService getInstance() {
		if (self == null) 
			self = new RestaurantService(new JDBCRestaurantDAO());
		return self;
	}
	
	private RestaurantService (RestaurantDAO rsDAO) {
		this.DAO = rsDAO;
	}
	
	public List<Restaurant> getBestRatedRestaurants(int cant) {
		return DAO.getBestRatedRestaurants(cant);
	}
	
	public List<Restaurant> getAll() {
		return DAO.getAll();
	}
	
	public List<Restaurant> getRestaurantsByFoodType(int foodTypeId) {
		FoodType ft = FoodTypeService.getInstance().getSingleFoodType(foodTypeId);
		if (ft != null)
			return DAO.getRestaurantsByFoodtype(ft);
		return null;
	}
	
	public Restaurant getSingleRestaurant(int id) {
		return DAO.getSingleRestaurant(id);
	}
	
	public void updateRestaurantRatings (Rating rate) {
		DAO.updateRestaurantRatings(rate);
	}
	
	public List<Restaurant> getRestaurantsByQuery(String query) {
		return DAO.getRestaurantsByQuery(query);
	}

}
