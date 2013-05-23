package ar.edu.itba.it.paw.domain.restaurant;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.AbstractHibernateRepo;

@Repository
public class HibernateRestaurantRepo extends AbstractHibernateRepo implements RestaurantRepo {

	@Autowired
	public HibernateRestaurantRepo(SessionFactory sessionFactory) {
		super(sessionFactory);
	}
	
	@Override
	public List<Restaurant> getAll() {
		return find("from Restaurant");
	}
	
	@Override
	public Restaurant get(int restaurantId) {
		return get(Restaurant.class, restaurantId);
	}

	@Override
	public List<Restaurant> getBestRatedRestaurants(int cant) {

		//TODO Mejorar implementacion

		List<Restaurant> allRestaurants = getAll();

		Collections.sort(allRestaurants, new Comparator<Restaurant>(){
			@Override
			public int compare(Restaurant o1, Restaurant o2) {
				return Math.round(Math.signum(o2.getAvgScore() - o1.getAvgScore()));
			}});

		if (allRestaurants.size() <= cant) {
			
			return allRestaurants;
		}

		return allRestaurants.subList(0, cant-1);
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

	@Override
	public List<Restaurant> getRestaurantsByQuery(String query) {
		
		return null;
	}

	
}
