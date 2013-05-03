package ar.edu.itba.it.paw.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ar.edu.itba.it.paw.dao.JDBCRestaurantDAO;
import ar.edu.itba.it.paw.dao.interfaces.RestaurantDAO;
import ar.edu.itba.it.paw.exceptions.SQLNoConnectionException;
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
	
	public List<Restaurant> getRestaurantsByFoodType(FoodType ft) {
		return DAO.getRestaurantsByFoodtype(ft);
	}
	
	public Restaurant getSingleRestaurant(int id) {
		return DAO.getSingleRestaurant(id);
	}
	
	public void save(Restaurant r) {
		DAO.save(r);
	}
	
	public List<Restaurant> getRestaurantsByQuery(String query) {
		Set<Restaurant> auxSet = new HashSet<Restaurant>();
        List<Restaurant> result = DAO.getRestaurantsByName(query);
        for (Restaurant r : result) {
			auxSet.add(r);
		}
        List<Restaurant> restByArea = DAO.getRestaurantsByArea(query);
        for (Restaurant r : restByArea)
        if (auxSet.add(r))
        	result.add(r);
        List<Restaurant> restByFoodtype = DAO.getRestaurantsByFoodType(query);
        for (Restaurant r : restByFoodtype)
            if (auxSet.add(r))
            	result.add(r);
        return result;
	}

}
