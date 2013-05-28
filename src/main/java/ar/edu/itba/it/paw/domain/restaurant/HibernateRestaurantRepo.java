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
import ar.edu.itba.it.paw.domain.user.User;

@Repository
public class HibernateRestaurantRepo extends AbstractHibernateRepo implements RestaurantRepo {

	@Autowired
	public HibernateRestaurantRepo(SessionFactory sessionFactory) {

		super(sessionFactory);
	}

	@Override
	public List<Restaurant> getAll() {
		return find("from Restaurant where state = ?", RestaurantState.ACCEPTED);
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

				return Math.round(Math.signum(o2.getAvgScore() - o1.getAvgScore()));
			}
		});

		if (allRestaurants.size() <= cant) {

			return allRestaurants;
		}

		return allRestaurants.subList(0, cant - 1);
	}

	private List<Restaurant> getRestaurantsByName(String name) {

		return find("from Restaurant where lower(name) like ? ", name);

	}

	private List<Restaurant> getRestaurantsByArea(String area) {

		return find("from Restaurant where lower(area) like ? ", area);
	}

	private List<Restaurant> getRestaurantsByFoodType(String query) {

		return find("from Restaurant where foodtype = ? ", query);
	}

	@Override
	public List<Restaurant> getRestaurantsByQuery(String query) {

		query = "%" + query.toLowerCase() + "%";

		List<Restaurant> result = new ArrayList<Restaurant>();
		List<Restaurant> byName = getRestaurantsByName(query);
		List<Restaurant> byArea = getRestaurantsByArea(query);
		List<Restaurant> byFoodType = getRestaurantsByFoodType(query);

		Set<Restaurant> s = new HashSet<Restaurant>();

		for (Restaurant restaurant : byName) {
			if (s.add(restaurant)) {
				result.add(restaurant);
            }
		}

		for (Restaurant restaurant : byArea) {
			if (s.add(restaurant)) {
				result.add(restaurant);
            }
		}

		for (Restaurant restaurant : byFoodType) {
			if (s.add(restaurant)) {
				result.add(restaurant);
            }
		}

		return result;
	}

	@Override
	public void save(Restaurant r) {

		super.save(r);
	}

	@Override
	public List<Restaurant> getPendingRestaurants() {

		return find("from Restaurant  where state = ? ORDER BY applicationdate DESC", RestaurantState.PENDING);
	}

	public Rating getRating(int id) {

		return get(Rating.class, id);
	}

	@Override
	public Set<Restaurant> getRecommendedRestaurants(Restaurant r, User u) {

		Set<Rating> userRatings = r.getRatings();
		Set<Restaurant> recommendedRestaurants = new HashSet<Restaurant>();

		for (Rating rate : userRatings) {
			if (!rate.getUser().equals(u)) {
				if (rate.getScore() >= 3) {
					addLikedRestaurants(recommendedRestaurants, rate.getUser(), r);
				}
			}
		}

		return recommendedRestaurants;
	}

	public void addLikedRestaurants(Set<Restaurant> set, User u, Restaurant rest) {

		for(Rating r : u.getComments()) {
			if (r.getScore() >= 3 && !r.getRestaurant().equals(rest)) {
				set.add(r.getRestaurant());
			}
		}
	}
}

