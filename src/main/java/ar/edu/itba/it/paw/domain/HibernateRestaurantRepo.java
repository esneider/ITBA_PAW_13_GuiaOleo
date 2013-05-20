package ar.edu.itba.it.paw.domain;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.interfaces.RestaurantRepo;

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
	
}
