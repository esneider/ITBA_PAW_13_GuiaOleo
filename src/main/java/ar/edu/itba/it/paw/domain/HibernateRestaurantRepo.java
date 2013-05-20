package ar.edu.itba.it.paw.domain;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.interfaces.RestaurantRepo;
import ar.edu.itba.it.paw.exceptions.SQLNoConnectionException;

@Repository
public class HibernateRestaurantRepo extends AbstractHibernateRepo implements RestaurantRepo {

	@Autowired
	public HibernateRestaurantRepo(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	@Override
	public List<Restaurant> getAll() {
		return find("from restaurant");
	}
	
	@Override
	public Restaurant get(int restaurantId) {
		return get(Restaurant.class, restaurantId);
	}

	@Override
	public List<Restaurant> getBestRatedRestaurants(int cant)
			throws SQLNoConnectionException {
		return find("from Subject ORDER BY rating ASC LIMIT ?", cant);
	}

	@Override
	public List<Restaurant> getRestaurantsByFoodtype(FoodType ft) {
		return find("from Subject  where foodtype = ? ?", cant);
		
	}

	@Override
	public List<Restaurant> getRestaurantsByName(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Restaurant> getRestaurantsByArea(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Restaurant> getRestaurantsByFoodType(String query) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
