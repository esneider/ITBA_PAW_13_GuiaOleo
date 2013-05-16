package ar.edu.itba.it.paw.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.edu.itba.it.paw.dao.JDBCRestaurantDAO;
import ar.edu.itba.it.paw.dao.interfaces.RestaurantDAO;
import ar.edu.itba.it.paw.model.FoodType;
import ar.edu.itba.it.paw.model.Restaurant;


public interface RestaurantService {
	
	public List<Restaurant> getBestRatedRestaurants(int cant);
	
	public List<Restaurant> getAll();
	
	public List<Restaurant> getRestaurantsByFoodType(FoodType ft);
	
	public Restaurant getSingleRestaurant(int id);
	
	public void save(Restaurant r);
	
	public List<Restaurant> getRestaurantsByQuery(String query);
	
}
