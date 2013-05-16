package ar.edu.itba.it.paw.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.itba.it.paw.dao.interfaces.RestaurantDAO;
import ar.edu.itba.it.paw.model.FoodType;
import ar.edu.itba.it.paw.model.Restaurant;

@Service
public class RestaurantServiceImpl implements RestaurantService {
	
	private RestaurantDAO DAO;

	@Autowired
	public RestaurantServiceImpl(RestaurantDAO DAO) {
		this.DAO = DAO;
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
