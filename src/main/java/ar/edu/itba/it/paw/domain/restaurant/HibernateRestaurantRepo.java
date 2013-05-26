package ar.edu.itba.it.paw.domain.restaurant;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import ar.edu.itba.it.paw.domain.AbstractHibernateRepo;

@Repository
public class HibernateRestaurantRepo extends AbstractHibernateRepo implements
		RestaurantRepo {

	@Autowired
	public HibernateRestaurantRepo(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	@Override
	public List<Restaurant> getAll() {
		return find("from Restaurant where state = 'Accepted'");
	}

	@Override
	public Restaurant get(int restaurantId) {
		return get(Restaurant.class, restaurantId);
	}

	@Override
	public List<Restaurant> getBestRatedRestaurants(int cant) {

		List<Restaurant> allRestaurants = getAll();

		Collections.sort(allRestaurants, new Comparator<Restaurant>() {
			@Override
			public int compare(Restaurant o1, Restaurant o2) {
				return Math.round(Math.signum(o2.getAvgScore()
						- o1.getAvgScore()));
			}
		});

		if (allRestaurants.size() <= cant) {

			return allRestaurants;
		}

		return allRestaurants.subList(0, cant - 1);
	}

	@Override
	public List<Restaurant> getRestaurantsByName(String name) {
		return find("from Restaurant  where name = ? ", name);

	}

	@Override
	public List<Restaurant> getRestaurantsByArea(String area) {
		return find("from Restaurant  where area = ? ", area);

	}

	@Override
	public List<Restaurant> getRestaurantsByFoodType(String query) {
		return find("from Restaurant  where foodtype = ? ", query);
	}

	@Override
	public List<Restaurant> getRestaurantsByQuery(String query) {
		query = "%" + query + "%";
		//NO ES MAS INSENSITIVE. NO EXISTE EL EQUIVALENTE iLIKE en HQL
		List<Restaurant> result = new ArrayList<Restaurant>();
		List<Restaurant> byName = find("from Restaurant where name LIKE ? ",
				query);
		List<Restaurant> byArea = find("from Restaurant where area LIKE ? ",
				query);
		// List<Restaurant> byFoodType = find(
		// "from Restaurant join restaurant_foodtype where foodtype LIKE ? ",
		// query);
		Set<Restaurant> s = new HashSet<Restaurant>();
		//SIGUE INSERTANDO REPETIDOS, AUNQUE METÍ BIEN EL HASHCODE Y EL EQUALS
		for (Restaurant restaurant : byName) {
			if (s.add(restaurant))
				result.add(restaurant);
		}
		for (Restaurant restaurant : byArea) {
			if (s.add(restaurant))
				result.add(restaurant);
		}
		// for (Restaurant restaurant : byFoodType) {
		// if (s.add(restaurant))
		// result.add(restaurant);
		// }
		return result;
	}

	@Override
	public void save(Restaurant r) {
		super.save(r);
	}

	@Override
	public List<Restaurant> getPendingRestaurants() {
		return find("from Restaurant  where state = 'Pending' ORDER BY applicationdate DESC");
	}

	public Rating getRating(int id) {
		return get(Rating.class, id);
	}

}
