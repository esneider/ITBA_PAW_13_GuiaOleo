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
		return find("from restaurant ORDER BY rating ASC LIMIT ?", cant);
	}

	@Override
	public List<Restaurant> getRestaurantsByFoodtype(FoodType ft) {
		return find("from restaurant  where foodtype = ? ", ft.getName());
		// TODO : BAD IMPLEMENTATION, EL SPRINT DOS TIENE N a N no ??
	}

	@Override
	public List<Restaurant> getRestaurantsByName(String name) {
		return find("from restaurant  where name = ? ", name);

	}

	@Override
	public List<Restaurant> getRestaurantsByArea(String area) {
		return find("from restaurant  where area = ? ", area);

	}

	@Override
	public List<Restaurant> getRestaurantsByFoodType(String query) {
		return find("from restaurant  where foodtype = ? ", query);

	}
	
}
