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
import ar.edu.itba.it.paw.domain.foodtype.FoodType;
import ar.edu.itba.it.paw.domain.user.User;

@Repository
public class HibernateRestaurantRepo extends AbstractHibernateRepo implements
		RestaurantRepo {

	@Autowired
	public HibernateRestaurantRepo(SessionFactory sessionFactory) {

		super(sessionFactory);
	}

	@Override
	public List<Restaurant> getAll() {

		return find("from Restaurant where state = ?", RestaurantState.Accepted);
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

	private List<Restaurant> getRestaurantsByName(String name) {

		return find("from Restaurant where lower(name) like ? and state = ?",
				name, RestaurantState.Accepted);

	}

	private List<Restaurant> getRestaurantsByArea(String area) {

		return find("from Restaurant where lower(area) like ? and state = ?",
				area, RestaurantState.Accepted);
	}

	private List<Restaurant> getRestaurantsByFoodType(String query) {

		// return
		// find("from Restaurant as rest join rest.foodtypes as foodtype where rest.foodtypes.name like ? ",
		// query);
		// return
		// find("select Restaurant.* from Restaurant r, FoodTypes ft, restaurant_foodtype rft "
		// +
		// "where rft.restaurants_id = r.id and rft.foodtypes_id = ft.id and ft.name like ?",
		// query);

		List<Restaurant> lstRest = getAll();
		Set<Restaurant> ans = new HashSet<Restaurant>();
		for (Restaurant r : lstRest) {
			for (FoodType ft : r.getFoodtypes()) {
				if (ft.getName()
						.toLowerCase()
						.contains(
								query.substring(1, query.length() - 1)
										.toLowerCase()))
					ans.add(r);
			}
		}
		return new ArrayList<Restaurant>(ans);
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

		return find(
				"from Restaurant where state = ? ORDER BY applicationdate DESC",
				RestaurantState.Pending);
	}

	public Rating getRating(int id) {

		return get(Rating.class, id);
	}

	@Override
	public List<Restaurant> getRecommendedRestaurants(Restaurant restaurant,
			User user) {

		if (restaurant == null) {
			throw new IllegalArgumentException("Empty restaurant");
		}

		List<Rating> userRatings = restaurant.getRatings();
		Set<Restaurant> recommendedRestaurants = new HashSet<Restaurant>();

		for (Rating rating : userRatings) {
			if (user == null || !rating.getUser().equals(user)) {
				if (rating.getScore() >= 3) {
					addLikedRestaurants(recommendedRestaurants,
							rating.getUser(), restaurant);
				}
			}
		}

		List<Restaurant> copy = new ArrayList<Restaurant>(
				recommendedRestaurants);
		Collections.shuffle(copy);
		return copy.subList(0, Math.min(copy.size(), 3));
	}

	public List<Restaurant> getRecommendedRestaurants(Restaurant restaurant) {
		return getRecommendedRestaurants(restaurant, null);
	}

	public void addLikedRestaurants(Set<Restaurant> set, User user,
			Restaurant rest) {

		if (set == null) {
			throw new IllegalArgumentException("Empty set");
		}
		if (user == null) {
			throw new IllegalArgumentException("Empty user");
		}
		if (rest == null) {
			throw new IllegalArgumentException("Empty rest");
		}

		for (Rating rating : user.getComments()) {
			if (rating.getScore() >= 3 && !rating.getRestaurant().equals(rest)) {
				set.add(rating.getRestaurant());
			}
		}
	}

	@Override
	public List<Restaurant> getAdvertisedRestaurants(User user) {

		if (user == null)
			throw new IllegalArgumentException("Empty user");
		List<Restaurant> highlightedRest = find(
				"from Restaurant where highlighted = true AND registeruser_id != ?",
				user.getId());
		Collections.shuffle(highlightedRest);
		return highlightedRest.subList(0, Math.min(highlightedRest.size(), 3));
	}

	@Override
	public List<Restaurant> getAdvertisedRestaurants() {
		List<Restaurant> highlightedRest = find(
				"from Restaurant where highlighted = ?", true);
		Collections.shuffle(highlightedRest);
		return highlightedRest.subList(0, Math.min(highlightedRest.size(), 3));

	}
}
